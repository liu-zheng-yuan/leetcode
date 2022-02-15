//给你一个整数数组 coins 表示不同面额的硬币，另给一个整数 amount 表示总金额。
//
// 请你计算并返回可以凑成总金额的硬币组合数。如果任何硬币组合都无法凑出总金额，返回 0 。 
//
// 假设每一种面额的硬币有无限个。 
//
// 题目数据保证结果符合 32 位带符号整数。 
//
// 
//
// 
// 
//
// 示例 1： 
//
// 
//输入：amount = 5, coins = [1, 2, 5]
//输出：4
//解释：有四种方式可以凑成总金额：
//5=5
//5=2+2+1
//5=2+1+1+1
//5=1+1+1+1+1
// 
//
// 示例 2： 
//
// 
//输入：amount = 3, coins = [2]
//输出：0
//解释：只用面额 2 的硬币不能凑成总金额 3 。
// 
//
// 示例 3： 
//
// 
//输入：amount = 10, coins = [10] 
//输出：1
// 
//
// 
//
// 提示： 
//
// 
// 1 <= coins.length <= 300 
// 1 <= coins[i] <= 5000 
// coins 中的所有值 互不相同 
// 0 <= amount <= 5000 
// 
// Related Topics 数组 动态规划 👍 717 👎 0


package com.company.leetcode.editor.cn;

public class No_Five18_CoinChange2 {
    public static void main(String[] args) {
        Solution solution = new No_Five18_CoinChange2().new Solution();
    }

    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        //前i个物品，恰好凑成j的面额，有多少种拼法
        public int change1(int amount, int[] coins) {
            int[][] dp = new int[coins.length + 1][amount + 1];
            //j = 0，边界case 为1，能拼成
            for (int i = 0; i <= coins.length; i++) {
                dp[i][0] = 1;
            }
            //
            for (int i = 1; i <= coins.length; i++) {
                for (int j = 1; j <= amount; j++) {
                    if (j >= coins[i - 1]) {
                        //可以 选当前coin（即i-1个，因为dp从1开始计数） || 不选
                        //完全背包问题：可以一直选一个，所以不用从i-1转移到i，而是不断的从i转移到i。
                        //可以理解为：如果确定“要选当前coin，则就”一直用“当前的coin去凑就完事了
                        dp[i][j] = dp[i - 1][j] + dp[i][j - coins[i - 1]];
                    } else {
                        dp[i][j] = dp[i - 1][j];
                    }
                }
            }
            return dp[coins.length][amount];
        }

        public int change(int amount, int[] coins) {
            int[] dp = new int[amount + 1];
            //j = 0，边界case 为1，能拼成
            dp[0] = 1;
            //
            for (int i = 1; i <= coins.length; i++) {
                for (int j = 1; j <= amount; j++) {
                    if (j >= coins[i - 1]) {
                        //可以 选当前coin（即i-1个，因为dp从1开始计数） || 不选
                        //完全背包问题：可以一直选一个，所以不用从i-1转移到i，而是不断的从i转移到i。
                        //可以理解为：如果确定“要选当前coin，则就”一直用“当前的coin去凑就完事了

                        //[i - 1][j] 是上一轮的j结果，就是未覆盖之前的dp[j]
                        //[i][j - coins[i - 1]] 是本轮的[j - coins[i - 1]]，需要在[j]之前就已经计算得到，所以正常从后向前遍历即可
                        dp[j] = dp[j] + dp[j - coins[i - 1]];
                    } else {
                        dp[j] = dp[j];
                    }
                }
            }
            return dp[amount];
        }
    }
//leetcode submit region end(Prohibit modification and deletion)

}