//在一个 m*n 的棋盘的每一格都放有一个礼物，每个礼物都有一定的价值（价值大于 0）。你可以从棋盘的左上角开始拿格子里的礼物，并每次向右或者向下移动一格、直
//到到达棋盘的右下角。给定一个棋盘及其上面的礼物的价值，请计算你最多能拿到多少价值的礼物？ 
//
// 
//
// 示例 1: 
//
// 输入: 
//[
//  [1,3,1],
//  [1,5,1],
//  [4,2,1]
//]
//输出: 12
//解释: 路径 1→3→5→2→1 可以拿到最多价值的礼物 
//
// 
//
// 提示： 
//
// 
// 0 < grid.length <= 200 
// 0 < grid[0].length <= 200 
// 
// Related Topics 数组 动态规划 矩阵 👍 268 👎 0


package com.company.leetcode.editor.cn;

public class No_剑指Offer47_LiWuDeZuiDaJieZhiLcof {
    public static void main(String[] args) {
        Solution solution = new No_剑指Offer47_LiWuDeZuiDaJieZhiLcof().new Solution();
    }

    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        //dp[i][j]= max(dp[i-1][j] dp[i][j-1])+num[i][j]
        public int maxValue(int[][] grid) {
            int[][] dp = new int[grid.length][grid[0].length];
            int row = grid.length;
            int col = grid[0].length;
            //初始值
            dp[0][0] = grid[0][0];
            for (int i = 1; i < col; i++) {
                dp[0][i] = dp[0][i - 1] + grid[0][i];
            }
            for (int i = 1; i < row; i++) {
                dp[i][0] = dp[i - 1][0] + grid[i][0];
            }
            //
            for (int i = 1; i < row; i++) {
                for (int j = 1; j < col; j++) {
                    dp[i][j] = Math.max(dp[i - 1][j], dp[i][j - 1]) + grid[i][j];
                }
            }
            //
            return dp[row - 1][col - 1];

        }
    }
//leetcode submit region end(Prohibit modification and deletion)

}