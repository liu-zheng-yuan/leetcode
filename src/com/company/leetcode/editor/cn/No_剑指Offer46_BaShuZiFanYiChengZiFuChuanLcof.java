//给定一个数字，我们按照如下规则把它翻译为字符串：0 翻译成 “a” ，1 翻译成 “b”，……，11 翻译成 “l”，……，25 翻译成 “z”。一个数字可
//能有多个翻译。请编程实现一个函数，用来计算一个数字有多少种不同的翻译方法。 
//
// 
//
// 示例 1: 
//
// 输入: 12258
//输出: 5
//解释: 12258有5种不同的翻译，分别是"bccfi", "bwfi", "bczi", "mcfi"和"mzi" 
//
// 
//
// 提示： 
//
// 
// 0 <= num < 2³¹ 
// 
// Related Topics 字符串 动态规划 👍 397 👎 0


package com.company.leetcode.editor.cn;

public class No_剑指Offer46_BaShuZiFanYiChengZiFuChuanLcof {
    public static void main(String[] args) {
        Solution solution = new No_剑指Offer46_BaShuZiFanYiChengZiFuChuanLcof().new Solution();
        System.out.println(solution.translateNum(506));
    }

    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        public int translateNum(int num) {
            return recusion(String.valueOf(num), 0);
        }

        private int recusion(String s, int idx) {
            //边界条件：如果到最后一位，只有1种可能0-9一一对应字母
            if (idx == s.length()) {
                return 1;
            }
            //两种选择：1.拿当前idx直接翻译 2.拿idx和idx+1（如存在）一起翻译,但是首位不能是0
            if (idx + 1 < s.length() && isLess26(s.charAt(idx), s.charAt(idx + 1)) && s.charAt(idx) != '0') {
                return recusion(s, idx + 1) + recusion(s, idx + 2);
            } else {
                return recusion(s, idx + 1);
            }
        }

        //两位数字是不是小于26
        private boolean isLess26(char s1, char s2) {
            return (s1 - '0') * 10 + (s2 - '0') < 26;
        }
    }
//leetcode submit region end(Prohibit modification and deletion)

}