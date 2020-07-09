package epi;
import epi.test_framework.EpiTest;
import epi.test_framework.GenericTest;

import java.util.ArrayList;
import java.util.List;
public class TreeWithParentInorder {

  static class Cur {

    BinaryTree<Integer> node;
    BinaryTree<Integer> prev;

    Cur(BinaryTree<Integer> node, BinaryTree<Integer> prev){
      this.node = node;
      this.prev = prev;
    }
  }


  @EpiTest(testDataFile = "tree_with_parent_inorder.tsv")
  public static List<Integer> inorderTraversal(BinaryTree<Integer> tree) {
    List<Integer> res = new ArrayList<>();
    Cur cur = new Cur(tree, tree);

    while(cur.node != null){
      BinaryTree<Integer> next;
      while(cur.node.left != null
              && cur.node.left != cur.prev
              && cur.node.right != cur.prev){
        cur.prev = cur.node;
        cur.node = cur.node.left;
      }
      next = cur.node.parent;

      if(cur.node.right != cur.prev)
        res.add(cur.node.data);

      if(cur.node.right != null
              && cur.node.right != cur.prev){
        next = cur.node.right;
      }

      cur.prev = cur.node;
      cur.node = next;
    }

    return res;
  }

  public static void main(String[] args) {
    System.exit(
        GenericTest
            .runFromAnnotations(args, "TreeWithParentInorder.java",
                                new Object() {}.getClass().getEnclosingClass())
            .ordinal());
  }
}
