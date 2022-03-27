//统计一个数字在排序数组中出现的次数。
//
// 
//
// 示例 1: 
//
// 
//输入: nums = [5,7,7,8,8,10], target = 8
//输出: 2 
//
// 示例 2: 
//
// 
//输入: nums = [5,7,7,8,8,10], target = 6
//输出: 0 
//
// 
//
// 提示： 
//
// 
// 0 <= nums.length <= 10⁵ 
// -10⁹ <= nums[i] <= 10⁹ 
// nums 是一个非递减数组 
// -10⁹ <= target <= 10⁹ 
// 
//
// 
//
// 注意：本题与主站 34 题相同（仅返回值不同）：https://leetcode-cn.com/problems/find-first-and-last-
//position-of-element-in-sorted-array/ 
// Related Topics 数组 二分查找 👍 287 👎 0


package com.company.leetcode.editor.cn;

public class No_剑指Offer53I_ZaiPaiXuShuZuZhongChaZhaoShuZiLcof {
    public static void main(String[] args) {
        Solution solution = new No_剑指Offer53I_ZaiPaiXuShuZuZhongChaZhaoShuZiLcof().new Solution();
    }

    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        //二分搜索找左边界：等价于 排序数组中小于target的元素的数量（即左边界的下标 [0,idx).size == idx ）  ===  nums[mid]=target时，right = mid-1  === 从右边逼近
        //重点考虑边界情况，taget比所有元素都大、小的情况：
        //大：left一直增加，达到length
        //小：right一直减小，达到-1；此时 left == 0，需要额外判断nums[left] 是否等于target（也可能左边界就是idx = 0）
        public int search(int[] nums, int target) {
            //标准套路
            int left = 0, right = nums.length - 1;
            while (left <= right) {
                int mid = (left + right) / 2;
                if (nums[mid] == target) {
                    right = mid - 1;
                } else if (nums[mid] > target) {
                    right = mid - 1;
                } else if (nums[mid] < target) {
                    left = mid + 1;
                }
            }
            //left指向resIdx，但要先判断边界条件:target大于所有数 || target小于所有数
            if (left == nums.length || nums[left] != target) {
                return 0;
            }
            //从左边界统计符合target的值
            int count = 0;
            for (int i = left; i < nums.length; i++) {
                if (nums[i] == target) {
                    count++;
                }
            }
            return count;

        }

        //二分搜索找右边界：等价于 排序数组中大于target的元素的数量（即 (idx,len).size == len - idx -1 ）  ===  nums[mid]=target时，left = mid+1  === 从左边逼近
        //重点考虑边界情况，taget比所有元素都大、小的情况：
        //大：left一直增加，达到length
        //小：right一直减小，达到-1；此时 left == 0，需要额外判断nums[left] 是否等于target（也可能左边界就是idx = 0）
        public int search1(int[] nums, int target) {
            //标准套路
            int left = 0, right = nums.length - 1;
            while (left <= right) {
                int mid = (left + right) / 2;
                if (nums[mid] == target) {
                    left = mid + 1;
                } else if (nums[mid] > target) {
                    right = mid - 1;
                } else if (nums[mid] < target) {
                    left = mid + 1;
                }
            }
            //通过left+1 》 right 来退出循环，所以resIdx = left -1 或者是 right
            //target大于所有数 ： left一直加到len === left-1需要判断是否等于target来确实是否是找到了而退出 === right需要判断是否等于target
            //target小于所有数：right一直减到-1 === right == -1
            if (right == -1 || nums[right] != target) {
                return 0;
            }
            //从左边界统计符合target的值
            int count = 0;
            for (int i = right; i >= 0; i--) {
                if (nums[i] == target) {
                    count++;
                }
            }
            return count;

        }
    }
//leetcode submit region end(Prohibit modification and deletion)

}