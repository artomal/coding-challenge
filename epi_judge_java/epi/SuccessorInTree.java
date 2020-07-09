package epi;
import epi.test_framework.BinaryTreeUtils;
import epi.test_framework.EpiTest;
import epi.test_framework.GenericTest;
import epi.test_framework.TimedExecutor;
public class SuccessorInTree {

  public static BinaryTree<Integer> traverseUp(BinaryTree<Integer> node, BinaryTree<Integer> prev){
    while(node.parent != null && node.parent.right == node){
      node = node.parent;
    }
    return node;
  }

  public static BinaryTree<Integer> findLeftmost(BinaryTree<Integer> node){
    while(node.left != null){
      node = node.left;
    }
    return node;
  }

  public static BinaryTree<Integer> findSuccessor(BinaryTree<Integer> node) {
    if(node == null)
      return null;

    if(node.right != null)
      return findLeftmost(node.right);

    if(node.parent != null){
      if(node.parent.left == node) {
        return node.parent;
      } else {
        return traverseUp(node.parent, node).parent;
      }
    }
    return null;
  }
  @EpiTest(testDataFile = "successor_in_tree.tsv")
  public static int findSuccessorWrapper(TimedExecutor executor,
                                         BinaryTree<Integer> tree, int nodeIdx)
      throws Exception {
    BinaryTree<Integer> n = BinaryTreeUtils.mustFindNode(tree, nodeIdx);

    BinaryTree<Integer> result = executor.run(() -> findSuccessor(n));

    return result == null ? -1 : result.data;
  }

  public static void main(String[] args) {
    System.exit(
        GenericTest
            .runFromAnnotations(args, "SuccessorInTree.java",
                                new Object() {}.getClass().getEnclosingClass())
            .ordinal());
  }
}
