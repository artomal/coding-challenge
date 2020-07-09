package epi;
import epi.test_framework.EpiTest;
import epi.test_framework.GenericTest;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class PickingUpCoins {

  public static int f(List<Integer> coins, int left, int right, int[][] c) {
    if(left > right)
      return 0;

    if(c[left][right] == 0) {
      int p1 = coins.get(left) + Math.min(f(coins, left + 2, right, c), f(coins, left + 1, right - 1, c));
      int p2 = coins.get(right) + Math.min(f(coins, left + 1, right - 1, c), f(coins, left, right - 2, c));
      c[left][right] = Math.max(p1 , p2);
    }

    return c[left][right];
  }

  @EpiTest(testDataFile = "picking_up_coins.tsv")
  public static int pickUpCoins(List<Integer> coins) {
    return f(coins, 0, coins.size() - 1, new int[coins.size()][coins.size()]);
  }

  public static void main(String[] args) {
    System.exit(
        GenericTest
            .runFromAnnotations(args, "PickingUpCoins.java",
                                new Object() {}.getClass().getEnclosingClass())
            .ordinal());
  }
}
