package epi;
import epi.test_framework.EpiTest;
import epi.test_framework.GenericTest;
import java.util.List;
public class BuyAndSellStock {
  @EpiTest(testDataFile = "buy_and_sell_stock.tsv")
  public static double computeMaxProfit(List<Double> prices) {
    double max = 0;
    if(prices == null || prices.size() == 0)
      return max;

    double start = prices.get(0);

    for(Double num : prices){
      double diff = num - start;

      if(diff < 0){
        start = num;
        continue;
      } else if(diff > max){
        max = diff;
      }
    }

    return max;
  }

  public static void main(String[] args) {
    System.exit(
        GenericTest
            .runFromAnnotations(args, "BuyAndSellStock.java",
                                new Object() {}.getClass().getEnclosingClass())
            .ordinal());
  }
}
