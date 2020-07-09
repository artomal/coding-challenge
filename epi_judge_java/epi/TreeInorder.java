package epi;
import epi.test_framework.EpiTest;
import epi.test_framework.GenericTest;

import java.util.*;

public class TreeInorder {

  @EpiTest(testDataFile = "tree_inorder.tsv")
  public static List<Integer> inorderTraversal(BinaryTreeNode<Integer> tree) {
    List<Integer> ans = new ArrayList<>();
    Deque<BinaryTreeNode<Integer>> st = new ArrayDeque<>();
    BinaryTreeNode<Integer> cur = tree;

    while(!st.isEmpty() || cur != null){
      if(cur != null){
        st.push(cur);
        cur = cur.left;
      } else {
        cur = st.pop();
        ans.add(cur.data);
        cur = cur.right;
      }
    }

    return ans;
  }

  public static void main(String[] args) {
    System.exit(
        GenericTest
            .runFromAnnotations(args, "TreeInorder.java",
                                new Object() {}.getClass().getEnclosingClass())
            .ordinal());
  }
}
