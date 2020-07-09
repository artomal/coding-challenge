package epi;
import epi.test_framework.EpiTest;
import epi.test_framework.GenericTest;
public class IntAsListAdd {

  public static int len(ListNode<Integer> node){
    int len = 0;
    while(node != null){
      node = node.next;
      len++;
    }
    return len;
  }

  @EpiTest(testDataFile = "int_as_list_add.tsv")
  public static ListNode<Integer> addTwoNumbers(ListNode<Integer> L1,
                                                ListNode<Integer> L2) {
    ListNode<Integer> head = L1;
    if(len(L1) < len(L2)){
      L1 = L2; L2 = head; head = L1;
    }

    ListNode<Integer> prev = L1;
    int rem = 0;

    while(L1 != null || L2 != null){
      int sum = rem;

      if(L2 != null){
        sum += L2.data;
        L2 = L2.next;
      }

      if(L1 != null){
        sum += L1.data;
        L1.data = sum % 10;
        prev = L1;
        L1 = L1.next;
      }

      rem = sum / 10;
    }

    if(rem > 0)
      prev.next = new ListNode<>(rem, null);

    return head;
  }

  public static void main(String[] args) {
    System.exit(
        GenericTest
            .runFromAnnotations(args, "IntAsListAdd.java",
                                new Object() {}.getClass().getEnclosingClass())
            .ordinal());
  }
}
