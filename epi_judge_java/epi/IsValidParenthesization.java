package epi;
import epi.test_framework.EpiTest;
import epi.test_framework.GenericTest;

import java.util.HashMap;
import java.util.Map;
import java.util.Stack;

public class IsValidParenthesization {
  @EpiTest(testDataFile = "is_valid_parenthesization.tsv")

  public static boolean isWellFormed(String s) {
    Stack<Character> st = new Stack<>();
    Map<Character, Character> ch = new HashMap<>();
    ch.put('(', ')');
    ch.put('[', ']');
    ch.put('{', '}');

    for(int i = 0; i < s.length(); i++){
      char cur = s.charAt(i);

      if(ch.containsKey(cur)){
        st.push(cur);
      } else {
        if(st.isEmpty() || ch.get(st.pop()) != cur)
          return false;
      }
    }

    return st.isEmpty();
  }

  public static void main(String[] args) {
    System.exit(
        GenericTest
            .runFromAnnotations(args, "IsValidParenthesization.java",
                                new Object() {}.getClass().getEnclosingClass())
            .ordinal());
  }
}
