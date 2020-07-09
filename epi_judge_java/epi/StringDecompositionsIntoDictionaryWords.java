package epi;
import epi.test_framework.EpiTest;
import epi.test_framework.GenericTest;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class StringDecompositionsIntoDictionaryWords {

//  public static void rebuild(Map<String, Integer> frq, String s, int sIdx, int eIdx, int wSize){
//    while(eIdx > sIdx){
//      String word = s.substring(eIdx - wSize, eIdx);
//      frq.putIfAbsent(word, 0);
//      frq.put(word, frq.get(word) + 1);
//      eIdx -= wSize;
//    }
//  }
//
//  @EpiTest(testDataFile = "string_decompositions_into_dictionary_words.tsv")
//  public static List<Integer> findAllSubstrings(String s, List<String> words) {
//    List<Integer> ans = new ArrayList<>();
//    Map<String, Integer> frq = new HashMap<>();
//    for(String w : words){
//      frq.putIfAbsent(w, 0);
//      frq.put(w, frq.get(w) + 1);
//    }
//
//    int wCnt = words.get(0).length();
//    int sIdx = 0;
//
//    for(int i = sIdx; i + wCnt <= s.length(); i++){
//      String word = s.substring(i, i + wCnt);
//      if(frq.containsKey(word)){
//        if(frq.get(word) == 1) {
//          frq.remove(word);
//        } else {
//          frq.put(word, frq.get(word) - 1);
//        }
//
//        if(frq.isEmpty()){
//          ans.add(sIdx);
//          rebuild(frq, s, sIdx, i + wCnt, wCnt);
//          sIdx = sIdx + 1;
//          i = sIdx - 1;
//        } else {
//          i = i + wCnt - 1;
//        }
//      } else {
//        if(i != sIdx){
//          rebuild(frq, s, sIdx, i, wCnt);
//          i = sIdx;
//        }
//        sIdx = i + 1;
//      }
//    }
//
//    return ans;
//  }

//  public static Map<String, Integer> buildFrq(List<String> words){
//    Map<String, Integer> frq = new HashMap<>();
//    for(String w : words){
//      frq.putIfAbsent(w, 0);
//      frq.put(w, frq.get(w) + 1);
//    }
//    return frq;
//  }
//
//  @EpiTest(testDataFile = "string_decompositions_into_dictionary_words.tsv")
//  public static List<Integer> findAllSubstrings(String s, List<String> words) {
//    List<Integer> ans = new ArrayList<>();
//    Map<String, Integer> frq = buildFrq(words);
//
//    int wCnt = words.get(0).length();
//    int sIdx = 0;
//
//    for(int i = sIdx; i + wCnt <= s.length(); i++){
//      String word = s.substring(i, i + wCnt);
//      if(frq.containsKey(word)){
//        if(frq.get(word) == 1) {
//          frq.remove(word);
//        } else {
//          frq.put(word, frq.get(word) - 1);
//        }
//
//        i = i + wCnt - 1;
//        if(frq.isEmpty()){
//          ans.add(sIdx);
//          sIdx = sIdx + 1;
//          i = sIdx - 1;
//          frq = buildFrq(words);
//        }
//      } else {
//        if(i != sIdx){
//          frq = buildFrq(words);
//          i = sIdx;
//        }
//        sIdx = i + 1;
//      }
//    }
//
//    return ans;
//  }

  public static void increment(String word, Map<String, Integer> frq){
    Integer count = frq.get(word);
    if(count == null)
      count = 0;

    frq.put(word, count + 1);
  }

  public static boolean findWords(String s, int start, int unitSize, int numWords, Map<String, Integer> frq){
    Map<String, Integer> wFrq = new HashMap<>();

    for(int i = start; i + unitSize <= start + numWords * unitSize; i += unitSize){
      String word = s.substring(i, i + unitSize);
      if(!frq.containsKey(word))
        return false;

      increment(word, wFrq);
      if(wFrq.get(word) > frq.get(word))
        return false;
    }

    return true;
  }

  @EpiTest(testDataFile = "string_decompositions_into_dictionary_words.tsv")
  public static List<Integer> findAllSubstrings(String s, List<String> words) {
    List<Integer> ans = new ArrayList<>();
    Map<String, Integer> frq = new HashMap<>();
    for(String word : words){
      increment(word, frq);
    }

    int unitSize = words.get(0).length();
    for(int i = 0; i + unitSize * words.size() <= s.length(); i++){
      String word = s.substring(i, i + unitSize);
      if(frq.containsKey(word) && findWords(s, i, unitSize, words.size(), frq)){
        ans.add(i);
      }
    }
    return ans;
  }

  public static void main(String[] args) {
    System.exit(GenericTest
                    .runFromAnnotations(
                        args, "StringDecompositionsIntoDictionaryWords.java",
                        new Object() {}.getClass().getEnclosingClass())
                    .ordinal());
  }
}
