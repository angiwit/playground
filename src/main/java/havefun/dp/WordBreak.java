package havefun.dp;

import java.util.*;

public class WordBreak {

    public static void main(String[] args) {
//        String s = "catsandog";
        String s = "leetcode";
        List<String> wordDict = new ArrayList<>();
//        wordDict.add("cats");
//        wordDict.add("dog");
//        wordDict.add("sand");
//        wordDict.add("and");
//        wordDict.add("cat");
        wordDict.add("leet");
        wordDict.add("code");
        System.out.println(wordBreak(s, wordDict));
    }

    public static boolean wordBreak(String s, List<String> wordDict) {
        if (wordDict == null) return false;
        Deque<String> result = new ArrayDeque<>();
        return wordBreakCore(s, wordDict, result);
    }

    public static boolean wordBreakCoreDp(String s, List<String> wordDict) {
        boolean[] dp = new boolean[s.length() + 1]; // s(0, i) if a word.
        Set<String> words = new HashSet<>(wordDict);
        dp[0] = true;
        for (int j = 1; j < s.length(); j++) {
            for (int i = 0; i < j; i++) {
                String subString = s.substring(i, j);
                if (dp[i] && words.contains(subString)) {
                    dp[j] = true;
                    break; // once found a true, break to avoid following iteration's overwriting.
                }
            }
        }
        return dp[s.length()];
    }

    // backtrace.
    public static boolean wordBreakCore(String s, List<String> wordDict, Deque<String> result) {
        Set<String> words = new HashSet<>(wordDict);
        for (int i = 0; i < s.length(); i++) {
            if (words.contains(s.substring(0, i)) && words.contains(s.substring(i))) {

            }
        }
        return false;
    }
}
