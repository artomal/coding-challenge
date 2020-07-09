package epi;
import epi.test_framework.EpiTest;
import epi.test_framework.GenericTest;

import java.util.*;

public class LongestContainedInterval {
  @EpiTest(testDataFile = "longest_contained_interval.tsv")

  public static int longestContainedRange(List<Integer> A) {
    Collections.sort(A);
    Set<Integer> hist = new HashSet<>();
    int max = 0;
    hist.add(A.get(0));

    for(int i = 1; i < A.size(); i++){
      if(A.get(i - 1).compareTo(A.get(i)) == 0)
        continue;

      if(A.get(i).compareTo(A.get(i - 1) + 1) != 0){
        max = Math.max(max, hist.size());
        hist = new HashSet<>();
      }
      hist.add(A.get(i));
    }
    max = Math.max(max, hist.size());

    return max;
  }

  public static void main(String[] args) {
    System.exit(
        GenericTest
            .runFromAnnotations(args, "LongestContainedInterval.java",
                                new Object() {}.getClass().getEnclosingClass())
            .ordinal());
  }
}
