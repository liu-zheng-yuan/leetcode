//给你一个字符串 s，找到 s 中最长的回文子串。
//
// 
//
// 示例 1： 
//
// 
//输入：s = "babad"
//输出："bab"
//解释："aba" 同样是符合题意的答案。
// 
//
// 示例 2： 
//
// 
//输入：s = "cbbd"
//输出："bb"
// 
//
// 示例 3： 
//
// 
//输入：s = "a"
//输出："a"
// 
//
// 示例 4： 
//
// 
//输入：s = "ac"
//输出："a"
// 
//
// 
//
// 提示： 
//
// 
// 1 <= s.length <= 1000 
// s 仅由数字和英文字母（大写和/或小写）组成 
// 
// Related Topics 字符串 动态规划 👍 4526 👎 0


package com.company.leetcode.editor.cn;

public class No_Five_LongestPalindromicSubstring {
    public static void main(String[] args) {
        Solution solution = new No_Five_LongestPalindromicSubstring().new Solution();
    }

    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        /**
         * 正确解法是：双指针 扩散，On2
         * 动态规划也是On2，不是最优解
         * 马拉车算法On 最优 但是很复杂。
         */
        public String longestPalindrome(String s) {
            int maxL = 0;
            String maxS = "";
            for (int i = 0; i < s.length(); i++) {
                //找到以i为中心的
                String s1 = find(s, i, i);
                //找到以i-1，i为中心的
                String s2 = find(s, i - 1, i);
                if (s1.length() > maxL) {
                    maxL = s1.length();
                    maxS = s1;
                }
                if (s2.length() > maxL) {
                    maxL = s2.length();
                    maxS = s2;
                }
            }
            return maxS;
        }

        //找到s里以left和right为中心的回文串。之所以传入两个，是为了处理回文串是偶数和奇数长度的情况
        public String find(String s, int left, int right) {
            while (left >= 0 && right < s.length() && s.charAt(left) == s.charAt(right)) {
                left--;
                right++;
            }
            //真实区间是[left+1,right-1],但是subString右边是开区间。
            return s.substring(left + 1, right);
        }
    }
//leetcode submit region end(Prohibit modification and deletion)

}