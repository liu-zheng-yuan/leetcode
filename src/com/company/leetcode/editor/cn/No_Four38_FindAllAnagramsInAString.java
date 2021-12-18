//给定两个字符串 s 和 p，找到 s 中所有 p 的 异位词 的子串，返回这些子串的起始索引。不考虑答案输出的顺序。
//
// 异位词 指由相同字母重排列形成的字符串（包括相同的字符串）。 
//
// 
//
// 示例 1: 
//
// 
//输入: s = "cbaebabacd", p = "abc"
//输出: [0,6]
//解释:
//起始索引等于 0 的子串是 "cba", 它是 "abc" 的异位词。
//起始索引等于 6 的子串是 "bac", 它是 "abc" 的异位词。
// 
//
// 示例 2: 
//
// 
//输入: s = "abab", p = "ab"
//输出: [0,1,2]
//解释:
//起始索引等于 0 的子串是 "ab", 它是 "ab" 的异位词。
//起始索引等于 1 的子串是 "ba", 它是 "ab" 的异位词。
//起始索引等于 2 的子串是 "ab", 它是 "ab" 的异位词。
// 
//
// 
//
// 提示: 
//
// 
// 1 <= s.length, p.length <= 3 * 10⁴ 
// s 和 p 仅包含小写字母 
// 
// Related Topics 哈希表 字符串 滑动窗口 👍 736 👎 0


package com.company.leetcode.editor.cn;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

public class No_Four38_FindAllAnagramsInAString {
    public static void main(String[] args) {
        Solution solution = new No_Four38_FindAllAnagramsInAString().new Solution();
    }

    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        public List<Integer> findAnagrams(String s, String p) {
            //与66题的区别是：找到所有解
            List<Integer> res = new LinkedList<>();
            Map<Character, Integer> need = new HashMap<>();
            Map<Character, Integer> window = new HashMap<>();
            int valid = 0;
            for (char c : p.toCharArray()) need.put(c, need.getOrDefault(c, 0) + 1);
            //
            int left = 0, right = 0;
            while (right < s.length()) {
                char cur = s.charAt(right);
                //
                right++;
                //
                if (need.containsKey(cur)) {
                    window.put(cur, window.getOrDefault(cur, 0) + 1);
                    if (window.get(cur).equals(need.get(cur))) {
                        valid++;
                    }
                }
                //
                while (isNeedShrink(valid, need, left, right, p)) {
                    //
                    if (right - left == p.length()) {
                        res.add(left);
                    }
                    //
                    char curLeft = s.charAt(left);
                    if (need.containsKey(curLeft)) {
                        if (window.get(curLeft).equals(need.get(curLeft))) {
                            valid--;
                        }
                        window.put(curLeft, window.getOrDefault(curLeft, 0) - 1);
                    }
                    left++;
                }
            }
            return res;
        }

        private boolean isNeedShrink(int valid, Map<Character, Integer> need, int left, int right, String p) {
            return valid == need.keySet().size() && right - left >= p.length();
        }

    }
//leetcode submit region end(Prohibit modification and deletion)

}