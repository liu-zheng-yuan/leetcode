//给定一个数组，它的第 i 个元素是一支给定的股票在第 i 天的价格。
//
// 设计一个算法来计算你所能获取的最大利润。你最多可以完成 两笔 交易。 
//
// 注意：你不能同时参与多笔交易（你必须在再次购买前出售掉之前的股票）。 
//
// 
//
// 示例 1: 
//
// 
//输入：prices = [3,3,5,0,0,3,1,4]
//输出：6
//解释：在第 4 天（股票价格 = 0）的时候买入，在第 6 天（股票价格 = 3）的时候卖出，这笔交易所能获得利润 = 3-0 = 3 。
//     随后，在第 7 天（股票价格 = 1）的时候买入，在第 8 天 （股票价格 = 4）的时候卖出，这笔交易所能获得利润 = 4-1 = 3 。 
//
// 示例 2： 
//
// 
//输入：prices = [1,2,3,4,5]
//输出：4
//解释：在第 1 天（股票价格 = 1）的时候买入，在第 5 天 （股票价格 = 5）的时候卖出, 这笔交易所能获得利润 = 5-1 = 4 。   
//     注意你不能在第 1 天和第 2 天接连购买股票，之后再将它们卖出。   
//     因为这样属于同时参与了多笔交易，你必须在再次购买前出售掉之前的股票。
// 
//
// 示例 3： 
//
// 
//输入：prices = [7,6,4,3,1] 
//输出：0 
//解释：在这个情况下, 没有交易完成, 所以最大利润为 0。 
//
// 示例 4： 
//
// 
//输入：prices = [1]
//输出：0
// 
//
// 
//
// 提示： 
//
// 
// 1 <= prices.length <= 10⁵ 
// 0 <= prices[i] <= 10⁵ 
// 
// Related Topics 数组 动态规划 👍 970 👎 0


package com.company.leetcode.editor.cn;

public class No_One23_BestTimeToBuyAndSellStockIii {
    public static void main(String[] args) {
        Solution solution = new No_One23_BestTimeToBuyAndSellStockIii().new Solution();
    }

    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        /**
         * dp[i][k][0] = max(dp[i-1][k][1] + prices[i],dp[i-1][k][0])
         * dp[i][k][1] = max(dp[i-1][k-1][0] - prices[i], dp[i-1][k][1])
         * 边界条件：dp[-1][...][0] = dp[...][0][0] = 0     dp[-1][...][1] = dp[...][0][1] = -infinity
         * <p>
         * k=2的特殊情况：状态转移需要考虑k
         * 边界条件 dp[-1][0] = 0 dp[-1][1] = -infinity
         */
        public int maxProfit1(int[] prices) {
            int n = prices.length;
            int k = 2;
            int[][][] dp = new int[n][k + 1][2];
            //k = 0的边界情况
            for (int i = 0; i < n; i++) {
                dp[i][0][0] = 0;
                dp[i][0][1] = Integer.MIN_VALUE;
            }
            //i=-1的边界情况要在for循环里指定
            for (int i = 0; i < n; i++) {
                for (int j = 1; j <= k; j++) { // k=0的边界情况已经处理过了
                    //i = 0 依赖边界情况i = -1，所以手动计算出i=0
                    if (i - 1 == -1) {
                        //i = 0
                        dp[i][j][0] = 0;
                        dp[i][j][1] = -prices[i];
                        continue;
                    }
                    dp[i][j][0] = Math.max(dp[i - 1][j][1] + prices[i], dp[i - 1][j][0]);
                    dp[i][j][1] = Math.max(dp[i - 1][j - 1][0] - prices[i], dp[i - 1][j][1]);
                }
            }
            return dp[n - 1][k][0];
        }


        //状态压缩
        public int maxProfit2(int[] prices) {
            int n = prices.length;
            int k = 2;
            int[][] dp = new int[k + 1][2];
            //k = 0的边界情况
            dp[0][0] = 0;
            dp[0][1] = Integer.MIN_VALUE;

            //i=-1的边界情况要在for循环里指定
            for (int i = 0; i < n; i++) {
                for (int j = 1; j <= k; j++) { // k=0的边界情况已经处理过了
                    //i = 0 依赖边界情况i = -1，所以手动计算出i=0
                    if (i - 1 == -1) {
                        //i = 0
                        dp[j][0] = 0;
                        dp[j][1] = -prices[i];
                        continue;
                    }
                    dp[j][0] = Math.max(dp[j][1] + prices[i], dp[j][0]);
                    dp[j][1] = Math.max(dp[j - 1][0] - prices[i], dp[j][1]);
                }
            }
            return dp[k][0];
        }

        public int maxProfit(int[] prices) {
            int n = prices.length;
            int[][][] dp = new int[n + 1][2 + 1][2];//i=0,k=0的边界情况都要计算，所以申请的dp数组要大1
            //k = 0,i = 0的特殊情况，就不用像公众号里写在循环里了，单独写在外面更直观。
            for (int i = 0; i <= n; i++) {
                dp[i][0][1] = Integer.MIN_VALUE;
            }
            for (int i = 0; i <= 2; i++) {
                dp[0][i][1] = Integer.MIN_VALUE;
            }
            //边界情况已经考虑完了，直接从有意义的第一天开始算，dp【1】 = prices【0】 起始点不同要减一
            for (int i = 1; i <= n; i++) {
                for (int k = 1; k <= 2; k++) {
                    //将dp数组延长一位，用dp数组里的i，代表prices数组里的[i-1]的情况，即dp数组从0开始表示不可能的情况，dp数组的第1位才是真正prices数组的第0位。
                    dp[i][k][0] = Math.max(dp[i - 1][k][0], dp[i - 1][k][1] + prices[i - 1]);
                    dp[i][k][1] = Math.max(dp[i - 1][k][1], dp[i - 1][k - 1][0] - prices[i - 1]);
                }
            }
            return dp[n][2][0];
        }
    }
//leetcode submit region end(Prohibit modification and deletion)

}