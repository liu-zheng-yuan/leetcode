//你是一个专业的小偷，计划偷窃沿街的房屋，每间房内都藏有一定的现金。这个地方所有的房屋都 围成一圈 ，这意味着第一个房屋和最后一个房屋是紧挨着的。同时，相邻的
//房屋装有相互连通的防盗系统，如果两间相邻的房屋在同一晚上被小偷闯入，系统会自动报警 。 
//
// 给定一个代表每个房屋存放金额的非负整数数组，计算你 在不触动警报装置的情况下 ，今晚能够偷窃到的最高金额。 
//
// 
//
// 示例 1： 
//
// 
//输入：nums = [2,3,2]
//输出：3
//解释：你不能先偷窃 1 号房屋（金额 = 2），然后偷窃 3 号房屋（金额 = 2）, 因为他们是相邻的。
// 
//
// 示例 2： 
//
// 
//输入：nums = [1,2,3,1]
//输出：4
//解释：你可以先偷窃 1 号房屋（金额 = 1），然后偷窃 3 号房屋（金额 = 3）。
//     偷窃到的最高金额 = 1 + 3 = 4 。 
//
// 示例 3： 
//
// 
//输入：nums = [0]
//输出：0
// 
//
// 
//
// 提示： 
//
// 
// 1 <= nums.length <= 100 
// 0 <= nums[i] <= 1000 
// 
// Related Topics 数组 动态规划 👍 880 👎 0


package com.company.leetcode.editor.cn;

public class No_Two13_HouseRobberIi {
    public static void main(String[] args) {
        Solution solution = new No_Two13_HouseRobberIi().new Solution();
    }

    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        //首尾不能同时选：有三种可能
        //1.不选最后一个，[0,n-2] 2.不选第一个 [1,n-1] 3.都不选 [1,n-2] （由于前两者选择范围比第三个大，所以第三个可有可无）
        //取三者最大值（相当于手动再找了三个子问题，取最值）
        //状态：i
        //选择：当前i 抢与不抢
        //转移方程：dp[i] = max(dp[i-1],dp[i-2]+nums[i]) 其实也可以写成max(dp[i+1],dp[i+2]+num[i]) 遍历循序不同
        //边界条件：i-1 == -1 ,0 i-2 == -1,0
        public int rob(int[] nums) {
            if (nums.length == 1) {
                return nums[0];
            }
            return Math.max(dp(nums, 0, nums.length - 2), dp(nums, 1, nums.length - 1));
        }

        public int dp(int[] nums, int start, int end) {
            int n = nums.length;
            int[] dp = new int[n];
            //
            for (int i = start; i <= end; i++) {
                //可以兼容传入的start = 0或者1 的情况，因为数组dp[i-1]默认是0
                //根据边界条件手动计算i = 0 ，i = 1的情况
                if (i - 1 == -1) {
                    dp[i] = nums[i];
                    continue;
                }
                if (i - 2 == -1) {
                    dp[i] = Math.max(dp[i - 1], nums[i]);
                    continue;
                }
                dp[i] = Math.max(dp[i - 1], dp[i - 2] + nums[i]);
            }
            return dp[end];
        }
    }
//leetcode submit region end(Prohibit modification and deletion)

}