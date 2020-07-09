package epi;
import epi.test_framework.EpiTest;
import epi.test_framework.GenericTest;

import java.util.ArrayList;
import java.util.List;
public class IntersectSortedArrays {
  @EpiTest(testDataFile = "intersect_sorted_arrays.tsv")
  public static List<Integer> intersectTwoSortedArrays(List<Integer> A,
                                                       List<Integer> B) {
    List<Integer> res = new ArrayList<>();
    int aIdx = 0;
    int bIdx = 0;

    while(aIdx < A.size() && bIdx < B.size()){
      if(A.get(aIdx) < B.get(bIdx)){
        aIdx++;
      } else if(B.get(bIdx) < A.get(aIdx)){
        bIdx++;
      } else {
        int num = A.get(aIdx);
        if(!res.contains(num)){
          res.add(num);
        }
        aIdx++;
        bIdx++;
      }
    }

    return res;
  }

  public static void main(String[] args) {
    System.exit(
        GenericTest
            .runFromAnnotations(args, "IntersectSortedArrays.java",
                                new Object() {}.getClass().getEnclosingClass())
            .ordinal());
  }
}
