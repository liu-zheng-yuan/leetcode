//给你一个 只包含正整数 的 非空 数组 nums 。请你判断是否可以将这个数组分割成两个子集，使得两个子集的元素和相等。
//
// 
//
// 示例 1： 
//
// 
//输入：nums = [1,5,11,5]
//输出：true
//解释：数组可以分割成 [1, 5, 5] 和 [11] 。 
//
// 示例 2： 
//
// 
//输入：nums = [1,2,3,5]
//输出：false
//解释：数组不能分割成两个元素和相等的子集。
// 
//
// 
//
// 提示： 
//
// 
// 1 <= nums.length <= 200 
// 1 <= nums[i] <= 100 
// 
// Related Topics 数组 动态规划 👍 1129 👎 0


package com.company.leetcode.editor.cn;

import java.util.Arrays;

public class No_Four16_PartitionEqualSubsetSum {
    public static void main(String[] args) {
        Solution solution = new No_Four16_PartitionEqualSubsetSum().new Solution();
    }

    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        /**
         * 两个子集元素和相等 == 一个子集的元素和是总的一班（一个确定值） == 每个元素只能被选取一次 == 零一背包 == 回溯法选元素，有选和不选两种选择
         * 1.回溯+剪枝：还是会超时，剪枝能剪的不多。只有在刚好经过不同路径达到 idx-sum一直的情况下才能剪
         */
//        int target = 0;
//        Map<String, Boolean> memory = new HashMap<>();
//
//        public boolean canPartition(int[] nums) {
//            int sum = 0;
//            sum = Arrays.stream(nums).sum();
//            if (sum % 2 == 1) { //奇数肯定不可能
//                return false;
//            }
//            target = sum / 2;
//            return recusion(nums, 0, 0);
//
//        }
//
//        //从idx = 0开始选取对象，选或者不选，到最后看能否让sum == target
//        private boolean recusion(int[] nums, int idx, int sum) {
//            if (idx == nums.length) {
//                return sum == target;
//            }
//            String key = String.valueOf(idx) + "-" + String.valueOf(sum);
//            if (memory.containsKey(key)) {
//                return memory.get(key);
//            }
//            //选当前
//            sum += nums[idx];
//            boolean choose = recusion(nums, idx + 1, sum);
//            sum -= nums[idx]; //特意为了体现回溯法，正常不需要这么写
//            //不选
//            boolean notChoose = recusion(nums, idx + 1, sum);
//            memory.put(key, choose || notChoose);
//            return memory.get(key);
//        }

        /**
         * 2.只能选一次的零一背包问题
         * 给一个可装载重量为sum/2的背包和N个物品，每个物品的重量为nums[i]。现在让你装物品，是否存在一种装法，能够恰好将背包装满？
         */
        public boolean canPartition(int[] nums) {
            int sum = 0;
            sum = Arrays.stream(nums).sum();
            if (sum % 2 == 1) { //奇数肯定不可能
                return false;
            }
            //对于前i个物品，当前背包的容量为j时，若dp为true，则说明可以恰好将背包装满，若dp为false，则说明不能恰好将背包装满。
            boolean[][] dp = new boolean[nums.length + 1][sum / 2+1];
            //初始化：背包空间等0 就是装满了
            for (int i = 0; i <= nums.length; i++) {
                dp[i][0] = true;
            }
            //选择
            //需要i-1，因为我们计数从1开始，给没有元素即0留了位置，但原始数组从0开始计数
            for (int i = 1; i <= nums.length; i++) {
                for (int j = 1; j <= sum / 2; j++) {
                    //能装下：两种选择
                    if (j >= nums[i - 1]) {
                        dp[i][j] = dp[i - 1][j - nums[i - 1]] || dp[i - 1][j];
                    } else {
                        //不能装下：只能选不装
                        dp[i][j] = dp[i - 1][j];
                    }
                }
            }
            return dp[nums.length][sum / 2];
        }
    }
//leetcode submit region end(Prohibit modification and deletion)

}