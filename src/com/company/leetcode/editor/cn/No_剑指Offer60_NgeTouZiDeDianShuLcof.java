//把n个骰子扔在地上，所有骰子朝上一面的点数之和为s。输入n，打印出s的所有可能的值出现的概率。 
//
// 
//
// 你需要用一个浮点数数组返回答案，其中第 i 个元素代表这 n 个骰子所能掷出的点数集合中第 i 小的那个的概率。 
//
// 
//
// 示例 1: 
//
// 输入: 1
//输出: [0.16667,0.16667,0.16667,0.16667,0.16667,0.16667]
// 
//
// 示例 2: 
//
// 输入: 2
//输出: [0.02778,0.05556,0.08333,0.11111,0.13889,0.16667,0.13889,0.11111,0.08333,0
//.05556,0.02778] 
//
// 
//
// 限制： 
//
// 1 <= n <= 11 
// Related Topics 数学 动态规划 概率与统计 👍 386 👎 0


package com.company.leetcode.editor.cn;

import java.util.ArrayList;
import java.util.List;

public class No_剑指Offer60_NgeTouZiDeDianShuLcof {
    public static void main(String[] args) {
        Solution solution = new No_剑指Offer60_NgeTouZiDeDianShuLcof().new Solution();
    }

    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        //dp[i][j]:表示投完第i个骰子后，【之前所有骰子点数和】j 的【出现次数】
        //背景指示：一共n个骰子，值域[n,6n],一共有6^n种可能的情况（每个情况对一个 【点数和】）.所以每个点数和s的概率p = （该点数和S出现的次数）/ 总局面次数6^n
        public double[] dicesProbability(int n) {
            int[][] dp = new int[n + 1][6 * n + 1];//点数和的值域上限也就是6n了。
            //不考虑等于0的边界情况 从最简单的1个骰子开始
            for (int i = 1; i <= 6; i++) {
                dp[1][i] = 1;
            }
            //
            for (int i = 2; i <= n; i++) {
                for (int j = i; j <= 6 * i; j++) {
                    //当骰子数量和当前点数和固定的情况下， 要看怎么做选择，让n-1的状态转移到当前层
                    //一个骰子最多有6种可能的数字，需要都算上
                    //比如 6 这个取值，可能是上一次是 1 这次是 5，也可能上次 2 这次 4，也可能上次 3 这次 3，也可能上次 4 这次 2，也可能上次 5 这次 1，所以要 1-6 都遍历一次
                    for (int cur = 1; cur <= 6; cur++) {
                        if (j - cur >= 0) {
                            dp[i][j] += dp[i - 1][j - cur];//当前的点数和J可能由上一层不同的点数和S‘通过加上本轮不同的cur转移得到。
                        }
                    }
                }
            }
            //计算概率
            double all = Math.pow(6, n);
            List<Double> res = new ArrayList<>();
            for (int j = n; j <= 6 * n; j++) {
                res.add(dp[n][j] / all);
            }
            return res.stream().mapToDouble(a -> a).toArray();
        }
    }
//leetcode submit region end(Prohibit modification and deletion)

}