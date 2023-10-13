package havefun.recursivewithmemo;

public class Regex1 {

    public static boolean matches(String str, String pattern) {
        if (str == null || pattern == null) return false;
        return matchesCore(str, 0, pattern, 0);
    }

    private static boolean matchesCore(String str, int is, String pattern, int ip) {
        if (is == str.length() && ip == pattern.length()) {
            return true;
        }
        if (ip >= pattern.length()) return false;
        if (ip == pattern.length() - 1) {
            if (is >= str.length()) return false;
            else return (str.charAt(is) == pattern.charAt(ip) || pattern.charAt(ip) == '.')
                    && matchesCore(str, is + 1, pattern, ip + 1);
        }
        char curr = pattern.charAt(ip);
        char next = pattern.charAt(ip + 1);
        if ('*' == next) {
            if (is < str.length() && (curr == str.charAt(is) || curr == '.')) {
                return matchesCore(str, is + 1, pattern, ip)
                        || matchesCore(str, is + 1, pattern, ip + 2)
                        || matchesCore(str, is, pattern, ip + 2);
            } else {
                return matchesCore(str, is, pattern, ip + 2);
            }
        } else {
            if (is >= str.length()) return false;
            if (curr == '.' || curr == str.charAt(is)) {
                return matchesCore(str, is + 1, pattern, ip + 1);
            } else {
                return false;
            }
        }
    }

    public static void main(String[] args) {
        boolean result = matches("aab", "c*a*b");
        System.out.println(result);
    }
}
