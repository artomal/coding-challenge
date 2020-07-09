package epi;
import epi.test_framework.EpiTest;
import epi.test_framework.GenericTest;
public class LookAndSay {
  @EpiTest(testDataFile = "look_and_say.tsv")

  public static String lookAndSay(int n) {
    if(n == 1)
      return "1";

    String num = lookAndSay(n - 1);
    StringBuilder sb = new StringBuilder();
    int counter = 1;
    for(int i = 1; i < num.length(); i++){
      if(num.charAt(i) == num.charAt(i - 1)){
        counter++;
      } else {
        sb.append(counter + "" + num.charAt(i - 1));
        counter = 1;
      }
    }

    sb.append(counter + "" + num.charAt(num.length() - 1));

    return sb.toString();
  }

  public static void main(String[] args) {
    System.exit(
        GenericTest
            .runFromAnnotations(args, "LookAndSay.java",
                                new Object() {}.getClass().getEnclosingClass())
            .ordinal());
  }
}
