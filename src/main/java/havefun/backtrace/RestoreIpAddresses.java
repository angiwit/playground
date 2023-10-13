package havefun.backtrace;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

public class RestoreIpAddresses {

    public static void main(String[] args) {
        String s = "0000";
        List<String> result = restoreIpAddresses(s);
        result.stream().forEach(System.out::println);
    }

    public static List<String> restoreIpAddresses(String s) {
        if (s == null || s.length() < 4 || s.length() > 12) return null;
        List<String> res = new ArrayList<>();
        restoreIpAddressesCore(s, 0, res, new Stack<>());
        return res;
    }

    public static void restoreIpAddressesCore(String s, int start, List<String> res, Stack<String> path) {
        if (path.size() < 4 && start == s.length() - 1) return;
        if (path.size() == 4 && start < s.length() - 1) return;
        if (path.size() == 4 && start == s.length() - 1) {
            res.add(String.join(".", path));
            return;
        }
        for (int i = start; i < 3; i++) {
            String segment = s.substring(start, i + 1);
            if (segment.length() > 1 && segment.startsWith("0")) {
                break;
            }
            if (isValidIpAddress(segment)) {
                if (s.length() - segment.length() > 3 * (4 - path.size())) {
                    break;
                }
                path.push(segment);
                restoreIpAddressesCore(s, i + 1, res, path);
                path.pop();
            } else {
                break;
            }
        }
    }

    private static boolean isValidIpAddress(String first) {
        if (first.length() > 1 && first.startsWith("0")) {
            return false;
        }
        int fv = Integer.parseInt(first);
        if (fv >= 0 && fv <= 255) {
            return true;
        }
        return false;
    }
}
