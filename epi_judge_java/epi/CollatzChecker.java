package epi;
import epi.test_framework.EpiTest;
import epi.test_framework.GenericTest;

import java.util.HashSet;
import java.util.Set;

public class CollatzChecker {
  @EpiTest(testDataFile = "collatz_checker.tsv")
  public static boolean testCollatzConjecture(int n) {
    Set<Integer> verified = new HashSet<>();

    for(int i = 3; i <= n; i += 2){
      Set<Integer> curSeq = new HashSet<>();
      int cur = i;

      while(cur >= i){
        if(!curSeq.add(cur))
          return false;

        if(verified.contains(cur))
          return true;

        if(cur % 2 != 0){
          int nextCur = cur * 3 + 1;
          if(nextCur <= cur)
            return false;

          cur = nextCur;
        } else {
          cur /= 2;
        }
        if(!verified.add(cur))
          break;
      }
    }

    return true;
  }

  public static void main(String[] args) {
    System.exit(
        GenericTest
            .runFromAnnotations(args, "CollatzChecker.java",
                                new Object() {}.getClass().getEnclosingClass())
            .ordinal());
  }
}
