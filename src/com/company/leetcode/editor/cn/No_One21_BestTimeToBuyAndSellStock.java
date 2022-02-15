//给定一个数组 prices ，它的第 i 个元素 prices[i] 表示一支给定股票第 i 天的价格。
//
// 你只能选择 某一天 买入这只股票，并选择在 未来的某一个不同的日子 卖出该股票。设计一个算法来计算你所能获取的最大利润。 
//
// 返回你可以从这笔交易中获取的最大利润。如果你不能获取任何利润，返回 0 。 
//
// 
//
// 示例 1： 
//
// 
//输入：[7,1,5,3,6,4]
//输出：5
//解释：在第 2 天（股票价格 = 1）的时候买入，在第 5 天（股票价格 = 6）的时候卖出，最大利润 = 6-1 = 5 。
//     注意利润不能是 7-1 = 6, 因为卖出价格需要大于买入价格；同时，你不能在买入前卖出股票。
// 
//
// 示例 2： 
//
// 
//输入：prices = [7,6,4,3,1]
//输出：0
//解释：在这种情况下, 没有交易完成, 所以最大利润为 0。
// 
//
// 
//
// 提示： 
//
// 
// 1 <= prices.length <= 10⁵ 
// 0 <= prices[i] <= 10⁴ 
// 
// Related Topics 数组 动态规划 👍 2031 👎 0


package com.company.leetcode.editor.cn;

public class No_One21_BestTimeToBuyAndSellStock {
    public static void main(String[] args) {
        Solution solution = new No_One21_BestTimeToBuyAndSellStock().new Solution();
    }

    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        /**
         * dp[i][k][0] = max(dp[i-1][k][1] + prices[i],dp[i-1][k][0])
         * dp[i][k][1] = max(dp[i-1][k-1][0] - prices[i], dp[i-1][k][1])
         * 边界条件：dp[-1][...][0] = dp[...][0][0] = 0     dp[-1][...][1] = dp[...][0][1] = -infinity
         * <p>
         * k=1的特殊情况，相当于可以不用考虑k这个维度（但是当k>1还是需要考虑和遍历的）
         * dp[i][0] = max(dp[i-1][1] + prices[i],dp[i-1][0])
         * dp[i][1] = max(0 - prices[i], dp[i-1][1]) 注意！因为dp[i-1][k-1][0] = dp[i-1][0][0] 根据边界情况 k-1=0时，dp = 0
         * <p>
         * dp[-1][0] = 0 dp[-1][1] = -infinity
         */
        public int maxProfit1(int[] prices) {
            int n = prices.length;
            int[][] dp = new int[n][2];
            //
            for (int i = 0; i < n; i++) {
                if (i - 1 == -1) {
                    //i = 0的特殊情况，依赖i = -1
                    //dp[-1][0] = 0 dp[-1][1] = -infinity
                    //dp[i][0] = max(dp[-1][1] + prices[i],dp[-1][0]) = 0
                    //dp[i][1] = max(dp[-1][0] - prices[i], dp[-1][1]) = -prices[i]
                    dp[i][0] = 0;
                    dp[i][1] = -prices[i];
                    continue;
                }
                dp[i][0] = Math.max(dp[i - 1][1] + prices[i], dp[i - 1][0]);
                dp[i][1] = Math.max(-prices[i], dp[i - 1][1]);//k-1 = 0时，dp[i-1][k-1][0] = 0，所以这里直接就是-prices[i]
            }
            return dp[n - 1][0];
        }

        //状态压缩
        public int maxProfit2(int[] prices) {
            int n = prices.length;
            //
            int dp_0 = 0;//保留上一轮i循环里，rest=0，即没有持股情况下的dp值
            int dp_1 = -prices[0];//保留上一轮i循环里，rest=1，即持股情况下的dp值
            for (int i = 0; i < n; i++) {
                if (i - 1 == -1) {
                    //i = 0的特殊情况，依赖i = -1
                    //dp[-1][0] = 0 dp[-1][1] = -infinity
                    //dp[i][0] = max(dp[-1][1] + prices[i],dp[-1][0]) = 0
                    //dp[i][1] = max(dp[-1][0] - prices[i], dp[-1][1]) = -prices[i]
                    dp_0 = 0;
                    dp_1 = -prices[i];
                    continue;
                }
                dp_0 = Math.max(dp_1 + prices[i], dp_0);
                dp_1 = Math.max(-prices[i], dp_1);//k-1 = 0时，dp[i-1][k-1][0] = 0，所以这里直接就是-prices[i]
            }
            return dp_0;
        }

        public int maxProfit(int[] prices) {
            int n = prices.length;
            int[][] dp = new int[n + 1][2];

            for (int i = 0; i <= n; i++) {
                //将dp数组延长一位，用dp数组里的i，代表prices数组里的[i-1]的情况，即dp数组从0开始表示不可能的情况，dp数组的第1位才是真正prices数组的第0位。
                if (i - 1 == -1) {
                    dp[i][0] = 0;
                    dp[i][1] = Integer.MIN_VALUE;
                    continue;
                }
                dp[i][0] = Math.max(dp[i - 1][0], dp[i - 1][1] + prices[i - 1]);
                dp[i][1] = Math.max(dp[i - 1][1], -prices[i - 1]);
            }
            return dp[n][0];
        }
    }
//leetcode submit region end(Prohibit modification and deletion)

}