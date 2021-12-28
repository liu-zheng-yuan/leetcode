//返回 s 字典序最小的子序列，该子序列包含 s 的所有不同字符，且只包含一次。
//
// 注意：该题与 316 https://leetcode.com/problems/remove-duplicate-letters/ 相同 
//
// 
//
// 示例 1： 
//
// 
//输入：s = "bcabc"
//输出："abc"
// 
//
// 示例 2： 
//
// 
//输入：s = "cbacdcbc"
//输出："acdb" 
//
// 
//
// 提示： 
//
// 
// 1 <= s.length <= 1000 
// s 由小写英文字母组成 
// 
// Related Topics 栈 贪心 字符串 单调栈 👍 131 👎 0


package com.company.leetcode.editor.cn;

import java.util.*;

public class No_One081_SmallestSubsequenceOfDistinctCharacters {
    public static void main(String[] args) {
        Solution solution = new No_One081_SmallestSubsequenceOfDistinctCharacters().new Solution();
    }

    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        public String smallestSubsequence(String s) {
            //统计已经存下的字符串里的元素，为了去重
            Set<Character> existed = new HashSet<>();
            //统计每个字符出现的次数 ==》 1 去重 2pop字典序更大的
            Map<Character, Integer> c2Num = new HashMap<>();
            for (Character c : s.toCharArray()) {
                c2Num.put(c, c2Num.getOrDefault(c, 0) + 1);
            }
            //单调栈：储存最终结果
            Stack<Character> stack = new Stack<>();
            for (Character c : s.toCharArray()) {
                //维护c2Num：当前字符c之后每个字符还有多少个
                c2Num.put(c, c2Num.get(c) - 1);
                //注意：如果当前字符c是已经存在了的，就不需要进行如下while
                if (existed.contains(c)) {
                    continue;
                }
                //加入当前字符C之前，需要判断stack之前的字符是不是 字典序小于当前 || 全局只有1个（c2Num里Value是1）。如果不符合以上条件，说明（不符合字典序最小的条件）或者（字母缺失，没包含所有字母）
                while (!stack.isEmpty() && stack.peek() > c) {
                    Character top = stack.peek();
                    //如果之后不存在top了，就不把top pop掉
                    if (c2Num.get(top) == 0) {
                        break;
                    }
                    //如果之后还有top，则可以继续pop
                    existed.remove(top);
                    stack.pop();
                }
                //直接将当前加入，并且扣除c2Num中后序可能的出现次数

                stack.push(c);
                existed.add(c);


            }
            //stack里的就是反过来的结果
            StringBuilder sb = new StringBuilder();
            for (Character c : stack) {
                sb.append(c);
            }
            return sb.toString();

        }
    }
//leetcode submit region end(Prohibit modification and deletion)

}