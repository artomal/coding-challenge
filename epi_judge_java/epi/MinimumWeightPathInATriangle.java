package epi;
import epi.test_framework.EpiTest;
import epi.test_framework.GenericTest;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MinimumWeightPathInATriangle {

  public static int minimumPathTotalHelper(List<List<Integer>> triangle, int from, int to, int lvl, List<Map<Integer, Integer>> cache) {
    if(lvl == triangle.size())
      return 0;

    if(cache.size() == lvl)
      cache.add(new HashMap<>());

    int ans = Integer.MAX_VALUE;
    for(int i = from; i <= to; i++){
        Integer res = cache.get(lvl).get(i);
        if(res == null){
          res = minimumPathTotalHelper(triangle, i, i + 1, lvl + 1, cache);
          cache.get(lvl).put(i, res);
        }
        ans = Math.min(ans, cache.get(lvl).get(i) + triangle.get(lvl).get(i));
    }

    return ans;
  }

  @EpiTest(testDataFile = "minimum_weight_path_in_a_triangle.tsv")
  public static int minimumPathTotal(List<List<Integer>> triangle) {
    return minimumPathTotalHelper(triangle, 0, 0,0, new ArrayList<>());
  }

  public static void main(String[] args) {
    System.exit(
        GenericTest
            .runFromAnnotations(args, "MinimumWeightPathInATriangle.java",
                                new Object() {}.getClass().getEnclosingClass())
            .ordinal());
  }
}
