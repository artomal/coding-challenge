package epi;
import epi.test_framework.EpiTest;
import epi.test_framework.GenericTest;
public class ConvertBase {

  @EpiTest(testDataFile = "convert_base.tsv")
  public static String convertBase(String numAsString, int b1, int b2) {
    if(numAsString == null || numAsString.length() == 0)
      return numAsString;

    boolean negative = numAsString.charAt(0) == '-' ? true : false;
    int sum = 0; int pow = 0;

    for(int i = numAsString.length() - 1; i >= (negative ? 1 : 0); i--){
      char num = numAsString.charAt(i);
      int number = num > 64 ? num - 55 : num - 48;
      sum += number * Math.pow(b1, pow++);
    }

    StringBuilder sb = new StringBuilder();
    do {
      int num = sum % b2;
      sum /= b2;
      sb.append(num > 9 ? (char)(55 + num) : (char)(48 + num));
    } while(sum > 0);

    return negative ? sb.append('-').reverse().toString() : sb.reverse().toString();
  }

  public static void main(String[] args) {
    System.exit(
        GenericTest
            .runFromAnnotations(args, "ConvertBase.java",
                                new Object() {}.getClass().getEnclosingClass())
            .ordinal());
  }
}
