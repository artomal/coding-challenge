package epi;
import epi.test_framework.EpiTest;
import epi.test_framework.GenericTest;
public class IsNumberPalindromic {
  @EpiTest(testDataFile = "is_number_palindromic.tsv")
  public static boolean isPalindromeNumber(int x) {
    if(x < 0)
      return false;

    int dig = ((int) Math.log10(x));

    while(dig >= 0){
      int divider = (int) (Math.pow(10, dig));
      if(x / divider != x % 10)
        return false;

      x %= divider;
      x /= 10;

      dig = dig - 2;
    }
    return true;
  }

  public static void main(String[] args) {
    System.exit(
        GenericTest
            .runFromAnnotations(args, "IsNumberPalindromic.java",
                                new Object() {}.getClass().getEnclosingClass())
            .ordinal());
  }
}
