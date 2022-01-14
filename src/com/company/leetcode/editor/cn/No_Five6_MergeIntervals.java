//以数组 intervals 表示若干个区间的集合，其中单个区间为 intervals[i] = [starti, endi] 。请你合并所有重叠的区间，并返
//回一个不重叠的区间数组，该数组需恰好覆盖输入中的所有区间。 
//
// 
//
// 示例 1： 
//
// 
//输入：intervals = [[1,3],[2,6],[8,10],[15,18]]
//输出：[[1,6],[8,10],[15,18]]
//解释：区间 [1,3] 和 [2,6] 重叠, 将它们合并为 [1,6].
// 
//
// 示例 2： 
//
// 
//输入：intervals = [[1,4],[4,5]]
//输出：[[1,5]]
//解释：区间 [1,4] 和 [4,5] 可被视为重叠区间。 
//
// 
//
// 提示： 
//
// 
// 1 <= intervals.length <= 10⁴ 
// intervals[i].length == 2 
// 0 <= starti <= endi <= 10⁴ 
// 
// Related Topics 数组 排序 👍 1259 👎 0


package com.company.leetcode.editor.cn;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class No_Five6_MergeIntervals {
    public static void main(String[] args) {
        Solution solution = new No_Five6_MergeIntervals().new Solution();
        System.out.println(solution.merge(new int[][]{{1, 3}, {2, 6}, {8, 10}, {15, 18}}));
    }

    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        public int[][] merge(int[][] intervals) {
            Arrays.sort(intervals, (a, b) -> {
                if (a[0] == b[0]) {
                    return b[1] - a[1];
                } else {
                    return a[0] - b[0];
                }
            });
            //
            if (intervals.length <= 1) {
                return intervals;
            }
            List<int[]> res = new ArrayList<>();
            int curLeft = intervals[0][0];
            int curRight = intervals[0][1];
            for (int i = 1; i < intervals.length; i++) {
                int nextLeft = intervals[i][0];
                int nextRight = intervals[i][1];
                //完全覆盖，则什么都不做
                if (curLeft <= nextLeft && nextRight <= curRight) {
                    continue;
                } else if (nextLeft <= curRight && nextRight > curRight) {
                    //部分重叠，需要合并
                    curRight = nextRight;
                } else if (curRight <= nextLeft) {
                    //与当前区间不重叠，找到一个合并完成的区间
                    res.add(new int[]{curLeft, curRight});
                    curLeft = nextLeft;
                    curRight = nextRight;
                }
            }
            //注意：循环的最后不会将curLeft，curRight这个区间加进res，需要手动加
            res.add(new int[]{curLeft, curRight});
            //
            return res.toArray(new int[][]{});
        }
    }
//leetcode submit region end(Prohibit modification and deletion)

}