package epi;
import epi.test_framework.EpiTest;
import epi.test_framework.GenericTest;
import epi.test_framework.TestFailure;
import epi.test_framework.TimedExecutor;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
public class TreeExterior {

  enum Side {
    L, R;
  }

  public static void traverse(BinaryTreeNode<Integer> tree, Side side, boolean log, List<BinaryTreeNode<Integer>> ans){
    if(tree == null)
      return;

    if(tree.left == null && tree.right == null) {
      ans.add(tree);
      return;
    }

    if(Side.L.equals(side) && log)
      ans.add(tree);

    traverse(tree.left, side, (Side.L.equals(side) || tree.right == null) && log, ans);
    traverse(tree.right, side, (Side.R.equals(side) || tree.left == null) && log, ans);

    if(Side.R.equals(side) && log)
      ans.add(tree);
  }

  public static List<BinaryTreeNode<Integer>>
  exteriorBinaryTree(BinaryTreeNode<Integer> tree) {
    if(tree == null)
      return Collections.EMPTY_LIST;

    List<BinaryTreeNode<Integer>> ans = new ArrayList<>();
    ans.add(tree);

    traverse(tree.left, Side.L, true, ans);
    traverse(tree.right, Side.R, true, ans);
    return ans;
  }
  private static List<Integer> createOutputList(List<BinaryTreeNode<Integer>> L)
      throws TestFailure {
    if (L.contains(null)) {
      throw new TestFailure("Resulting list contains null");
    }
    List<Integer> output = new ArrayList<>();
    for (BinaryTreeNode<Integer> l : L) {
      output.add(l.data);
    }
    return output;
  }

  @EpiTest(testDataFile = "tree_exterior.tsv")
  public static List<Integer>
  exteriorBinaryTreeWrapper(TimedExecutor executor,
                            BinaryTreeNode<Integer> tree) throws Exception {
    List<BinaryTreeNode<Integer>> result =
        executor.run(() -> exteriorBinaryTree(tree));

    return createOutputList(result);
  }

  public static void main(String[] args) {
    System.exit(
        GenericTest
            .runFromAnnotations(args, "TreeExterior.java",
                                new Object() {}.getClass().getEnclosingClass())
            .ordinal());
  }
}
