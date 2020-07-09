package epi;
import epi.test_framework.EpiTest;
import epi.test_framework.GenericTest;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
public class IntAsArrayIncrement {

  public static boolean add(List<Integer> A, int index){
    int num = A.get(index) + 1;
    if(num > 9){
      A.set(index, 0);
      return true;
    }
    A.set(index, num);
    return false;
  }

  @EpiTest(testDataFile = "int_as_array_increment.tsv")
  public static List<Integer> plusOne(List<Integer> A) {
    List<Integer> result = new ArrayList<>();
    if(A == null || A.size() == 0){
      return result;
    }

    boolean isCarry = true;
    int index = A.size();

    while(isCarry && index > 0){
      isCarry = add(A, --index);
    }

    if(isCarry){
      A.add(0);
      A.set(0, 1);
    }
    return A;
  }

  public static void main(String[] args) {
    System.exit(
        GenericTest
            .runFromAnnotations(args, "IntAsArrayIncrement.java",
                                new Object() {}.getClass().getEnclosingClass())
            .ordinal());
  }
}
