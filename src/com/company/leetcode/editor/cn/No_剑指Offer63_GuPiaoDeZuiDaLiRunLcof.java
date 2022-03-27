//假设把某股票的价格按照时间先后顺序存储在数组中，请问买卖该股票一次可能获得的最大利润是多少？
//
// 
//
// 示例 1: 
//
// 输入: [7,1,5,3,6,4]
//输出: 5
//解释: 在第 2 天（股票价格 = 1）的时候买入，在第 5 天（股票价格 = 6）的时候卖出，最大利润 = 6-1 = 5 。
//     注意利润不能是 7-1 = 6, 因为卖出价格需要大于买入价格。
// 
//
// 示例 2: 
//
// 输入: [7,6,4,3,1]
//输出: 0
//解释: 在这种情况下, 没有交易完成, 所以最大利润为 0。 
//
// 
//
// 限制： 
//
// 0 <= 数组长度 <= 10^5 
//
// 
//
// 注意：本题与主站 121 题相同：https://leetcode-cn.com/problems/best-time-to-buy-and-sell-
//stock/ 
// Related Topics 数组 动态规划 👍 231 👎 0


package com.company.leetcode.editor.cn;

public class No_剑指Offer63_GuPiaoDeZuiDaLiRunLcof {
    public static void main(String[] args) {
        Solution solution = new No_剑指Offer63_GuPiaoDeZuiDaLiRunLcof().new Solution();
    }

    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        //dp[i][k][0/1]：第n天，最多能买卖k次，手里有无股票情况下的获利最大值
        //dp[i][k][0] = max(dp[i-1][k][0] , dp[i-1][k][1] + price[i])
        //dp[i][k][1] = max(dp[i-1][k][1]   dp[i-1][k-1][0] - price[i])
        //边界条件:dp[-1][][0] = 0 ; dp[-1][][1] = -infinity(还没开始的时候，是不可能持有股票的,因为我们的算法要求一个最大值，所以初始值设为一个最小值，方便取最大值。)
        //dp[][0][0] = 0,dp[][0][1] = -infinity(不允许交易的情况下，是不可能持有股票的。因为我们的算法要求一个最大值，所以初始值设为一个最小值，方便取最大值。)
        //base case：
        //dp[-1][...][0] = dp[...][0][0] = 0           只要是边界情况hold = 0，就是没有利润都是0
        //dp[-1][...][1] = dp[...][0][1] = -infinity  只要是边界情况有hold =1 ，都是-infinity
        //本题是k=1的情况
        //dp[i][0] = max(dp[i-1][0],dp[i-1][1]+price[i])
        //dp[i][1] = max(dp[i-1][1],-price[i])
        public int maxProfit(int[] prices) {
            int[][] dp = new int[prices.length + 1][2];
            //初始化，当天数还没有开始时
            dp[0][1] = Integer.MIN_VALUE;
            dp[0][0] = 0;
            //
            for (int i = 1; i <= prices.length; i++) {
                dp[i][0] = Math.max(dp[i - 1][0], dp[i - 1][1] + prices[i - 1]);
                dp[i][1] = Math.max(dp[i - 1][1], -prices[i - 1]);
            }
            //
            return dp[prices.length][0];

        }
    }
//leetcode submit region end(Prohibit modification and deletion)

}