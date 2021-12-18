  //编写一个函数，其作用是将输入的字符串反转过来。输入字符串以字符数组 s 的形式给出。 
//
// 不要给另外的数组分配额外的空间，你必须原地修改输入数组、使用 O(1) 的额外空间解决这一问题。 
//
// 
//
// 示例 1： 
//
// 
//输入：s = ["h","e","l","l","o"]
//输出：["o","l","l","e","h"]
// 
//
// 示例 2： 
//
// 
//输入：s = ["H","a","n","n","a","h"]
//输出：["h","a","n","n","a","H"] 
//
// 
//
// 提示： 
//
// 
// 1 <= s.length <= 10⁵ 
// s[i] 都是 ASCII 码表中的可打印字符 
// 
// Related Topics 递归 双指针 字符串 👍 497 👎 0

  
  package com.company.leetcode.editor.cn;
  public class No_Three44_ReverseString{
      public static void main(String[] args) {
           Solution solution = new No_Three44_ReverseString().new Solution();
      }
      //leetcode submit region begin(Prohibit modification and deletion)
class Solution {
    public void reverseString(char[] s) {
        int left = 0;
        int right = s.length-1;

        //区间虽然是[left ,right],但是按题意当区间只剩一个元素时，没必要交换，所以退出条件是 left <right
        while (left < right) {
            char t = s[right];
            s[right] = s[left];
            s[left] = t;
            left++;
            right--;
        }
    }
}
//leetcode submit region end(Prohibit modification and deletion)

  }