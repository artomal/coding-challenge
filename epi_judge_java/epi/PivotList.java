package epi;
import epi.test_framework.EpiTest;
import epi.test_framework.GenericTest;
import epi.test_framework.TestFailure;
import epi.test_framework.TimedExecutor;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
public class PivotList {

  public static ListNode<Integer> listPivoting(ListNode<Integer> l, int p) {
    ListNode<Integer> dummyHead = new ListNode<>(null, l);

    // for max
    ListNode<Integer> mHead = new ListNode<>(null, null);
    ListNode<Integer> mRun = mHead;
    // for min
    ListNode<Integer> lHead = new ListNode<>(null, null);
    ListNode<Integer> lRun = lHead;

    // prePivot node
    ListNode<Integer> LEend = null;

    ListNode<Integer> prev = dummyHead;
    ListNode<Integer> cur = prev.next;

    while(cur != null){
      if(LEend == null && cur.data.compareTo(p) > 0){
        mRun.next = cur;
        mRun = mRun.next;
        prev.next = cur.next;
        cur = prev.next;
        mRun.next = null;
        continue;
      }

      if(LEend != null && cur.data.compareTo(p) < 0){
        lRun.next = cur;
        lRun = lRun.next;
        prev.next = cur.next;
        cur = prev.next;
        lRun.next = null;
        continue;
      }

      if(cur.data.compareTo(p) == 0)
        LEend = prev;

      prev = cur;
      cur = cur.next;
    }

    if(lHead.next != null){
      lRun.next = LEend.next;
      LEend.next = lHead.next;
    }

    if(mHead.next != null)
      prev.next = mHead.next;

    dummyHead = LEend != null || mHead.next == null ? dummyHead : mHead;
    return dummyHead.next;
  }
  public static List<Integer> linkedToList(ListNode<Integer> l) {
    List<Integer> v = new ArrayList<>();
    while (l != null) {
      v.add(l.data);
      l = l.next;
    }
    return v;
  }

  @EpiTest(testDataFile = "pivot_list.tsv")
  public static void listPivotingWrapper(TimedExecutor executor,
                                         ListNode<Integer> l, int x)
      throws Exception {
    List<Integer> original = linkedToList(l);

    final ListNode<Integer> finalL = l;
    l = executor.run(() -> listPivoting(finalL, x));

    List<Integer> pivoted = linkedToList(l);

    int mode = -1;
    for (Integer i : pivoted) {
      switch (mode) {
      case -1:
        if (i == x) {
          mode = 0;
        } else if (i > x) {
          mode = 1;
        }
        break;
      case 0:
        if (i < x) {
          throw new TestFailure("List is not pivoted");
        } else if (i > x) {
          mode = 1;
        }
        break;
      case 1:
        if (i <= x) {
          throw new TestFailure("List is not pivoted");
        }
      }
    }

    Collections.sort(original);
    Collections.sort(pivoted);
    if (!original.equals(pivoted))
      throw new TestFailure("Result list contains different values");
  }

  public static void main(String[] args) {
    System.exit(
        GenericTest
            .runFromAnnotations(args, "PivotList.java",
                                new Object() {}.getClass().getEnclosingClass())
            .ordinal());
  }
}
