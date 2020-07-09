package epi;
import epi.test_framework.EpiTest;
import epi.test_framework.GenericTest;
public class IntSquareRoot {

  @EpiTest(testDataFile = "int_square_root.tsv")
  public static int squareRoot(int k) {
    if (k < 0)
      return -1;

    long L = 0; long R = k;
    long res = -1;

    while(L <= R){
      long M = L + ((R - L) / 2);
      long pow = M * M;

      if(pow > k){
        R = M - 1;
      } else if(pow == k){
        return (int) M;
      } else {
        res = M;
        L = M + 1;
      }
    }

    return (int )res;
  }

  public static void main(String[] args) {
    System.exit(
        GenericTest
            .runFromAnnotations(args, "IntSquareRoot.java",
                                new Object() {}.getClass().getEnclosingClass())
            .ordinal());
  }
}
