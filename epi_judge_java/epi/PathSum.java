package epi;
import epi.test_framework.EpiTest;
import epi.test_framework.GenericTest;
public class PathSum {

  public static boolean hasPathSumHelper(BinaryTreeNode<Integer> tree,
                                   int remainingWeight, int sum) {
    boolean leftRes = false;
    boolean rightRes = false;

    sum += tree.data;

    if(tree.left != null)
      leftRes = hasPathSumHelper(tree.left, remainingWeight, sum);

    if(tree.right != null)
      rightRes = hasPathSumHelper(tree.right, remainingWeight, sum);

    if(tree.left == null && tree.right == null)
      return sum == remainingWeight;

    return leftRes || rightRes;
  }

  @EpiTest(testDataFile = "path_sum.tsv")
  public static boolean hasPathSum(BinaryTreeNode<Integer> tree,
                                   int remainingWeight) {
    return hasPathSumHelper(tree, remainingWeight, 0);
  }

  public static void main(String[] args) {
    System.exit(
        GenericTest
            .runFromAnnotations(args, "PathSum.java",
                                new Object() {}.getClass().getEnclosingClass())
            .ordinal());
  }
}
