package havefun.monotonestack;

import java.util.Stack;

public class DailyTemperatures {

    public static int[] dailyTemperatures(int[] temperatures) {
        if (temperatures == null || temperatures.length == 0) return new int[0];
        return dailyTemperaturesCoreFor(temperatures);
    }

    public static int[] dailyTemperaturesCoreFor(int[] temperatures) {
        int[] result = new int[temperatures.length];
        Stack<Integer> stack = new Stack<>();
        for (int i = 0; i < temperatures.length; i++) {
            while (stack.size() > 0 && temperatures[i] > temperatures[stack.peek()]) {
                int preIndex = stack.pop();
                result[preIndex] = i - preIndex;
            }
            stack.push(i);
        }
        while (stack.size() > 0) {
            result[stack.pop()] = 0;
        }
        return result;
    }

    public static int[] dailyTemperaturesCore(int[] temperatures) {
        int[] result = new int[temperatures.length];
        Stack<Integer> stack = new Stack<>();
        int i = 1;
        stack.push(0);
        while (i < temperatures.length || stack.size() > 0) {
            while (i < temperatures.length && stack.size() > 0 && temperatures[i] > temperatures[stack.peek()]) {
                int index = stack.pop();
                result[index] = i - index;
            }
            stack.push(i++);
        }
        while (stack.size() > 0) {
            int index = stack.pop();
            result[index] = 0;
        }
        return result;
    }


    public static void main(String[] args) {
        int[] temperatures = {73, 74, 75, 71, 69, 72, 76, 73};
        int[] result = dailyTemperatures(temperatures);
        for (int i = 0; i < result.length; i++) {
            System.out.print(result[i] + " ");
        }
    }
}
