package havefun.backtrace;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class LetterCombinations {

    public List<String> letterCombinations(String digits) {
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
        backtrack(combinations, phoneMap, digits, 0, new StringBuffer());
        return combinations;
    }

    public void backtrack(List<String> combinations, Map<Character, String> phoneMap, String digits, int index, StringBuffer combination) {
        if (index == digits.length()) {
            combinations.add(combination.toString());
        } else {
            for (int i = index; i < digits.length(); i++) {
                char digit = digits.charAt(index);
                String letters = phoneMap.get(digit);
                int lettersCount = letters.length();
                for (int j = 0; j < lettersCount; j++) {
                    combination.append(letters.charAt(j));
                    backtrack(combinations, phoneMap, digits, i + 1, combination);
                    combination.deleteCharAt(index);
                }
            }
        }
    }
}
