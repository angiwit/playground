package havefun.backtrace;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * https://leetcode-cn.com/problems/letter-combinations-of-a-phone-number/
 */
public class LetterCombinations {

    public static void main(String[] args) {
        String digits = "23";
        System.out.println(letterCombinations(digits));
    }

    public static List<String> letterCombinations(String digits) {
        List<String> combinations = new ArrayList<String>();
        if (digits.length() == 0) {
            return combinations;
        }
        Map<Character, String> phoneMap = new HashMap<Character, String>() {{
            put('2', "abc");
            put('3', "def");
            put('4', "ghi");
            put('5', "jkl");
            put('6', "mno");
            put('7', "pqrs");
            put('8', "tuv");
            put('9', "wxyz");
        }};
        letterCombinationsCore(phoneMap, combinations, new StringBuilder(), digits, 0);
        return combinations;
    }

    /**
     * Approach 1: iterate every key to combine with following keys, for the last key, it can only
     * combine itself, so the length is not matching, so adding the length check in if condition.
     *
     * @param phoneMap
     * @param result
     * @param temp
     * @param digits
     * @param start
     */
    public static void letterCombinationsCore(Map<Character, String> phoneMap,
                                              List<String> result, StringBuilder temp,
                                              String digits, int start) {
        if (start >= digits.length() && temp.length() == digits.length()) {
            result.add(temp.toString());
            return;
        }

        for (int i = start; i < digits.length(); i++) {
            String key = phoneMap.get(digits.charAt(i));
            for (int j = 0; j < key.length(); j++) {
                temp.append(key.charAt(j));
                letterCombinationsCore(phoneMap, result, temp, digits, i + 1);
                temp.deleteCharAt(temp.length() - 1);
            }
        }
    }

    /**
     * Use start to represent the key index, and from the first key we're combining all the keys together,
     * so every key will be used in the backtrace, every time the start greater or equals to digits.length,
     * it means a combination with all keys found.
     *
     * @param phoneMap
     * @param result
     * @param temp
     * @param digits
     * @param start
     */
    public static void letterCombinationsCoreOptimized(Map<Character, String> phoneMap,
                                                       List<String> result, StringBuilder temp,
                                                       String digits, int start) {
        if (start >= digits.length()) {
            result.add(temp.toString());
            return;
        }

        String key = phoneMap.get(digits.charAt(start));
        for (int j = 0; j < key.length(); j++) {
            temp.append(key.charAt(j));
            letterCombinationsCoreOptimized(phoneMap, result, temp, digits, start + 1);
            temp.deleteCharAt(temp.length() - 1);
        }
    }
}
