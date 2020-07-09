package epi;
import epi.test_framework.EpiTest;
import epi.test_framework.GenericTest;
public class SumRootToLeaf {

  public static int f(BinaryTreeNode<Integer> tree, StringBuilder sb) {
    int res = 0;
    if(tree == null)
      return res;

    sb.append(tree.data);

    if(tree.left == null && tree.right == null){
      res += Integer.parseInt(sb.toString(), 2);
      sb.deleteCharAt(sb.length() - 1);
      return res;
    }

    res = f(tree.left, sb) + f(tree.right, sb);
    sb.deleteCharAt(sb.length() - 1);
    return res;
  }

  @EpiTest(testDataFile = "sum_root_to_leaf.tsv")
  public static int sumRootToLeaf(BinaryTreeNode<Integer> tree) {
    return f(tree, new StringBuilder());
  }

  public static void main(String[] args) {
    System.exit(
        GenericTest
            .runFromAnnotations(args, "SumRootToLeaf.java",
                                new Object() {}.getClass().getEnclosingClass())
            .ordinal());
  }
}
