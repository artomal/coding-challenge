package epi;
import epi.test_framework.EpiTest;
import epi.test_framework.GenericTest;
import epi.test_framework.TestFailure;
import epi.test_framework.TimedExecutor;
public class DoTerminatedListsOverlap {

  public static int findLength(ListNode<Integer> node){
    ListNode<Integer> dummy = new ListNode<>(null, node);
    int length = 0;

    while(dummy.next != null){
      length++;
      dummy = dummy.next;
    }
    return length;
  }

  public static ListNode<Integer>
  overlappingNoCycleLists(ListNode<Integer> l0, ListNode<Integer> l1) {
    int l0Length = findLength(l0);
    int l1Length = findLength(l1);

    ListNode<Integer> shortList = l0Length < l1Length ? l0 : l1;
    ListNode<Integer> longList = l0Length >= l1Length ? l0 : l1;

    int skip = Math.abs(l0Length - l1Length);

    // skip to start
    while(skip-- > 0){
      longList = longList.next;
    }

    while(longList != null && shortList != null && shortList != longList){
      shortList = shortList.next;
      longList = longList.next;
    }

    return longList != null ? longList : null;
  }
  @EpiTest(testDataFile = "do_terminated_lists_overlap.tsv")
  public static void
  overlappingNoCycleListsWrapper(TimedExecutor executor, ListNode<Integer> l0,
                                 ListNode<Integer> l1, ListNode<Integer> common)
      throws Exception {
    if (common != null) {
      if (l0 != null) {
        ListNode<Integer> i = l0;
        while (i.next != null) {
          i = i.next;
        }
        i.next = common;
      } else {
        l0 = common;
      }

      if (l1 != null) {
        ListNode<Integer> i = l1;
        while (i.next != null) {
          i = i.next;
        }
        i.next = common;
      } else {
        l1 = common;
      }
    }

    final ListNode<Integer> finalL0 = l0;
    final ListNode<Integer> finalL1 = l1;
    ListNode<Integer> result =
        executor.run(() -> overlappingNoCycleLists(finalL0, finalL1));

    if (result != common) {
      throw new TestFailure("Invalid result");
    }
  }

  public static void main(String[] args) {
    System.exit(
        GenericTest
            .runFromAnnotations(args, "DoTerminatedListsOverlap.java",
                                new Object() {}.getClass().getEnclosingClass())
            .ordinal());
  }
}
