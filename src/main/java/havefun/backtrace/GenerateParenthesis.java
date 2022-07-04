package havefun.backtrace;

import java.util.ArrayList;
import java.util.List;

public class GenerateParenthesis {

    public static List<String> generateParenthesis(int n) {
        List<String> result = new ArrayList<>();
        StringBuilder sb = new StringBuilder();
        generateParenthesisCore(n, 0, 0, result, sb);
        return result;
    }

    public static void generateParenthesisCore(int n, int open, int close, List<String> result, StringBuilder once) {
        // exit condition
        if (once.length() >= n * 2) {
            result.add(once.toString());
            return;
        }
        // once logic
        if (open < n) {
            once.append("(");
            generateParenthesisCore(n, open + 1, close, result, once);
            once.deleteCharAt(once.length() - 1);
        }
        if (close < open) {
            once.append(")");
            generateParenthesisCore(n, open, close + 1, result, once);
            once.deleteCharAt(once.length() - 1);
        }
    }


}
