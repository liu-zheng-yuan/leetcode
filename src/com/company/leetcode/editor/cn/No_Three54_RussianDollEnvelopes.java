//给你一个二维整数数组 envelopes ，其中 envelopes[i] = [wi, hi] ，表示第 i 个信封的宽度和高度。
//
// 当另一个信封的宽度和高度都比这个信封大的时候，这个信封就可以放进另一个信封里，如同俄罗斯套娃一样。 
//
// 请计算 最多能有多少个 信封能组成一组“俄罗斯套娃”信封（即可以把一个信封放到另一个信封里面）。 
//
// 注意：不允许旋转信封。 
// 
//
// 示例 1： 
//
// 
//输入：envelopes = [[5,4],[6,4],[6,7],[2,3]]
//输出：3
//解释：最多信封的个数为 3, 组合为: [2,3] => [5,4] => [6,7]。 
//
// 示例 2： 
//
// 
//输入：envelopes = [[1,1],[1,1],[1,1]]
//输出：1
// 
//
// 
//
// 提示： 
//
// 
// 1 <= envelopes.length <= 5000 
// envelopes[i].length == 2 
// 1 <= wi, hi <= 10⁴ 
// 
// Related Topics 数组 二分查找 动态规划 排序 👍 635 👎 0


package com.company.leetcode.editor.cn;

import java.util.Arrays;

public class No_Three54_RussianDollEnvelopes {
    public static void main(String[] args) {
        Solution solution = new No_Three54_RussianDollEnvelopes().new Solution();
        System.out.println(solution.maxEnvelopes(new int[][]{{5, 4}, {6, 4}, {6, 7}, {2, 3}}));
    }

    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        /**
         * 最长递增子序列的变种：
         * 先对宽度w进行升序排序，如果遇到w相同的情况，则按照高度h降序排序。之后把所有的h作为一个数组，在这个数组上计算 LIS 的长度就是答案。
         * 因为两个宽度相同的信封不能相互包含的，而逆序排序保证在w相同的数对中最多只选取一个计入 LIS。
         * 三维，比如箱子就不能这么搞了，需要【树状数组】
         */
        /**
         * 比较器的记法：返回负数表示不用交换位置；返回正数表示需要交换位置
         * 比如o1,o2，o2在后面，比较器实现为 o2.x-o1.x
         * 如果o2.x > o1.x，则比较器返回正数，表示需要调整位置，则o2在前面，降序排列
         * 如果o2.x < o1.x，则比较器返回负数，表示不需要调整位置。则o1在前面，还是降序排列
         */
        public int maxEnvelopes(int[][] envelopes) {
            Arrays.sort(envelopes, (o1, o2) -> {
                //先按w升序排列
                if (o1[0] != o2[0]) {
                    return o1[0] - o2[0];
                } else {
                    return o2[1] - o1[1];
                }
            });
            int[] heights = new int[envelopes.length];
            for (int i = 0; i < envelopes.length; i++) {
                heights[i] = envelopes[i][1];
            }
            //最长递增子序列
            int[] dp = new int[heights.length + 1];
            Arrays.fill(dp, 1);
            //
            for (int i = 1; i < dp.length; i++) {
                for (int j = 1; j < i; j++) {
                    if (heights[j - 1] < heights[i - 1]) {
                        dp[i] = Math.max(dp[i], dp[j] + 1);
                    }
                }
            }
            int res = 0;
            for (int i = 0; i < dp.length; i++) {
                res = Math.max(res, dp[i]);
            }
            return res;
        }
    }
//leetcode submit region end(Prohibit modification and deletion)

}