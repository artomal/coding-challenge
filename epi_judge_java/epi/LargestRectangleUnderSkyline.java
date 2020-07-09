package epi;
import epi.test_framework.EpiTest;
import epi.test_framework.GenericTest;
import java.util.List;
public class LargestRectangleUnderSkyline {
  @EpiTest(testDataFile = "largest_rectangle_under_skyline.tsv")

  public static int calculateLargestRectangle(List<Integer> A) {
    int max = 0;

    for(int i = 0; i < A.size(); i++){
      int curH = A.get(i);
      int found = 1;

      // Left
      int lIdx = i;
      while(--lIdx >= 0){
        if(A.get(lIdx) < curH)
          break;

        found++;
      }

      // Right
      int rIdx = i;
      while(++rIdx < A.size()){
        if(A.get(rIdx) < curH)
          break;

        found++;
      }

      max = Math.max(max, curH * found);
    }

    return max;
  }

  public static void main(String[] args) {
    System.exit(
        GenericTest
            .runFromAnnotations(args, "LargestRectangleUnderSkyline.java",
                                new Object() {}.getClass().getEnclosingClass())
            .ordinal());
  }
}
