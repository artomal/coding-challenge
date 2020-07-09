package epi;
import epi.test_framework.EpiTest;
import epi.test_framework.GenericTest;
public class ClosestIntSameWeight {
  @EpiTest(testDataFile = "closest_int_same_weight.tsv")
  public static long closestIntSameBitCount(long x) {
    long y = x;

    int idx = 0;
    while((y & 1) != ((x & 1) == 1 ? 0 : 1)){
      y = y >> 1;
      idx++;
    }

    long mask = 1 << idx - 1 | 1 << idx;
    return x ^ mask;
  }

  public static void main(String[] args) {
    System.exit(
        GenericTest
            .runFromAnnotations(args, "ClosestIntSameWeight.java",
                                new Object() {}.getClass().getEnclosingClass())
            .ordinal());
  }
}
