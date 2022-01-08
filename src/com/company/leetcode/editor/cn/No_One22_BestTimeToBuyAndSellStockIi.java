//给定一个数组 prices ，其中 prices[i] 是一支给定股票第 i 天的价格。
//
// 设计一个算法来计算你所能获取的最大利润。你可以尽可能地完成更多的交易（多次买卖一支股票）。 
//
// 注意：你不能同时参与多笔交易（你必须在再次购买前出售掉之前的股票）。 
//
// 
//
// 示例 1: 
//
// 
//输入: prices = [7,1,5,3,6,4]
//输出: 7
//解释: 在第 2 天（股票价格 = 1）的时候买入，在第 3 天（股票价格 = 5）的时候卖出, 这笔交易所能获得利润 = 5-1 = 4 。
//     随后，在第 4 天（股票价格 = 3）的时候买入，在第 5 天（股票价格 = 6）的时候卖出, 这笔交易所能获得利润 = 6-3 = 3 。
// 
//
// 示例 2: 
//
// 
//输入: prices = [1,2,3,4,5]
//输出: 4
//解释: 在第 1 天（股票价格 = 1）的时候买入，在第 5 天 （股票价格 = 5）的时候卖出, 这笔交易所能获得利润 = 5-1 = 4 。
//     注意你不能在第 1 天和第 2 天接连购买股票，之后再将它们卖出。因为这样属于同时参与了多笔交易，你必须在再次购买前出售掉之前的股票。
// 
//
// 示例 3: 
//
// 
//输入: prices = [7,6,4,3,1]
//输出: 0
//解释: 在这种情况下, 没有交易完成, 所以最大利润为 0。 
//
// 
//
// 提示： 
//
// 
// 1 <= prices.length <= 3 * 10⁴ 
// 0 <= prices[i] <= 10⁴ 
// 
// Related Topics 贪心 数组 动态规划 👍 1509 👎 0


package com.company.leetcode.editor.cn;

public class No_One22_BestTimeToBuyAndSellStockIi {
    public static void main(String[] args) {
        Solution solution = new No_One22_BestTimeToBuyAndSellStockIi().new Solution();
    }

    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        /**
         * dp[i][k][0] = max(dp[i-1][k][1] + prices[i],dp[i-1][k][0])
         * dp[i][k][1] = max(dp[i-1][k-1][0] - prices[i], dp[i-1][k][1])
         * 边界条件：dp[-1][...][0] = dp[...][0][0] = 0     dp[-1][...][1] = dp[...][0][1] = -infinity
         * <p>
         * k=infinity的特殊情况，相当于可以不用考虑k这个维度（但是当k>1还是需要考虑和遍历的）
         * dp[i][k][0] = max(dp[i-1][k][1] + prices[i],dp[i-1][k][0])
         * dp[i][k][1] = max(dp[i-1][k][0] - prices[i], dp[i-1][k][1]) 注意！因为k=infiniti k-1 = k ,dp[i-1][k-1][0] = dp[i-1][k][0] 根据边界情况 k-1=0时，dp = 0
         * 观察到dp变化与k无关了，即不存在k-1了，即
         * dp[i][0] = max(dp[i-1][1] + prices[i],dp[i-1][0])
         * dp[i][1] = max(dp[i-1][0] - prices[i], dp[i-1][1])
         * 边界条件 dp[-1][0] = 0 dp[-1][1] = -infinity
         */
        public int maxProfit1(int[] prices) {
            int n = prices.length;
            int[][] dp = new int[n][2];

            for (int i = 0; i < n; i++) {
                //边界条件 需要在循环里指定
                if (i - 1 == -1) {
                    //手动计算i = 0
                    dp[i][0] = 0;
                    dp[i][1] = -prices[i];
                    continue;
                }
                dp[i][0] = Math.max(dp[i - 1][1] + prices[i], dp[i - 1][0]);
                dp[i][1] = Math.max(dp[i - 1][0] - prices[i], dp[i - 1][1]);
            }
            return dp[n - 1][0];
        }

        //状态压缩
        public int maxProfit(int[] prices) {
            int n = prices.length;
            int dp_0 = 0;
            int dp_1 = 0;
            for (int i = 0; i < n; i++) {
                //边界条件 需要在循环里指定
                if (i - 1 == -1) {
                    //手动计算i = 0
                    dp_0 = 0;
                    dp_1 = -prices[i];
                    continue;
                }
                dp_0 = Math.max(dp_1 + prices[i], dp_0);
                dp_1 = Math.max(dp_0 - prices[i], dp_1);
            }
            return dp_0;
        }
    }
//leetcode submit region end(Prohibit modification and deletion)

}