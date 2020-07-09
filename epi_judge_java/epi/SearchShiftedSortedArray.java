package epi;
import epi.test_framework.EpiTest;
import epi.test_framework.GenericTest;
import java.util.List;
public class SearchShiftedSortedArray {
  @EpiTest(testDataFile = "search_shifted_sorted_array.tsv")
  public static int searchSmallest(List<Integer> A) {
    int L = 0; int R = A.size() - 1;

    while(L < R){
      int M = L + (R - L) / 2;

      if(A.get(M) > A.get(R)){
        L = M + 1;
      } else {
        R = M;
      }
    }
    return L;
  }

  public static void main(String[] args) {
    System.exit(
        GenericTest
            .runFromAnnotations(args, "SearchShiftedSortedArray.java",
                                new Object() {}.getClass().getEnclosingClass())
            .ordinal());
  }
}
