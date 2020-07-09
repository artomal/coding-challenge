package epi;
import epi.test_framework.EpiTest;
import epi.test_framework.GenericTest;
import epi.test_framework.TestFailure;
import epi.test_framework.TimedExecutor;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Objects;
import java.util.Set;
public class SudokuSolve {

  public static boolean checkRow(List<Integer> A, int num){
    for(Integer elm : A){
      if(elm == num)
        return false;
    }
    return true;
  }

  public static boolean checkCol(List<List<Integer>> A, int c, int num){
    for(List<Integer> row : A){
      if(row.get(c) == num)
        return false;
    }
    return true;
  }

  public static boolean checkSub(List<List<Integer>> A, int r, int c, int num){
    int rowSub = r / 3;
    int colSub = c / 3;

    for(int x = 3 * rowSub; x < 3 * rowSub + 3; x++){
      for(int y = 3 * colSub; y < 3 * colSub + 3; y++){
        if(A.get(x).get(y) == num)
          return false;
      }
    }

    return true;
  }

  public static boolean check(List<List<Integer>> A, int r, int c, int num){
    return checkRow(A.get(r), num)
            && checkCol(A, c, num)
            && checkSub(A, r, c, num);
  }

  public static boolean solve(List<List<Integer>> A, int idx, int total) {
    if (idx > total)
      return true;

    int row = idx / A.size();
    int col = idx % A.get(row).size();

    int elm = A.get(row).get(col);
    if(elm != 0){
      return solve(A, idx + 1, total);
    }

    for (int num = 1; num < 10; num++) {
      if (!check(A, row, col, num)) {
        continue;
      }

      A.get(row).set(col, num);
      if (solve(A, idx + 1, total))
        return true;
    }
    A.get(row).set(col, 0);
    return false;
  }

  public static boolean solveSudoku(List<List<Integer>> partialAssignment) {
    int total = partialAssignment.size() * partialAssignment.get(0).size() - 1;
    boolean res = solve(partialAssignment, 0, total);
    return res;
  }
  @EpiTest(testDataFile = "sudoku_solve.tsv")
  public static void solveSudokuWrapper(TimedExecutor executor,
                                        List<List<Integer>> partialAssignment)
      throws Exception {
    List<List<Integer>> solved = new ArrayList<>();
    for (List<Integer> row : partialAssignment) {
      solved.add(new ArrayList<>(row));
    }

    executor.run(() -> solveSudoku(solved));

    if (partialAssignment.size() != solved.size()) {
      throw new TestFailure("Initial cell assignment has been changed");
    }

    for (int i = 0; i < partialAssignment.size(); i++) {
      List<Integer> br = partialAssignment.get(i);
      List<Integer> sr = solved.get(i);
      if (br.size() != sr.size()) {
        throw new TestFailure("Initial cell assignment has been changed");
      }
      for (int j = 0; j < br.size(); j++)
        if (br.get(j) != 0 && !Objects.equals(br.get(j), sr.get(j)))
          throw new TestFailure("Initial cell assignment has been changed");
    }

    int blockSize = (int)Math.sqrt(solved.size());
    for (int i = 0; i < solved.size(); i++) {
      assertUniqueSeq(solved.get(i));
      assertUniqueSeq(gatherColumn(solved, i));
      assertUniqueSeq(gatherSquareBlock(solved, blockSize, i));
    }
  }

  private static void assertUniqueSeq(List<Integer> seq) throws TestFailure {
    Set<Integer> seen = new HashSet<>();
    for (Integer x : seq) {
      if (x == 0) {
        throw new TestFailure("Cell left uninitialized");
      }
      if (x < 0 || x > seq.size()) {
        throw new TestFailure("Cell value out of range");
      }
      if (seen.contains(x)) {
        throw new TestFailure("Duplicate value in section");
      }
      seen.add(x);
    }
  }

  private static List<Integer> gatherColumn(List<List<Integer>> data, int i) {
    List<Integer> result = new ArrayList<>();
    for (List<Integer> row : data) {
      result.add(row.get(i));
    }
    return result;
  }

  private static List<Integer> gatherSquareBlock(List<List<Integer>> data,
                                                 int blockSize, int n) {
    List<Integer> result = new ArrayList<>();
    int blockX = n % blockSize;
    int blockY = n / blockSize;
    for (int i = blockX * blockSize; i < (blockX + 1) * blockSize; i++) {
      for (int j = blockY * blockSize; j < (blockY + 1) * blockSize; j++) {
        result.add(data.get(i).get(j));
      }
    }

    return result;
  }

  public static void main(String[] args) {
    System.exit(
        GenericTest
            .runFromAnnotations(args, "SudokuSolve.java",
                                new Object() {}.getClass().getEnclosingClass())
            .ordinal());
  }
}
