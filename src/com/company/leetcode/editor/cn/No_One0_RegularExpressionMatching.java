//给你一个字符串 s 和一个字符规律 p，请你来实现一个支持 '.' 和 '*' 的正则表达式匹配。
//
// 
// '.' 匹配任意单个字符 
// '*' 匹配零个或多个前面的那一个元素 
// 
//
// 所谓匹配，是要涵盖 整个 字符串 s的，而不是部分字符串。 
// 
//
// 示例 1： 
//
// 
//输入：s = "aa" p = "a"
//输出：false
//解释："a" 无法匹配 "aa" 整个字符串。
// 
//
// 示例 2: 
//
// 
//输入：s = "aa" p = "a*"
//输出：true
//解释：因为 '*' 代表可以匹配零个或多个前面的那一个元素, 在这里前面的元素就是 'a'。因此，字符串 "aa" 可被视为 'a' 重复了一次。
// 
//
// 示例 3： 
//
// 
//输入：s = "ab" p = ".*"
//输出：true
//解释：".*" 表示可匹配零个或多个（'*'）任意字符（'.'）。
// 
//
// 示例 4： 
//
// 
//输入：s = "aab" p = "c*a*b"
//输出：true
//解释：因为 '*' 表示零个或多个，这里 'c' 为 0 个, 'a' 被重复一次。因此可以匹配字符串 "aab"。
// 
//
// 示例 5： 
//
// 
//输入：s = "mississippi" p = "mis*is*p*."
//输出：false 
//
// 
//
// 提示： 
//
// 
// 1 <= s.length <= 20 
// 1 <= p.length <= 30 
// s 可能为空，且只包含从 a-z 的小写字母。 
// p 可能为空，且只包含从 a-z 的小写字母，以及字符 . 和 *。 
// 保证每次出现字符 * 时，前面都匹配到有效的字符 
// 
// Related Topics 递归 字符串 动态规划 👍 2628 👎 0


package com.company.leetcode.editor.cn;

import java.util.HashMap;
import java.util.Map;

public class No_One0_RegularExpressionMatching {
    public static void main(String[] args) {
        Solution solution = new No_One0_RegularExpressionMatching().new Solution();
    }

    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        /**
         * 正则表达式算法题：
         * 中心思路：指针i，j在两个字符串上移动，依次计算s[i]子串和p[j]子串是否能匹配，依次计算每个字符能不能匹配，如果最后两个指针都能移动到字符串的末尾，那么久匹配成功，反之则匹配失败。
         * 基本点：看两个字符是否匹配，一切逻辑围绕匹配/不匹配两种情况展开即可。
         * 通配符：表示前一个字符可以【匹配0次】【匹配大于等于1次】，把所有的情况都尝试一遍，只有有一种情况可以让i，j同时移动到末尾，就说明可以匹配
         */
        public boolean isMatch(String s, String p) {
            return dp(s, 0, p, 0);

        }

        Map<String, Boolean> memory = new HashMap<>();

        public boolean dp(String s, int i, String p, int j) {
            //
            if (j == p.length()) {
                //pattern匹配完了，必须字符串也匹配完，才算能匹配上
                return i == s.length();
            }
            if (i == s.length()) {
                //剩余的pattern是[j,p.length-1]
                //这是需要判断pattern是否能匹配空，比如x*y*z* 这样的，就算s匹配完了，但是p能匹配空，也算匹配的上
                if ((p.length() - j) % 2 == 1) {
                    //比如是偶数的长度
                    return false;
                }
                //两个两个遍历剩下的pattern，确认都是x* 这样形式的
                for (; j < p.length(); j = j + 2) {
                    if (p.charAt(j + 1) != '*') {
                        return false;
                    }
                }
                return true;
            }
            //转移逻辑

            //memory
            String key = String.valueOf(i) + "#" + String.valueOf(j);
            if (memory.containsKey(key)) {
                return memory.get(key);
            }
            //如果s[i]和p[j]能匹配上:根据j+1是不是*，做不同选择【匹配0次】【匹配大于等于1次】
            boolean res = false;
            if (s.charAt(i) == p.charAt(j) || p.charAt(j) == '.') {
                if (j < p.length() - 1 && p.charAt(j + 1) == '*') {
                    res = dp(s, i, p, j + 2) //匹配0次
                            || dp(s, i + 1, p, j);//匹配大于等于1次
                } else {
                    //普通匹配一个字符
                    res = dp(s, i + 1, p, j + 1);
                }
            } else {
                //如果s[i]和p[j]不能匹配上:只有j+1是*，且匹配一次的情况才“可能”符合
                if (j < p.length() - 1 && p.charAt(j + 1) == '*') {
                    res = dp(s, i, p, j + 2);
                } else {
                    res = false;
                }
            }
            //
            memory.put(key, res);
            return res;
        }
    }
//leetcode submit region end(Prohibit modification and deletion)

}