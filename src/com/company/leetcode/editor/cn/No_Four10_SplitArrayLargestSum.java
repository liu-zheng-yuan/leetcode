//给定一个非负整数数组 nums 和一个整数 m ，你需要将这个数组分成 m 个非空的连续子数组。
//
// 设计一个算法使得这 m 个子数组各自和的最大值最小。 
//
// 
//
// 示例 1： 
//
// 
//输入：nums = [7,2,5,10,8], m = 2
//输出：18
//解释：
//一共有四种方法将 nums 分割为 2 个子数组。 其中最好的方式是将其分为 [7,2,5] 和 [10,8] 。
//因为此时这两个子数组各自的和的最大值为18，在所有情况中最小。 
//
// 示例 2： 
//
// 
//输入：nums = [1,2,3,4,5], m = 2
//输出：9
// 
//
// 示例 3： 
//
// 
//输入：nums = [1,4,4], m = 3
//输出：4
// 
//
// 
//
// 提示： 
//
// 
// 1 <= nums.length <= 1000 
// 0 <= nums[i] <= 10⁶ 
// 1 <= m <= min(50, nums.length) 
// 
// Related Topics 贪心 数组 二分查找 动态规划 👍 590 👎 0


package com.company.leetcode.editor.cn;

public class No_Four10_SplitArrayLargestSum {
    public static void main(String[] args) {
        Solution solution = new No_Four10_SplitArrayLargestSum().new Solution();
    }

    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        /**
         * 题目固定给出了m，那么m就是target，且因变量代表 可以把数组分为多少个连续子数组
         * 题目要求和的最大值最小，那么自变量就是 连续子数组和的最大值，求的是左边界
         */
        public int splitArray(int[] nums, int m) {
            //注意上下界:自变量-子数组和的最大值X 肯定要大于每个元素，不然和的最大值只会比X更大
            int max = Integer.MIN_VALUE;
            int sum = 0;
            for (int i = 0; i < nums.length; i++) {
                if (nums[i] > max) {
                    max = nums[i];
                }
                sum += nums[i];
            }
            int left = max;
            int right = sum;//所有子数组和的最大值的最大的情况，就是子数组等于原数组

            //单调递减
            while (left <= right) {
                int mid = left + (right - left) / 2;
                int midM = f(mid, nums);
                if (midM == m) {
                    right = mid - 1;
                } else if (midM > m) {
                    left = mid + 1;
                } else if (midM < m) {
                    right = mid - 1;
                }
            }
            return left;
        }

        //当子数组和最大值“不超过”maxSum时，能分成几个连续子数组
        private int f(int maxSum, int[] nums) {
            int count = 0;
            int index = 0;
            int curSum = 0;
            count++;//第一个连续子数组
            while (index < nums.length) {
                //默认maxSum肯定大于数组里每一个元素
                if (curSum + nums[index] <= maxSum) {
                    curSum += nums[index];
                    index++;
                } else {
                    //不满足条件了，需要重新开一个连续子数组
                    curSum = 0;
                    count++;
                }
            }
            return count;

        }
    }
//leetcode submit region end(Prohibit modification and deletion)

}