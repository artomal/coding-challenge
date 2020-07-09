package epi;
import epi.test_framework.EpiTest;
import epi.test_framework.GenericTest;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.PriorityQueue;

public class SortAlmostSortedArray {

  public static List<Integer>
  sortApproximatelySortedData(Iterator<Integer> sequence, int k) {
    List<Integer> result = new ArrayList<>();
    PriorityQueue<Integer> min = new PriorityQueue<>(k, (o1, o2) -> Integer.compare(o1, o2));

    while(sequence.hasNext()){
      for(int j = 0; j < k && sequence.hasNext(); j++){
        Integer num = sequence.next();
        min.add(num);
      }

      while(!min.isEmpty()){
        result.add(min.poll());
      }
    }

    return result;
  }
  @EpiTest(testDataFile = "sort_almost_sorted_array.tsv")
  public static List<Integer>
  sortApproximatelySortedDataWrapper(List<Integer> sequence, int k) {
    return sortApproximatelySortedData(sequence.iterator(), k);
  }

  public static void main(String[] args) {
    System.exit(
        GenericTest
            .runFromAnnotations(args, "SortAlmostSortedArray.java",
                                new Object() {}.getClass().getEnclosingClass())
            .ordinal());
  }
}
