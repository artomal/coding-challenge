package epi;
import epi.test_framework.EpiTest;
import epi.test_framework.GenericTest;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class IsValidSudoku {

  public static boolean checkHorizontal(List<Integer> A){
    Set<Integer> numbers = new HashSet<>();

    for(Integer num : A){
      if(num == 0) continue;

      if(numbers.contains(num))
        return false;
      numbers.add(num);
    }
    return true;
  }

  public static boolean checkVertical(List<List<Integer>> A, int col){
    Set<Integer> numbers = new HashSet<>();

    for(List<Integer> row : A){
      Integer num = row.get(col);
      if(num == 0) continue;

      if(numbers.contains(num))
        return false;
      numbers.add(num);
    }
    return true;
  }

  public static boolean checkMatrix(List<List<Integer>> A, int mX, int mY){
    Set<Integer> numbers = new HashSet<>();
    int size = (int) Math.sqrt(A.size());

    for(int row = mX * size; row < mX * size + size; row++){
      for(int col = mY * size; col < mY * size + size; col++){
        Integer num = A.get(row).get(col);
        if(num == 0) continue;

        if(numbers.contains(num))
          return false;
        numbers.add(num);
      }
    }
    return true;
  }

  // Check if a partially filled matrix has any conflicts.
  @EpiTest(testDataFile = "is_valid_sudoku.tsv")
  public static boolean isValidSudoku(List<List<Integer>> partialAssignment) {
    if(partialAssignment == null || partialAssignment.size() == 0)
      return false;

//    printSudoku(partialAssignment);

    // Check rows O(n^2)
    for(int i = 0; i < partialAssignment.size(); i++) {
      boolean valid = checkHorizontal(partialAssignment.get(i));
      if (!valid)
        return false;
    }

    // Check columns O(n^2)
    for(int i = 0; i < partialAssignment.size(); i++) {
      boolean valid = checkVertical(partialAssignment, i);
      if (!valid)
        return false;
    }

    // Check 3x3 matrix O(n^2)
    for(int r = 0; r < Math.sqrt(partialAssignment.size()); r++){
      for(int c = 0; c < Math.sqrt(partialAssignment.size()); c++){
        boolean valid = checkMatrix(partialAssignment, r, c);
        if (!valid)
          return false;
      }
    }

    return true;
  }

  public static void printSudoku(List<List<Integer>> A){
    for(List<Integer> row : A){
      for(Integer num : row){
        System.out.print(num + " ");
      }
      System.out.println();
    }
  }

  public static void main(String[] args) {
    System.exit(
        GenericTest
            .runFromAnnotations(args, "IsValidSudoku.java",
                                new Object() {}.getClass().getEnclosingClass())
            .ordinal());
  }
}
