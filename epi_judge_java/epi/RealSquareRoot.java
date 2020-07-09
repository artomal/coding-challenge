package epi;
import epi.test_framework.EpiTest;
import epi.test_framework.GenericTest;
public class RealSquareRoot {
  @EpiTest(testDataFile = "real_square_root.tsv")

  public static double squareRoot(double x) {
    double left, right;
    if(x < 1.0){
      left = x;
      right = 1.0;
    } else {
      left = 1.0;
      right = x;
    }

    while(compare(left, right) == Ordering.SMALLER){
      double mid = (right + left) / 2;
      double midSq = mid * mid;
      if(compare(midSq, x) == Ordering.EQUAL) {
        return mid;
      } else if (compare(midSq, x) == Ordering.LARGER){
        right = mid;
      } else {
        left = mid;
      }
    }
    return left;
  }

  private enum Ordering { SMALLER, EQUAL, LARGER}

  public static Ordering compare(double a, double b){
    final double EPSILON = 0.000001;

    double diff = (a - b) / b;
    return diff < -EPSILON
            ? Ordering.SMALLER
            : (diff > EPSILON ? Ordering.LARGER : Ordering.EQUAL);
  }

  public static void main(String[] args) {
    System.exit(
        GenericTest
            .runFromAnnotations(args, "RealSquareRoot.java",
                                new Object() {}.getClass().getEnclosingClass())
            .ordinal());
  }
}
