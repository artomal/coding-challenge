package epi;
import epi.test_framework.EpiTest;
import epi.test_framework.GenericTest;

import java.util.ArrayList;
import java.util.List;
public class SpiralOrderingSegments {
  @EpiTest(testDataFile = "spiral_ordering_segments.tsv")

//  public static List<Integer>
//  matrixInSpiralOrder(List<List<Integer>> squareMatrix) {
//    List<Integer> result = new ArrayList<>();
//    if (squareMatrix == null || squareMatrix.size() == 0)
//      return result;
//
//    int lvl = 0;
//    int length = squareMatrix.size();
//    int max = length / 2 + length % 2;
//
//    while(lvl < max){
//      int c = lvl; int r = lvl;
//
//      // Right
//      while(c < length - lvl){
//        int num = squareMatrix.get(r).get(c);
//        result.add(num);
//        c++;
//      }
//      c--;
//      r++;
//
//      // Down
//      while(r < length - lvl){
//        int num = squareMatrix.get(r).get(c);
//        result.add(num);
//        r++;
//      }
//      r--;
//      c--;
//
//      // Left
//      while(c >= lvl){
//        int num = squareMatrix.get(r).get(c);
//        result.add(num);
//        c--;
//      }
//      c++;
//      r--;
//
//      // Down
//      while(r > lvl){
//        int num = squareMatrix.get(r).get(c);
//        result.add(num);
//        r--;
//      }
//      lvl++;
//    }
//
//    return result;
//  }

  public static List<Integer>
  matrixInSpiralOrder(List<List<Integer>> squareMatrix) {
    List<Integer> result = new ArrayList<>();
    if (squareMatrix == null || squareMatrix.size() == 0)
      return result;

    int length = squareMatrix.size();
    boolean odd = length % 2 > 0 ? true : false;
    double max = Math.ceil(0.5 * length);
    int lvl = 1;

    while(lvl <= max){
      int c = lvl - 1; int r = lvl - 1;

      // Right
      while(c < length - lvl){
        int num = squareMatrix.get(r).get(c);
        result.add(num);
        c++;
      }

      // Down
      while(r < length - lvl){
        int num = squareMatrix.get(r).get(c);
        result.add(num);
        r++;
      }

      // Left
      while(c >= lvl){
        int num = squareMatrix.get(r).get(c);
        result.add(num);
        c--;
      }

      // Down
      while(r >= lvl){
        int num = squareMatrix.get(r).get(c);
        result.add(num);
        r--;
      }

      // Special case
      if(odd && lvl == max) {
        int num = squareMatrix.get(r).get(c);
        result.add(num);
      }
      lvl++;
    }

    return result;
  }

  public static void main(String[] args) {
    System.exit(
        GenericTest
            .runFromAnnotations(args, "SpiralOrderingSegments.java",
                                new Object() {}.getClass().getEnclosingClass())
            .ordinal());
  }
}
