package epi;
import epi.test_framework.EpiTest;
import epi.test_framework.GenericTest;
public class PrimitiveMultiply {

  public static long add(long x, long y){
    long i = 0;
    long rem = 0;

    while((x >> i) != 0 || (y >> i) != 0){
      long xbit = x >> i & 1;
      long ybit = y >> i & 1;
      long res = xbit;
      res ^= ybit;
      res ^= rem;

      rem = ((xbit ^ ybit) & rem) | (xbit & ybit);

      if(xbit != res)
        x ^= 1 << i;
      i++;
    }

    if(rem == 1)
      x ^= 1L << i;

    return x;
  }

  public static long minus(long x){
    long rem = 1;
    long i = 0;

    while(rem > 0){
      rem = (x >> i & 1) == 1 ? 0 : 1;
      x ^= 1 << i;
      i++;
    }
    return x;
  }

  @EpiTest(testDataFile = "primitive_multiply.tsv")
  public static long multiply(long x, long y) {
    long res = 0;
    while(y != 0){
      res = add(res, x);
      y = minus(y);
    }

    return res;
  }

  public static void main(String[] args) {
    System.exit(
        GenericTest
            .runFromAnnotations(args, "PrimitiveMultiply.java",
                                new Object() {}.getClass().getEnclosingClass())
            .ordinal());
  }
}
