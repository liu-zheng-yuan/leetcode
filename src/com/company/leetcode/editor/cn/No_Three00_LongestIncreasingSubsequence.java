//给你一个整数数组 nums ，找到其中最长严格递增子序列的长度。
//
// 子序列是由数组派生而来的序列，删除（或不删除）数组中的元素而不改变其余元素的顺序。例如，[3,6,2,7] 是数组 [0,3,1,6,2,2,7] 的子序
//列。 
// 
//
// 示例 1： 
//
// 
//输入：nums = [10,9,2,5,3,7,101,18]
//输出：4
//解释：最长递增子序列是 [2,3,7,101]，因此长度为 4 。
// 
//
// 示例 2： 
//
// 
//输入：nums = [0,1,0,3,2,3]
//输出：4
// 
//
// 示例 3： 
//
// 
//输入：nums = [7,7,7,7,7,7,7]
//输出：1
// 
//
// 
//
// 提示： 
//
// 
// 1 <= nums.length <= 2500 
// -10⁴ <= nums[i] <= 10⁴ 
// 
//
// 
//
// 进阶： 
//
// 
// 你可以设计时间复杂度为 O(n²) 的解决方案吗？ 
// 你能将算法的时间复杂度降低到 O(n log(n)) 吗? 
// 
// Related Topics 数组 二分查找 动态规划 👍 2108 👎 0


package com.company.leetcode.editor.cn;

import java.util.Arrays;

public class No_Three00_LongestIncreasingSubsequence {
    public static void main(String[] args) {
        Solution solution = new No_Three00_LongestIncreasingSubsequence().new Solution();
    }

    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        public int lengthOfLIS(int[] nums) {
            //状态：下标index
            //定义：dp[i]表示以i为结尾的最长递增子序列的长度
            //转移：数学归纳法：已知dp[0..k-1]成立，怎么证明dp[k]成立
            /**
             * 找到[0..k-1]中每一个小于num[k]的dp[k`],这样num[k]可以尝试接在dp[k`]之后组成一个新的最长递增子序列，找到dp[k`]+1的最大值 就是dp[k]
             * dp[i] = {max(dp[j]+1)| j in [0,l-1] && num[i] > num[j]}
             */
            //边界条件：dp数组默认是1，因为只有自己一个元素也是一个子序列
            int[] dp = new int[nums.length ];
            Arrays.fill(dp,1);
            for (int i = 0; i < nums.length; i++) {
                for (int j = 0; j < i; j++) {
                    if (nums[i] > nums[j]) {
                        dp[i] = Math.max(dp[i], dp[j] + 1);
                    }
                }
            }
            int result = Integer.MIN_VALUE;
            for (int i = 0; i < nums.length; i++) {
                result = Math.max(dp[i], result);
            }
            return result;
        }
    }
//leetcode submit region end(Prohibit modification and deletion)

}