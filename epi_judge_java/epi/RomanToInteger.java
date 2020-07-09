package epi;
import epi.test_framework.EpiTest;
import epi.test_framework.GenericTest;

import java.util.HashMap;
import java.util.Map;

public class RomanToInteger {
  @EpiTest(testDataFile = "roman_to_integer.tsv")

  public static int romanToInteger(String s) {
    Map<Character, Integer> look = new HashMap<>();
    look.put('M', 1000);
    look.put('D', 500);
    look.put('C', 100);
    look.put('L',50);
    look.put('X', 10);
    look.put('V', 5);
    look.put('I', 1);

    int res = look.get(s.charAt(0));
    int counter = 1;

    for(int i = 1; i < s.length(); i++) {
      char cur = s.charAt(i);
      char prev = s.charAt(i - 1);

      if(look.get(prev) < look.get(cur)){
        res -= look.get(prev) * counter;
        res += look.get(cur) - (look.get(prev) * counter);
        counter = 1;
      } else {
        res += look.get(cur);
        if(cur == prev) {
          counter++;
        } else {
          counter = 1;
        }
      }
    }

    return res;
  }

  public static void main(String[] args) {
    System.exit(
        GenericTest
            .runFromAnnotations(args, "RomanToInteger.java",
                                new Object() {}.getClass().getEnclosingClass())
            .ordinal());
  }
}
