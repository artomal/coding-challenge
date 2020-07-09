package epi;
import epi.test_framework.EpiTest;
import epi.test_framework.GenericTest;

import java.util.*;

public class OnlineMedian {
  public static List<Double> onlineMedian(Iterator<Integer> sequence) {
    List<Double> res = new ArrayList<>();

    PriorityQueue<Integer> min = new PriorityQueue<>();
    PriorityQueue<Integer> max = new PriorityQueue<>(Collections.reverseOrder());

    while(sequence.hasNext()) {
      int num = sequence.next();

      if (max.size() == 0 || max.peek() > num) {
        max.add(num);
      } else {
        min.add(num);
      }

      if (Math.abs(max.size() - min.size()) > 1) {
        if (max.size() < min.size()) {
          max.add(min.remove());
        } else {
          min.add(max.remove());
        }
      }

      if (max.size() == min.size()) {
        double median = (double)(max.peek() + min.peek()) / 2;
        res.add(median);
      } else {
        PriorityQueue<Integer> queue = max.size() > min.size() ? max : min;
        res.add((double) queue.peek());
      }
    }

    return res;
  }
  @EpiTest(testDataFile = "online_median.tsv")
  public static List<Double> onlineMedianWrapper(List<Integer> sequence) {
    return onlineMedian(sequence.iterator());
  }

  public static void main(String[] args) {
    System.exit(
        GenericTest
            .runFromAnnotations(args, "OnlineMedian.java",
                                new Object() {}.getClass().getEnclosingClass())
            .ordinal());
  }
}
