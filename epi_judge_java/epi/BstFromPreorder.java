  package epi;
import epi.test_framework.EpiTest;
import epi.test_framework.GenericTest;
import java.util.List;
public class BstFromPreorder {

  public static void f(List<Integer> l, BstNode<Integer> node, int from, int to){
    int lFrom = from;
    int rFrom = lFrom;
    while(rFrom < to && l.get(rFrom) < node.data){
      rFrom++;
    }

    if(lFrom != rFrom && l.get(lFrom) < node.data){
      node.left = new BstNode<>(l.get(lFrom));
      f(l, node.left, lFrom + 1, rFrom);
    }

    if(rFrom != to && l.get(rFrom) > node.data){
      node.right = new BstNode<>(l.get(rFrom));
      f(l, node.right, rFrom + 1, to);
    }
  }

  @EpiTest(testDataFile = "bst_from_preorder.tsv")
  public static BstNode<Integer>
  rebuildBSTFromPreorder(List<Integer> preorderSequence) {
    if(preorderSequence.size() == 0)
      return null;

    BstNode<Integer> res = new BstNode<>(preorderSequence.get(0));
    BstNode<Integer> node = res;
    f(preorderSequence, node, 1, preorderSequence.size());
    return res;
  }

  public static void main(String[] args) {
    System.exit(
        GenericTest
            .runFromAnnotations(args, "BstFromPreorder.java",
                                new Object() {}.getClass().getEnclosingClass())
            .ordinal());
  }
}
