package epi;
import epi.test_framework.EpiTest;
import epi.test_framework.GenericTest;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class IsStringInMatrix {

  static class Coord {
    int x;
    int y;
    int i;

    Coord(int x, int y, int i){
      this.x = x;
      this.y = y;
      this.i = i;
    }

    @Override
    public boolean equals(Object obj) {
      if(obj instanceof Coord){
        Coord o = (Coord) obj;
        if(this.x == o.x && this.y == o.y && this.i == o.i){
          return true;
        }
      }

      return false;
    }

    @Override
    public int hashCode() {
      return x * 1000 + y * 100 + i * 10;
    }
  }

  public static boolean checkNext(List<List<Integer>> A, List<Integer> B, Coord c, int[] dir){
    int nextX = c.x + dir[0];
    int nextY = c.y + dir[1];

    if(nextX < A.size()
            && nextX >= 0
            && nextY < A.get(0).size()
            && nextY >= 0
            && A.get(nextX).get(nextY) == B.get(c.i + 1)){
      return true;
    }

    if(nextX < A.size()
            && nextX >= 0
            && nextY < A.get(0).size()
            && nextY + dir[1] >= 0) {
    }
    return false;
  }

  public static boolean dfs(List<List<Integer>> A, List<Integer> B, Coord c, Map<Coord, Boolean> visited){
    int[][] DIRS = {{0, 1}, {0, -1}, {1, 0}, {-1,0}};

    for(int[] dir : DIRS){
      if(visited.containsKey(c)) {
        return visited.get(c);
      }

      if(B.size() == c.i + 1)
        return true;

      if(checkNext(A, B, c, dir)){
        Coord nextCoord = new Coord(
                c.x + dir[0],
                c.y + dir[1],
                c.i + 1
                );

        boolean res = dfs(A, B, nextCoord, visited);
        visited.put(nextCoord, res);

        if(res)
          return res;
      }
    }
    return false;
  }

  @EpiTest(testDataFile = "is_string_in_matrix.tsv")
  public static boolean isPatternContainedInGrid(List<List<Integer>> grid,
                                                 List<Integer> pattern) {
    Map<Coord, Boolean> visited = new HashMap<>();
    for(int x = 0; x < grid.size(); x++){
      for(int y = 0; y < grid.get(x).size(); y++){
        Coord c = new Coord(x, y, 0);
        if(grid.get(x).get(y) == pattern.get(0)){
          boolean found = dfs(grid, pattern, c, visited);
          if(found)
            return true;
        }
      }
    }

    return false;
  }

  public static void main(String[] args) {
    System.exit(
        GenericTest
            .runFromAnnotations(args, "IsStringInMatrix.java",
                                new Object() {}.getClass().getEnclosingClass())
            .ordinal());
  }
}
