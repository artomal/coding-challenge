package epi;
import epi.test_framework.EpiTest;
import epi.test_framework.GenericTest;

import java.util.ArrayDeque;
import java.util.Deque;

public class EvaluateRpn {

  public static int calculate(int a, int b, char sign){
    int result = 0;

    switch(sign){
      case '+':
        result = a + b;
        break;
      case '-':
        result = a - b;
        break;
      case '*':
        result = a * b;
        break;
      case '/':
        result = a / b;
        break;
      default:
        break;
    }
    return result;
  }

  public static boolean checkForSign(char sign){
    boolean result = false;
    switch(sign){
      case '+':
        result = true;
        break;
      case '-':
        result = true;
        break;
      case '*':
        result = true;
        break;
      case '/':
        result = true;
        break;
      default:
        break;
    }
    return result;
  }

  @EpiTest(testDataFile = "evaluate_rpn.tsv")
  public static int eval(String expression) {
    if(expression == null || expression.length() ==0)
      return 0;

    Deque<String> stack = new ArrayDeque<>();
    String[] seq = expression.split(",");
    for(String word : seq) {
      if (!checkForSign(word.charAt(0))){
        stack.push(word);
        continue;
      }

      int b = Integer.parseInt(stack.pop());
      int a = Integer.parseInt(stack.pop());
      a = calculate(a, b, word.charAt(0));
      stack.push(String.valueOf(a));
    }
    return stack.isEmpty() ? 0 : Integer.parseInt(stack.pop());
  }

  public static void main(String[] args) {
    System.exit(
        GenericTest
            .runFromAnnotations(args, "EvaluateRpn.java",
                                new Object() {}.getClass().getEnclosingClass())
            .ordinal());
  }
}
