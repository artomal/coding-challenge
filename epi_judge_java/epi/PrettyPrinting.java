package epi;
import epi.test_framework.EpiTest;
import epi.test_framework.GenericTest;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
public class PrettyPrinting {

  public static int wordsSum(List<String> w, int from, int to){
    int sum = 0;
    for(int i = from; i < to; i++){
      sum += w.get(i).length();
    }
    return sum + (to - from - 1);
  }

  public static int minimumMessinessHelper(List<String> w, int len, int from, int to, int[][] cache) {
    if (from == to)
      return 0;

    if (cache[from][to] == Integer.MAX_VALUE){
      for (int i = to; i > from; i--) {
        int w_sum = wordsSum(w, from, i);
        if (w_sum > len)
          continue;

        int curMessCount = (int) Math.pow(len - w_sum, 2);
        cache[from][to] = Math.min(cache[from][to], curMessCount + minimumMessinessHelper(w, len, i, to, cache));
      }
    }
    return cache[from][to];
  }

  @EpiTest(testDataFile = "pretty_printing.tsv")
  public static int minimumMessiness(List<String> words, int lineLength) {
    int[][] cache = new int[words.size() + 1][words.size() + 1];
    for(int[] arr : cache){
      Arrays.fill(arr, Integer.MAX_VALUE);
    }
    return minimumMessinessHelper(words, lineLength, 0, words.size(), cache);
  }

  public static void main(String[] args) {
    System.exit(
        GenericTest
            .runFromAnnotations(args, "PrettyPrinting.java",
                                new Object() {}.getClass().getEnclosingClass())
            .ordinal());
  }
}
