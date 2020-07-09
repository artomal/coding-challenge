package epi;
import epi.test_framework.EpiTest;
import epi.test_framework.GenericTest;

import java.util.*;

public class SortIncreasingDecreasingArray {

  static class Num {
    int val;
    Iterator<Integer> iter;

    Num(int val, Iterator<Integer> iter){
      this.val = val;
      this.iter = iter;
    }
  }

  public static List<Integer> mergeSorted(List<List<Integer>> lists){
    PriorityQueue<Num> maxH = new PriorityQueue<>(Comparator.comparingInt(o -> o.val));

    for(List<Integer> list : lists){
      Iterator<Integer> iter = list.iterator();
      maxH.add(new Num(iter.next(), iter));
    }

    List<Integer> ans = new ArrayList<>();
    while(!maxH.isEmpty()){
      Num num = maxH.poll();
      ans.add(num.val);
      if(num.iter.hasNext())
        maxH.add(new Num(num.iter.next(), num.iter));
    }

    return ans;
  }

  @EpiTest(testDataFile = "sort_increasing_decreasing_array.tsv")
  public static List<Integer> sortKIncreasingDecreasingArray(List<Integer> A) {
    List<List<Integer>> sortedLists = new ArrayList<>();
    boolean inc = true;
    int startIdx = 0;

    for(int i = 1; i <= A.size(); i++){
      if(A.size() == i
              || (A.get(i - 1) < A.get(i) && !inc)
              || (A.get(i - 1) > A.get(i) && inc)) {
        List<Integer> list = A.subList(startIdx, i);
        if(!inc)
          Collections.reverse(list);

        sortedLists.add(list);
        startIdx = i;
        inc = !inc;
      }
    }

    return mergeSorted(sortedLists);
  }

  public static void main(String[] args) {
    System.exit(
        GenericTest
            .runFromAnnotations(args, "SortIncreasingDecreasingArray.java",
                                new Object() {}.getClass().getEnclosingClass())
            .ordinal());
  }
}
