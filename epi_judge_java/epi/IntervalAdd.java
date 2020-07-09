package epi;
import epi.test_framework.EpiTest;
import epi.test_framework.EpiUserType;
import epi.test_framework.GenericTest;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
public class IntervalAdd {
  @EpiUserType(ctorParams = {int.class, int.class})

  public static class Interval {
    public int left, right;

    public Interval(int l, int r) {
      this.left = l;
      this.right = r;
    }

    @Override
    public boolean equals(Object o) {
      if (this == o) {
        return true;
      }
      if (o == null || getClass() != o.getClass()) {
        return false;
      }

      Interval interval = (Interval)o;

      if (left != interval.left) {
        return false;
      }
      return right == interval.right;
    }

    @Override
    public String toString() {
      return "[" + left + ", " + right + "]";
    }
  }

  @EpiTest(testDataFile = "interval_add.tsv")

  public static List<Interval> addInterval(List<Interval> disjointIntervals,
                                           Interval newInterval) {
//    disjointIntervals.add(newInterval);
//    Collections.sort(disjointIntervals, Comparator.comparingInt(o -> o.left));
//
//    int lastIdx = 0;
//    for(int i = 1; i < disjointIntervals.size(); i++){
//      Interval last = disjointIntervals.get(lastIdx);
//      Interval cur = disjointIntervals.get(i);
//
//       if(cur.left <= last.right){
//        last.left = Math.min(last.left, cur.left);
//        last.right = Math.max(last.right, cur.right);
//      } else {
//        last = disjointIntervals.get(++lastIdx);
//        last.left = cur.left;
//        last.right = cur.right;
//      }
//    }
//
//    disjointIntervals.subList(++lastIdx, disjointIntervals.size()).clear();
//
//    return disjointIntervals;

    List<Interval> ans = new ArrayList<>();
    int i = 0;

    while(i < disjointIntervals.size() && newInterval.left > disjointIntervals.get(i).right){
      ans.add(disjointIntervals.get(i++));
    }

    while(i < disjointIntervals.size() && newInterval.right >= disjointIntervals.get(i).left){
      Interval cur = disjointIntervals.get(i++);
      newInterval = new Interval(Math.min(newInterval.left, cur.left), Math.max(newInterval.right, cur.right));
    }

    ans.add(newInterval);

    while(i < disjointIntervals.size()){
      ans.add(disjointIntervals.get(i++));
    }

    return ans;
  }

  public static void main(String[] args) {
    System.exit(
        GenericTest
            .runFromAnnotations(args, "IntervalAdd.java",
                                new Object() {}.getClass().getEnclosingClass())
            .ordinal());
  }
}
