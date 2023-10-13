package havefun.array;


/**
 * https://leetcode-cn.com/problems/multiply-strings/
 */
public class Multiply {

    public static void main(String[] args) {
        System.out.println(multiply("9", "99"));
    }

    public static String multiply(String num1, String num2) {
        if ("0".equals(num1) || "0".equals(num2)) return "0";
        return multiplyCore(num1, num2);
    }

    public static String multiplyCore(String num1, String num2) {
        String result = "0";
        int carry = 0;
        for (int j = num2.length() - 1; j >= 0; j--) {
            int c = num2.charAt(j) - '0';
            StringBuilder temp = new StringBuilder();
            for (int i = j + 1; i < num2.length(); i++) {
                temp.append("0");
            }
            for (int i = num1.length() - 1; i >= 0; i--) {
                int product = c * (num1.charAt(i) - '0') + carry;
                temp.append(product % 10);
                carry = product / 10;
            }
            if (carry != 0) {
                temp.append(carry);
                carry = 0;
            }
            result = addString(result, temp.toString());
        }
        return new StringBuilder(result).reverse().toString();
    }

    private static String addString(String s1, String s2) {
        int carry = 0, index = 0;
        StringBuilder result = new StringBuilder();
        while (index < s1.length() || index < s2.length()) {
            int c1 = index >= s1.length() ? 0 : s1.charAt(index) - '0';
            int c2 = index >= s2.length() ? 0 : s2.charAt(index) - '0';
            int product = c1 + c2 + carry;
            result.append(product % 10);
            carry = product / 10;
            index++;
        }
        if (carry != 0) {
            result.append(carry);
        }
        return result.toString();
    }
}
