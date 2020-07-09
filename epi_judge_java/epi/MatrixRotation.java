package epi;
import epi.test_framework.EpiTest;
import epi.test_framework.GenericTest;
import java.util.List;
public class MatrixRotation {

  public static void printMatrix(List<List<Integer>> A){
    System.out.println("");
    for(int r = 0; r < A.size(); r++){
      for(int c = 0; c < A.get(r).size(); c++){
        System.out.print(" " + A.get(r).get(c) + " ");
      }
      System.out.println("");
    }
  }

  public static void swap(List<List<Integer>> A,
                          int fromR,
                          int fromC,
                          int toR,
                          int toC) {
    int tmp = A.get(toR).get(toC);
    A.get(toR).set(toC, A.get(fromR).get(fromC));
    A.get(fromR).set(fromC, tmp);
  }

  public static void rotateMatrix(List<List<Integer>> A) {
    int length = A.size();
    for(int i = 0; i < length / 2; i++){
      int lenCyc = length - (2 * i) - 1;
      for(int j = 0; j < lenCyc; j++){
        swap(A, i, i+j, i+j, i + lenCyc);
        swap(A, i, i+j, i+lenCyc, i + lenCyc - j);
        swap(A, i, i+j, i + lenCyc - j, i);
      }
    }
    return;
  }
  @EpiTest(testDataFile = "matrix_rotation.tsv")
  public static List<List<Integer>>
  rotateMatrixWrapper(List<List<Integer>> squareMatrix) {
    rotateMatrix(squareMatrix);
    return squareMatrix;
  }

  public static void main(String[] args) {
    System.exit(
        GenericTest
            .runFromAnnotations(args, "MatrixRotation.java",
                                new Object() {}.getClass().getEnclosingClass())
            .ordinal());
  }
}
