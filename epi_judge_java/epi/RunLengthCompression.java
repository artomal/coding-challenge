package epi;
import epi.test_framework.EpiTest;
import epi.test_framework.GenericTest;
import epi.test_framework.TestFailure;
public class RunLengthCompression {

  public static String decoding(String s) {
    int sIdx = 0;
    while(sIdx < s.length()){
      StringBuilder sb = new StringBuilder();
      int idx = sIdx;

      while(s.charAt(idx) >= '0' && s.charAt(idx) <= '9'){
        idx++;
      }

      int num = Integer.parseInt(s.substring(sIdx, idx));
      char sym = s.charAt(idx);

      int nextStartIdx = sIdx + num;
      while(num-- > 0){
        sb.append(sym);
      }

      s = s.substring(0, sIdx) + sb.toString() + s.substring(idx + 1);
      sIdx = nextStartIdx;
    }
    return s;
  }
  public static String encoding(String s) {
    int sIdx = 0;
    while(sIdx < s.length()){
      StringBuilder sb = new StringBuilder();
      int idx = sIdx;
      int count = 1;

      while(idx + 1 < s.length() && s.charAt(idx) == s.charAt(idx + 1)){
        count++;
        idx++;
      }

      sb.append(count);
      sb.append(s.charAt(idx));

      int newStartIdx = idx - count + sb.length() + 1;
      s = s.substring(0, sIdx) + sb.toString() + s.substring(idx + 1);
      sIdx = newStartIdx;
    }
    return s;
  }
  @EpiTest(testDataFile = "run_length_compression.tsv")
  public static void rleTester(String encoded, String decoded)
      throws TestFailure {
    if (!decoding(encoded).equals(decoded)) {
      throw new TestFailure("Decoding failed");
    }
    if (!encoding(decoded).equals(encoded)) {
      throw new TestFailure("Encoding failed");
    }
  }

  public static void main(String[] args) {
    System.exit(
        GenericTest
            .runFromAnnotations(args, "RunLengthCompression.java",
                                new Object() {}.getClass().getEnclosingClass())
            .ordinal());
  }
}
