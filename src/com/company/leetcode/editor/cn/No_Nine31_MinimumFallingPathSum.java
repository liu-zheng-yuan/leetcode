//给你一个 n x n 的 方形 整数数组 matrix ，请你找出并返回通过 matrix 的下降路径 的 最小和 。
//
// 下降路径 可以从第一行中的任何元素开始，并从每一行中选择一个元素。在下一行选择的元素和当前行所选元素最多相隔一列（即位于正下方或者沿对角线向左或者向右的第
//一个元素）。具体来说，位置 (row, col) 的下一个元素应当是 (row + 1, col - 1)、(row + 1, col) 或者 (row + 1
//, col + 1) 。 
//
// 
//
// 示例 1： 
//
// 
//输入：matrix = [[2,1,3],[6,5,4],[7,8,9]]
//输出：13
//解释：下面是两条和最小的下降路径，用加粗+斜体标注：
//[[2,1,3],      [[2,1,3],
// [6,5,4],       [6,5,4],
// [7,8,9]]       [7,8,9]]
// 
//
// 示例 2： 
//
// 
//输入：matrix = [[-19,57],[-40,-5]]
//输出：-59
//解释：下面是一条和最小的下降路径，用加粗+斜体标注：
//[[-19,57],
// [-40,-5]]
// 
//
// 示例 3： 
//
// 
//输入：matrix = [[-48]]
//输出：-48
// 
//
// 
//
// 提示： 
//
// 
// n == matrix.length 
// n == matrix[i].length 
// 1 <= n <= 100 
// -100 <= matrix[i][j] <= 100 
// 
// Related Topics 数组 动态规划 矩阵 👍 139 👎 0


package com.company.leetcode.editor.cn;

import java.util.Arrays;

public class No_Nine31_MinimumFallingPathSum {
    public static void main(String[] args) {
        Solution solution = new No_Nine31_MinimumFallingPathSum().new Solution();
    }

    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        /**
         * 状态：row，col
         * 定义：dp[i,j]从第一层[0,..]到[i,j]的路径最小值,结果是 {min(dp[i,j])|j in [0,n-1] && i = n-1)}
         * 转移：dp[i,j] = min(dp[i-1,j-1],dp[i-1,j],dp[i-1,j+1]) + dp[i,j]
         * 边界条件：dp[0,..] = matrix[0,..]
         */
        int n;
        int[][] memory;

        public int minFallingPathSum(int[][] matrix) {
            n = matrix.length;
            int res = Integer.MAX_VALUE;
            // 初始化备忘录
            //为啥要初始化呢？正常dp数据遍历过程中，隐含了“后面逻辑依赖的数据一定先算出来了”，而dfs+备忘录不能保证，不知道1.什么时候去到的是默认值（能覆盖，不能用） 2.什么时候去到的是之前计算过的值（能用，不能覆盖）
            // 所以要初始化一个自己确定的默认值且这个值是超过上限的，方便判断
            memory = new int[n][n];
            for (int i = 0; i < n; i++) {
                Arrays.fill(memory[i], Integer.MAX_VALUE - 1);
            }

            //可能落到最后一行的任意一列，需要遍历最后一行
            for (int j = 0; j < n; j++) {
                res = Math.min(res, dp(matrix, n - 1, j));
            }
            return res;
        }

        public int dp(int[][] martix, int i, int j) {
            if (i < 0 || j < 0 || j >= n || i >= n) {
                return Integer.MAX_VALUE;
            }
            //由于逻辑里赋值了第一行的初始值，就不用在初始化阶段给memory赋值了
            if (i == 0) {
                return martix[i][j];
            }
            if (memory[i][j] != Integer.MAX_VALUE - 1) {
                return memory[i][j];
            }
            memory[i][j] = Math.min(dp(martix, i - 1, j), Math.min(dp(martix, i - 1, j - 1), dp(martix, i - 1, j + 1))) + martix[i][j];
            return memory[i][j];
        }
//
//        不带备忘录的dp
//        public int dp(int[][] martix, int i, int j) {
//            if (i < 0 || j < 0 || j >= n || i >= n) {
//                //为了保证min有效，所以越界情况，返回个MAXINT
//                return Integer.MAX_VALUE;
//            }
//            //初始值
//            if (i == 0) {
//                return martix[i][j];
//            }
//            return Math.min(dp(martix, i - 1, j), Math.min(dp(martix, i - 1, j), dp(martix, i - 1, j + 1))) + martix[i][j];
//        }
    }
//leetcode submit region end(Prohibit modification and deletion)

}