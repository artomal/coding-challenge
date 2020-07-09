package epi;
import epi.test_framework.EpiTest;
import epi.test_framework.GenericTest;

import java.util.HashMap;
import java.util.Map;

public class IsStringPermutableToPalindrome {
  @EpiTest(testDataFile = "is_string_permutable_to_palindrome.tsv")
  public static boolean canFormPalindrome(String s) {
    Map<Character, Integer> count = new HashMap<>();

    for(char c : s.toCharArray()){
      if(count.containsKey(c)) {
        count.remove(c);
      } else {
        count.put(c, 1);
      }
    }

    return count.size() <= 1;
  }

  public static void main(String[] args) {
    System.exit(
        GenericTest
            .runFromAnnotations(args, "IsStringPermutableToPalindrome.java",
                                new Object() {}.getClass().getEnclosingClass())
            .ordinal());
  }
}
