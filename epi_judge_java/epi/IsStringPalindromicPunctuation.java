package epi;
import epi.test_framework.EpiTest;
import epi.test_framework.GenericTest;
public class IsStringPalindromicPunctuation {

  @EpiTest(testDataFile = "is_string_palindromic_punctuation.tsv")
  public static boolean isPalindrome(String s) {
    if(s == null || s.length() == 0)
      return true;

    int l = 0; int r = s.length() - 1;

    while(l < r){
      char a = Character.toLowerCase(s.charAt(l));
      char b = Character.toLowerCase(s.charAt(r));

      if(!Character.isLetterOrDigit(a)){
        l++;
        continue;
      }

      if(!Character.isLetterOrDigit(b)){
        r--;
        continue;
      }

      if(a != b)
        return false;

      l++; r--;
    }
    return true;
  }

  public static void main(String[] args) {
    System.exit(
        GenericTest
            .runFromAnnotations(args, "IsStringPalindromicPunctuation.java",
                                new Object() {}.getClass().getEnclosingClass())
            .ordinal());
  }
}
