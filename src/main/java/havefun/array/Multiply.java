package havefun.array;

/**
 * https://leetcode-cn.com/problems/multiply-strings/
 */
public class Multiply {

    public static void main(String[] args) {
        System.out.println(multiply("255", "22"));
    }

    public static String multiply(String num1, String num2) {
        return multiplyCore(num1, num2);
    }

    public static String multiplyCore(String num1, String num2) {
        String result = "0";
        for (int i = num1.length() - 1; i >= 0; i--) {
            StringBuilder temp = new StringBuilder();
            for (int k = i + 1; k < num2.length(); k++) {
                temp.append("0");
            }
            int carry = 0;
            int num1Cur = num1.charAt(i) - '0';
            for (int j = num2.length() - 1; j >= 0; j--) {
                int num2Cur = num2.charAt(j) - '0';
                int product = num1Cur * num2Cur + carry;
                temp.append(product % 10);
                carry = product / 10;
            }
            if (carry != 0) {
                temp.append(carry);
            }
            result = addString(result, temp.reverse().toString());
        }
        return result;
    }

    private static String addString(String result, String temp) {
        int i = result.length(), j = temp.length(), carry = 0;
        StringBuilder output = new StringBuilder();
        while (i >= 0 || j >= 0 || carry > 0) {
            int x = i >= 0 ? result.charAt(i) - '0' : 0;
            int y = j >= 0 ? temp.charAt(j) - '0' : 0;
            int product = x + y + carry;
            output.append(product % 10);
            carry = product / 10;
            i--;
            j--;
        }
        if (carry != 0) {
            output.append(carry);
        }
        return output.reverse().toString();
    }
}
