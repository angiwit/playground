package havefun.string;

import java.util.HashSet;
import java.util.Set;

public class PartitionStrings {

    public static void main(String[] args) {
        System.out.println(solution("dddd"));
    }


    public static int solution(String S) {
        // write your code in Java SE 8
        if (S == null || S.length() == 0) return 0;
        Set<Character> set = new HashSet<>();
        int result = 0;
        for (int i = 0; i < S.length(); i++) {
            if (!set.contains(S.charAt(i))) {
                set.add(S.charAt(i));
            } else {
                // found a non-repeat string, result++
                result++;
                set.clear();
                set.add(S.charAt(i));
            }
        }
        return result + 1;
    }
}
