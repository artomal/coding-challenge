package epi;
import epi.test_framework.EpiTest;
import epi.test_framework.GenericTest;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class LongestNondecreasingSubsequence {
  public static int f(List<Integer> A, Map<Integer, Integer> map, int startIdx, int prev){
    if(startIdx == A.size())
      return 0;

    int max = 0;
    for(int i = startIdx; i < A.size(); i++){
      if(A.get(i) >= prev){
        int count = 0;
        if(!map.containsKey(i)) {
          count = f(A, map, i + 1, A.get(i)) + 1;
          map.put(i, count);
        }

        count = map.get(i);
        max = Math.max(max, count);
      }
    }
    return max;
  }

  @EpiTest(testDataFile = "longest_nondecreasing_subsequence.tsv")
  public static int longestNondecreasingSubsequenceLength(List<Integer> A) {
    // TODO - you fill in here.
    return f(A, new HashMap<Integer, Integer>(), 0, 0);
  }

  public static void main(String[] args) {
    System.exit(
        GenericTest
            .runFromAnnotations(args, "LongestNondecreasingSubsequence.java",
                                new Object() {}.getClass().getEnclosingClass())
            .ordinal());
  }
}
