package havefun.recursivewithmemo;

public class Regex {

    public static boolean matches(String target, String pattern, int it, int ip) {
        if (target == null || pattern == null) return false;
        char curr = pattern.charAt(ip);
        char next = pattern.charAt(ip + 1);
        if (next == '*' && (curr == target.charAt(it) || curr == '.')) {
            return matches(target, pattern, it, ip + 2)
                    || matches(target, pattern, it + 1, ip + 2)
                    || matches(target, pattern, it + 1, ip);
        } else {
            if (curr == target.charAt(it)) {
                return matches(target, pattern, it + 1, ip + 1);
            } else {
                return false;
            }
        }
    }
}
