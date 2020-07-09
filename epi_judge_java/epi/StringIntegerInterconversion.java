package epi;
import epi.test_framework.EpiTest;
import epi.test_framework.GenericTest;
import epi.test_framework.TestFailure;

import java.util.ArrayList;
import java.util.List;

/*

 */

public class StringIntegerInterconversion {

  public static String intToString(int x) {
    StringBuilder sb = new StringBuilder();

    if(x < 0) {
      sb.append('-');
    }

    if(x == 0)
      return "0";

    List<Character> result = new ArrayList<>();
    while(x != 0){
      int number = x % 10;
      x /= 10;
      number = number < 0 ? number * -1: number;
      char n = (char) (48 + number);
      result.add(n);
    }

    for(int i = result.size() - 1; i >= 0; i--){
      sb.append(result.get(i));
    }

    return sb.toString();
  }
  public static int stringToInt(String s) {
    if(s == null || s.length() == 0)
      return 0;

    int startIdx = 0;
    boolean negative = false;
    if(s.charAt(startIdx) == '-'){
      startIdx++;
      negative = true;
    }

    int result = 0;
    for(int i = startIdx; i < s.length(); i++){
      result = result * 10 + (s.charAt(i) - 48);
    }

    return negative ? result * -1 : result;
  }
  @EpiTest(testDataFile = "string_integer_interconversion.tsv")
  public static void wrapper(int x, String s) throws TestFailure {
    if (!intToString(x).equals(s)) {
      throw new TestFailure("Int to string conversion failed");
    }
    if (stringToInt(s) != x) {
      throw new TestFailure("String to int conversion failed");
    }
  }

  public static void main(String[] args) {
    System.exit(
        GenericTest
            .runFromAnnotations(args, "StringIntegerInterconversion.java",
                                new Object() {}.getClass().getEnclosingClass())
            .ordinal());
  }
}
