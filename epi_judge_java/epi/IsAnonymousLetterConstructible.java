package epi;
import epi.test_framework.EpiTest;
import epi.test_framework.GenericTest;

import java.util.HashMap;
import java.util.Map;

public class IsAnonymousLetterConstructible {
  @EpiTest(testDataFile = "is_anonymous_letter_constructible.tsv")

  public static boolean isLetterConstructibleFromMagazine(String letterText,
                                                          String magazineText) {
    Map<Character, Integer> count = new HashMap<>();
    for(char c : magazineText.toCharArray()){
      if(c == ' ') continue;

      if(!count.containsKey(c)){
        count.put(c, 1);
        continue;
      }
      int cnt = count.get(c);
      count.put(c, ++cnt);
    }

    for(char c : letterText.toCharArray()){
      if(c == ' ') continue;

      if(!count.containsKey(c))
        return false;

      int cnt = count.get(c);
      count.put(c, --cnt);

      if(cnt == 0)
        count.remove(c);
    }

    return true;
  }

  public static void main(String[] args) {
    System.exit(
        GenericTest
            .runFromAnnotations(args, "IsAnonymousLetterConstructible.java",
                                new Object() {}.getClass().getEnclosingClass())
            .ordinal());
  }
}
