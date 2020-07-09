package epi;
import epi.test_framework.EpiTest;
import epi.test_framework.EpiTestComparator;
import epi.test_framework.GenericTest;

import java.util.*;
import java.util.function.BiPredicate;
public class KLargestInHeap {

//  @EpiTest(testDataFile = "k_largest_in_heap.tsv")
//  public static List<Integer> kLargestInBinaryHeap(List<Integer> A, int k) {
//    PriorityQueue<Integer> minHeap = new PriorityQueue<>();
//    for(int num : A){
//      minHeap.add(num);
//      if(minHeap.size() > k)
//        minHeap.remove();
//    }
//
//    List<Integer> ans = new ArrayList<>();
//    while(!minHeap.isEmpty()){
//      ans.add(minHeap.poll());
//    }
//
//    return ans;
//  }

  record Candidate(int num, int idx){};

  @EpiTest(testDataFile = "k_largest_in_heap.tsv")
  public static List<Integer> kLargestInBinaryHeap(List<Integer> A, int k) {
    List<Integer> ans = new ArrayList<>();
    PriorityQueue<Candidate> maxHeap = new PriorityQueue<>((o1, o2) -> Integer.compare(o2.num, o1.num));
    maxHeap.add(new Candidate(A.get(0), 0));

    for(int i = 1; i<=k; i++){
      Candidate cur = maxHeap.remove();
      ans.add(cur.num);

      int leftChIdx = cur.idx * 2 + 1;
      if(leftChIdx < A.size()){
        maxHeap.add(new Candidate(A.get(leftChIdx), leftChIdx));
      }

      int rightChIdx = cur.idx * 2 + 2;
      if(rightChIdx < A.size()){
        maxHeap.add(new Candidate(A.get(rightChIdx), rightChIdx));
      }
    }

    return ans;
  }

  @EpiTestComparator
  public static BiPredicate<List<Integer>, List<Integer>> comp =
      (expected, result) -> {
    if (result == null) {
      return false;
    }
    Collections.sort(expected);
    Collections.sort(result);
    return expected.equals(result);
  };

  public static void main(String[] args) {
    System.exit(
        GenericTest
            .runFromAnnotations(args, "KLargestInHeap.java",
                                new Object() {}.getClass().getEnclosingClass())
            .ordinal());
  }
}
