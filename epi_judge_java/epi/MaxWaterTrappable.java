package epi;
import epi.test_framework.EpiTest;
import epi.test_framework.GenericTest;
import java.util.List;
public class MaxWaterTrappable {
  @EpiTest(testDataFile = "max_water_trappable.tsv")

  public static int calculateTrappingWater(List<Integer> heights) {
    int max = 0;
    int l = 0;
    int r = heights.size() - 1;

    while(l < r){
      int lHeight = heights.get(l);
      int rHeight = heights.get(r);
      int width = r - l;

      max = Math.max(max, width * Math.min(lHeight, rHeight));
//      System.out.println("[CUR MAX] " + max);

      if(lHeight < rHeight){
        l++;
      } else if(lHeight > rHeight) {
        r--;
      } else {
        l++;
        r--;
      }
    }

    return max;
  }

  public static void main(String[] args) {
    System.exit(
        GenericTest
            .runFromAnnotations(args, "MaxWaterTrappable.java",
                                new Object() {}.getClass().getEnclosingClass())
            .ordinal());
  }
}
