package epi;
import epi.test_framework.EpiTest;
import epi.test_framework.GenericTest;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
public class IntAsArrayMultiply {
  @EpiTest(testDataFile = "int_as_array_multiply.tsv")
  public static List<Integer> multiply(List<Integer> L1, List<Integer> L2) {
    int n1 = L1.get(0);
    int n2 = L2.get(0);
    boolean neg = n1 * n2 < 0;

    L1.set(0, Math.abs(n1));
    L2.set(0, Math.abs(n2));

    if(n1 == 0 || n2 == 0)
      return Arrays.asList(0);

    List<List<Integer>> calc = new ArrayList<>();
    for(int i = L2.size() - 1; i >= 0; i--){
      List<Integer> cal = new ArrayList<>();
      int rem = 0;
      for(int j = L1.size() - 1; j >= 0; j--){
        n1 = L1.get(j);
        n2 = L2.get(i);

        int res = n1 * n2 + rem;
        rem = res / 10;
        cal.add(res % 10);
      }

      if(rem > 0)
        cal.add(rem);

      calc.add(cal);
    }

    List<Integer> res = calc.get(0);
    for(int i = 1; i < calc.size(); i++){
      List<Integer> curList = calc.get(i);
      int rem = 0;

      for(int idx = 0; idx < curList.size(); idx++) {
        if (idx + i < res.size()){
          int cal = res.get(idx + i) + curList.get(idx) + rem;
          rem = cal / 10;
          res.set(idx + i, cal % 10);
        } else {
          int cal = curList.get(idx) + rem;
          rem = cal / 10;
          res.add(cal % 10);
        }
      }

      if(rem > 0)
        res.add(rem);
    }

    Collections.reverse(res);
    res.set(0, neg ? res.get(0) * -1 : res.get(0));
    return res;
  }

  public static void main(String[] args) {
    System.exit(
        GenericTest
            .runFromAnnotations(args, "IntAsArrayMultiply.java",
                                new Object() {}.getClass().getEnclosingClass())
            .ordinal());
  }
}
