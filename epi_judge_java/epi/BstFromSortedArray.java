package epi;
import epi.test_framework.BinaryTreeUtils;
import epi.test_framework.EpiTest;
import epi.test_framework.GenericTest;
import epi.test_framework.TestFailure;
import epi.test_framework.TestUtils;
import epi.test_framework.TimedExecutor;
import java.util.List;
public class BstFromSortedArray {

  public static BstNode<Integer> f(List<Integer> list, int from, int to){
    if(from == to)
      return null;

    int mid = (from + to) / 2;
    return new BstNode<>(list.get(mid), f(list, from, mid), f(list, mid + 1, to));
  }

  public static BstNode<Integer>
  buildMinHeightBSTFromSortedArray(List<Integer> A) {
    if(A.size() == 0)
      return null;

    int mid = A.size() / 2;
    return new BstNode<Integer>(A.get(mid),
            f(A, 0, mid),
            f(A, mid + 1, A.size()));
  }
  @EpiTest(testDataFile = "bst_from_sorted_array.tsv")
  public static int
  buildMinHeightBSTFromSortedArrayWrapper(TimedExecutor executor,
                                          List<Integer> A) throws Exception {
    BstNode<Integer> result =
        executor.run(() -> buildMinHeightBSTFromSortedArray(A));

    List<Integer> inorder = BinaryTreeUtils.generateInorder(result);

    TestUtils.assertAllValuesPresent(A, inorder);
    BinaryTreeUtils.assertTreeIsBst(result);
    return BinaryTreeUtils.binaryTreeHeight(result);
  }

  public static void main(String[] args) {
    System.exit(
        GenericTest
            .runFromAnnotations(args, "BstFromSortedArray.java",
                                new Object() {}.getClass().getEnclosingClass())
            .ordinal());
  }
}
