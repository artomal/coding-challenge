package epi;
import epi.test_framework.EpiTest;
import epi.test_framework.GenericTest;

import java.util.*;

public class NumberOfScoreCombinations {

  @EpiTest(testDataFile = "number_of_score_combinations.tsv")
  public static int
  numCombinationsForFinalScore(int finalScore,
                               List<Integer> individualPlayScores) {
    Collections.sort(individualPlayScores);

    int[][] M = new int[individualPlayScores.size()][finalScore + 1];

    for(int r = 0; r < M.length; r++){
      for(int c = 0; c < M[r].length; c++){
        if(c == 0) {
          M[r][c] = 1;
          continue;
        }

        if(r == 0){
          M[r][c] = c % individualPlayScores.get(r) > 0 ? 0 : 1;
          continue;
        }

        int res = M[r - 1][c];
        if(c - individualPlayScores.get(r) >= 0){
          res += M[r][c - individualPlayScores.get(r)];
        }

        M[r][c] = res;
      }
    }

    return M[individualPlayScores.size() - 1][finalScore];
  }

  public static void main(String[] args) {
    System.exit(
        GenericTest
            .runFromAnnotations(args, "NumberOfScoreCombinations.java",
                                new Object() {}.getClass().getEnclosingClass())
            .ordinal());
  }
}
