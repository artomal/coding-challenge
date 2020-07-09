package epi;
import epi.test_framework.EpiTest;
import epi.test_framework.GenericTest;
import epi.test_framework.TestFailure;
import epi.test_framework.TimedExecutor;

import java.util.LinkedList;
import java.util.List;
public class RefuelingSchedule {
  private static final int MPG = 20;

  static class City {
    public int idx;
    public int remaining;

    City(int idx, int remaining){
      this.idx = idx;
      this.remaining = remaining;
    }
  }

  // gallons[i] is the amount of gas in city i, and distances[i] is the distance
  // city i to the next city.
  public static int findAmpleCity(List<Integer> gallons,
                                  List<Integer> distances) {

    int remaining = 0;
    City city = new City(0, 0);
    for(int i = 0; i < distances.size() - 1; i++){
      remaining += gallons.get(i) * MPG - distances.get(i);
      if(remaining < city.remaining){
        city = new City(i + 1, remaining);
      }
    }

    return city.idx;
  }
  @EpiTest(testDataFile = "refueling_schedule.tsv")
  public static void findAmpleCityWrapper(TimedExecutor executor,
                                          List<Integer> gallons,
                                          List<Integer> distances)
      throws Exception {
    int result = executor.run(() -> findAmpleCity(gallons, distances));
    final int numCities = gallons.size();
    int tank = 0;
    for (int i = 0; i < numCities; ++i) {
      int city = (result + i) % numCities;
      tank += gallons.get(city) * MPG - distances.get(city);
      if (tank < 0) {
        throw new TestFailure(String.format("Out of gas on city %d", city));
      }
    }
  }

  public static void main(String[] args) {
    System.exit(
        GenericTest
            .runFromAnnotations(args, "RefuelingSchedule.java",
                                new Object() {}.getClass().getEnclosingClass())
            .ordinal());
  }
}
