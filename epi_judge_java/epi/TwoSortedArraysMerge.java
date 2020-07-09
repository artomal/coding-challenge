package epi;
import epi.test_framework.EpiTest;
import epi.test_framework.GenericTest;
import java.util.List;
public class TwoSortedArraysMerge {

  public static void mergeTwoSortedArrays(List<Integer> A, int m,
                                          List<Integer> B, int n) {
    int aIdx = m - 1; int bIdx = n - 1; int wIdx = m + n - 1;

    while(wIdx >= 0){
      int a = aIdx >= 0 ? A.get(aIdx) : Integer.MIN_VALUE;
      int b = bIdx >= 0 ? B.get(bIdx) : Integer.MIN_VALUE;

      if(a > b || a == b){
        A.set(wIdx--, a);
        aIdx--;
      } else {
        A.set(wIdx--, b);
        bIdx--;
      }
    }

    return;
  }
  @EpiTest(testDataFile = "two_sorted_arrays_merge.tsv")
  public static List<Integer>
  mergeTwoSortedArraysWrapper(List<Integer> A, int m, List<Integer> B, int n) {
    mergeTwoSortedArrays(A, m, B, n);
    return A;
  }

  public static void main(String[] args) {
    System.exit(
        GenericTest
            .runFromAnnotations(args, "TwoSortedArraysMerge.java",
                                new Object() {}.getClass().getEnclosingClass())
            .ordinal());
  }
}
