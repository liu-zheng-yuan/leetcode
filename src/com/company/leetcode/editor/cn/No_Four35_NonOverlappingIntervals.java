//给定一个区间的集合，找到需要移除区间的最小数量，使剩余区间互不重叠。
//
// 注意: 
//
// 
// 可以认为区间的终点总是大于它的起点。 
// 区间 [1,2] 和 [2,3] 的边界相互“接触”，但没有相互重叠。 
// 
//
// 示例 1: 
//
// 
//输入: [ [1,2], [2,3], [3,4], [1,3] ]
//
//输出: 1
//
//解释: 移除 [1,3] 后，剩下的区间没有重叠。
// 
//
// 示例 2: 
//
// 
//输入: [ [1,2], [1,2], [1,2] ]
//
//输出: 2
//
//解释: 你需要移除两个 [1,2] 来使剩下的区间没有重叠。
// 
//
// 示例 3: 
//
// 
//输入: [ [1,2], [2,3] ]
//
//输出: 0
//
//解释: 你不需要移除任何区间，因为它们已经是无重叠的了。
// 
// Related Topics 贪心 数组 动态规划 排序 👍 576 👎 0


package com.company.leetcode.editor.cn;

import java.util.Arrays;

public class No_Four35_NonOverlappingIntervals {
    public static void main(String[] args) {
        Solution solution = new No_Four35_NonOverlappingIntervals().new Solution();
    }

    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        //区间问题：用贪心
        //重叠区间：区间调度问题，找到有多少个互不重叠的区间即可
        //每次都找到“结束时间”最小的区间
        public int eraseOverlapIntervals(int[][] intervals) {
            return intervals.length - notOverlapIntervals(intervals);
        }

        public int notOverlapIntervals(int[][] intervals) {
            //排序，按end升序
            Arrays.sort(intervals, (a, b) -> a[1] - b[1]);
            //每次选择end最小的，并排除重叠的
            int count = 1;
            int minEnd = intervals[0][1];
            for (int[] interval : intervals) {
                int curStart = interval[0];
                if (curStart >= minEnd) { //等于也是区间无重叠
                    count++;
                    minEnd = interval[1];
                }
            }
            return count;
        }
    }
//leetcode submit region end(Prohibit modification and deletion)

}