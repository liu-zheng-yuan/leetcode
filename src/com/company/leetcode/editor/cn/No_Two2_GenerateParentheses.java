//数字 n 代表生成括号的对数，请你设计一个函数，用于能够生成所有可能的并且 有效的 括号组合。
//
// 
//
// 示例 1： 
//
// 
//输入：n = 3
//输出：["((()))","(()())","(())()","()(())","()()()"]
// 
//
// 示例 2： 
//
// 
//输入：n = 1
//输出：["()"]
// 
//
// 
//
// 提示： 
//
// 
// 1 <= n <= 8 
// 
// Related Topics 字符串 动态规划 回溯 👍 2214 👎 0


package com.company.leetcode.editor.cn;

import java.util.LinkedList;
import java.util.List;

public class No_Two2_GenerateParentheses {
    public static void main(String[] args) {
        Solution solution = new No_Two2_GenerateParentheses().new Solution();
        System.out.println(solution.generateParenthesis(3));
        System.out.println();
    }

    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        private List<String> res = new LinkedList<>();

        public List<String> generateParenthesis(int n) {
            StringBuilder trace = new StringBuilder();//记录路径的
            recursion(n, n, trace);
            return res;
        }

        //定义：可用左右括号数为left和right的情况下，遍历所所有合法的组合，trace保留遍历中的路径
        private void recursion(int left, int right, StringBuilder trace) {
            //边界条件:没有可用
            if (left == 0 && right == 0) {
                res.add(trace.toString());
                return;
            }

            //当前节点的合法性判断：之前用的left括号一定比right括号多 ==> 生下来的left如果比right多 就是“不合法”的
            if (left > right) {
                return;
            }
            //当前节点两种选择
            if (left > 0) {
                trace.append('(');
                recursion(left - 1, right, trace);
                trace.deleteCharAt(trace.length() - 1);
            }
            if (right > 0) {
                trace.append(')');
                recursion(left, right - 1, trace);
                trace.deleteCharAt(trace.length() - 1);
            }

        }
    }
//leetcode submit region end(Prohibit modification and deletion)

}