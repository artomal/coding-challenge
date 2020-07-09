package epi;
import epi.test_framework.EpiTest;
import epi.test_framework.GenericTest;
public class SwapBits {
  @EpiTest(testDataFile = "swap_bits.tsv")
  public static long swapBits(long x, int i, int j) {
    long bitI = x & ((long)1 << i);
    long bitJ = x & ((long)1 << j);

    if(bitI == bitJ || (bitI > 0 && bitJ > 0))
      return x;

    long mask = 1L << i | 1L << j;

    return x ^ mask;
  }

  public static void main(String[] args) {
    System.exit(
        GenericTest
            .runFromAnnotations(args, "SwapBits.java",
                                new Object() {}.getClass().getEnclosingClass())
            .ordinal());
  }
}
