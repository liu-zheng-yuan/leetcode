//给定两个字符串s1, s2，找到使两个字符串相等所需删除字符的ASCII值的最小和。
//
// 示例 1: 
//
// 
//输入: s1 = "sea", s2 = "eat"
//输出: 231
//解释: 在 "sea" 中删除 "s" 并将 "s" 的值(115)加入总和。
//在 "eat" 中删除 "t" 并将 116 加入总和。
//结束时，两个字符串相等，115 + 116 = 231 就是符合条件的最小和。
// 
//
// 示例 2: 
//
// 
//输入: s1 = "delete", s2 = "leet"
//输出: 403
//解释: 在 "delete" 中删除 "dee" 字符串变成 "let"，
//将 100[d]+101[e]+101[e] 加入总和。在 "leet" 中删除 "e" 将 101[e] 加入总和。
//结束时，两个字符串都等于 "let"，结果即为 100+101+101+101 = 403 。
//如果改为将两个字符串转换为 "lee" 或 "eet"，我们会得到 433 或 417 的结果，比答案更大。
// 
//
// 注意: 
//
// 
// 0 < s1.length, s2.length <= 1000。 
// 所有字符串中的字符ASCII值在[97, 122]之间。 
// 
// Related Topics 字符串 动态规划 👍 247 👎 0


package com.company.leetcode.editor.cn;

public class No_Seven12_MinimumAsciiDeleteSumForTwoStrings {
    public static void main(String[] args) {
        Solution solution = new No_Seven12_MinimumAsciiDeleteSumForTwoStrings().new Solution();
        System.out.println(solution.minimumDeleteSum("sea", "eat"));
    }

    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        //类比 1134题，其实就是找最长公共子序列
        //但是由于需要算出ASCII值，所以原来的 “定义”，状态转移，和边界条件都需要改变

        /**
         * 定义：s1[0..i]和s2[0..j] 需要删除ascii值最小和
         * 边界条件：i ==0时，是s2[0..j]所有字符的acii值，即全部删光。j==0时，同理
         * 转移：i==j时，继承[i-1][j-1],不需要删除。
         * i！=j时，取最小:dp[i-1][j]+i,dp[i][j-1]+j,dp[i-1][j-1]+i+j(保险起见 把第三种情况加上，但是看答案是不需要的)
         */
        public int minimumDeleteSum1(String s1, String s2) {
            int[][] dp = new int[s1.length() + 1][s2.length() + 1];
            //
            for (int i = 1; i <= s1.length(); i++) {
                for (int k = 0; k <= i - 1; k++) {
                    dp[i][0] += s1.charAt(k);
                }
            }
            for (int j = 1; j <= s2.length(); j++) {
                for (int k = 0; k <= j - 1; k++) {
                    dp[0][j] += s2.charAt(k);
                }
            }
            //
            for (int i = 1; i <= s1.length(); i++) {
                for (int j = 1; j <= s2.length(); j++) {
                    if (s1.charAt(i - 1) == s2.charAt(j - 1)) {
                        dp[i][j] = dp[i - 1][j - 1];
                    } else {
                        dp[i][j] = Math.min(
                                Math.min(dp[i - 1][j] + s1.charAt(i - 1), dp[i][j - 1] + s2.charAt(j - 1)),
                                dp[i - 1][j - 1] + s1.charAt(i - 1) + s2.charAt(j - 1)
                        );
                    }
                }
            }
            //
            return dp[s1.length()][s2.length()];
        }

        //状态压缩版本
        public int minimumDeleteSum(String s1, String s2) {
            int[] dp = new int[s2.length() + 1];
            //关于i的边界逻辑移到 转移方程内
//            for (int i = 1; i <= s1.length(); i++) {
//                for (int k = 0; k <= i - 1; k++) {
//                    dp[i][0] += s1.charAt(k);
//                }
//            }
            for (int j = 1; j <= s2.length(); j++) {
                for (int k = 0; k <= j - 1; k++) {
                    dp[j] += s2.charAt(k);
                }
            }
            //
            for (int i = 1; i <= s1.length(); i++) {
                //每轮i循环，边界位置dp[i][0]的值，也就是s1有长度，s2无时候的删除最小和
                dp[0] = 0;
                for (int k = 0; k <= i - 1; k++) {
                    dp[0] += s1.charAt(k);
                }
                //上一轮i循环，边界位置dp[i][0]的值
                int preI = 0;
                for (int k = 0; k <= i - 2; k++) {
                    preI += s1.charAt(k);
                }
                //上一次i循环时，[i-1][j-1]的值
                for (int j = 1; j <= s2.length(); j++) {
                    int temp = dp[j];
                    if (s1.charAt(i - 1) == s2.charAt(j - 1)) {
                        dp[j] = preI;
                    } else {
                        dp[j] = Math.min(
                                Math.min(dp[j] + s1.charAt(i - 1), dp[j - 1] + s2.charAt(j - 1)),
                                dp[j - 1] + s1.charAt(i - 1) + s2.charAt(j - 1)
                        );
                    }
                    preI = temp;
                }
            }
            //
            return dp[s2.length()];
        }
    }
//leetcode submit region end(Prohibit modification and deletion)

}