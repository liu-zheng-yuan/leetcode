//给你一个整数数组 nums 和一个整数 target 。 
//
// 向数组中的每个整数前添加 '+' 或 '-' ，然后串联起所有整数，可以构造一个 表达式 ： 
//
// 
// 例如，nums = [2, 1] ，可以在 2 之前添加 '+' ，在 1 之前添加 '-' ，然后串联起来得到表达式 "+2-1" 。 
// 
//
// 返回可以通过上述方法构造的、运算结果等于 target 的不同 表达式 的数目。 
//
// 
//
// 示例 1： 
//
// 
//输入：nums = [1,1,1,1,1], target = 3
//输出：5
//解释：一共有 5 种方法让最终目标和为 3 。
//-1 + 1 + 1 + 1 + 1 = 3
//+1 - 1 + 1 + 1 + 1 = 3
//+1 + 1 - 1 + 1 + 1 = 3
//+1 + 1 + 1 - 1 + 1 = 3
//+1 + 1 + 1 + 1 - 1 = 3
// 
//
// 示例 2： 
//
// 
//输入：nums = [1], target = 1
//输出：1
// 
//
// 
//
// 提示： 
//
// 
// 1 <= nums.length <= 20 
// 0 <= nums[i] <= 1000 
// 0 <= sum(nums[i]) <= 1000 
// -1000 <= target <= 1000 
// 
// Related Topics 数组 动态规划 回溯 👍 982 👎 0


package com.company.leetcode.editor.cn;

import java.util.Arrays;

public class No_Four94_TargetSum {
    public static void main(String[] args) {
        Solution solution = new No_Four94_TargetSum().new Solution();
        System.out.println(solution.findTargetSumWays(new int[]{1}, 1));
    }

    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        /**
         * 1.bfs+备忘录：子集划分问题，O(2^N)
         * dp函数定义：从idx开始积累到target的表达式的数目
         */
//        int res = 0; 备忘录之后就不需要这个res了
//        HashMap<String, Integer> memory = new HashMap<>();
//
//        public int findTargetSumWays(int[] nums, int target) {
//            if (nums.length == 0) return 0;
//            return dp(nums, 0, 0, target);
//
//        }
//
//        private int dp(int[] nums, int idx, int acc, int target) {
//            //实际就是个子集问题，每个状态有两种选择，正号或者负号
//            if (idx == nums.length) {
//                if (acc == target) {
//                    return 1;
//                }
//                return 0;
//            }
//            //备忘录
//            //注意：将当前的【所有状态】都当做状态记录的key
//            //同时，由于是Map，也不存在初始化的问题
//            String key = idx + "." + acc;
//            if (memory.containsKey(key)) {
//                return memory.get(key);
//            }
//            //状态转移:只有两种选择
//            /**
//             * 当前状态（i，acc）的数目，等于 两次选择返回结果【之和】
//             */
//            int curSum = 0;
//            for (char c : new Character[]{'+', '-'}) {
//                if (c == '+') {
//                    acc += nums[idx];
//                    curSum += dp(nums, idx + 1, acc, target);
//                    acc -= nums[idx];
//                } else {
//                    acc -= nums[idx];
//                    curSum += dp(nums, idx + 1, acc, target);
//                    acc += nums[idx];
//                }
//            }
//            //保存记忆化
//            memory.put(key, curSum);
//            return curSum;
//        }


        /**
         * 2.动态规划：可以转化为一个子集问题，而子集问题又是背包问题
         * nums中添加+号的是A，添加-号的是B，这样就分成了两个子集，使得SumA-SumB = target。
         * 即SumA+SumA = target + SumB +SumA 即 sumA =（target+Sum（nums））/2
         * 即找到一个子集，使得子集的和等于（target+Sum（nums））/2
         * 即 背包问题：一共有n个元素，每个元素都最多选一次，总容量是（target+Sum（nums））/2 ，有几种不同的办法能装满这个包
         */
        public int findTargetSumWays(int[] nums, int target) {
            int sum = 0;
            for (int i = 0; i < nums.length; i++) {
                sum += nums[i];
            }
            int goal = (target + sum) / 2;
            if (goal < 0 || (target + sum) % 2 == 1) {
                return 0;//除不尽就表示没有可能
            }
            //背包问题：若只在前i个物品中做选择，若目标和是j，则有dp[i][j]种方法
            //因为数组从0开始，所以都要+1，i表示下标为i-1的元素
//            int[][] dp1 = new int[nums.length + 1][goal + 1];
//            //边界条件：如果没有物品，则肯定满足不了目标和，就是0；如果目标和是0，装法“只有一个”：就是什么都不选
//            Arrays.fill(dp1[0], 0);
//            for (int i = 0; i < nums.length + 1; i++) {
//                dp1[i][0] = 1;
//            }
//            //转移方程：
//            //如果j小于当前元素大小，那只有一种选择：不选，继承上个i的状态
//            //如果j大于等于当前元素大小，那需要把“选择”和“不选”两种可能的方法“累加”
//            //没有状态压缩：
//            for (int i = 1; i < nums.length + 1; i++) {
//                for (int j = 0; j < goal + 1; j++) {
//                    if (j < nums[i - 1]) {
//                        //只有一种选择：不选当前元素
//                        dp1[i][j] = dp1[i - 1][j];
//                    } else {
//                        dp1[i][j] = dp1[i - 1][j] + dp1[i - 1][j - nums[i - 1]];
//                    }
//                }
//            }
            //有状态压缩
            int[] dp2 = new int[goal + 1];
            dp2[0] = 1;//边界条件也要投影到降维
            for (int i = 1; i < nums.length + 1; i++) {
                for (int j = goal; j >= 0; j--) { //由于计算当前状态需要用到【左侧的历史状态】，所以只能【从右到左】，避免【左侧状态被覆盖】
                    if (j < nums[i - 1]) {
                        dp2[j] = dp2[j];
                    } else {
                        dp2[j] = dp2[j] + dp2[j - nums[i - 1]];
                    }
                }
            }
            //
            return dp2[goal];

        }
    }
//leetcode submit region end(Prohibit modification and deletion)

}