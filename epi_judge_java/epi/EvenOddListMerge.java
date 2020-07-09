package epi;
import epi.test_framework.EpiTest;
import epi.test_framework.GenericTest;
import java.util.List;
public class EvenOddListMerge {
  @EpiTest(testDataFile = "even_odd_list_merge.tsv")

  public static ListNode<Integer> evenOddMerge(ListNode<Integer> L) {
    if(L == null)
      return null;

    ListNode<Integer> e = L;
    ListNode<Integer> o = e.next;
    ListNode<Integer> b = o;

    while(e.next != null && o.next != null){
      e.next = e.next.next;
      o.next = o.next.next;

      e = e.next;
      o = o.next;
    }

    e.next = b;

    return L;
  }

  public static void main(String[] args) {
    System.exit(
        GenericTest
            .runFromAnnotations(args, "EvenOddListMerge.java",
                                new Object() {}.getClass().getEnclosingClass())
            .ordinal());
  }
}
