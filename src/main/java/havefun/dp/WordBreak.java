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
        Set<String> wordsSet = new HashSet<>(wordDict);
        boolean[] dp = new boolean[s.length() + 1];
        dp[0] = true;
        for (int i = 1; i <= s.length(); i++) {
            for (int j = 0; j < i; j++) {
                if (dp[j] && wordsSet.contains(s.substring(j, i))) {
                    dp[i] = true;
                    break;
                }
            }
        }
        return dp[s.length()];
    }

    // exceeds time limit.
    public static boolean wordBreakCore(String s, List<String> wordDict, Deque<String> result) {
        for (int i = 0; i < wordDict.size(); i++) {
            result.addLast(wordDict.get(i));
            String temp = String.join("", result);
            if (temp.length() > s.length()) {
                result.removeLast();
                continue;
            }
            if (temp.equals(s)) {
                return true;
            }
            if (!s.startsWith(temp)) {
                result.removeLast();
                continue;
            }
            boolean r = wordBreakCore(s, wordDict, result);
            if (r == true) {
                return true;
            } else {
                result.removeLast();
            }
        }
        return false;
    }
}
