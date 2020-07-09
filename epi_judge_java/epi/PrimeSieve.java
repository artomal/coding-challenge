package epi;
import epi.test_framework.EpiTest;
import epi.test_framework.GenericTest;

import java.util.ArrayList;
import java.util.List;
public class PrimeSieve {


  public static boolean isPrime(int num, List<Integer> primes){
    for(int i = 0; i < primes.size() / 2; i++){
      if(num % primes.get(i) == 0)
        return false;
    }
    return true;
  }

  @EpiTest(testDataFile = "prime_sieve.tsv")
  // Given n, return all primes up to and including n.
  public static List<Integer> generatePrimes(int n) {
    List<Integer> res = new ArrayList<>();

    for(int i = 2; i <= n; i++){
      if(isPrime(i, res))
        res.add(i);
    }
    return res;
  }

  public static void main(String[] args) {
    System.exit(
        GenericTest
            .runFromAnnotations(args, "PrimeSieve.java",
                                new Object() {}.getClass().getEnclosingClass())
            .ordinal());
  }
}
