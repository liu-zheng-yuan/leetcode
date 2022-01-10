//你是一个专业的小偷，计划偷窃沿街的房屋。每间房内都藏有一定的现金，影响你偷窃的唯一制约因素就是相邻的房屋装有相互连通的防盗系统，如果两间相邻的房屋在同一晚上
//被小偷闯入，系统会自动报警。 
//
// 给定一个代表每个房屋存放金额的非负整数数组，计算你 不触动警报装置的情况下 ，一夜之内能够偷窃到的最高金额。 
//
// 
//
// 示例 1： 
//
// 
//输入：[1,2,3,1]
//输出：4
//解释：偷窃 1 号房屋 (金额 = 1) ，然后偷窃 3 号房屋 (金额 = 3)。
//     偷窃到的最高金额 = 1 + 3 = 4 。 
//
// 示例 2： 
//
// 
//输入：[2,7,9,3,1]
//输出：12
//解释：偷窃 1 号房屋 (金额 = 2), 偷窃 3 号房屋 (金额 = 9)，接着偷窃 5 号房屋 (金额 = 1)。
//     偷窃到的最高金额 = 2 + 9 + 1 = 12 。
// 
//
// 
//
// 提示： 
//
// 
// 1 <= nums.length <= 100 
// 0 <= nums[i] <= 400 
// 
// Related Topics 数组 动态规划 👍 1825 👎 0


package com.company.leetcode.editor.cn;

public class No_One98_HouseRobber {
    public static void main(String[] args) {
        Solution solution = new No_One98_HouseRobber().new Solution();
    }

    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        //状态：i
        //选择：当前i 抢与不抢
        //转移方程：dp[i] = max(dp[i-1],dp[i-2]+nums[i]) 其实也可以写成max(dp[i+1],dp[i+2]+num[i]) 遍历循序不同
        //边界条件：i-1 == -1 ,0 i-2 == -1,0
        public int rob1(int[] nums) {
            int n = nums.length;
            int[] dp = new int[n];
            //
            for (int i = 0; i < n; i++) {
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
            return dp[n - 1];
        }

        public int rob(int[] nums) {
            int n = nums.length;
            int dp1 = 0;//前一个的状态
            int dp2 = 0;//前两个的状态
            //
            for (int i = 0; i < n; i++) {
                //根据边界条件手动计算i = 0 ，i = 1的情况
                if (i - 1 == -1) {
                    int t = dp1;
                    dp1 = nums[i];
                    dp2 = t;
                    continue;
                }
                if (i - 2 == -1) {
                    int t = dp1;
                    dp1 = Math.max(dp1, nums[i]);
                    dp2 = t;
                    continue;
                }
                int t = dp1;
                dp1 = Math.max(dp1, dp2 + nums[i]);
                dp2 = t;

            }
            return dp1;
        }
    }
//leetcode submit region end(Prohibit modification and deletion)

}