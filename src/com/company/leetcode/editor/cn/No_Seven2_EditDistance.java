//给你两个单词 word1 和 word2，请你计算出将 word1 转换成 word2 所使用的最少操作数 。
//
// 你可以对一个单词进行如下三种操作： 
//
// 
// 插入一个字符 
// 删除一个字符 
// 替换一个字符 
// 
//
// 
//
// 示例 1： 
//
// 
//输入：word1 = "horse", word2 = "ros"
//输出：3
//解释：
//horse -> rorse (将 'h' 替换为 'r')
//rorse -> rose (删除 'r')
//rose -> ros (删除 'e')
// 
//
// 示例 2： 
//
// 
//输入：word1 = "intention", word2 = "execution"
//输出：5
//解释：
//intention -> inention (删除 't')
//inention -> enention (将 'i' 替换为 'e')
//enention -> exention (将 'n' 替换为 'x')
//exention -> exection (将 'n' 替换为 'c')
//exection -> execution (插入 'u')
// 
//
// 
//
// 提示： 
//
// 
// 0 <= word1.length, word2.length <= 500 
// word1 和 word2 由小写英文字母组成 
// 
// Related Topics 字符串 动态规划 👍 2024 👎 0


package com.company.leetcode.editor.cn;

public class No_Seven2_EditDistance {
    public static void main(String[] args) {
        Solution solution = new No_Seven2_EditDistance().new Solution();
    }

    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        /**
         * 状态：两个指向字符串末尾的指针 i j
         * 定义：将s1[0..i]变成s2[0.。j]所需要的最小操作数，注意！s1[0..i]与[0.。j]是完全一样的！
         * 转移方程：如果i == j，则继承上一个状态的步数[i-1][j-1]。
         * 如果i != j,则取最小值：1.s1增加一个字符 s[i][j-1] （在i位置之后虚拟地加了一个跟j匹配的值，j就被匹配了，所以变成j-1，而i位置的值没有变）;
         * 2.s1删除一个字符 s[i-1][j] （删除当前i位置的字符，j没有变，再匹配i-1和j）
         * 3.s1变换一个字符 s[i-1][j-1]（变换表示 i和j 已经匹配了，统统转移成-1）
         * 边界条件：如果s1或者s2长度是0，那么最少操作数就是另一个字符串的长度
         */
        public int minDistance1(String word1, String word2) {
            int[][] dp = new int[word1.length() + 1][word2.length() + 1];
            for (int i = 0; i <= word1.length(); i++) {
                dp[i][0] = i;//word2长度为0的情况
            }
            for (int j = 0; j <= word2.length(); j++) {
                dp[0][j] = j;//word1长度为0的情况
            }
            //
            for (int i = 1; i <= word1.length(); i++) {
                for (int j = 1; j <= word2.length(); j++) {
                    if (word1.charAt(i - 1) == word2.charAt(j - 1)) {
                        dp[i][j] = dp[i - 1][j - 1];
                    } else {
                        dp[i][j] = Math.min(Math.min(dp[i - 1][j], dp[i][j - 1]), dp[i - 1][j - 1]) + 1;
                    }
                }
            }
            return dp[word1.length()][word2.length()];
        }

        /**
         * 状态压缩：每轮i循环，都需要更改边界条件dp[0]，即dp[i][0]
         * 且需要临时变量记录：上一轮i时，dp[i-1][j-1]位置的值
         * 每轮J循环之前 都需要临时保存dp[j],即“下一个dp[i][j+1]计算所依赖的dp[i-1][j+1 -1]的值”，再计算完之后再赋值给preI
         */
        public int minDistance(String word1, String word2) {
            int[] dp = new int[word2.length() + 1];
            //边界条件：i的投影
            dp[0] = 0;
            //j的投影
            for (int j = 0; j <= word2.length(); j++) {
                dp[j] = j;//word1长度为0的情况
            }
            //
            for (int i = 1; i <= word1.length(); i++) {
                dp[0] = i;//每轮i循环，边界位置dp[i][0]的值，也就是“word2”长度为0的边界情况，也就是此时word1的长度，即i。
                int preI = i - 1;//代表：上一次i循环时，dp[i-1][j-1]位置的值。
                for (int j = 1; j <= word2.length(); j++) {
                    int temp = dp[j];//当前dp[i][j]保存的是下一个dp[i][j+1]计算所依赖的dp[i-1][j-1]的值，所以需要先暂存，当dp[i][j]计算完之后再赋给pre，让j+1用
                    if (word1.charAt(i - 1) == word2.charAt(j - 1)) {
                        dp[j] = preI; // 本应该是dp[i - 1][j - 1] 用pre替换
                    } else {
                        dp[j] = Math.min(Math.min(dp[j], dp[j - 1]), preI) + 1;// 最后本应该是dp[i - 1][j - 1] 用pre替换
                    }
                    preI = temp;//dp[i][j]计算完之后再赋给pre，让j+1用
                }
            }
            return dp[word2.length()];
        }
    }
//leetcode submit region end(Prohibit modification and deletion)

}