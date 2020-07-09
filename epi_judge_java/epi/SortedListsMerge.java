package epi;
import epi.test_framework.EpiTest;
import epi.test_framework.GenericTest;
public class SortedListsMerge {
  @EpiTest(testDataFile = "sorted_lists_merge.tsv")
  public static ListNode<Integer> mergeTwoSortedLists(ListNode<Integer> L1,
                                                      ListNode<Integer> L2) {
    ListNode<Integer> result = new ListNode<>(null, null);
    ListNode<Integer> dump = result;

    while(L1 != null && L2 != null){
      if(L1.data.compareTo(L2.data) < 0){
        dump.next = L1;
        L1 = L1.next;
      } else {
        dump.next = L2;
        L2 = L2.next;
      }
      dump = dump.next;
    }
    dump.next = L1 != null ? L1 : L2;

    return result.next;
  }

  public static void main(String[] args) {
    System.exit(
        GenericTest
            .runFromAnnotations(args, "SortedListsMerge.java",
                                new Object() {}.getClass().getEnclosingClass())
            .ordinal());
  }
}
