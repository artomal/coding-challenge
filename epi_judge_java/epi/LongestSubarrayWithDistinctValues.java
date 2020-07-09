package epi;
import epi.test_framework.EpiTest;
import epi.test_framework.GenericTest;

import java.util.*;

public class LongestSubarrayWithDistinctValues {
  @EpiTest(testDataFile = "longest_subarray_with_distinct_values.tsv")

  public static int longestSubarrayWithDistinctEntries(List<Integer> A) {
    int ans = 0;
    Map<Integer, Integer> hist = new HashMap<>();

    int sIdx = 0;
    for(int i=0;i<A.size();i++){
      Integer num = hist.put(A.get(i), i);

      if(num != null){
        if(num >= sIdx) {
          ans = Math.max(ans, i - sIdx);
          sIdx = num + 1;
        }
      }
    }

    return Math.max(ans, A.size() - sIdx);
  }

  public static void main(String[] args) {
    System.exit(
        GenericTest
            .runFromAnnotations(args, "LongestSubarrayWithDistinctValues.java",
                                new Object() {}.getClass().getEnclosingClass())
            .ordinal());
  }
}
