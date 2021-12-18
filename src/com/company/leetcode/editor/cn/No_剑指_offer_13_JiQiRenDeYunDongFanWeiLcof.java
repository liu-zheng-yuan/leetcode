//地上有一个m行n列的方格，从坐标 [0,0] 到坐标 [m-1,n-1] 。一个机器人从坐标 [0, 0] 的格子开始移动，它每次可以向左、右、上、下移动一
//格（不能移动到方格外），也不能进入行坐标和列坐标的数位之和大于k的格子。例如，当k为18时，机器人能够进入方格 [35, 37] ，因为3+5+3+7=18。但
//它不能进入方格 [35, 38]，因为3+5+3+8=19。请问该机器人能够到达多少个格子？ 
//
// 
//
// 示例 1： 
//
// 输入：m = 2, n = 3, k = 1
//输出：3
// 
//
// 示例 2： 
//
// 输入：m = 3, n = 1, k = 0
//输出：1
// 
//
// 提示： 
//
// 
// 1 <= n,m <= 100 
// 0 <= k <= 20 
// 
// Related Topics 深度优先搜索 广度优先搜索 动态规划 👍 388 👎 0


package com.company.leetcode.editor.cn;

public class No_剑指_offer_13_JiQiRenDeYunDongFanWeiLcof {
    public static void main(String[] args) {
        Solution solution = new No_剑指_offer_13_JiQiRenDeYunDongFanWeiLcof().new Solution();
        System.out.println(solution.movingCount(2, 3, 17));
    }

    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        int[] rowDelta = new int[]{1, 0, -1, 0};
        int[] colDelta = new int[]{0, 1, 0, -1};

        public int movingCount(int m, int n, int k) {
            int[][] flag = new int[m][n];
            flag[0][0] = 1;
            return dfs(m, n, k, 0, 0, flag);
        }

        public int dfs(int m, int n, int k, int row, int col, int[][] flag) {

            int sum = 0;
            for (int i = 0; i < 4; i++) {
                int nowRow = row + rowDelta[i];
                int nowCol = col + colDelta[i];
                if (isValid(m, n, nowRow, nowCol, flag) && digitalSum(nowRow) + digitalSum(nowCol) <= k) {
                    flag[nowRow][nowCol] = 1;
                    sum += dfs(m, n, k, nowRow, nowCol, flag);

                }
            }
            return sum + 1;

        }

        private boolean isValid(int m, int n, int nowRow, int nowCol, int[][] flag) {
            return nowRow < m && nowCol < n && nowCol >= 0 && nowRow >= 0 && flag[nowRow][nowCol] == 0;
        }


        public int digitalSum(int num) {
            int ans = 0;
            while (num > 0) {
                ans = ans + num % 10;
                num = num / 10;
            }
            return ans;
        }
    }
//leetcode submit region end(Prohibit modification and deletion)

}