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
        if (start >= s.length() && path.size() == 4) {
            res.add(String.join(".", path));
            return;
        }
        if (path.size() >= 4) {
            return;
        }
        for (int i = start + 1; i < start + 4; i++) {
            String first = s.substring(start, i);
            if (first.length() > 3) {
                break;
            }
            if (s.length() - start < 4 - path.size()) {
                break;
            }
            if (s.length() - start - 1 > 3 * (4 - path.size())) {
                break;
            }
            if (isValidIpAddress(first)) {
                path.push(first);
            } else {
                break; // NOT continue since current is not valid, adding numbers won't make it valid.
            }
            restoreIpAddressesCore(s, start + first.length(), res, path);
            path.pop();
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
