package epi;
import epi.test_framework.EpiTest;
import epi.test_framework.GenericTest;

import java.util.*;

public class ABSqrt2 {

  public static class AB2 implements Comparable<AB2> {
    int a;
    int b;
    double val;

    AB2(int a, int b){
      this.a = a;
      this.b = b;
      this.val = a + b * Math.sqrt(2);
    }

    @Override
    public boolean equals(Object obj) {
      return this.val == ((AB2) obj).val;
    }

    @Override
    public int compareTo(AB2 o) {
      return Double.compare(this.val, o.val);
    }
  }

  public static void f(int k, int a, int b, PriorityQueue<Double> heap){
    if(k == 0)
      return;

    double count = a + b * Math.sqrt(2);
    heap.add(count);

    f(k - 1, a + 1, b, heap);
    f(k - 1, a, b + 1, heap);
  }

  @EpiTest(testDataFile = "a_b_sqrt2.tsv")
  public static List<Double> generateFirstKABSqrt2(int k) {
//    SortedSet<AB2> minHeap = new TreeSet<>();
    PriorityQueue<AB2> minHeap = new PriorityQueue<>();
    List<Double> res = new ArrayList<>();
    minHeap.add(new AB2(0, 0));
    while(res.size() != k){
//      for(AB2 c : minHeap){
//        System.out.print(c.val + ", ");
//      }
//      System.out.println();
//      AB2 cand = minHeap.first();
      AB2 cand = minHeap.peek();
      res.add(cand.val);

      AB2 a = new AB2(cand.a + 1, cand.b);
      AB2 b = new AB2(cand.a, cand.b + 1);

      if(!minHeap.contains(a))
        minHeap.add(a);

      if(!minHeap.contains(b))
        minHeap.add(b);

      minHeap.remove(cand);
    }
    return res;
  }

  public static void main(String[] args) {
    System.exit(
        GenericTest
            .runFromAnnotations(args, "ABSqrt2.java",
                                new Object() {}.getClass().getEnclosingClass())
            .ordinal());
  }
}
