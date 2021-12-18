//在一个 2 x 3 的板上（board）有 5 块砖瓦，用数字 1~5 来表示, 以及一块空缺用 0 来表示.
//
// 一次移动定义为选择 0 与一个相邻的数字（上下左右）进行交换. 
//
// 最终当板 board 的结果是 [[1,2,3],[4,5,0]] 谜板被解开。 
//
// 给出一个谜板的初始状态，返回最少可以通过多少次移动解开谜板，如果不能解开谜板，则返回 -1 。 
//
// 示例： 
//
// 
//输入：board = [[1,2,3],[4,0,5]]
//输出：1
//解释：交换 0 和 5 ，1 步完成
// 
//
// 
//输入：board = [[1,2,3],[5,4,0]]
//输出：-1
//解释：没有办法完成谜板
// 
//
// 
//输入：board = [[4,1,2],[5,0,3]]
//输出：5
//解释：
//最少完成谜板的最少移动次数是 5 ，
//一种移动路径:
//尚未移动: [[4,1,2],[5,0,3]]
//移动 1 次: [[4,1,2],[0,5,3]]
//移动 2 次: [[0,1,2],[4,5,3]]
//移动 3 次: [[1,0,2],[4,5,3]]
//移动 4 次: [[1,2,0],[4,5,3]]
//移动 5 次: [[1,2,3],[4,5,0]]
// 
//
// 
//输入：board = [[3,2,4],[1,5,0]]
//输出：14
// 
//
// 提示： 
//
// 
// board 是一个如上所述的 2 x 3 的数组. 
// board[i][j] 是一个 [0, 1, 2, 3, 4, 5] 的排列. 
// 
// Related Topics 广度优先搜索 数组 矩阵 👍 236 👎 0


package com.company.leetcode.editor.cn;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Set;

public class No_Seven73_SlidingPuzzle {
    public static void main(String[] args) {
        Solution solution = new No_Seven73_SlidingPuzzle().new Solution();
        int[][] xx = new int[][]{{1, 2, 3}, {5, 4, 0}};
        System.out.println(solution.slidingPuzzle(xx));
        System.out.println();
    }

    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        public int slidingPuzzle(int[][] board) {
            Queue<String> queue = new LinkedList<>();
            Set<String> visited = new HashSet<>();
            int count = 0;
            //将二维数组打平成一维，即上下两层拼到同一层上。
            //定义：第i位存 打平后一维数组中第i个数字 在原来二维平面上邻接的数字 的对应到一维数组上的下表。
            //比如(0,0)相邻的元素是(0,1)和(1,0)，这两个在打平后的一维数组的index是1和4.
            int[][] adj = new int[][]{
                    {1, 3},
                    {0, 2, 4},
                    {1, 5},
                    {0, 4},
                    {1, 3, 5},
                    {2, 4}
            };
            //生成初始局面
            StringBuilder sb = new StringBuilder();
            for (int i = 0; i < board.length; i++) {
                for (int j = 0; j < board[0].length; j++) {
                    sb.append(board[i][j]);
                }
            }
            String init = sb.toString();
            queue.offer(init);
            visited.add(init);
            //框架
            while (!queue.isEmpty()) {
                int curSize = queue.size();
                for (int i = 0; i < curSize; i++) {
                    String cur = queue.poll();
                    //找到0对应的index，才能查表知道相邻节点是谁
                    int index = 0;
                    for (int j = 0; j < 6; j++) {
                        if (cur.charAt(j) == '0') {
                            index = j;
                            break;
                        }
                    }
                    //终点
                    if (cur.equals("123450")) {
                        return count;
                    }
                    for (int adjIdx : adj[index]) {
                        char[] chars = cur.toCharArray();
                        char t = chars[adjIdx];
                        chars[adjIdx] = '0';
                        chars[index] = t;
                        //
                        String next = new String(chars);
                        if (!visited.contains(next)) {
                            queue.offer(next);
                            visited.add(next);
                        }
                    }
                }
                count++;
            }
            return -1;
        }
    }
//leetcode submit region end(Prohibit modification and deletion)

}