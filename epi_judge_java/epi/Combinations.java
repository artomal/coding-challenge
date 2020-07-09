package epi;
import epi.test_framework.EpiTest;
import epi.test_framework.EpiTestComparator;
import epi.test_framework.LexicographicalListComparator;
import epi.test_framework.GenericTest;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.function.BiPredicate;
public class Combinations {

  public static void combinationsHelper(int n, int k, int idx, List<Integer> list, List<List<Integer>> res){
    if(list.size() == k){
      res.add(new ArrayList<>(list));
      return;
    }

    for(int i = idx; i <= n; i++){
      list.add(i);
      combinationsHelper(n, k, i + 1, list, res);
      list.remove(list.size() - 1);
    }
  }

  @EpiTest(testDataFile = "combinations.tsv")
  public static List<List<Integer>> combinations(int n, int k) {
    List<List<Integer>> ans = new ArrayList<>();
    combinationsHelper(n, k, 1, new ArrayList<>(), ans);
    return ans;
  }
  @EpiTestComparator
  public static BiPredicate<List<List<Integer>>, List<List<Integer>>> comp =
      (expected, result) -> {
    if (result == null) {
      return false;
    }
    expected.sort(new LexicographicalListComparator<>());
    result.sort(new LexicographicalListComparator<>());
    return expected.equals(result);
  };

  public static void main(String[] args) {
    System.exit(
        GenericTest
            .runFromAnnotations(args, "Combinations.java",
                                new Object() {}.getClass().getEnclosingClass())
            .ordinal());
  }
}
