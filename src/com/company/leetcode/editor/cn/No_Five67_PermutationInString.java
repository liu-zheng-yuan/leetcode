//给你两个字符串 s1 和 s2 ，写一个函数来判断 s2 是否包含 s1 的排列。如果是，返回 true ；否则，返回 false 。
//
// 换句话说，s1 的排列之一是 s2 的 子串 。 
//
// 
//
// 示例 1： 
//
// 
//输入：s1 = "ab" s2 = "eidbaooo"
//输出：true
//解释：s2 包含 s1 的排列之一 ("ba").
// 
//
// 示例 2： 
//
// 
//输入：s1= "ab" s2 = "eidboaoo"
//输出：false
// 
//
// 
//
// 提示： 
//
// 
// 1 <= s1.length, s2.length <= 10⁴ 
// s1 和 s2 仅包含小写字母 
// 
// Related Topics 哈希表 双指针 字符串 滑动窗口 👍 512 👎 0


package com.company.leetcode.editor.cn;

import java.util.HashMap;
import java.util.Map;

public class No_Five67_PermutationInString {
    public static void main(String[] args) {
        Solution solution = new No_Five67_PermutationInString().new Solution();
        System.out.println(solution.checkInclusion("ab", "eidbaoo"));
    }

    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        public boolean checkInclusion(String s1, String s2) {
            //与76的区别是：子串是“包含”还是“排列”，即本题要求子串长度与目标串相等
            Map<Character, Integer> need = new HashMap<>();
            Map<Character, Integer> window = new HashMap<>();
            int validChar = 0;
            for (char c : s1.toCharArray()) need.put(c, need.getOrDefault(c, 0) + 1);
            //
            int left = 0;
            int right = 0;

            while (right < s2.length()) {
                char cur = s2.charAt(right);
                //先扩展右,注意后续都是[left,right)
                right++;
                //更新状态
                if (need.containsKey(cur)) {
                    window.put(cur, window.getOrDefault(cur, 0) + 1);
                    if (window.get(cur).equals(need.get(cur))) {
                        validChar++;
                    }
                }
                //判断收缩的逻辑是：首先满足最基本的“子串包含s1”的语义，其次要“子串长度大于等于s1”就需要慢慢收缩。当最后一次“子串长度等于s1”进入while触发返回true
                while (isNeedShrink(validChar, need, left, right, s1)) {
                    //先判断是否达成最终条件:子串[left,right)长度等于s1
                    if (right - left == s1.length()) {
                        return true;
                    }
                    //左侧先更新状态，再收缩,反着更新状态
                    char curLeft = s2.charAt(left);
                    if (need.containsKey(curLeft)) {
                        if (window.get(curLeft).equals(need.get(curLeft))) {
                            validChar--;
                        }
                        window.put(curLeft, window.getOrDefault(curLeft, 0) - 1);
                    }
                    left++;

                }

            }
            return false;
        }

        private boolean isNeedShrink(int validChar, Map<Character, Integer> need, int left, int right, String s1) {
            return validChar == need.keySet().size() && right - left >= s1.length();
        }

    }
//leetcode submit region end(Prohibit modification and deletion)

}