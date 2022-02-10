//给定两个字符串 text1 和 text2，返回这两个字符串的最长 公共子序列 的长度。如果不存在 公共子序列 ，返回 0 。
//
// 一个字符串的 子序列 是指这样一个新的字符串：它是由原字符串在不改变字符的相对顺序的情况下删除某些字符（也可以不删除任何字符）后组成的新字符串。 
//
// 
// 例如，"ace" 是 "abcde" 的子序列，但 "aec" 不是 "abcde" 的子序列。 
// 
//
// 两个字符串的 公共子序列 是这两个字符串所共同拥有的子序列。 
//
// 
//
// 示例 1： 
//
// 
//输入：text1 = "abcde", text2 = "ace" 
//输出：3  
//解释：最长公共子序列是 "ace" ，它的长度为 3 。
// 
//
// 示例 2： 
//
// 
//输入：text1 = "abc", text2 = "abc"
//输出：3
//解释：最长公共子序列是 "abc" ，它的长度为 3 。
// 
//
// 示例 3： 
//
// 
//输入：text1 = "abc", text2 = "def"
//输出：0
//解释：两个字符串没有公共子序列，返回 0 。
// 
//
// 
//
// 提示： 
//
// 
// 1 <= text1.length, text2.length <= 1000 
// text1 和 text2 仅由小写英文字符组成。 
// 
// Related Topics 字符串 动态规划 👍 797 👎 0


package com.company.leetcode.editor.cn;

public class No_One143_LongestCommonSubsequence {
    public static void main(String[] args) {
        Solution solution = new No_One143_LongestCommonSubsequence().new Solution();
    }

    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        /**
         * 状态：两个指向字符串末尾的指针
         * 定义：dp【i】表示s1[0..i]和s2[0..j]的最长公共子序列
         * 转移：如果i ==j，继承dp[i-1][j-1]再加1；
         * 如果i!=j,意味着s1[i]和s2[j]中至少有一个字符不在lcs中，可能的上一轮状态是：1.dp[i-1][j] 2.dp[i][j-1] 3.dp[i-1][j-1] 取最大值
         * 有一个优化点：dp[i-1][j-1]肯定小于等于dp[i-1][j]，就不用算了。因为s1[0,j-1]比s1[0,j]短嘛，那从这里面算出的lcs当然也不可能更长嘛。
         * 边界条件：s1，s2长度是0时，lcs长度都是0，即没有公共的
         */
//        public int longestCommonSubsequence(String text1, String text2) {
//            int[][] dp = new int[text1.length() + 1][text2.length() + 1];
//            //边界条件：都是0,不需要手动赋值了
//            //转移
//            for (int i = 1; i < text1.length() + 1; i++) {
//                for (int j = 1; j < text2.length() + 1; j++) {
//                    if (text1.charAt(i-1) == text2.charAt(j-1)) {
//                        dp[i][j] = dp[i - 1][j - 1] + 1;
//                    } else {
//                        dp[i][j] = Math.max(dp[i - 1][j], dp[i][j - 1]);//第三种情况自动省略
//                    }
//                }
//            }
//            //
//            return dp[text1.length()][text2.length()];
//        }
        public int longestCommonSubsequence(String text1, String text2) {
            int[] dp = new int[text2.length() + 1];
            //初始化都是0
            for (int i = 1; i <= text1.length(); i++) {
                //dp[i] = 0 每轮的初始化 不过不需要了
                int preI = 0;//上一轮里dp[i-1][0]的值，不过其实也是0
                for (int j = 1; j <= text2.length(); j++) {
                    int temp = dp[j];//下一轮i的，dp[i-1][j-1] 由于dp[j]在本轮j的赋值前，还是上一轮的j-1的值，所以需要事先保存。
                    if (text1.charAt(i - 1) == text2.charAt(j - 1)) {
                        dp[j] = preI + 1;
                    } else {
                        dp[j] = Math.max(dp[j], dp[j - 1]);
                    }
                    preI = temp;
                }
            }
            return dp[text2.length()];
        }
    }
//leetcode submit region end(Prohibit modification and deletion)

}