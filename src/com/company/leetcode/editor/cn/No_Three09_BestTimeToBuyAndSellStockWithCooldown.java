//给定一个整数数组，其中第 i 个元素代表了第 i 天的股票价格 。
//
// 设计一个算法计算出最大利润。在满足以下约束条件下，你可以尽可能地完成更多的交易（多次买卖一支股票）: 
//
// 
// 你不能同时参与多笔交易（你必须在再次购买前出售掉之前的股票）。 
// 卖出股票后，你无法在第二天买入股票 (即冷冻期为 1 天)。 
// 
//
// 示例: 
//
// 输入: [1,2,3,0,2]
//输出: 3 
//解释: 对应的交易状态为: [买入, 卖出, 冷冻期, 买入, 卖出] 
// Related Topics 数组 动态规划 👍 1013 👎 0


package com.company.leetcode.editor.cn;

public class No_Three09_BestTimeToBuyAndSellStockWithCooldown {
    public static void main(String[] args) {
        Solution solution = new No_Three09_BestTimeToBuyAndSellStockWithCooldown().new Solution();
    }

    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        /**
         * dp[i][k][0] = max(dp[i-1][k][1] + prices[i],dp[i-1][k][0])
         * dp[i][k][1] = max(dp[i-1][k-1][0] - prices[i], dp[i-1][k][1])
         * 边界条件：dp[-1][...][0] = dp[...][0][0] = 0     dp[-1][...][1] = dp[...][0][1] = -infinity
         * <p>
         * k=infinity + 冷冻期的特殊情况：状态转移不需要考虑k，且 选择 “买”时，要从2天前的状态转移到当前（多一天冷冻期）
         * dp[i][k][0] = max(dp[i-1][k][1] + prices[i],dp[i-1][k][0])
         * dp[i][k][1] = max(dp[i-2][k][0] - prices[i], dp[i-1][k][1])  即当前要选择买，需要从i-2天的状态转移过来。且因为k = infinity ，所以k-1 = k
         * 即去除k的影响
         * dp[i][0] = max(dp[i-1][1] + prices[i],dp[i-1][0])
         * dp[i][1] = max(dp[i-2][0] - prices[i], dp[i-1][1])
         * 边界条件 dp[-1][0] = 0 dp[-2][0] = 0 dp[-1][1] = -infinity
         */
        public int maxProfit(int[] prices) {
            int n = prices.length;
            int[][] dp = new int[n][2];
            //
            for (int i = 0; i < n; i++) {
                //手动处理i-1 = -1 或 i-2 =-1的情况
                if (i - 1 == -1) {
                    dp[i][0] = 0;
                    dp[i][1] = -prices[i];
                    continue;
                }
                if (i - 2 == -1) {
                    dp[i][0] = Math.max(dp[i - 1][1] + prices[i], dp[i - 1][0]);
                    dp[i][1] = Math.max(0 - prices[i], dp[i - 1][1]);//由于dp[i-2][0] 是 0，但超出数组范围，需要手动计算
                    continue;
                }
                dp[i][0] = Math.max(dp[i - 1][1] + prices[i], dp[i - 1][0]);
                dp[i][1] = Math.max(dp[i - 2][0] - prices[i], dp[i - 1][1]);
            }
            return dp[n - 1][0];
        }
    }
//leetcode submit region end(Prohibit modification and deletion)

}