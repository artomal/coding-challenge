package epi;
import epi.test_framework.EpiTest;
import epi.test_framework.GenericTest;

import java.util.List;

public class ReverseSublist {
  @EpiTest(testDataFile = "reverse_sublist.tsv")
  public static ListNode<Integer> reverseSublist(ListNode<Integer> L, int start,
                                                 int finish) {
    ListNode<Integer> dummy = new ListNode<>(null, L);
    ListNode<Integer> cur = dummy;

    int size = finish - start;

    // iterate to starting point
    while(--start > 0){
      cur = cur.next;
    }

    // reverse
    ListNode<Integer> b = cur;
    cur = cur.next;
    while(size-- > 0){
      ListNode<Integer> e = b.next;
      b.next = cur.next;
      cur.next = cur.next.next;
      b.next.next = e;
    }

    return dummy.next;
  }

  public static void main(String[] args) {
    System.exit(
        GenericTest
            .runFromAnnotations(args, "ReverseSublist.java",
                                new Object() {}.getClass().getEnclosingClass())
            .ordinal());
  }
}
