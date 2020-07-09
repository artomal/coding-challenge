package epi;
import epi.test_framework.EpiTest;
import epi.test_framework.EpiTestComparator;
import epi.test_framework.GenericTest;

import java.util.*;
import java.util.function.BiPredicate;
public class PhoneNumberMnemonic {

  public static Map<Character, List<Character>> LOOK = new HashMap<>();

  public static void f(String digits, int s, StringBuilder sb, List<String> res){
    if(s == digits.length()){
      res.add(sb.toString());
      return;
    }

    List<Character> digit = LOOK.get(digits.charAt(s));
    if(digit == null)
      return;

    for(int i = 0; i < digit.size(); i++){
      sb.append(digit.get(i));
      f(digits, s + 1, sb, res);
      sb.deleteCharAt(sb.length() - 1);
    }
  }

  @EpiTest(testDataFile = "phone_number_mnemonic.tsv")
  public static List<String> phoneMnemonic(String digits) {
    LOOK.put('0', Arrays.asList('0'));
    LOOK.put('1', Arrays.asList('1'));
    LOOK.put('2', Arrays.asList('A', 'B', 'C'));
    LOOK.put('3', Arrays.asList('D', 'E', 'F'));
    LOOK.put('4', Arrays.asList('G', 'H', 'I'));
    LOOK.put('5', Arrays.asList('J', 'K', 'L'));
    LOOK.put('6', Arrays.asList('M', 'N', 'O'));
    LOOK.put('7', Arrays.asList('P', 'Q', 'R', 'S'));
    LOOK.put('8', Arrays.asList('T', 'U', 'V'));
    LOOK.put('9', Arrays.asList('W', 'X', 'Y', 'Z'));

    List<String> res = new ArrayList<>();
    List<Character> digit = LOOK.get(digits.charAt(0));
    if(digit == null || digits.length() == 0)
      return res;

    for(int i = 0; i < digit.size(); i++){
      StringBuilder sb = new StringBuilder();
      sb.append(digit.get(i));
      f(digits, 1, sb, res);
      sb.deleteCharAt(sb.length() - 1);
    }

    return res;
  }
  @EpiTestComparator
  public static BiPredicate<List<String>, List<String>> comp =
      (expected, result) -> {
    if (result == null) {
      return false;
    }
    Collections.sort(expected);
    Collections.sort(result);
    return expected.equals(result);
  };

  public static void main(String[] args) {
    System.exit(
        GenericTest
            .runFromAnnotations(args, "PhoneNumberMnemonic.java",
                                new Object() {}.getClass().getEnclosingClass())
            .ordinal());
  }
}
