//编写一个程序，通过填充空格来解决数独问题。
//
// 数独的解法需 遵循如下规则： 
//
// 
// 数字 1-9 在每一行只能出现一次。 
// 数字 1-9 在每一列只能出现一次。 
// 数字 1-9 在每一个以粗实线分隔的 3x3 宫内只能出现一次。（请参考示例图） 
// 
//
// 数独部分空格内已填入了数字，空白格用 '.' 表示。 
//
// 
//
// 
// 
// 
// 示例： 
//
// 
//输入：board = [["5","3",".",".","7",".",".",".","."],["6",".",".","1","9","5",".
//",".","."],[".","9","8",".",".",".",".","6","."],["8",".",".",".","6",".",".",".
//","3"],["4",".",".","8",".","3",".",".","1"],["7",".",".",".","2",".",".",".","6
//"],[".","6",".",".",".",".","2","8","."],[".",".",".","4","1","9",".",".","5"],[
//".",".",".",".","8",".",".","7","9"]]
//输出：[["5","3","4","6","7","8","9","1","2"],["6","7","2","1","9","5","3","4","8
//"],["1","9","8","3","4","2","5","6","7"],["8","5","9","7","6","1","4","2","3"],[
//"4","2","6","8","5","3","7","9","1"],["7","1","3","9","2","4","8","5","6"],["9",
//"6","1","5","3","7","2","8","4"],["2","8","7","4","1","9","6","3","5"],["3","4",
//"5","2","8","6","1","7","9"]]
//解释：输入的数独如上图所示，唯一有效的解决方案如下所示：
//
//
// 
//
// 
//
// 提示： 
//
// 
// board.length == 9 
// board[i].length == 9 
// board[i][j] 是一位数字或者 '.' 
// 题目数据 保证 输入数独仅有一个解 
// 
// 
// 
// 
// Related Topics 数组 回溯 矩阵 👍 1048 👎 0


package com.company.leetcode.editor.cn;

public class No_Three7_SudokuSolver {
    public static void main(String[] args) {
        Solution solution = new No_Three7_SudokuSolver().new Solution();
    }

    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        public void solveSudoku(char[][] board) {
            recursion(board, 0, 0);
        }

        private boolean recursion(char[][] board, int row, int col) {

            //最终结束:【注意】这里不能是row==8 && col == 8，这样最后一个元素还没有被遍历到
            if (row == board.length) {
                return true;//通过返回true快速结束
            }

            //边界条件：到这一行末尾，转移到下一行起始
            if (col == board[0].length) {
                return recursion(board, row + 1, 0);
            }
            //排除当前节点是有数字的情况
            if (board[row][col] != '.') {
                return recursion(board, row, col + 1);
            }
            //做选择：对于board[row][col]尝试不同的数字
            for (char i = '1'; i <= '9'; i++) {
                if (!isValid(board, row, col, i)) {
                    continue;
                }
                board[row][col] = i;
                if (recursion(board, row, col + 1)) {
                    return true;
                }
                board[row][col] = '.';
            }
            return false;
        }

        private boolean isValid(char[][] board, int row, int col, char n) {
            for (char i = 0; i < 9; i++) {
                if (board[i][col] == n) {
                    return false;
                }
                if (board[row][i] == n) {
                    return false;
                }
                if (board[(row / 3) * 3 + i / 3][(col / 3) * 3 + i % 3] == n) {
                    return false;
                }
            }
            return true;
        }


    }
//leetcode submit region end(Prohibit modification and deletion)

}