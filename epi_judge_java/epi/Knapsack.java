package epi;
import epi.test_framework.EpiTest;
import epi.test_framework.EpiUserType;
import epi.test_framework.GenericTest;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;
public class Knapsack {
  @EpiUserType(ctorParams = {Integer.class, Integer.class})

  public static class Item {
    public Integer weight;
    public Integer value;

    public Item(Integer weight, Integer value) {
      this.weight = weight;
      this.value = value;
    }
  }

  public static void print(List<Item> items){
    for(Item item : items){
      System.out.println("Value: " + item.value + " Weight: " + item.weight);
    }
  }

//  public static int find(List<Item> items, int max, int index, int sum){
//    int maxSum = sum;
////    System.out.println("[MAX SUM] " + maxSum);
//    for(int i = index; i < items.size(); i++){
//      Item item = items.get(i);
//      if(max - item.weight < 0)
//        break;
//
//      sum += item.value;
//      max -= item.weight;
//      maxSum = Math.max(find(items, max, i + 1, sum), maxSum);
//      sum -= item.value;
//      max += item.weight;
//    }
//    return maxSum;
//  }


  @EpiTest(testDataFile = "knapsack.tsv")
  public static int optimumSubjectToCapacity(List<Item> items, int capacity) {
//    Collections.sort(items, Comparator.comparing(x -> x.weight));

//    print(items);
    int[][] M = new int[items.size()][capacity + 1];

    for(int r = 0; r < M.length; r++){
      for(int c = 0; c < M[r].length; c++){
        Item item = items.get(r);
        if(r == 0){
          M[r][c] = item.weight > c ? 0 : items.get(r).value;
          continue;
        }

        int without = M[r - 1][c];

        if(item.weight > c){
          M[r][c] = without;
          continue;
        }

        int with = M[r - 1][c - item.weight] + item.value;
        M[r][c] = Math.max(with, without);
      }
    }

    return M[items.size() - 1][capacity];
  }

  public static void main(String[] args) {
    System.exit(
        GenericTest
            .runFromAnnotations(args, "Knapsack.java",
                                new Object() {}.getClass().getEnclosingClass())
            .ordinal());
  }
}
