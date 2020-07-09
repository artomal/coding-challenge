package epi;
import epi.test_framework.EpiTest;
import epi.test_framework.GenericTest;
import epi.test_framework.TimedExecutor;
import java.util.ArrayList;
import java.util.List;
public class TreeFromPreorderWithNull {

  static class Status {
    int idx;

    Status(int idx){
      this.idx = idx;
    }
  }

  public static void f(List<Integer> L, Status s, BinaryTreeNode<Integer> node){
    if(node == null)
      return;

    node.left =  ++s.idx < L.size() && L.get(s.idx) != null ? new BinaryTreeNode<>(L.get(s.idx)) : null;
    f(L, s, node.left);

    node.right = ++s.idx < L.size() && L.get(s.idx) != null ? new BinaryTreeNode<>(L.get(s.idx)) : null;
    f(L, s, node.right);
  }

  public static BinaryTreeNode<Integer>
  reconstructPreorder(List<Integer> preorder) {
    BinaryTreeNode<Integer> node = preorder.get(0) != null ? new BinaryTreeNode<>(preorder.get(0)) : null;
    f(preorder, new Status(0), node);
    return node;
  }
  @EpiTest(testDataFile = "tree_from_preorder_with_null.tsv")
  public static BinaryTreeNode<Integer>
  reconstructPreorderWrapper(TimedExecutor executor, List<String> strings)
      throws Exception {
    List<Integer> ints = new ArrayList<>();
    for (String s : strings) {
      if (s.equals("null")) {
        ints.add(null);
      } else {
        ints.add(Integer.parseInt(s));
      }
    }

    return executor.run(() -> reconstructPreorder(ints));
  }

  public static void main(String[] args) {
    System.exit(
        GenericTest
            .runFromAnnotations(args, "TreeFromPreorderWithNull.java",
                                new Object() {}.getClass().getEnclosingClass())
            .ordinal());
  }
}
