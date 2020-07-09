package epi;
import epi.test_framework.EpiTest;
import epi.test_framework.GenericTest;
public class SnakeString {
  @EpiTest(testDataFile = "snake_string.tsv")

  public static String snakeString(String s) {
    StringBuilder res = new StringBuilder();

    for(int i = 1; i < s.length(); i += 4){
      res.append(s.charAt(i));
    }

    for(int i = 0; i < s.length(); i += 2){
      res.append(s.charAt(i));
    }

    for(int i = 3; i < s.length(); i += 4){
      res.append(s.charAt(i));
    }
    return res.toString();
  }

  public static void main(String[] args) {
    System.exit(
        GenericTest
            .runFromAnnotations(args, "SnakeString.java",
                                new Object() {}.getClass().getEnclosingClass())
            .ordinal());
  }
}
