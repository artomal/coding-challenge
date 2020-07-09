package epi;
import epi.test_framework.EpiTest;
import epi.test_framework.GenericTest;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;

public class IsListPalindromic {
  @EpiTest(testDataFile = "is_list_palindromic.tsv")

  public static boolean isLinkedListAPalindrome(ListNode<Integer> L) {
    if(L == null || L.size() == 1)
      return true;

    ListNode<Integer> run = L;
    ListNode<Integer> cur = L;

    List<Integer> nums = new ArrayList<>();
    while(run.next != null){
      nums.add(cur.data);

      if(run.next.next != null){
        run = run.next.next;
      } else {
        run = run.next;
      }
      cur = cur.next;
    }

    if(L.size() % 2 > 0)
      cur = cur.next;

    ListIterator<Integer> iter = nums.listIterator(nums.size());
    while(iter.hasPrevious()){
      if(iter.previous().compareTo(cur.data) != 0)
        return false;

      cur = cur.next;
    }

    return true;
  }

  public static void main(String[] args) {
    System.exit(
        GenericTest
            .runFromAnnotations(args, "IsListPalindromic.java",
                                new Object() {}.getClass().getEnclosingClass())
            .ordinal());
  }
}
