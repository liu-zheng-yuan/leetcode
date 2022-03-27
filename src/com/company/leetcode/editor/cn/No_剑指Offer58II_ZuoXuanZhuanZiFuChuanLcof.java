//字符串的左旋转操作是把字符串前面的若干个字符转移到字符串的尾部。请定义一个函数实现字符串左旋转操作的功能。比如，输入字符串"abcdefg"和数字2，该函数
//将返回左旋转两位得到的结果"cdefgab"。 
//
// 
//
// 示例 1： 
//
// 输入: s = "abcdefg", k = 2
//输出: "cdefgab"
// 
//
// 示例 2： 
//
// 输入: s = "lrloseumgh", k = 6
//输出: "umghlrlose"
// 
//
// 
//
// 限制： 
//
// 
// 1 <= k < s.length <= 10000 
// 
// Related Topics 数学 双指针 字符串 👍 216 👎 0


package com.company.leetcode.editor.cn;

public class No_剑指Offer58II_ZuoXuanZhuanZiFuChuanLcof {
    public static void main(String[] args) {
        Solution solution = new No_剑指Offer58II_ZuoXuanZhuanZiFuChuanLcof().new Solution();
    }

    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        //1.全部翻转
        //2.翻转左边，翻转右边
        public String reverseLeftWords(String s, int n) {
            char[] cc = s.toCharArray();
            reverse(cc, 0, cc.length - 1);
            reverse(cc, 0, cc.length - n - 1);
            reverse(cc, cc.length - n, cc.length - 1);
            return new String(cc);
        }

        //翻转[left,right]
        public void reverse(char[] cc, int left, int right) {
            while (left < right) {
                char t = cc[left];
                cc[left] = cc[right];
                cc[right] = t;
                //
                left++;
                right--;
            }
        }
    }
//leetcode submit region end(Prohibit modification and deletion)

}