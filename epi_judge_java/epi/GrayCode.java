package epi;
import epi.test_framework.EpiTest;
import epi.test_framework.GenericTest;
import epi.test_framework.TestFailure;
import epi.test_framework.TimedExecutor;

import java.util.*;

public class GrayCode {

  public static boolean solve(int bits, HashSet<Integer> hist, List<Integer> res) {
    if(res.size() == (1 << bits))
      return true;

    int last = res.get(res.size() - 1);
    for(int i = 0; i < bits; i++){
      int cand = last ^ (1 << i);
      if(!hist.contains(cand)
              && diff(cand, last)){
        res.add(cand);
        hist.add(cand);
        if(solve(bits, hist, res))
          return true;
        hist.remove(cand);
        res.remove(res.size() - 1);
      }
    }

    return false;
  }

  public static boolean diff(int cand, int last){
    if(Math.abs(Integer.bitCount(cand) - Integer.bitCount(last)) == 1){
      return true;
    }
    return false;
  }

  public static List<Integer> grayCode(int numBits) {
//    System.out.println(Integer.toBinaryString(Integer.parseInt("011", 2) ^ (1 << 2)));
    List<Integer> res = new ArrayList<>(Arrays.asList(0));
    solve(numBits, new HashSet<>(Arrays.asList(0)), res);
    return res;
  }
  private static boolean differsByOneBit(int x, int y) {
    int bitDifference = x ^ y;
    return bitDifference != 0 && (bitDifference & (bitDifference - 1)) == 0;
  }

  @EpiTest(testDataFile = "gray_code.tsv")
  public static void grayCodeWrapper(TimedExecutor executor, int numBits)
      throws Exception {
    List<Integer> result = executor.run(() -> grayCode(numBits));

    int expectedSize = (1 << numBits);
    if (result.size() != expectedSize) {
      throw new TestFailure("Length mismatch: expected " +
                            String.valueOf(expectedSize) + ", got " +
                            String.valueOf(result.size()));
    }
    for (int i = 1; i < result.size(); i++)
      if (!differsByOneBit(result.get(i - 1), result.get(i))) {
        if (result.get(i - 1).equals(result.get(i))) {
          throw new TestFailure("Two adjacent entries are equal");
        } else {
          throw new TestFailure(
              "Two adjacent entries differ by more than 1 bit");
        }
      }

    Set<Integer> uniq = new HashSet<>(result);
    if (uniq.size() != result.size()) {
      throw new TestFailure("Not all entries are distinct: found " +
                            String.valueOf(result.size() - uniq.size()) +
                            " duplicates");
    }
  }

  public static void main(String[] args) {
    System.exit(
        GenericTest
            .runFromAnnotations(args, "GrayCode.java",
                                new Object() {}.getClass().getEnclosingClass())
            .ordinal());
  }
}
