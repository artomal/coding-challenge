package epi;
import epi.test_framework.EpiTest;
import epi.test_framework.GenericTest;
public class SubstringMatch {

  public static long sign(String s){
    long signature = 0;
    for(int i=0; i<s.length(); i++){
      signature = signature * 26 + s.charAt(i);
    }
    return signature;
  }

  @EpiTest(testDataFile = "substring_match.tsv")
  public static int rabinKarp(String t, String s) {
    if(t.length() < s.length())
      return -1;

    if(s.length() == 0 || (s.length() == t.length() && s.equals(t)))
      return 0;

    int sLen = s.length();
    long signature = sign(s);
    long curSignature = sign(t.substring(0, sLen - 1));

    for(int i = 0; i < t.length() - sLen + 1; i++){
      curSignature = curSignature * 26 + (long) t.charAt(i + sLen - 1);
      if(curSignature == signature)
        return i;
      curSignature =  curSignature - (long) Math.pow(26, sLen - 1) * (long) t.charAt(i);
    }

    return -1;
  }

  public static void main(String[] args) {
    System.exit(
        GenericTest
            .runFromAnnotations(args, "SubstringMatch.java",
                                new Object() {}.getClass().getEnclosingClass())
            .ordinal());
  }
}
