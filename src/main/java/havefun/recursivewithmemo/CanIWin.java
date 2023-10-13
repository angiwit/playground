package havefun.recursivewithmemo;

/**
 * https://leetcode.com/problems/can-i-win/
 */
public class CanIWin {

    /**
     * for each number, you can choose or not choose it, so there's 2^x possibilities in total.
     * <p>
     * 这道题目中使用的状态和另外一道题目: https://leetcode.cn/problems/partition-to-k-equal-sum-subsets/ 使用了类似的状态压缩。
     * 状态压缩简单来说就是一个int值，这个int值最大可以为1 << size. 代表针对每个数字，都有两种不同的状态，所以总状态数为2 ^ size种。
     * 这里使用了int[]并且将改数组大小设置为1 << size, 在另外一道题中使用了Map，key为integer，value为boolean，他们本质上是一样的。
     * 注意这里的每个state并不代表某一位数字，而是代表了在0-1<<size 范围内的唯一一个数字对应的结果！！！
     *
     * @param desiredTotal
     * @return
     */
    public boolean canIWin(int maxChoosableInteger, int desiredTotal) {
        if (desiredTotal <= 0) return true; // if given a negative number,we can choose nothing to win this game.
        if ((maxChoosableInteger * (maxChoosableInteger + 1)) / 2 < desiredTotal) return false;
        //boolean[] memo = new boolean[1 << maxChoosableInteger]; // can NOT use boolean since boolean has default false value.
        int[] memo = new int[1 << maxChoosableInteger];
        return canIWinCore(maxChoosableInteger, desiredTotal, memo, 0);
    }

    public boolean canIWinCore(int maxChoosableInteger, int desiredTotal, int[] memo, int state) {
        if (desiredTotal <= 0)
            return false; // if in a round, the number is negative, it means the last round the opponent has won this game.
        if (memo[state] != 0) return memo[state] == 1;
        for (int i = 1; i <= maxChoosableInteger; i++) {
            if (memo[state & (1 << i)] != 0) continue;
            if (i >= desiredTotal) {
                memo[state | (1 << i)] = 1;
                break;
            }
            // state | (1 << i) means to add current num state to the full state collection.
            // current state is false when running to here. If opponent can't win this game,
            // it means for current state, we can win.
            boolean result = canIWinCore(maxChoosableInteger, desiredTotal - i, memo, state | (1 << i));
            if (!result) {
                memo[state] = 1;
            }
        }
        memo[state] = -1;
        return false;
    }

}
