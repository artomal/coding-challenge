package epi;
import epi.test_framework.EpiTest;
import epi.test_framework.GenericTest;
public class IsTreeBalanced {

  static class Status {
    boolean balanced;
    int height;

    Status(boolean balanced, int height){
      this.balanced = balanced;
      this.height = height;
    }
  }

  private static Status traverse(BinaryTreeNode<Integer> tree, int h){
    if(tree == null)
      return new Status(true, h);
    h++;


    Status leftT = traverse(tree.left, h);
    if(!leftT.balanced)
      return leftT;

    Status rightT = traverse(tree.right, h);
    if(!rightT.balanced)
      return rightT;

    boolean status = Math.abs(leftT.height - rightT.height) > 1 ? false : true;
    return new Status(status, Math.max(leftT.height, rightT.height));
  }

  @EpiTest(testDataFile = "is_tree_balanced.tsv")
  public static boolean isBalanced(BinaryTreeNode<Integer> tree) {
    return traverse(tree, 0).balanced;
  }

  public static void main(String[] args) {
    System.exit(
        GenericTest
            .runFromAnnotations(args, "IsTreeBalanced.java",
                                new Object() {}.getClass().getEnclosingClass())
            .ordinal());
  }
}
