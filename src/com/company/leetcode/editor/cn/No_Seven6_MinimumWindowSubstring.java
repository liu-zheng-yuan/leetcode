//给你一个字符串 s 、一个字符串 t 。返回 s 中涵盖 t 所有字符的最小子串。如果 s 中不存在涵盖 t 所有字符的子串，则返回空字符串 "" 。 
//
// 
//
// 注意： 
//
// 
// 对于 t 中重复字符，我们寻找的子字符串中该字符数量必须不少于 t 中该字符数量。 
// 如果 s 中存在这样的子串，我们保证它是唯一的答案。 
// 
//
// 
//
// 示例 1： 
//
// 
//输入：s = "ADOBECODEBANC", t = "ABC"
//输出："BANC"
// 
//
// 示例 2： 
//
// 
//输入：s = "a", t = "a"
//输出："a"
// 
//
// 示例 3: 
//
// 
//输入: s = "a", t = "aa"
//输出: ""
//解释: t 中两个字符 'a' 均应包含在 s 的子串中，
//因此没有符合条件的子字符串，返回空字符串。 
//
// 
//
// 提示： 
//
// 
// 1 <= s.length, t.length <= 10⁵ 
// s 和 t 由英文字母组成 
// 
//
// 
//进阶：你能设计一个在 o(n) 时间内解决此问题的算法吗？ Related Topics 哈希表 字符串 滑动窗口 👍 1495 👎 0


package com.company.leetcode.editor.cn;

import java.util.HashMap;
import java.util.Map;

public class No_Seven6_MinimumWindowSubstring {
    public static void main(String[] args) {
        Solution solution = new No_Seven6_MinimumWindowSubstring().new Solution();
        System.out.println(solution.minWindow("aa", "aa"));
        System.out.println();
    }

    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        public String minWindow(String s, String t) {
            //
            int minLen = Integer.MAX_VALUE;
            int minStart = 0;
            //需要维护的状态变量
            Map<Character, Integer> need = new HashMap<>();//需要的字符情况
            Map<Character, Integer> window = new HashMap<>();//窗口中字符的情况
            int valid = 0;//窗口中数量等于t中的字符的统计  当valid = t.length 表示满足题意：窗口涵盖t
            // 初始化
            for (char c : t.toCharArray()) need.put(c, need.getOrDefault(c, 0) + 1);
            //窗口[left,right)
            int left = 0;
            int right = 0;

            while (right < s.length()) {
                //当前处理字符
                char cur = s.charAt(right);
                //右移窗口,为下一次做准备:注意！此后的right就不是当前cur对应的right了，而是cur对应的right+1，即之后的窗口就是[left,right)
                right++;
                //更新所有状态字段：tips 只有当前字符c是need中包含的，才需要更新
                if (need.containsKey(cur)) {
                    //注意！这里顺序是先window[cur]++，再检侧valid是否满足
                    window.put(cur, window.getOrDefault(cur, 0) + 1);
                    if (window.get(cur).equals(need.get(cur))) {
                        valid++;//对于某一个字符c，窗口子串包含C的数量，等于t中C字符的数量，对于字符c来说，满足了条件
                    }
                }
                /*** debug 输出的位置 ***/
                System.out.printf("window: [%d, %d)\n", left, right);
                /********************/

                //判断左边界left是否要收缩
                //只有当符合题意时，才需要收缩以找到最优解
                //注意！不能判断valid = t.length ，要考虑到字符重复的情况，应该用valid = need.keyset().size()
                while (isNeedShrink(valid, need)) {
                    //记录所有的解:比如求最小长度的解
                    //注意：此时right已经是cur对应right+1 就是[left,right),所以区间内元素数量是 right-len
                    if (right - left < minLen) {
                        minLen = right - left;
                        minStart = left;
                    }
                    char curLeft = s.charAt(left);
                    left++;//对应右移left
                    //对应更新所有状态字段 = 移动right时的反操作
                    if (need.containsKey(curLeft)) {
                        //注意！这里顺序也是和right相反，先检侧valid是否满足，再window[curLeft]--
                        if (window.get(curLeft).equals(need.get(curLeft))) {
                            valid--;
                        }
                        window.put(curLeft, window.getOrDefault(curLeft, 0) - 1);
                    }
                }
            }
            //最后返回的是以minStart为起点，元素总数是minLen的区间,即[minStart,minStart+minLen) 或者表述为[minStart,minStart+minLen-1]
            return minLen == Integer.MAX_VALUE ? "" : s.substring(minStart, minStart + minLen); //subString也是左闭右开
        }

        private boolean isNeedShrink(int valid, Map<Character, Integer> need) {
            return valid == need.keySet().size();
        }
    }
//leetcode submit region end(Prohibit modification and deletion)

}