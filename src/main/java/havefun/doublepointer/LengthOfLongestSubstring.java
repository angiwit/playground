package havefun.doublepointer;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * https://leetcode.cn/problems/longest-substring-without-repeating-characters/
 */
public class LengthOfLongestSubstring {

    public static void main(String[] args) {
        System.out.println(lengthOfLongestSubstring("dvdf"));
    }

    // official answer
    public static int lengthOfLongestSubstring1(String s) {
        Set<Character> chars = new HashSet<>();
        int max = 0, rightIndex = 0;
        for (int i = 0; i < s.length(); i++) {
            if (i != 0) {
                chars.remove(s.charAt(i - 1));
            }
            while (rightIndex < s.length() && !chars.contains(s.charAt(rightIndex))) {
                chars.add(s.charAt(rightIndex));
                rightIndex++;
            }
            max = Math.max(max, rightIndex - i + 1);
        }
        return max;
    }

    //optimized
    public static int lengthOfLongestSubstring(String s) {
        if (s == null || s.length() == 0) return 0;
        int max = 1, leftIndex = 0;
        Map<Character, Integer> positions = new HashMap<>();
        for (int i = 0; i < s.length(); i++) {
            if (positions.containsKey(s.charAt(i))) {
                leftIndex = Math.max(leftIndex, positions.get(s.charAt(i)) + 1);
            }
            positions.put(s.charAt(i), i); // always update the lastest index to map.
            max = Math.max(max, i - leftIndex + 1);
        }
        return max;
    }

}
