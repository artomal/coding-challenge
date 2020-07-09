package epi;
import epi.test_framework.EpiTest;
import epi.test_framework.EpiTestComparator;
import epi.test_framework.GenericTest;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.function.BiPredicate;
public class EnumerateBalancedParentheses {
  public static void f(int left, int right, String word, List<String> res){
    if(left == 0 && right == 0){
      res.add(word);
      return;
    }

    if(left > 0){
      f(left-1, right, word + "(", res);
    }

    if(left < right) {
      f(left, right - 1, word + ")", res);
    }
  }

  @EpiTest(testDataFile = "enumerate_balanced_parentheses.tsv")
  public static List<String> generateBalancedParentheses(int numPairs) {
    List<String> result = new ArrayList<>();
    f(numPairs, numPairs, "", result);
    return result;
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
            .runFromAnnotations(args, "EnumerateBalancedParentheses.java",
                                new Object() {}.getClass().getEnclosingClass())
            .ordinal());
  }
}
