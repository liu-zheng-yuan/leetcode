//给定一个整数数组 prices ，它的第 i 个元素 prices[i] 是一支给定的股票在第 i 天的价格。
//
// 设计一个算法来计算你所能获取的最大利润。你最多可以完成 k 笔交易。 
//
// 注意：你不能同时参与多笔交易（你必须在再次购买前出售掉之前的股票）。 
//
// 
//
// 示例 1： 
//
// 
//输入：k = 2, prices = [2,4,1]
//输出：2
//解释：在第 1 天 (股票价格 = 2) 的时候买入，在第 2 天 (股票价格 = 4) 的时候卖出，这笔交易所能获得利润 = 4-2 = 2 。 
//
// 示例 2： 
//
// 
//输入：k = 2, prices = [3,2,6,5,0,3]
//输出：7
//解释：在第 2 天 (股票价格 = 2) 的时候买入，在第 3 天 (股票价格 = 6) 的时候卖出, 这笔交易所能获得利润 = 6-2 = 4 。
//     随后，在第 5 天 (股票价格 = 0) 的时候买入，在第 6 天 (股票价格 = 3) 的时候卖出, 这笔交易所能获得利润 = 3-0 = 3 
//。 
//
// 
//
// 提示： 
//
// 
// 0 <= k <= 100 
// 0 <= prices.length <= 1000 
// 0 <= prices[i] <= 1000 
// 
// Related Topics 数组 动态规划 👍 640 👎 0


package com.company.leetcode.editor.cn;

public class No_One88_BestTimeToBuyAndSellStockIv {
    public static void main(String[] args) {
        Solution solution = new No_One88_BestTimeToBuyAndSellStockIv().new Solution();
        System.out.println(solution.maxProfit(2, new int[]{3, 3, 5, 0, 0, 3, 1, 4}));
    }

    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        /**
         * 详细思路见笔记，ref：https://mp.weixin.qq.com/s?__biz=MzAxODQxMDM0Mw==&mid=2247494095&idx=4&sn=7aed55b22e93c0e43b83172923b51acc&scene=21#wechat_redirect
         * dp[i][k][0] = max(dp[i-1][k][1] + prices[i],dp[i-1][k][0])
         * dp[i][k][1] = max(dp[i-1][k-1][0] - prices[i], dp[i-1][k][1])
         */
        public int maxProfit(int k, int[] prices) {
            int n = prices.length;
            int[][][] dp = new int[n][k + 1][2];
            if (n <= 0) {
                return 0;
            }
            //一次交易由买入和卖出构成，至少需要2天，所以说有效的次数限制k 应该不超过n/2
            //如果超过了就没有约束作用了，相当于k = infinity，可以服用这种情况的逻辑以优化空间使用
            
            //k=0的边界条件
            for (int i = 0; i < n; i++) {
                //第i = 0..n-1天，k = 0 收益肯定是0 手上持有股票是不可能的，为了取Max方便，赋值-infinity
                dp[i][0][0] = 0;
                dp[i][0][1] = Integer.MIN_VALUE;
            }
            //i=-1的边界条件，由于数组下标没有负一，所以不能预先写好，而是在循环里写case解决

            //只依赖正上方和左上方的状态，可以正着遍历
            //虽然i和j逻辑上可以从0开始，但是由于前面边界情况已经写过了，且i = -1会数组越界，所以从1开始
            for (int i = 0; i < n; i++) {//i=-1的边界条件其实还没有写好，所以需要手动算i=0的情况，因为数组下标没有-1
                for (int j = 1; j <= k; j++) { //k=0的case已经当做边界条件写好了，所以可以从1开始
                    //即手动算i=0 ，跳过需要i=-1的情况
                    if (i - 1 == -1) {
                        //i=-1时，dp[-1][j][0] = 0，dp[-1][j][1] = -infinity
                        //所以i = 0时，dp[i][j][0] =Math.max(dp[-1][j][1] + prices[i], dp[-1][k][0]) = max(-infinity+XX,0) = 0
                        //所以i = 0时，dp[i][j][1] =Math.max(dp[-1][j-1][0] - prices[i], dp[-1][k][1]) = max(0-prices[0],-infinity) = -prices[0]
                        dp[i][j][0] = 0;
                        dp[i][j][1] = -prices[i];
                        continue;
                    }
                    dp[i][j][0] = Math.max(dp[i - 1][j][1] + prices[i], dp[i - 1][j][0]);
                    dp[i][j][1] = Math.max(dp[i - 1][j - 1][0] - prices[i], dp[i - 1][j][1]);
                }
            }
            return dp[n - 1][k][0];//手上没有股票肯定比有股票收益大
        }
    }
//leetcode submit region end(Prohibit modification and deletion)

}