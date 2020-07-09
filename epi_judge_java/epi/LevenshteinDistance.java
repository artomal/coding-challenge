package epi;
import epi.test_framework.EpiTest;
import epi.test_framework.GenericTest;
public class LevenshteinDistance {
  @EpiTest(testDataFile = "levenshtein_distance.tsv")

  public static int levenshteinDistance(String A, String B) {
    int[][] M = new int[A.length() + 1][B.length() + 1];

      for(int r = 0; r < M.length; r++){
        for(int c = 0; c < M[r].length; c++){
          if(r == 0 || c == 0){
            M[r][c] = r == 0 ? c : r;
            continue;
          }

          int topLeft = M[r - 1][c - 1];
          int top = M[r - 1][c];
          int left = M[r][c - 1];

          int sum = topLeft;

          if(A.charAt(r - 1) != B.charAt(c - 1)) {
            sum = 1 + Math.min(Math.min(topLeft, top), left);
          }

          M[r][c] = sum;
        }
    }

//    printMatrix(M);

    return M[A.length()][B.length()];
  }

  public static void printMatrix(int[][] M){
    System.out.println();
    for(int r = 0; r < M.length; r++){
      for(int c = 0; c < M[r].length; c++){
        System.out.print(M[r][c] + " ");
      }
      System.out.println();
    }
  }

  public static void main(String[] args) {
    System.exit(
        GenericTest
            .runFromAnnotations(args, "LevenshteinDistance.java",
                                new Object() {}.getClass().getEnclosingClass())
            .ordinal());
  }
}
