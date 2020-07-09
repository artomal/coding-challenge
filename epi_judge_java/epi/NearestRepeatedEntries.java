package epi;
import epi.test_framework.EpiTest;
import epi.test_framework.GenericTest;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class NearestRepeatedEntries {
  @EpiTest(testDataFile = "nearest_repeated_entries.tsv")
  public static int findNearestRepetition(List<String> paragraph) {
    int result = -1;
    Map<String, Integer> occ = new HashMap<>();

    for(int i = 0; i < paragraph.size(); i++){
      String word = paragraph.get(i);
      Integer idx = occ.putIfAbsent(word, i);

      if(idx != null){
        result = result < 0 ? i - idx : Math.min(result, i - idx);
        occ.put(word, i);
      }
    }

    return result;
  }

  public static void main(String[] args) {
    System.exit(
        GenericTest
            .runFromAnnotations(args, "NearestRepeatedEntries.java",
                                new Object() {}.getClass().getEnclosingClass())
            .ordinal());
  }
}
