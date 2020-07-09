package epi;
import com.sun.source.tree.Tree;
import epi.test_framework.EpiTest;
import epi.test_framework.GenericTest;
import epi.test_framework.TestFailure;
import epi.test_framework.TimedExecutor;

import java.util.*;

public class SmallestSubarrayCoveringAllValues {

  public static class Subarray {
    // Represent subarray by starting and ending indices, inclusive.
    public Integer start;
    public Integer end;

    public Subarray(Integer start, Integer end) {
      this.start = start;
      this.end = end;
    }
  }

  static class S {
    int start;
    int end;
    int idx;

    S(int start, int end, int idx){
      this.start = start;
      this.end = end;
      this.idx = idx;
    }
  }


  public static Subarray
  findSmallestSequentiallyCoveringSubset(List<String> paragraph,
                                         List<String> keywords) {
    Map<String, S> counters = new HashMap<>();
    Subarray ans = new Subarray(-1, -1);

    for(int i = 0; i < paragraph.size(); i++){
      String w = paragraph.get(i);

      if(keywords.contains(w)){
        if(keywords.get(0).equals(w)){
          counters.put(keywords.get(1), new S(i, -1, 1));
        } else if(counters.containsKey(w)) {
          S s = counters.remove(w);

          if(s.idx == keywords.size() - 1) {
            s.end = i;
            if(ans.start == -1 || (ans.end - ans.start) > (s.end - s.start)){
              ans = new Subarray(s.start, s.end);
            }
            continue;
          }

          s.idx++;
          String nextWord = keywords.get(s.idx);

          if(!counters.containsKey(nextWord) || counters.get(nextWord).start < s.start){
            counters.put(nextWord, s);
          }
        }
      }
    }

    return ans;
  }

//  public static Subarray
//  findSmallestSequentiallyCoveringSubset(List<String> paragraph,
//                                         List<String> keywords) {
//    List<S> counters = new ArrayList<>();
//    Subarray ans = new Subarray(-1, -1);
//
//    Map<String, Integer> m = new HashMap<>();
//    for(int i = 0; i < keywords.size(); i++){
//      m.put(keywords.get(i), i);
//    }
//
//    for(int i = 0; i < paragraph.size(); i++){
//      String w = paragraph.get(i);
//
//      if(m.containsKey(w)){
//        if(m.get(w) == 0){
//          counters.add(new S(i, -1, 1));
//        } else {
//          int wordIdx = m.get(w);
//
//          for(S s : counters){
//            if(s.idx == wordIdx)
//              s.idx++;
//
//            if(s.idx == m.size()) {
//              s.end = i;
//
//              if(ans.start == -1
//                      || (ans.end - ans.start) > s.end - s.start)
//                ans = new Subarray(s.start, s.end);
//            }
//          }
//        }
//      }
//    }
//
//    return ans;
//  }

  @EpiTest(testDataFile = "smallest_subarray_covering_all_values.tsv")
  public static int findSmallestSequentiallyCoveringSubsetWrapper(
      TimedExecutor executor, List<String> paragraph, List<String> keywords)
      throws Exception {
    Subarray result = executor.run(
        () -> findSmallestSequentiallyCoveringSubset(paragraph, keywords));

    int kwIdx = 0;
    if (result.start < 0) {
      throw new TestFailure("Subarray start index is negative");
    }
    int paraIdx = result.start;

    while (kwIdx < keywords.size()) {
      if (paraIdx >= paragraph.size()) {
        throw new TestFailure("Not all keywords are in the generated subarray");
      }
      if (paraIdx >= paragraph.size()) {
        throw new TestFailure("Subarray end index exceeds array size");
      }
      if (paragraph.get(paraIdx).equals(keywords.get(kwIdx))) {
        kwIdx++;
      }
      paraIdx++;
    }
    return result.end - result.start + 1;
  }

  public static void main(String[] args) {
    System.exit(
        GenericTest
            .runFromAnnotations(args, "SmallestSubarrayCoveringAllValues.java",
                                new Object() {}.getClass().getEnclosingClass())
            .ordinal());
  }
}
