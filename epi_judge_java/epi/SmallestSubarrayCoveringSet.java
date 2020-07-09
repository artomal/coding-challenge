package epi;
import epi.test_framework.EpiTest;
import epi.test_framework.GenericTest;
import epi.test_framework.TestFailure;
import epi.test_framework.TimedExecutor;

import java.util.*;

public class SmallestSubarrayCoveringSet {

  // Represent subarray by starting and ending indices, inclusive.
  private static class Subarray {
    public Integer start;
    public Integer end;

    public Subarray(Integer start, Integer end) {
      this.start = start;
      this.end = end;
    }
  }

  public static class Pos implements Comparable<Pos> {
    String word;
    int idx;

    Pos(String word, int idx) {
      this.word = word;
      this.idx = idx;
    }

    @Override
    public int compareTo(Pos o) {
      System.out.println("[O WORD] " + o.word + " [THIS WORD] " + this.word + " [EQUALS] " + this.word.compareTo(o.word));
      if(this.word.compareTo(o.word) == 0){
        return 0;
      }

      return Integer.compare(this.idx, ((Pos) o).idx);
    }
  }

  public static Subarray findSmallestSubarrayCoveringSet(List<String> L,
                                                         Set<String> keywords) {
    Map<String, Integer> hist = new HashMap<>();

    for(String w : keywords){
      hist.put(w, hist.containsKey(w) ? hist.get(w) + 1 : 1);
    }

    Subarray min = new Subarray(-1, -1);
    int remaining = keywords.size();

    for(int start = 0, end = 0; end < L.size(); ++end){
      Integer wRemain = hist.get(L.get(end));
      if(wRemain != null){
        hist.put(L.get(end), --wRemain);
        if(wRemain >= 0)
          remaining--;
      }

      while(remaining == 0){
        if((min.start == -1 && min.end == -1)
                || (end - start < min.end - min.start)){
          min.start = start;
          min.end = end;
        }

        wRemain = hist.get(L.get(start));
        if(wRemain != null){
          hist.put(L.get(start), ++wRemain);
          if(wRemain > 0){
            remaining++;
          }
        }
        start++;
      }
    }

    return min;
  }

  @EpiTest(testDataFile = "smallest_subarray_covering_set.tsv")
  public static int findSmallestSubarrayCoveringSetWrapper(
      TimedExecutor executor, List<String> paragraph, Set<String> keywords)
      throws Exception {
    Set<String> copy = new HashSet<>(keywords);

    Subarray result = executor.run(
        () -> findSmallestSubarrayCoveringSet(paragraph, keywords));

    if (result.start < 0 || result.start >= paragraph.size() ||
        result.end < 0 || result.end >= paragraph.size() ||
        result.start > result.end)
      throw new TestFailure("Index out of range");

    for (int i = result.start; i <= result.end; i++) {
      copy.remove(paragraph.get(i));
    }

    if (!copy.isEmpty()) {
      throw new TestFailure("Not all keywords are in the range");
    }
    return result.end - result.start + 1;
  }

  public static void main(String[] args) {
    System.exit(
        GenericTest
            .runFromAnnotations(args, "SmallestSubarrayCoveringSet.java",
                                new Object() {}.getClass().getEnclosingClass())
            .ordinal());
  }
}
