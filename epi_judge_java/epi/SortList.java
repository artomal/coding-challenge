package epi;
import epi.test_framework.EpiTest;
import epi.test_framework.GenericTest;
public class SortList {
  @EpiTest(testDataFile = "sort_list.tsv")

  public static ListNode<Integer> stableSortList(ListNode<Integer> L) {
    if(L == null)
      return L;

    ListNode<Integer> head = L;
    ListNode<Integer> checker = head;
    ListNode<Integer> runner = checker;

    int i = 0;
    while(runner.next != null){
      if(runner.data <= runner.next.data){
        runner = runner.next;
        continue;
      }

      if(head.data > runner.next.data){
        head = runner.next;
        runner.next = runner.next.next;
        head.next = checker;
        checker = head;
        continue;
      }

      while(checker.next.data < runner.next.data){
        checker = checker.next;
      }

      ListNode<Integer> tmp = checker.next;
      checker.next = runner.next;
      runner.next = runner.next.next;
      checker.next.next = tmp;
      checker = head;
    }

    return head;
  }

//  public static ListNode<Integer> stableSortList(ListNode<Integer> L) {
//    if(L == null)
//      return L;
//
//    ListNode<Integer> head = L;
//    ListNode<Integer> checker = head;
//    ListNode<Integer> runner = checker;
//
//    int i = 0;
//    while(runner.next != null){
//      if(runner.data <= runner.next.data){
//        runner = runner.next;
//        continue;
//      }
//
//      if(head.data > runner.next.data){
//        head = runner.next;
//        runner.next = runner.next.next;
//        head.next = checker;
//        checker = head;
//        continue;
//      }
//
//      while(checker.next.data < runner.next.data){
//        checker = checker.next;
//      }
//
//      ListNode<Integer> tmp = checker.next;
//      checker.next = runner.next;
//      runner.next = runner.next.next;
//      checker.next.next = tmp;
//      checker = head;
//    }
//
//    return head;
//  }

  public static void main(String[] args) {
    System.exit(
        GenericTest
            .runFromAnnotations(args, "SortList.java",
                                new Object() {}.getClass().getEnclosingClass())
            .ordinal());
  }
}
