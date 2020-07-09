package epi;
import epi.test_framework.EpiTest;
import epi.test_framework.GenericTest;

import java.util.Arrays;

public class NumberOfTraversalsStaircase {

  public static int solve(int n, int k, int[] cache){
    if(n <= 1)
      return 1;

    if(cache[n] == 0) {
      for (int i = 1; i <= k; i++) {
        if (n - i < 0)
          break;
        cache[n] += solve(n - i, k, cache);
      }
    }
    return cache[n];
  }

  @EpiTest(testDataFile = "number_of_traversals_staircase.tsv")
  public static int numberOfWaysToTop(int top, int maximumStep) {
    return solve(top, maximumStep, new int[top + 1]);
  }

  public static void main(String[] args) {
    System.exit(
        GenericTest
            .runFromAnnotations(args, "NumberOfTraversalsStaircase.java",
                                new Object() {}.getClass().getEnclosingClass())
            .ordinal());
  }
}
