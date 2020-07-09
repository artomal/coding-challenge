package epi;
import epi.test_framework.EpiTest;
import epi.test_framework.GenericTest;
public class NumberOfTraversalsMatrix {

  @EpiTest(testDataFile = "number_of_traversals_matrix.tsv")
  public static int numberOfWays(int n, int m) {
    int[][] A = new int[n][m];

    for(int r = 0; r < A.length; r++){
      for(int c = 0; c < A[r].length; c++){
        if(r == 0 || c == 0) {
          A[r][c] = 1;
        } else {
          int top = A[r - 1][c];
          int left = A[r][c - 1];
          A[r][c] = top + left;
        }
      }
    }

    return A[n - 1][m - 1];
  }

  public static void main(String[] args) {
    System.exit(
        GenericTest
            .runFromAnnotations(args, "NumberOfTraversalsMatrix.java",
                                new Object() {}.getClass().getEnclosingClass())
            .ordinal());
  }
}
