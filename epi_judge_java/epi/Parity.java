package epi;
import epi.test_framework.EpiTest;
import epi.test_framework.GenericTest;

/*
 Parity of a word

 1011       - 1
 11001111   - 0

 */
public class Parity {
  @EpiTest(testDataFile = "parity.tsv")
  public static short parity(long x) {
    short parity = 0;
    while(x != 0){
      if((x & 1) == 1)
        parity ^= 1;
      x = x >> 1;
    }

    return parity;
  }

  public static void main(String[] args) {
    System.exit(
        GenericTest
            .runFromAnnotations(args, "Parity.java",
                                new Object() {}.getClass().getEnclosingClass())
            .ordinal());
  }
}
