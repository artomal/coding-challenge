package epi;
import epi.test_framework.BinaryTreeUtils;
import epi.test_framework.EpiTest;
import epi.test_framework.GenericTest;
import epi.test_framework.TimedExecutor;
public class DescendantAndAncestorInBst {

  public static boolean
  pairIncludesAncestorAndDescendantOfM(BstNode<Integer> n1,
                                       BstNode<Integer> n2,
                                       BstNode<Integer> m) {
    if(m == n1 && m == n2)
      return false;

    BstNode<Integer> nod1 = n1;
    BstNode<Integer> nod2 = n2;
    boolean found = false;

    while(nod1 != null || nod2 != null){
      if(nod1 != null && nod1.data.compareTo(m.data) == 0){
        found = true;
        break;
      }

      if(nod2 != null && nod2.data.compareTo(m.data) == 0){
        n2 = n1;
        found = true;
        break;
      }

      if(nod1 != null)
        nod1 = nod1.data.compareTo(m.data) < 0 ? nod1.right : nod1.left;

      if(nod2 != null)
        nod2 = nod2.data.compareTo(m.data) < 0 ? nod2.right : nod2.left;
    }

    if(!found)
      return false;

    while(m != null){
      if(m.data.compareTo(n2.data) == 0)
        return true;

      m = m.data.compareTo(n2.data) < 0 ? m.right : m.left;
    }

    return false;
  }
  @EpiTest(testDataFile = "descendant_and_ancestor_in_bst.tsv")
  public static boolean pairIncludesAncestorAndDescendantOfMWrapper(
      TimedExecutor executor, BstNode<Integer> tree, int possibleAncOrDesc0,
      int possibleAncOrDesc1, int middle) throws Exception {
    final BstNode<Integer> candidate0 =
        BinaryTreeUtils.mustFindNode(tree, possibleAncOrDesc0);
    final BstNode<Integer> candidate1 =
        BinaryTreeUtils.mustFindNode(tree, possibleAncOrDesc1);
    final BstNode<Integer> middleNode =
        BinaryTreeUtils.mustFindNode(tree, middle);

    return executor.run(()
                            -> pairIncludesAncestorAndDescendantOfM(
                                candidate0, candidate1, middleNode));
  }

  public static void main(String[] args) {
    System.exit(
        GenericTest
            .runFromAnnotations(args, "DescendantAndAncestorInBst.java",
                                new Object() {}.getClass().getEnclosingClass())
            .ordinal());
  }
}
