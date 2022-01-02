//给定两个单词 word1 和 word2，找到使得 word1 和 word2 相同所需的最小步数，每步可以删除任意一个字符串中的一个字符。
//
// 
//
// 示例： 
//
// 输入: "sea", "eat"
//输出: 2
//解释: 第一步将"sea"变为"ea"，第二步将"eat"变为"ea"
// 
//
// 
//
// 提示： 
//
// 
// 给定单词的长度不超过500。 
// 给定单词中的字符只含有小写字母。 
// 
// Related Topics 字符串 动态规划 👍 353 👎 0


package com.company.leetcode.editor.cn;

public class No_Five83_DeleteOperationForTwoStrings {
    public static void main(String[] args) {
        Solution solution = new No_Five83_DeleteOperationForTwoStrings().new Solution();
    }

    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        /**
         * 由1134题 字符串子序列的定义：只能通过删除的到，不能修改原字符串的顺序
         * 本题只需要找到最长的子序列，然后用每个原字符串减去子序列长度的结果相加，即是步数
         * 相当于：找到最大公倍数的感觉
         */
        public int minDistance1(String word1, String word2) {
            int[][] dp = new int[word1.length() + 1][word2.length() + 1];
            //边界 都是0
            //
            for (int i = 1; i <= word1.length(); i++) {
                for (int j = 1; j <= word2.length(); j++) {
                    if (word1.charAt(i - 1) == word2.charAt(j - 1)) {
                        dp[i][j] = dp[i - 1][j - 1] + 1;
                    } else {
                        dp[i][j] = Math.max(dp[i - 1][j], dp[i][j - 1]);
                    }
                }
            }
            int lcs = dp[word1.length()][word2.length()];
            return (word1.length() - lcs) + (word2.length() - lcs);
        }

        //状态压缩版本
        public int minDistance(String word1, String word2) {
            int[] dp = new int[word2.length() + 1];
            //边界 都是0
            //
            for (int i = 1; i <= word1.length(); i++) {
                int preI = 0;
                for (int j = 1; j <= word2.length(); j++) {
                    int temp = dp[j];
                    if (word1.charAt(i - 1) == word2.charAt(j - 1)) {
                        dp[j] = preI + 1;
                    } else {
                        dp[j] = Math.max(dp[j], dp[j - 1]);
                    }
                    preI = temp;
                }
            }
            int lcs = dp[word2.length()];
            return (word1.length() - lcs) + (word2.length() - lcs);
        }

    }
//leetcode submit region end(Prohibit modification and deletion)

}