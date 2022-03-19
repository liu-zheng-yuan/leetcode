//输入一个整数数组，实现一个函数来调整该数组中数字的顺序，使得所有奇数在数组的前半部分，所有偶数在数组的后半部分。
//
// 
//
// 示例： 
//
// 
//输入：nums = [1,2,3,4]
//输出：[1,3,2,4] 
//注：[3,1,2,4] 也是正确的答案之一。 
//
// 
//
// 提示： 
//
// 
// 0 <= nums.length <= 50000 
// 0 <= nums[i] <= 10000 
// 
// Related Topics 数组 双指针 排序 👍 207 👎 0


package com.company.leetcode.editor.cn;

public class No_剑指Offer21_DiaoZhengShuZuShunXuShiQiShuWeiYuOuShuQianMianLcof {
    public static void main(String[] args) {
        Solution solution = new No_剑指Offer21_DiaoZhengShuZuShunXuShiQiShuWeiYuOuShuQianMianLcof().new Solution();
    }

    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        public int[] exchange(int[] nums) {
            int left = 0;
            int right = nums.length - 1;
            //类似快拍的思路，不过更简单，从后先前找到第一个不满足的，从前向后找到第一个不满足的，交换。重复上述流程
            while (left < right) {
                while (left < right && nums[right] % 2 == 0) {
                    right--;
                }
                //此时right所在的位置不满足条件：偶数
                while (left < right && nums[left] % 2 == 1) {
                    left++;
                }
                //此时left所在位置不满足条件：奇数
                //所以left在偶数上，right在奇数上，交换这两者的位置
                int t = nums[left];
                nums[left] = nums[right];
                nums[right] = t;
            }
            return nums;

        }
    }
//leetcode submit region end(Prohibit modification and deletion)

}