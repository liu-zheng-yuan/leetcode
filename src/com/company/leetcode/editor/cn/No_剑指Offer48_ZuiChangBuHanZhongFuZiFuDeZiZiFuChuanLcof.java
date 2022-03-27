//请从字符串中找出一个最长的不包含重复字符的子字符串，计算该最长子字符串的长度。
//
// 
//
// 示例 1: 
//
// 输入: "abcabcbb"
//输出: 3 
//解释: 因为无重复字符的最长子串是 "abc"，所以其长度为 3。
// 
//
// 示例 2: 
//
// 输入: "bbbbb"
//输出: 1
//解释: 因为无重复字符的最长子串是 "b"，所以其长度为 1。
// 
//
// 示例 3: 
//
// 输入: "pwwkew"
//输出: 3
//解释: 因为无重复字符的最长子串是 "wke"，所以其长度为 3。
//     请注意，你的答案必须是 子串 的长度，"pwke" 是一个子序列，不是子串。
// 
//
// 
//
// 提示： 
//
// 
// s.length <= 40000 
// 
//
// 注意：本题与主站 3 题相同：https://leetcode-cn.com/problems/longest-substring-without-
//repeating-characters/ 
// Related Topics 哈希表 字符串 滑动窗口 👍 396 👎 0


package com.company.leetcode.editor.cn;

import java.util.HashMap;
import java.util.Map;

public class No_剑指Offer48_ZuiChangBuHanZhongFuZiFuDeZiZiFuChuanLcof {
    public static void main(String[] args) {
        Solution solution = new No_剑指Offer48_ZuiChangBuHanZhongFuZiFuDeZiZiFuChuanLcof().new Solution();
    }

    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        //正常的滑动窗口是：先不断扩大找可行解，再在内循环找最优解（比如包含xx字符串的最短子串）
        //本题是先不断扩大找最优解，再内循环找可行解（比如不含重复字符的最长子串）
        public int lengthOfLongestSubstring(String s) {
            int left = 0, right = 0;
            Map<Character, Integer> char2count = new HashMap<>();
            int res = 0;
            //窗口[left,right)
            while (right < s.length()) {
                char cur = s.charAt(right);
                right++;//因为要维护[left,right)开区间
                //更新状态记录
                char2count.put(cur, char2count.getOrDefault(cur, 0) + 1);

                //不断找最优解，如果不可行了，需要内循环找可行解
                while (char2count.get(cur) > 1) {
                    char curLeft = s.charAt(left);
                    left++;
                    //更新状态
                    char2count.put(curLeft, char2count.get(curLeft) - 1);
                }
                //从上面那个循环出来肯定是可行解
                if (right - left > res) {
                    res = right - left;
                }

            }
            return res;

        }


    }
//leetcode submit region end(Prohibit modification and deletion)

}