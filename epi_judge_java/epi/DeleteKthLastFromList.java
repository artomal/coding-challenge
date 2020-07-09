package epi;
import epi.test_framework.EpiTest;
import epi.test_framework.GenericTest;
public class DeleteKthLastFromList {
  @EpiTest(testDataFile = "delete_kth_last_from_list.tsv")

  // Assumes L has at least k nodes, deletes the k-th last node in L.
  public static ListNode<Integer> removeKthLast(ListNode<Integer> L, int k) {
    ListNode<Integer> head = new ListNode<>(null, L);
    ListNode<Integer> dummy = head;
    ListNode<Integer> advanced = head;

    while(advanced.next != null){
      advanced = advanced.next;
      if(k-- > 0)
        continue;
      dummy = dummy.next;
    }
    dummy.next = dummy.next.next;

    return head.next;
  }

  public static void main(String[] args) {
    System.exit(
        GenericTest
            .runFromAnnotations(args, "DeleteKthLastFromList.java",
                                new Object() {}.getClass().getEnclosingClass())
            .ordinal());
  }
}
