//给你一个字符串 s ，找出其中最长的回文子序列，并返回该序列的长度。 
//
// 子序列定义为：不改变剩余字符顺序的情况下，删除某些字符或者不删除任何字符形成的一个序列。 
//
// 
//
// 示例 1： 
//
// 
//输入：s = "bbbab"
//输出：4
//解释：一个可能的最长回文子序列为 "bbbb" 。
// 
//
// 示例 2： 
//
// 
//输入：s = "cbbd"
//输出：2
//解释：一个可能的最长回文子序列为 "bb" 。
// 
//
// 
//
// 提示： 
//
// 
// 1 <= s.length <= 1000 
// s 仅由小写英文字母组成 
// 
// Related Topics 字符串 动态规划 👍 694 👎 0


package com.company.leetcode.editor.cn;

public class No_Five16_LongestPalindromicSubsequence {
    public static void main(String[] args) {
        Solution solution = new No_Five16_LongestPalindromicSubsequence().new Solution();
    }

    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        /**
         * 定义：s[i..j]中最大回文子序列的长度
         * 转移：如果i ==j，则继承dp[i+1][j-1]再加上2,（i和j的字符）
         * 如果i！=j，则说明i和j不可能同时出现在最长回文子序列中，此时【有两种选择】：1，将i加入，s[i,j-1] 2.将j加入,s[i+1,j] ，两种选择取最大值
         * 边界条件：i==j时，单个字符回文序列长1.即对象线上都是1.并且，i肯定小于j，dp数组里i>j的地方，根本就不存在子序列
         * 【特别的】：dp与左下，左上，正下，三个方向的状态有关，需要i从大到小遍历，j从小到大遍历
         */
        public int longestPalindromeSubseq(String s) {
            int[][] dp = new int[s.length()][s.length()];
            for (int i = 0; i < s.length(); i++) {
                dp[i][i] = 1;
            }
            //
            for (int i = s.length() - 1; i >= 0; i--) {
                //记住i肯定小于j
                for (int j = i + 1; j < s.length(); j++) {
                    if (s.charAt(i) == s.charAt(j)) {
                        dp[i][j] = dp[i + 1][j - 1] + 2;
                    } else {
                        dp[i][j] = Math.max(dp[i][j - 1], dp[i + 1][j]);
                    }
                }
            }
            return dp[0][s.length() - 1];

        }
    }
//leetcode submit region end(Prohibit modification and deletion)

}