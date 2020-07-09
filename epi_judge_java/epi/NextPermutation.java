package epi;
import epi.test_framework.EpiTest;
import epi.test_framework.GenericTest;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
public class NextPermutation {
  public static int findMin(int from, int numIdx, List<Integer> A){
    for(int i = numIdx; i >= from; i--){
      if(A.get(i) < A.get(numIdx))
        return i;
    }
    return from;
  }

  @EpiTest(testDataFile = "next_permutation.tsv")
  public static List<Integer> nextPermutation(List<Integer> A) {
    int posR = A.size();
    int posL = posR;

    int R = posR;
    int L = 0;

    while(L < R--){
      int minPos = findMin(L, R, A);
      if(A.get(minPos) < A.get(R)){
        posL = minPos;
        posR = R;
        L = posL + 1;
      }
    }

    if(posL == posR)
      return new ArrayList<>();

    Collections.swap(A, posL, posR);

    L = posL;
    R = A.size();

    while(++L < --R){
      Collections.swap(A, L, R);
    }

    return A;
  }

  public static void main(String[] args) {
    System.exit(
        GenericTest
            .runFromAnnotations(args, "NextPermutation.java",
                                new Object() {}.getClass().getEnclosingClass())
            .ordinal());
  }
}
