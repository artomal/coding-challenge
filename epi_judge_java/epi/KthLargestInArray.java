package epi;
import epi.test_framework.EpiTest;
import epi.test_framework.GenericTest;

import java.util.*;

public class KthLargestInArray {
  // The numbering starts from one, i.e., if A = [3,1,-1,2] then
  // findKthLargest(A, 1) returns 3, findKthLargest(A, 2) returns 2,
  // findKthLargest(A, 3) returns 1, and findKthLargest(A, 4) returns -1.
  @EpiTest(testDataFile = "kth_largest_in_array.tsv")
  public static int findKthLargest(int k, List<Integer> A) {
    PriorityQueue<Integer> min = new PriorityQueue<>(k + 1, ((o1, o2) -> Integer.compare(o1, o2)));
    Iterator<Integer> iter = A.iterator();

    int i = 0;
    while(iter.hasNext() && i++ < k + 1){
      min.add(iter.next());
    }

    while(iter.hasNext()){
      min.poll();
      min.add(iter.next());
    }

    int result = min.poll();
    result = !min.isEmpty() && min.size() >= k ? min.poll() : result;
    return result;
  }

  public static void main(String[] args) {
    System.exit(
        GenericTest
            .runFromAnnotations(args, "KthLargestInArray.java",
                                new Object() {}.getClass().getEnclosingClass())
            .ordinal());
  }
}
