package epi;
import epi.test_framework.EpiTest;
import epi.test_framework.EpiUserType;
import epi.test_framework.GenericTest;

import java.util.ArrayList;
import java.util.List;
public class MinimumPointsCoveringIntervals {
  @EpiUserType(ctorParams = {int.class, int.class})

  public static class Interval {
    public int left, right;

    public Interval(int l, int r) {
      this.left = l;
      this.right = r;
    }
  }

  @EpiTest(testDataFile = "minimum_points_covering_intervals.tsv")

  public static Integer findMinimumVisits(List<Interval> intervals) {
    if(intervals.size() == 0)
      return 0;

    List<Integer> ans = new ArrayList<>();
    int curM = intervals.get(0).right;
    for(int i = 1; i < intervals.size(); i++){
      if(curM < intervals.get(i).left){
        ans.add(curM);
        curM = intervals.get(i).right;
      }
    }

    if(ans.size() == 0 || ans.get(ans.size() - 1).compareTo(curM) != 0)
      ans.add(curM);

    return ans.size();
  }

  public static void main(String[] args) {
    System.exit(
        GenericTest
            .runFromAnnotations(args, "MinimumPointsCoveringIntervals.java",
                                new Object() {}.getClass().getEnclosingClass())
            .ordinal());
  }
}
