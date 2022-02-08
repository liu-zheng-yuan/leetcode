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
// Related Topics 数组 回溯 矩阵 👍 489 👎 0


package com.company.leetcode.editor.cn;

import com.company.leetcode.editor.cn.solution.LOG;

import java.util.HashMap;
import java.util.Map;

public class No_剑指Offer12_JuZhenZhongDeLuJingLcof {
    public static void main(String[] args) {
        Solution solution = new No_剑指Offer12_JuZhenZhongDeLuJingLcof().new Solution();
        solution.exist(new char[][]{{'A', 'B', 'C', 'E'}, {'S', 'F', 'E', 'S'}, {'A', 'D', 'E', 'E'}}, "ABCESEEEFS");
    }

    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        int[] XX = new int[]{1, -1, 0, 0};
        int[] YY = new int[]{0, 0, 1, -1};
        Map<String, Boolean> map = new HashMap<>();

        public boolean exist(char[][] board, String word) {
            for (int i = 0; i < board.length; i++) {
                for (int j = 0; j < board[0].length; j++) {
                    if (board[i][j] == word.charAt(0)) {
                        if (dp(board, word, i, j, 0)) {
                            return true;
                        } else {
                            map = new HashMap<>();
                        }
                    }
                }
            }
            return false;
        }

        private boolean dp(char[][] board, String word, int row, int col, int idx) {
            LOG.printIn();
            LOG.log("row", row, "col", col, "idx", idx, "char", word.charAt(idx));
            //
            if (!(row >= 0 && row < board.length && col >= 0 && col < board[0].length)) {
                LOG.printOut();
                LOG.log("row", row, "col", col, "idx", idx, "char", word.charAt(idx));
                return false;
            }
            if (board[row][col] != word.charAt(idx)) {
                LOG.printOut();
                LOG.log("row", row, "col", col, "idx", idx, "char", word.charAt(idx));
                return false;
            }
            if (idx == word.length() - 1) {
                LOG.printOut();
                LOG.log("row", row, "col", col, "idx", idx, "char", word.charAt(idx));
                return true;
            }
            //记忆化
            String key = String.valueOf(row) + "-" + String.valueOf(col) + "-" + String.valueOf(idx);
//            if (map.containsKey(key)) {
//                return map.get(key);
//            }
            //
            boolean res = false;
            //赋值代替visited数组
            board[row][col] = '\0';
            for (int i = 0; i < 4; i++) {
                int nextRow = row + YY[i];
                int nexCol = col + XX[i];
                res = res || dp(board, word, nextRow, nexCol, idx + 1);
            }
            board[row][col] = word.charAt(idx);//回溯，因为只有相等的情况下才能走到这里，所以直接赋值即可
//            map.put(key, res);
            LOG.printOut();
            LOG.log("row", row, "col", col, "idx", idx, "char", word.charAt(idx));
            return res;
        }
    }
//leetcode submit region end(Prohibit modification and deletion)

}