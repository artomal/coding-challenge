package epi;
import epi.test_framework.EpiTest;
import epi.test_framework.GenericTest;
import epi.test_framework.EpiTestComparator;

import java.util.*;
import java.util.function.BiPredicate;
public class ValidIpAddresses {

  public static boolean isValid(String num){
    if(num.length() > 3)
      return false;
    int number = Integer.parseInt(num);
    if(number > 255 || (num.length() > 1 && num.charAt(0) == '0'))
      return false;

    return true;
  }

  @EpiTest(testDataFile = "valid_ip_addresses.tsv")
  public static List<String> getValidIpAddress(String s) {
    List<String> ans = new ArrayList<>();
    if(s.length() < 4)
      return ans;

    for(int x = 1; x < 4; x++){
      final String first = s.substring(0, x);
      if(!isValid(first))
        break;
      for(int y = 1; x + y < s.length(); y++){
        final String second = s.substring(x, x + y);
        if(!isValid(second))
          break;
        for(int z = 1; x + y + z < s.length(); z++){
          final String third = s.substring(x + y, x + y + z);
          if(!isValid(third))
            break;

          final String fourth = s.substring(x + y + z);
          if(!isValid(fourth))
            continue;

          ans.add(first + "." + second + "." + third + "." + fourth);
        }
      }
    }

    return ans;
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
            .runFromAnnotations(args, "ValidIpAddresses.java",
                                new Object() {}.getClass().getEnclosingClass())
            .ordinal());
  }
}
