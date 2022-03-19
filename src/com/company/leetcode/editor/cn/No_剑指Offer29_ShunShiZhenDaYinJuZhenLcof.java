//输入一个矩阵，按照从外向里以顺时针的顺序依次打印出每一个数字。
//
// 
//
// 示例 1： 
//
// 输入：matrix = [[1,2,3],[4,5,6],[7,8,9]]
//输出：[1,2,3,6,9,8,7,4,5]
// 
//
// 示例 2： 
//
// 输入：matrix = [[1,2,3,4],[5,6,7,8],[9,10,11,12]]
//输出：[1,2,3,4,8,12,11,10,9,5,6,7]
// 
//
// 
//
// 限制： 
//
// 
// 0 <= matrix.length <= 100 
// 0 <= matrix[i].length <= 100 
// 
//
// 注意：本题与主站 54 题相同：https://leetcode-cn.com/problems/spiral-matrix/ 
// Related Topics 数组 矩阵 模拟 👍 373 👎 0


package com.company.leetcode.editor.cn;

import java.util.ArrayList;
import java.util.List;

public class No_剑指Offer29_ShunShiZhenDaYinJuZhenLcof {
    public static void main(String[] args) {
        Solution solution = new No_剑指Offer29_ShunShiZhenDaYinJuZhenLcof().new Solution();
        int[][] ma = new int[][]{{1}, {2}, {3}};
        System.out.println(solution.spiralOrder(ma));
    }

    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        List<Integer> res = new ArrayList<>();

        public int[] spiralOrder(int[][] matrix) {
            if (matrix.length == 0 || matrix[0].length == 0) {
                return new int[]{};
            }
            //递归解决逻辑复杂的问题，每层递归只遍历周围一圈
            //递归函数入参是左上顶点（a1,b1)  右下顶点(a2,b2)
            int row = matrix.length;
            int col = matrix[0].length;
            recusion(matrix, 0, 0, row - 1, col - 1);
            return res.stream().mapToInt(a -> a).toArray();
        }

        //分别代表左上坐标和右下左边，只遍历周围一圈
        private void recusion(int[][] matrix, int a1, int b1, int a2, int b2) {
            if (a1 >= matrix.length || b1 >= matrix[0].length || a1 > a2 || b1 > b2) {
                return;
            }
            //遍历到最中心点，左上右下顶点重合时，需要特殊处理，下面的左闭右开区间不能处理这种情况，会漏掉中间的
            if (a1 == a2 && b1 == b2) {
                res.add(matrix[a1][b1]);
                return;
            }
            //下面的逻辑在只有一行或一列的情况下会有问题，比如{{1,2,3}} 所以加一个特殊逻辑
            if (a1 == a2) {
                for (int i = b1; i <= b2; i++) {
                    res.add(matrix[a1][i]);
                }
                return;
            }

            if (b1 == b2) {
                for (int i = a1; i <= a2; i++) {
                    res.add(matrix[i][b1]);
                }
                return;
            }
            //遍历四条边,每次都是包含起点节点而不包含终点节点
            //(a1,b1)    (a1,b2)
            //(a2,b1)    (a2,b2)

            //1.(a1,b1)  ->  (a1,b2)
            for (int i = b1; i < b2; i++) {
                res.add(matrix[a1][i]);
            }
            //2 (a1,b2) ->  (a2,b2)
            for (int i = a1; i < a2; i++) {
                res.add(matrix[i][b2]);
            }
            //3.(a2,b1)  <-  (a2,b2)
            for (int i = b2; i > b1; i--) {
                res.add(matrix[a2][i]);
            }
            //4.(a1,b1)  <- (a2,b1)
            for (int i = a2; i > a1; i--) {
                res.add(matrix[i][b1]);
            }
            //搞下一层
            recusion(matrix, a1 + 1, b1 + 1, a2 - 1, b2 - 1);
        }
    }
//leetcode submit region end(Prohibit modification and deletion)

}