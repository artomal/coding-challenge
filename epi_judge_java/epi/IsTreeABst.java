package epi;
import epi.test_framework.EpiTest;
import epi.test_framework.GenericTest;
public class IsTreeABst {

  public static boolean isBinaryTreeBST(BinaryTreeNode<Integer> tree, int lower, int upper) {
    if(tree == null)
      return true;

    if(lower > tree.data || tree.data > upper){
      return false;
    }

    return isBinaryTreeBST(tree.left, lower, tree.data) && isBinaryTreeBST(tree.right, tree.data, upper);
  }

  @EpiTest(testDataFile = "is_tree_a_bst.tsv")
  public static boolean isBinaryTreeBST(BinaryTreeNode<Integer> tree) {
    return isBinaryTreeBST(tree, Integer.MIN_VALUE, Integer.MAX_VALUE);
  }

  public static void main(String[] args) {
    System.exit(
        GenericTest
            .runFromAnnotations(args, "IsTreeABst.java",
                                new Object() {}.getClass().getEnclosingClass())
            .ordinal());
  }
}
