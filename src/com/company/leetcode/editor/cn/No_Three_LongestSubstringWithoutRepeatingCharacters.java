//给定一个字符串 s ，请你找出其中不含有重复字符的 最长子串 的长度。
//
// 
//
// 示例 1: 
//
// 
//输入: s = "abcabcbb"
//输出: 3 
//解释: 因为无重复字符的最长子串是 "abc"，所以其长度为 3。
// 
//
// 示例 2: 
//
// 
//输入: s = "bbbbb"
//输出: 1
//解释: 因为无重复字符的最长子串是 "b"，所以其长度为 1。
// 
//
// 示例 3: 
//
// 
//输入: s = "pwwkew"
//输出: 3
//解释: 因为无重复字符的最长子串是 "wke"，所以其长度为 3。
//     请注意，你的答案必须是 子串 的长度，"pwke" 是一个子序列，不是子串。
// 
//
// 示例 4: 
//
// 
//输入: s = ""
//输出: 0
// 
//
// 
//
// 提示： 
//
// 
// 0 <= s.length <= 5 * 10⁴ 
// s 由英文字母、数字、符号和空格组成 
// 
// Related Topics 哈希表 字符串 滑动窗口 👍 6594 👎 0


package com.company.leetcode.editor.cn;

import java.util.HashSet;
import java.util.Set;

public class No_Three_LongestSubstringWithoutRepeatingCharacters {
    public static void main(String[] args) {
        Solution solution = new No_Three_LongestSubstringWithoutRepeatingCharacters().new Solution();
        System.out.println(solution.lengthOfLongestSubstring("abcabcbb"));
    }

    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        public int lengthOfLongestSubstring(String s) {
            //跟其他滑动数组的区别是：1.没有第二个用来比较的字符串 2.找到不是最短的而是最长的（其实没区别，只要把所有符合条件的子串都遍历一遍，就能找出最值）
            //本题对于子串的约束条件是：没有重复数字 ==》如何O1判断有无重复 ==》维护状态参数Set
            Set<Character> set = new HashSet<>();
            int maxLen = 0;
            //
            int left = 0, right = 0;
            while (right < s.length()) {
                char cur = s.charAt(right);
                //
                right++;
                //
//                System.out.printf("left = %s,right= %s\n", left, right);
                while (isNeedShrink(set, cur)) {
                    char curLeft = s.charAt(left);
                    //
                    set.remove(curLeft);
                    left++;
                }
                //这里跟框架不一样：https://mp.weixin.qq.com/s?__biz=MzAxODQxMDM0Mw==&mid=2247485141&idx=1&sn=0e4583ad935e76e9a3f6793792e60734&scene=21#wechat_redirect
                //这里把判断最终结果和状态变更都放在了收缩后，因为“不满足条件才会收缩，收缩完之后肯定是满足条件”，才能进行最终判断
                //以及应该先收缩再“加入”，不然收缩时刚加入的cur也会在set中，被误判为重复的元素
                set.add(cur);
                if (right - left > maxLen) {
                    maxLen = right - left;
                }
            }
            return maxLen;
        }

        private boolean isNeedShrink(Set<Character> set, char cur) {
            return set.contains(cur);
        }
    }
//leetcode submit region end(Prohibit modification and deletion)

}