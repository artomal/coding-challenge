package epi;
import epi.test_framework.EpiTest;
import epi.test_framework.GenericTest;
public class ListCyclicRightShift {

  public static int len(ListNode<Integer> L){
    int len = 0;
    while(L != null){
      L = L.next;
      len++;
    }
    return len;
  }

  @EpiTest(testDataFile = "list_cyclic_right_shift.tsv")
  public static ListNode<Integer> cyclicallyRightShiftList(ListNode<Integer> L,
                                                           int k) {
    ListNode<Integer> dummy = new ListNode<>(null, L);
    ListNode<Integer> run = L;
    ListNode<Integer> end = L;

    int len = len(L);
    k = len < 1 ? 0 : k % len;
    if(k < 1 || len < 2)
      return dummy.next;

    while(--k >= 0)
      run = run.next;

    while(run.next != null){
      run = run.next;
      end = end.next;
    }

    ListNode<Integer> head = end.next;
    end.next = null;
    run.next = dummy.next;

    return head;
  }

  public static void main(String[] args) {
    System.exit(
        GenericTest
            .runFromAnnotations(args, "ListCyclicRightShift.java",
                                new Object() {}.getClass().getEnclosingClass())
            .ordinal());
  }
}
