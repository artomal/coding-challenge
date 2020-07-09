package epi;
import epi.test_framework.EpiTest;
import epi.test_framework.GenericTest;

import java.util.ArrayList;
import java.util.List;
import java.util.NavigableSet;
import java.util.TreeSet;

public class MinimumDistance3SortedArrays {

  public static class ArrayData implements Comparable<ArrayData> {
    public int val;
    public int idx;

    public ArrayData(int idx, int val) {
      this.val = val;
      this.idx = idx;
    }

    @Override
    public int compareTo(ArrayData o) {
      int result = Integer.compare(val, o.val);
      if (result == 0) {
        result = Integer.compare(idx, o.idx);
      }
      return result;
    }
  }

  @EpiTest(testDataFile = "minimum_distance_3_sorted_arrays.tsv")

  public static int
  findMinDistanceSortedArrays(List<List<Integer>> sortedArrays) {
//
//    int ans = Integer.MAX_VALUE;
//    int aIdx = 0; int bIdx = 0; int cIdx = 0;
//
//    while(aIdx < A.size() - 1
//            && bIdx < B.size() - 1
//            && cIdx < C.size() - 1){
//      int min = Math.min(Math.min(A.get(aIdx), B.get(bIdx)), C.get(cIdx));
//      int max = Math.max(Math.max(A.get(aIdx), B.get(bIdx)), C.get(cIdx));
//
//      ans = Math.min(max - min, ans);
//
//      System.out.println("[A] " + A.get(aIdx) + " [B] " + B.get(bIdx) + " [C] " + C.get(cIdx) + " [DIFF] " + (max - min));
//
//      if(aIdx < A.size() - 1 && A.get(aIdx).compareTo(min) == 0){
//        aIdx++;
//      } else if(bIdx < B.size() - 1 && B.get(bIdx).compareTo(min) == 0){
//        bIdx++;
//      } else if(cIdx < C.size() - 1 && C.get(cIdx).compareTo(min) == 0) {
//        cIdx++;
//      } else {
//        break;
//      }
//    }
//
//    System.out.println("[A] " + A);
//    System.out.println("[B] " + B);
//    System.out.println("[C] " + C);

    List<Integer> heads = new ArrayList<>(sortedArrays.size());
    for(List<Integer> arr : sortedArrays){
      heads.add(0);
    }

    int ans = Integer.MAX_VALUE;

    TreeSet<ArrayData> curHeads = new TreeSet<>();
    for(int i = 0; i < heads.size(); i++){
      curHeads.add(new ArrayData(i, sortedArrays.get(i).get(heads.get(i))));
    }

    while(true){
      ans = Math.min(curHeads.last().val - curHeads.first().val, ans);
      int lowIdx = curHeads.first().idx;

      heads.set(lowIdx, heads.get(lowIdx) + 1);
      if(heads.get(lowIdx) >= sortedArrays.get(lowIdx).size()){
        break;
      }
      curHeads.pollFirst();
      curHeads.add(new ArrayData(lowIdx, sortedArrays.get(lowIdx).get(heads.get(lowIdx))));
    }

    return ans;
  }

  public static void main(String[] args) {
    System.exit(
        GenericTest
            .runFromAnnotations(args, "MinimumDistance3SortedArrays.java",
                                new Object() {}.getClass().getEnclosingClass())
            .ordinal());
  }
}
