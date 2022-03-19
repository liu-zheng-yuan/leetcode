//输入一个整型数组，数组中的一个或连续多个整数组成一个子数组。求所有子数组的和的最大值。
//
// 要求时间复杂度为O(n)。 
//
// 
//
// 示例1: 
//
// 输入: nums = [-2,1,-3,4,-1,2,1,-5,4]
//输出: 6
//解释: 连续子数组 [4,-1,2,1] 的和最大，为 6。 
//
// 
//
// 提示： 
//
// 
// 1 <= arr.length <= 10^5 
// -100 <= arr[i] <= 100 
// 
//
// 注意：本题与主站 53 题相同：https://leetcode-cn.com/problems/maximum-subarray/ 
//
// 
// Related Topics 数组 分治 动态规划 👍 491 👎 0


package com.company.leetcode.editor.cn;

public class No_剑指Offer42_LianXuZiShuZuDeZuiDaHeLcof {
    public static void main(String[] args) {
        Solution solution = new No_剑指Offer42_LianXuZiShuZuDeZuiDaHeLcof().new Solution();
    }

    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        //如果是子序列，不用紧邻，就需要遍历i之前的所以j
        //但是本题是子数据，是紧邻的，所以只需要判断紧邻的前一个能不能组成更大的子数据和就行
        //dp[i]:以arr[i]结尾的最大子数据的和
        //初始状态：与LIS有区别！dp[0] = arr[0]，而不用给每个dp[i] = arr[i]这是错的
        public int maxSubArray(int[] nums) {
            int[] dp = new int[nums.length];
            dp[0] = nums[0];//初始化，以index = 0 结尾的子数组和因为没有更前面的元素，所以只能是0
            for (int i = 1; i < nums.length; i++) {
                dp[i] = Math.max(nums[i], dp[i - 1] + nums[i]);//如果前面是负的，就不如以自身为开头
            }
            int max = Integer.MIN_VALUE;
            for (int i = 0; i < dp.length; i++) {
                max = Math.max(dp[i], max);
            }
            return max;

        }
    }
//leetcode submit region end(Prohibit modification and deletion)

}