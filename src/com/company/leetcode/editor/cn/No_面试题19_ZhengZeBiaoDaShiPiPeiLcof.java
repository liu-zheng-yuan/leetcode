//请实现一个函数用来匹配包含'. '和'*'的正则表达式。模式中的字符'.'表示任意一个字符，而'*'表示它前面的字符可以出现任意次（含0次）。在本题中，匹配
//是指字符串的所有字符匹配整个模式。例如，字符串"aaa"与模式"a.a"和"ab*ac*a"匹配，但与"aa.a"和"ab*a"均不匹配。 
//
// 示例 1: 
//
// 输入:
//s = "aa"
//p = "a"
//输出: false
//解释: "a" 无法匹配 "aa" 整个字符串。
// 
//
// 示例 2: 
//
// 输入:
//s = "aa"
//p = "a*"
//输出: true
//解释: 因为 '*' 代表可以匹配零个或多个前面的那一个元素, 在这里前面的元素就是 'a'。因此，字符串 "aa" 可被视为 'a' 重复了一次。
// 
//
// 示例 3: 
//
// 输入:
//s = "ab"
//p = ".*"
//输出: true
//解释: ".*" 表示可匹配零个或多个（'*'）任意字符（'.'）。
// 
//
// 示例 4: 
//
// 输入:
//s = "aab"
//p = "c*a*b"
//输出: true
//解释: 因为 '*' 表示零个或多个，这里 'c' 为 0 个, 'a' 被重复一次。因此可以匹配字符串 "aab"。
// 
//
// 示例 5: 
//
// 输入:
//s = "mississippi"
//p = "mis*is*p*."
//输出: false 
//
// 
// s 可能为空，且只包含从 a-z 的小写字母。 
// p 可能为空，且只包含从 a-z 的小写字母以及字符 . 和 *，无连续的 '*'。 
// 
//
// 注意：本题与主站 10 题相同：https://leetcode-cn.com/problems/regular-expression-matching/
// 
// Related Topics 递归 字符串 动态规划 👍 350 👎 0


package com.company.leetcode.editor.cn;

public class No_面试题19_ZhengZeBiaoDaShiPiPeiLcof {
    public static void main(String[] args) {
        Solution solution = new No_面试题19_ZhengZeBiaoDaShiPiPeiLcof().new Solution();
        System.out.println(solution.isMatch("a", ".*..a*"));
    }

    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        public boolean isMatch(String s, String p) {
            if (s == null || p == null) {
                return false;
            }
            return recusion(s, p, 0, 0);
        }

        private boolean recusion(String s, String p, int si, int pi) {
            //边界条件：1.如果p走完了，但是s还没走完：肯定不匹配 2.如果s走完了，p还剩下：可能匹配，比如p剩下的部分都是.*这样的 3.如果s和p同时走完了，就是匹配
            if (pi == p.length() && si < s.length()) {
                return false;
            }
            if (si == s.length() && pi < p.length()) {
                //从pi开始到p结尾都是  “a*” 这样的组合搭配
                if ((p.length() - pi) % 2 == 1) {
                    return false; //剩下的p是奇数 肯定不可能
                }
                for (int i = pi; i < p.length(); i = i + 2) {
                    if (p.charAt(i + 1) == '*') {
                        continue;
                    } else {
                        return false;
                    }
                }
                return true;
            }
            if (si == s.length() && pi == p.length()) {
                return true;
            }
            //正常的匹配逻辑
            if (s.charAt(si) == p.charAt(pi) || p.charAt(pi) == '.') {
                if (pi + 1 < p.length() && p.charAt(pi + 1) == '*') {
                    //dp逻辑：*可以匹配零次、一次、多次，之间是或的关系
                    return recusion(s, p, si, pi + 2) //匹配零次
                            || recusion(s, p, si + 1, pi + 2)//匹配一次
                            || recusion(s, p, si + 1, pi);//匹配多次
                } else {
                    return recusion(s, p, si + 1, pi + 1);
                }
            } else {
                //需要额外考虑 当前不匹配的字符后面是*的情况
                if (pi + 1 < p.length() && p.charAt(pi + 1) == '*') {
                    //只能转移到 *匹配0次的情况
                    return recusion(s, p, si, pi + 2);
                } else {
                    //如果不是* 只能是不匹配
                    return false;
                }
            }
        }
    }
//leetcode submit region end(Prohibit modification and deletion)

}