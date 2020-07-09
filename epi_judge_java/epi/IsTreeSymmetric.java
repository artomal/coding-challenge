package epi;
import epi.test_framework.EpiTest;
import epi.test_framework.GenericTest;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class IsTreeSymmetric {

  public static void traverseL(BinaryTreeNode<Integer> tree, List<Integer> nums){
    if(tree == null){
      nums.add(null);
      return;
    }

    traverseL(tree.left, nums);
    traverseL(tree.right, nums);
    nums.add(tree.data);
  }

  public static void traverseR(BinaryTreeNode<Integer> tree, List<Integer> nums){
    if(tree == null){
      nums.add(null);
      return;
    }

    traverseR(tree.right, nums);
    traverseR(tree.left, nums);
    nums.add(tree.data);
  }

  @EpiTest(testDataFile = "is_tree_symmetric.tsv")
  public static boolean isSymmetric(BinaryTreeNode<Integer> tree) {
    if(tree == null)
      return true;

    List<Integer> left = new LinkedList<>();
    List<Integer> right = new LinkedList<>();

    traverseL(tree.left, left);
    traverseR(tree.right, right);

    if(left.size() != right.size()) {
//      System.out.println("NOT SAME SIZE");
      return false;
    }

    for(int i = 0; i < left.size(); i++){
//      System.out.println(left.get(i) + " : " + right.get(i));
      if(left.get(i) != right.get(i)) {
//        System.out.println("NOT EQUAL ELMS");
        return false;
      }
    }

    return true;
  }

  public static void main(String[] args) {
    System.exit(
        GenericTest
            .runFromAnnotations(args, "IsTreeSymmetric.java",
                                new Object() {}.getClass().getEnclosingClass())
            .ordinal());
  }
}
