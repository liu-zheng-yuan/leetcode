//给定一个 m x n 二维字符网格 board 和一个字符串单词 word 。如果 word 存在于网格中，返回 true ；否则，返回 false 。
//
// 单词必须按照字母顺序，通过相邻的单元格内的字母构成，其中“相邻”单元格是那些水平相邻或垂直相邻的单元格。同一个单元格内的字母不允许被重复使用。 
//
// 
//
// 例如，在下面的 3×4 的矩阵中包含单词 "ABCCED"（单词中的字母已标出）。 
//
// 
//
// 
//
// 示例 1： 
//
// 
//输入：board = [["A","B","C","E"],["S","F","C","S"],["A","D","E","E"]], word = 
//"ABCCED"
//输出：true
// 
//
// 示例 2： 
//
// 
//输入：board = [["a","b"],["c","d"]], word = "abcd"
//输出：false
// 
//
// 
//
// 提示： 
//
// 
// 1 <= board.length <= 200 
// 1 <= board[i].length <= 200 
// board 和 word 仅由大小写英文字母组成 
// 
//
// 
//
// 注意：本题与主站 79 题相同：https://leetcode-cn.com/problems/word-search/ 
// Related Topics 数组 回溯 矩阵 👍 456 👎 0


package com.company.leetcode.editor.cn;

public class No_剑指_offer_12_JuZhenZhongDeLuJingLcof {
    public static void main(String[] args) {
        Solution solution = new No_剑指_offer_12_JuZhenZhongDeLuJingLcof().new Solution();
        char[][] board = new char[][]{{'a', 'a', 'a', 'a'}, {'a', 'a', 'a', 'a'}, {'a', 'a', 'a', 'b'}};
        System.out.println(solution.exist(board, "aaaaaaaaaaab"));
    }

    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        int[] rowDelta = new int[]{1, -1, 0, 0};
        int[] colDelta = new int[]{0, 0, -1, 1};


        public boolean exist(char[][] board, String word) {
            if (board.length == 0) {
                return false;
            }
            for (int i = 0; i < board.length; i++) {
                for (int j = 0; j < board[0].length; j++) {
                    if (search(board, word, 0, i, j)) {
                        return true;
                    }
                }
            }
            return false;
        }

        public boolean search(char[][] board, String word, int index, int row, int col) {
            //客观限制条件不满足
            if (!isValid(board, word, index, row, col)) {
                return false;
            }
            //业务限制条件不满足
            if (board[row][col] != word.charAt(index)) {
                return false;
            }
            //搜索终点
            if (index == word.length() - 1) {
                return true;
            }

            boolean result = false;
            //标记访问
            board[row][col] = '\0';
            for (int i = 0; i < 4; i++) {
                int nowRow = row + rowDelta[i];
                int nowCol = col + colDelta[i];
                result = result || search(board, word, index + 1, nowRow, nowCol);
            }
            board[row][col] = word.charAt(index);
            return result;
        }

        private boolean isValid(char[][] board, String word, int index, int row, int col) {
            return index < word.length() && row < board.length && col < board[0].length && row >= 0 && col >= 0 && index >= 0;
        }
    }
//leetcode submit region end(Prohibit modification and deletion)

}