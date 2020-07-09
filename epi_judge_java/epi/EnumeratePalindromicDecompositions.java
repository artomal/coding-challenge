package epi;
import epi.test_framework.EpiTest;
import epi.test_framework.EpiTestComparator;
import epi.test_framework.LexicographicalListComparator;
import epi.test_framework.GenericTest;

import java.util.ArrayList;
import java.util.List;
import java.util.function.BiPredicate;
public class EnumeratePalindromicDecompositions {

  public static boolean isPalindrome(String s){
    for(int l = 0, r = s.length() - 1; l < r; l++, r--){
      if(s.charAt(l) != s.charAt(r))
        return false;
    }
    return true;
  }

  public static void f(String s, int idx, List<String> partial, List<List<String>> ans){
    if(idx == s.length()){
      ans.add(new ArrayList<>(partial));
      return;
    }

    for(int i = idx + 1; i <= s.length(); i++){
      String prefix = s.substring(idx, i);
      if(isPalindrome(prefix)){
        partial.add(prefix);
        f(s, i, partial, ans);
        partial.remove(partial.size() - 1);
      }
    }
  }

  @EpiTest(testDataFile = "enumerate_palindromic_decompositions.tsv")
  public static List<List<String>> palindromeDecompositions(String input) {
    List<List<String>> ans = new ArrayList<>();
    f(input, 0, new ArrayList<>(), ans);
    return ans;
  }
  @EpiTestComparator
  public static BiPredicate<List<List<String>>, List<List<String>>> comp =
      (expected, result) -> {
    if (result == null) {
      return false;
    }
    expected.sort(new LexicographicalListComparator<>());
    result.sort(new LexicographicalListComparator<>());
    return expected.equals(result);
  };

  public static void main(String[] args) {
    System.exit(
        GenericTest
            .runFromAnnotations(args, "EnumeratePalindromicDecompositions.java",
                                new Object() {}.getClass().getEnclosingClass())
            .ordinal());
  }
}
