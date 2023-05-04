package havefun.math;
// https://leetcode.cn/problems/excel-sheet-column-title/
public class ConvertToTitle {

    /**
     * Number to char problem, we need to think that the number should be processed from low bit to high bit, and to process this,
     * we need 进制转换. This seems a 26 进制转换 problem, so we can use while loop to check if the number is bigger than 0, if true,
     * we get the mode to 26 of the number and plus char 'A' we can get the correct letter. And we need to reverse the string because
     * we're calculating from low bit to high bit.
     * 
     * But another issue is we need to let the number - 1 before we get the mode value, because the 进制 is not from 0. E.g. 26 % 26 = 0,
     * but we don't have mapping for 0. So we can minus 1 before mode, (26 - 1) % 26 = 25, 25 + 'A' = 'Z'. No need to consider 0 case 
     * because we don't process 0.
     * 
     * Can we use plus 1 to process this? (26 + 1) % 26 = 1, 1 + 'Y' = 'Z'., (1 + 1) % 26 = 2, 2 + 'Y' = '[' which exceed the number range.
     * So we can't use plus 1, the basic here is we have to use 'A' to make sure the produced char is within the correct range.
     * @param columnNumber
     * @return
     */
    public static String convertToTitle(int columnNumber) {
        StringBuilder sb = new StringBuilder();
        while (columnNumber > 0) {
            columnNumber--;
            sb.append((char) columnNumber % 26 + 'A');
            columnNumber /= 26;
        }
        return sb.reverse().toString();
    }

    public static void main(String[] args) {
        System.out.println(convertToTitle(1));
    }
}
