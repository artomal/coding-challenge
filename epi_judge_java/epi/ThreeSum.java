package epi;
import epi.test_framework.EpiTest;
import epi.test_framework.GenericTest;

import java.util.Collections;
import java.util.List;
public class ThreeSum {

  public static boolean hasTwoSum(List<Integer> A, int t){
    int left = 0; int right = A.size() -1;
    while(left <= right){
      int sum = A.get(left) + A.get(right);
      if(sum == t){
        return true;
      } else if(sum < t){
        left++;
      } else{
        right--;
      }
    }
    return false;
  }

  @EpiTest(testDataFile = "three_sum.tsv")
  public static boolean hasThreeSum(List<Integer> A, int t) {
    Collections.sort(A);
    for(Integer num : A){
      if(hasTwoSum(A, t - num))
        return true;
    }
    return false;
  }

  public static void main(String[] args) {
    System.exit(
        GenericTest
            .runFromAnnotations(args, "ThreeSum.java",
                                new Object() {}.getClass().getEnclosingClass())
            .ordinal());
  }
}
