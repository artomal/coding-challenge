package epi;
import epi.test_framework.EpiTest;
import epi.test_framework.GenericTest;

import java.util.*;

public class SunsetView {

  static class Building {
    int h;
    int idx;

    Building(int h, int idx){
      this.h = h;
      this.idx = idx;
    }
  }

  public static List<Integer>
  examineBuildingsWithSunset(Iterator<Integer> sequence) {
    Deque<Building> wE = new ArrayDeque<>();
    int idx = 0;

    while(sequence.hasNext()){
      int height = sequence.next();
      while(!wE.isEmpty() && wE.peek().h <= height)
        wE.pop();

      wE.push(new Building(height, idx++));
    }

    List<Integer> ans = new ArrayList<>();
    while(!wE.isEmpty()){
      ans.add(wE.pop().idx);
    }
    return ans;
  }
  @EpiTest(testDataFile = "sunset_view.tsv")
  public static List<Integer>
  examineBuildingsWithSunsetWrapper(List<Integer> sequence) {
    return examineBuildingsWithSunset(sequence.iterator());
  }

  public static void main(String[] args) {
    System.exit(
        GenericTest
            .runFromAnnotations(args, "SunsetView.java",
                                new Object() {}.getClass().getEnclosingClass())
            .ordinal());
  }
}
