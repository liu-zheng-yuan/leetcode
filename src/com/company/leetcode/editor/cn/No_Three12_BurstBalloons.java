//有 n 个气球，编号为0 到 n - 1，每个气球上都标有一个数字，这些数字存在数组 nums 中。
//
// 现在要求你戳破所有的气球。戳破第 i 个气球，你可以获得 nums[i - 1] * nums[i] * nums[i + 1] 枚硬币。 这里的 i -
// 1 和 i + 1 代表和 i 相邻的两个气球的序号。如果 i - 1或 i + 1 超出了数组的边界，那么就当它是一个数字为 1 的气球。 
//
// 求所能获得硬币的最大数量。 
//
// 
//示例 1：
//
// 
//输入：nums = [3,1,5,8]
//输出：167
//解释：
//nums = [3,1,5,8] --> [3,5,8] --> [3,8] --> [8] --> []
//coins =  3*1*5    +   3*5*8   +  1*3*8  + 1*8*1 = 167 
//
// 示例 2： 
//
// 
//输入：nums = [1,5]
//输出：10
// 
//
// 
//
// 提示： 
//
// 
// n == nums.length 
// 1 <= n <= 500 
// 0 <= nums[i] <= 100 
// 
// Related Topics 数组 动态规划 👍 860 👎 0


package com.company.leetcode.editor.cn;

public class No_Three12_BurstBalloons {
    public static void main(String[] args) {
        Solution solution = new No_Three12_BurstBalloons().new Solution();
    }

    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        //最简单的思路：要求戳破的顺序，就暴力枚举所有的戳破顺序 ==》 全排列问题 O（N！），每获得一次戳破顺序就计算出该顺序下的硬币数，找最大的

        /**
         * 一个很先验的思路：这个直觉上不知道怎么写，很难 ==》 单状态一维DP肯定搞不了 ==》试试看二维DP ==》因为只有一个数据对象，结合回文子序列的经验
         * ==》 状态确定为数组起始和结尾[i,j]
         * 定义：dp[i][j]表示nums(i..j)戳破所有气球获得的最大值（之所以是开区间，是因为做了预处理：对于超出边界的情况，可以在0和n+1放上虚拟气球 1，把原来的nums放在1-n，所以最终解答就是dp[0][n+1]）
         * 状态和选择：对于nums[i..j]，可以做的选择：k in [i,j] 选一个K作为【最后一个戳的气球】，num[i,k-1] num[k+1,j]这两个区间已经戳完了，结果就是dp[i][k] dp[k][j].而戳完这两个区间之后，第k个气球和第i，j个气球，直接相邻，【子问题直接不再相互影响】
         * 转移方程：选一个k最后去戳，戳完获得的钱是nums[i]*nums[k]*nums[j]（因为ikj中间的都被戳破了，他们直接相邻）,剩下的区间num[i,k] num[k,j] 对应着dp[i,k]和dp[k,j] (因为是开区间，所以不用+1 -1)
         * 边界条件：若j<=i时，区间没有意义，都是0
         */
        public int maxCoins(int[] nums) {

            int[][] dp = new int[nums.length + 2][nums.length + 2];
            int[] coins = new int[nums.length + 2];//把超出边界的两个虚拟节点加进去
            coins[0] = 1;
            coins[coins.length - 1] = 1;
            for (int i = 0; i < nums.length; i++) {
                coins[i + 1] = nums[i];
            }
            //边界条件 都是0 默认成立

            //头尾虚拟节点能遍历到，因为是开区间
            //由于依赖 【左边所有节点的状态】 和 【下方所有节点的状态】 所以需要倒着遍历
            for (int i = coins.length - 1; i >= 0; i--) {
                for (int j = i + 1; j < coins.length; j++) {
                    //当前状态的选择：选择k去戳破
                    for (int k = i + 1; k < j; k++) {
                        dp[i][j] = Math.max(dp[i][j],
                                coins[i] * coins[k] * coins[j] + dp[i][k] + dp[k][j]
                        );
                    }
                }
            }
            return dp[0][coins.length - 1];

        }

        //错误思路：做选择时把K当做“第一次戳破的气球”，虽然戳完之后剩下num[i,k) num(k,j],这两个区间的结果“看起来”可以用dp代替
        //但是当戳破第K个时，计算dp[i][k]需要用到coin[k]，而此时第K个气球已经被戳破了，实际上不能用coin[k]
        // 这就是【子问题不再独立了，解决一个子问题时影响了另一个子问题的解】
        //其实可以类比为：dp计算依赖之前的状态，如果正着遍历没发获取到依赖的状态，就倒着遍历
        //本题也是类似，“正着从1开始选戳破的气球”会影响其他子问题，“倒着选最后一个戳破的气球”不会影响其他子问题
        public int maxCoinsWrong(int[] nums) {

            int[][] dp = new int[nums.length + 2][nums.length + 2];
            int[] coins = new int[nums.length + 2];//把超出边界的两个虚拟节点加进去
            coins[0] = 1;
            coins[coins.length - 1] = 1;
            for (int i = 0; i < nums.length; i++) {
                coins[i + 1] = nums[i];
            }
            //边界条件 都是0 默认成立

            //头尾虚拟节点不能遍历到
            //
            for (int i = 1; i < coins.length - 1; i++) {
                for (int j = i + 1; j < coins.length - 1; j++) {
                    //当前状态的选择：选择k去戳破
                    for (int k = i + 1; k < j; k++) {
                        dp[i][j] = Math.max(dp[i][j],
                                coins[k - 1] * coins[k] * coins[k + 1] + dp[i][k] + dp[k][j] //错了
                        );
                    }
                }
            }
            return dp[0][coins.length - 1];

        }
    }
//leetcode submit region end(Prohibit modification and deletion)

}