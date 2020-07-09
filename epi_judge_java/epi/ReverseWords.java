package epi;
import epi.test_framework.EpiTest;
import epi.test_framework.GenericTest;
import epi.test_framework.TimedExecutor;

import java.util.Arrays;
import java.util.Collections;

public class ReverseWords {

  public static void reverse(char[] input, int start, int end){
    while(start < end){
      char tmp = input[start];
      input[start++] = input[end];
      input[end--] = tmp;
    }
  }

  public static int findEnd(char[] input, int start){
    while(start + 1 < input.length && Character.isLetterOrDigit(input[start + 1])){
      start++;
    }
    return start;
  }

  public static void reverseWords(char[] input) {
    for(int i = 0; i < input.length; i++){
      char currentChar = input[i];

      if(Character.isLetterOrDigit(currentChar)){
        int endIdx = findEnd(input, i);
        reverse(input, i, endIdx);
        i = endIdx;
      }
    }

    reverse(input, 0, input.length - 1);
  }
  @EpiTest(testDataFile = "reverse_words.tsv")
  public static String reverseWordsWrapper(TimedExecutor executor, String s)
      throws Exception {
    char[] sCopy = s.toCharArray();

    executor.run(() -> reverseWords(sCopy));

    return String.valueOf(sCopy);
  }

  public static void main(String[] args) {
    System.exit(
        GenericTest
            .runFromAnnotations(args, "ReverseWords.java",
                                new Object() {}.getClass().getEnclosingClass())
            .ordinal());
  }
}
