package epi;
import epi.test_framework.EpiTest;
import epi.test_framework.GenericTest;
import java.util.List;
public class SearchFirstKey {

  @EpiTest(testDataFile = "search_first_key.tsv")
  public static int searchFirstOfK(List<Integer> A, int k) {
    if(A == null || A.size() == 0)
      return -1;

    int L = 0; int R = A.size() - 1;
    int result = -1;

    while(L <= R){
      int M = L + (R - L) / 2;
      if(A.get(M) < k){
        L = M + 1;
      } else if(A.get(M) == k){
        result = M;
        R = M - 1;
      } else {
        R = M - 1;
      }
    }

    return result;
  }

  public static void main(String[] args) {
    System.exit(
        GenericTest
            .runFromAnnotations(args, "SearchFirstKey.java",
                                new Object() {}.getClass().getEnclosingClass())
            .ordinal());
  }
}
