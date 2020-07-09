package epi;
import epi.test_framework.EpiTest;
import epi.test_framework.GenericTest;

import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

public class MajorityElement {

  public static String majoritySearch(Iterator<String> stream) {
    String candidate = "";
    int count = 0;
    while(stream.hasNext()){
      String elm = stream.next();

      if(count == 0){
        candidate = elm;
        count = 1;
      } else if(candidate.equals(elm)) {
        count++;
      } else {
        count--;
      }
    }

    return candidate;
  }

//  public static String majoritySearch(Iterator<String> stream) {
//    Map<String, Integer> count = new HashMap<>();
//    while(stream.hasNext()){
//      String elm = stream.next();
//      Integer cnt = count.putIfAbsent(elm, 1);
//      if(cnt != null){
//        count.put(elm, cnt + 1);
//      }
//    }
//
//    int max = 0;
//    String maxCnt = "";
//    for(Map.Entry<String, Integer> entry : count.entrySet()){
//      if(entry.getValue() > max){
//        max = entry.getValue();
//        maxCnt = entry.getKey();
//      }
//    }
//    return maxCnt;
//  }
  @EpiTest(testDataFile = "majority_element.tsv")
  public static String majoritySearchWrapper(List<String> stream) {
    return majoritySearch(stream.iterator());
  }

  public static void main(String[] args) {
    System.exit(
        GenericTest
            .runFromAnnotations(args, "MajorityElement.java",
                                new Object() {}.getClass().getEnclosingClass())
            .ordinal());
  }
}
