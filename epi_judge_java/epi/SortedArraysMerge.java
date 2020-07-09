package epi;
import epi.test_framework.EpiTest;
import epi.test_framework.GenericTest;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.PriorityQueue;

public class SortedArraysMerge {

  static class Elm {
    int num;
    Iterator<Integer> iter;

    Elm(int num, Iterator<Integer> iter){
      this.num = num;
      this.iter = iter;
    }
  }

  @EpiTest(testDataFile = "sorted_arrays_merge.tsv")
  public static List<Integer>
  mergeSortedArrays(List<List<Integer>> sortedArrays) {
    List<Integer> result = new ArrayList<>();
    List<Iterator<Integer>> iters = new ArrayList<>();
    PriorityQueue<Elm> min = new PriorityQueue<>(sortedArrays.size(),
            (o1, o2) -> Integer.compare(o1.num, o2.num));

    for(List<Integer> list : sortedArrays){
      iters.add(list.iterator());
    }

    for(Iterator<Integer> iter : iters){
      min.add(new Elm(iter.next(), iter));
    }

    while(!min.isEmpty()){
      Elm cur = min.poll();
      result.add(cur.num);

      if(cur.iter.hasNext()){
        min.add(new Elm(cur.iter.next(), cur.iter));
      }
    }

    return result;
  }

  public static void main(String[] args) {
    System.exit(
        GenericTest
            .runFromAnnotations(args, "SortedArraysMerge.java",
                                new Object() {}.getClass().getEnclosingClass())
            .ordinal());
  }
}
