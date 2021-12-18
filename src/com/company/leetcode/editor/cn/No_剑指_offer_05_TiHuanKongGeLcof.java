//请实现一个函数，把字符串 s 中的每个空格替换成"%20"。
//
// 
//
// 示例 1： 
//
// 输入：s = "We are happy."
//输出："We%20are%20happy." 
//
// 
//
// 限制： 
//
// 0 <= s 的长度 <= 10000 
// Related Topics 字符串 👍 174 👎 0


package com.company.leetcode.editor.cn;

public class No_剑指_offer_05_TiHuanKongGeLcof {
    public static void main(String[] args) {
        Solution solution = new No_剑指_offer_05_TiHuanKongGeLcof().new Solution();
        System.out.println(solution.replaceSpace("We are happy."));
    }

    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        public String replaceSpace(String s) {
            char[] chars = s.toCharArray();
            int spaceNum = 0;
            for (int i = 0; i < chars.length; i++) {
                if (chars[i] == ' ') {
                    spaceNum++;
                }
            }
            char[] newChars = new char[chars.length + 2 * spaceNum];
            int idx = chars.length - 1;
            int newIdx = newChars.length - 1;
            while (idx >= 0) {
                if (chars[idx] == ' ') {
                    newChars[newIdx] = '0';
                    newChars[newIdx - 1] = '2';
                    newChars[newIdx - 2] = '%';
                    newIdx = newIdx - 3;
                    idx--;
                } else {
                    newChars[newIdx] = chars[idx];
                    newIdx--;
                    idx--;
                }
            }
            return new String(newChars);
        }
    }
//leetcode submit region end(Prohibit modification and deletion)

}