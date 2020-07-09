package epi;
import epi.test_framework.EpiTest;
import epi.test_framework.GenericTest;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
public class PascalTriangle {
  @EpiTest(testDataFile = "pascal_triangle.tsv")

  public static List<List<Integer>> generatePascalTriangle(int numRows) {
    List<List<Integer>> pas = new ArrayList<>();
    if(numRows < 1)
      return pas;

    pas.add(Arrays.asList(1));
    for(int i = 1; i < numRows; i++){
      List<Integer> row = new ArrayList<>();
      row.add(1);

      for(int j = 1; j < i; j++){
        List<Integer> prevRow = pas.get(i - 1);
        int leftNum = prevRow.get(j - 1);
        int rightNum = prevRow.get(j);
        row.add(leftNum + rightNum);
      }

      row.add(1);
      pas.add(row);
    }

    return pas;
  }

  public static void main(String[] args) {
    System.exit(
        GenericTest
            .runFromAnnotations(args, "PascalTriangle.java",
                                new Object() {}.getClass().getEnclosingClass())
            .ordinal());
  }
}
