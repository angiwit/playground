package havefun.recursivewithmemo;

public class Regex1 {

    public boolean matches(String str, String pattern) {
        if (str == null || pattern == null) return false;
        return matchesCore(str, 0, pattern, 0);
    }

    private boolean matchesCore(String str, int is, String pattern, int ip) {
        if (is == str.length() && ip == pattern.length()) { // both used up.
            return true;
        } else if (ip == pattern.length()) { // pattern used up but string not.
            return false;
        }

        char curr = pattern.charAt(ip);
        char next = pattern.charAt(ip + 1);
        if (next == '*' && (str.charAt(is) == curr || curr == '.')) {
            return matchesCore(str, is, pattern, ip + 2) ||
                    matchesCore(str, is + 1, pattern, ip + 2) ||
                    matchesCore(str + 1, is, pattern, ip);
        } else if (str.charAt(is) == curr || curr == '.') {
            return matchesCore(str, is + 1, pattern, ip + 1);
        }
        return false;
    }
}
