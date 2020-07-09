package epi;
import epi.test_framework.EpiTest;
import epi.test_framework.GenericTest;

import java.util.ArrayList;
import java.util.List;
public class TreeLevelOrder {

  public static void traverseTree(BinaryTreeNode<Integer> tree, List<List<Integer>> list, int lvl){
    if(tree == null)
      return;

    if(list.size() == lvl)
      list.add(new ArrayList<>());

    list.get(lvl).add(tree.data);
    lvl++;

    traverseTree(tree.left, list, lvl);
    traverseTree(tree.right, list, lvl);
  }

  @EpiTest(testDataFile = "tree_level_order.tsv")
  public static List<List<Integer>>
  binaryTreeDepthOrder(BinaryTreeNode<Integer> tree) {
    List<List<Integer>> result = new ArrayList<>();
    traverseTree(tree, result, 0);
    return result;
  }

  public static void main(String[] args) {
    System.exit(
        GenericTest
            .runFromAnnotations(args, "TreeLevelOrder.java",
                                new Object() {}.getClass().getEnclosingClass())
            .ordinal());
  }
}
