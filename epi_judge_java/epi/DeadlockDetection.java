package epi;
import epi.test_framework.EpiTest;
import epi.test_framework.EpiUserType;
import epi.test_framework.GenericTest;
import epi.test_framework.TimedExecutor;

import java.util.*;

public class DeadlockDetection {

  public static class GraphVertex {
    public enum Color { WHITE, GRAY, BLACK };

    public Color color;
    public List<GraphVertex> edges;

    public GraphVertex() { edges = new ArrayList<>(); color = Color.WHITE; }
  }

  public static boolean isDeadlocked(List<GraphVertex> graph) {
    for(GraphVertex v : graph){
      if(v.color == GraphVertex.Color.WHITE && hasCycle(v))
        return true;
    }
    return false;
  }

  public static boolean hasCycle(GraphVertex cur){
    if(cur.color.equals(GraphVertex.Color.GRAY))
      return true;

    cur.color = GraphVertex.Color.GRAY;
    for(GraphVertex next : cur.edges){
      if(!next.color.equals(GraphVertex.Color.BLACK))
        if(hasCycle(next))
          return true;
    }
    cur.color = GraphVertex.Color.BLACK;
    return false;
  }

  @EpiUserType(ctorParams = {int.class, int.class})
  public static class Edge {
    public int from;
    public int to;

    public Edge(int from, int to) {
      this.from = from;
      this.to = to;
    }
  }

  @EpiTest(testDataFile = "deadlock_detection.tsv")
  public static boolean isDeadlockedWrapper(TimedExecutor executor,
                                            int numNodes, List<Edge> edges)
      throws Exception {
    if (numNodes <= 0) {
      throw new RuntimeException("Invalid numNodes value");
    }
    List<GraphVertex> graph = new ArrayList<>();
    for (int i = 0; i < numNodes; i++) {
      graph.add(new GraphVertex());
    }
    for (Edge e : edges) {
      if (e.from < 0 || e.from >= numNodes || e.to < 0 || e.to >= numNodes) {
        throw new RuntimeException("Invalid vertex index");
      }
      graph.get(e.from).edges.add(graph.get(e.to));
    }

    return executor.run(() -> isDeadlocked(graph));
  }

  public static void main(String[] args) {
    System.exit(
        GenericTest
            .runFromAnnotations(args, "DeadlockDetection.java",
                                new Object() {}.getClass().getEnclosingClass())
            .ordinal());
  }
}
