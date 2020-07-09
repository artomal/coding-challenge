package epi;
import epi.test_framework.EpiTest;
import epi.test_framework.GenericTest;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.PriorityQueue;

public class DirectoryPathNormalization {
  @EpiTest(testDataFile = "directory_path_normalization.tsv")

  public static String shortestEquivalentPath(String path) {
    boolean absolute = path.charAt(0) == '/';
    String[] dirs = path.split("/");
    Deque<String> p = new ArrayDeque<>();

    for(String dir : dirs){
      if(dir.length() == 0 || dir.equals(" ") || dir.equals("."))
        continue;

      if(dir.equals("..") && !p.isEmpty() && !p.getFirst().equals("..")) {
        p.removeFirst();
        continue;
      }
      p.push(dir);
    }


    StringBuilder sb = new StringBuilder();
    if (absolute)
      sb.append("/");

    if(!p.isEmpty()) {
      while (p.size() > 1) {
        sb.append(p.removeLast());
        sb.append("/");
      }
      sb.append(p.removeLast());
    }

    return sb.toString();
  }

  public static void main(String[] args) {
    System.exit(
        GenericTest
            .runFromAnnotations(args, "DirectoryPathNormalization.java",
                                new Object() {}.getClass().getEnclosingClass())
            .ordinal());
  }
}
