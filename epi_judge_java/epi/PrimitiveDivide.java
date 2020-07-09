package epi;
import epi.test_framework.EpiTest;
import epi.test_framework.GenericTest;
public class PrimitiveDivide {

//  @EpiTest(testDataFile = "primitive_divide.tsv")
//  public static int divide(int x, int y) {
//    int i = 0;
//    while(x >= y){
//      x -= y;
//      i++;
//    }
//    return i;
//  }


  @EpiTest(testDataFile = "primitive_divide.tsv")
  public static int divide(int x, int y) {
    if(x < y)
      return 0;

    long tY = y;
    long i = 1L;

    while((long)x - tY >= (long) y){
      i <<= 1L;
      tY <<= 1L;
    }

    if((long)x - tY < 0L) {
      i >>= 1L;
      tY >>= 1L;
    }

    while((long)x - tY >= (long)y){
      i += 1;
      tY += y;
    }
    return (int)i;
  }

  public static void main(String[] args) {
    System.exit(
        GenericTest
            .runFromAnnotations(args, "PrimitiveDivide.java",
                                new Object() {}.getClass().getEnclosingClass())
            .ordinal());
  }
}
