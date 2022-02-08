//在一个 n * m 的二维数组中，每一行都按照从左到右递增的顺序排序，每一列都按照从上到下递增的顺序排序。请完成一个高效的函数，输入这样的一个二维数组和一个
//整数，判断数组中是否含有该整数。 
//
// 
//
// 示例: 
//
// 现有矩阵 matrix 如下： 
//
// 
//[
//  [1,   4,  7, 11, 15],
//  [2,   5,  8, 12, 19],
//  [3,   6,  9, 16, 22],
//  [10, 13, 14, 17, 24],
//  [18, 21, 23, 26, 30]
//]
// 
//
// 给定 target = 5，返回 true。 
//
// 给定 target = 20，返回 false。 
//
// 
//
// 限制： 
//
// 0 <= n <= 1000 
//
// 0 <= m <= 1000 
//
// 
//
// 注意：本题与主站 240 题相同：https://leetcode-cn.com/problems/search-a-2d-matrix-ii/ 
// Related Topics 数组 二分查找 分治 矩阵 👍 543 👎 0


package com.company.leetcode.editor.cn;

public class No_剑指Offer04_ErWeiShuZuZhongDeChaZhaoLcof {
    public static void main(String[] args) {
        Solution solution = new No_剑指Offer04_ErWeiShuZuZhongDeChaZhaoLcof().new Solution();
        System.out.println(solution.findNumberIn2DArray(new int[][]{
                {1, 4, 7, 11, 15}, {2, 5, 8, 12, 19}, {3, 6, 9, 16, 22}, {10, 13, 14, 17, 24}, {18, 21, 23, 26, 30}
        }, 5));
    }

    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        //右上角满足一个性质：同时是（行的）最大值和（列的）最小值。所以可以根据这个值来二分，有两种选择（减行或者减列）；同理左下角也是
        //但左上和右下，同时是最小值（最大值）。当target大于左上时，不能转移状态（缩小问题范围），因为可能target在行上或者列上
        public boolean findNumberIn2DArray(int[][] matrix, int target) {
            if (matrix.length == 0) {
                return false;
            }
            int row = matrix.length;
            int col = matrix[0].length;
            //初始用右上角
            int curRow = 0;
            int curCol = col - 1;
            while (curRow >= 0 && curRow < row && curCol >= 0 && curCol < col) {
                //类似二分逻辑
                int curValue = matrix[curRow][curCol];
                if (curValue == target) {
                    return true;
                } else if (target > curValue) {
                    curRow++;
                } else if (target < curValue) {
                    curCol--;
                }
            }
            return false;
        }
    }
//leetcode submit region end(Prohibit modification and deletion)

}