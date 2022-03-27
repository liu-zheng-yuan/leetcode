  //从若干副扑克牌中随机抽 5 张牌，判断是不是一个顺子，即这5张牌是不是连续的。2～10为数字本身，A为1，J为11，Q为12，K为13，而大、小王为 0 ，
//可以看成任意数字。A 不能视为 14。 
//
// 
//
// 示例 1: 
//
// 
//输入: [1,2,3,4,5]
//输出: True 
//
// 
//
// 示例 2: 
//
// 
//输入: [0,0,1,2,5]
//输出: True 
//
// 
//
// 限制： 
//
// 数组长度为 5 
//
// 数组的数取值为 [0, 13] . 
// Related Topics 数组 排序 👍 217 👎 0

  
  package com.company.leetcode.editor.cn;

  import javax.swing.plaf.IconUIResource;
  import java.util.Arrays;
  import java.util.Collections;

  public class No_剑指Offer61_BuKePaiZhongDeShunZiLcof{
      public static void main(String[] args) {
           Solution solution = new No_剑指Offer61_BuKePaiZhongDeShunZiLcof().new Solution();
      }
      //leetcode submit region begin(Prohibit modification and deletion)
class Solution {
    public boolean isStraight(int[] nums) {
        //先排序。统计非0元素的差值，差值之和要是4.且不能有非0的重复元素
        //如果有0元素，则count0 + 正常差值和4 >= diffNum。意思是如果元素之间相差过大，0也补不回来了
        int diffSum = 0;
        int count0 = 0;
        Arrays.sort(nums);
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] == 0) {
                count0++;
                continue;
            }
            if (i + 1 < nums.length) {
                if (nums[i] == nums[i + 1]) {
                    return false;
                }
                diffSum += nums[i + 1] - nums[i];
            }
        }
        return count0 + 4 >= diffSum;

    }
}
//leetcode submit region end(Prohibit modification and deletion)

  }