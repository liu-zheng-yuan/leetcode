//你将会获得一系列视频片段，这些片段来自于一项持续时长为 time 秒的体育赛事。这些片段可能有所重叠，也可能长度不一。 
//
// 使用数组 clips 描述所有的视频片段，其中 clips[i] = [starti, endi] 表示：某个视频片段开始于 starti 并于 
//endi 结束。 
//
// 甚至可以对这些片段自由地再剪辑： 
//
// 
// 例如，片段 [0, 7] 可以剪切成 [0, 1] + [1, 3] + [3, 7] 三部分。 
// 
//
// 我们需要将这些片段进行再剪辑，并将剪辑后的内容拼接成覆盖整个运动过程的片段（[0, time]）。返回所需片段的最小数目，如果无法完成该任务，则返回 -1
// 。 
//
// 
//
// 示例 1： 
//
// 
//输入：clips = [[0,2],[4,6],[8,10],[1,9],[1,5],[5,9]], time = 10
//输出：3
//解释：
//选中 [0,2], [8,10], [1,9] 这三个片段。
//然后，按下面的方案重制比赛片段：
//将 [1,9] 再剪辑为 [1,2] + [2,8] + [8,9] 。
//现在手上的片段为 [0,2] + [2,8] + [8,10]，而这些覆盖了整场比赛 [0, 10]。
// 
//
// 示例 2： 
//
// 
//输入：clips = [[0,1],[1,2]], time = 5
//输出：-1
//解释：
//无法只用 [0,1] 和 [1,2] 覆盖 [0,5] 的整个过程。
// 
//
// 示例 3： 
//
// 
//输入：clips = [[0,1],[6,8],[0,2],[5,6],[0,4],[0,3],[6,7],[1,3],[4,7],[1,4],[2,5],
//[2,6],[3,4],[4,5],[5,7],[6,9]], time = 9
//输出：3
//解释： 
//选取片段 [0,4], [4,7] 和 [6,9] 。
// 
//
// 示例 4： 
//
// 
//输入：clips = [[0,4],[2,8]], time = 5
//输出：2
//解释：
//注意，你可能录制超过比赛结束时间的视频。
// 
//
// 
//
// 提示： 
//
// 
// 1 <= clips.length <= 100 
// 0 <= starti <= endi <= 100 
// 1 <= time <= 100 
// 
// Related Topics 贪心 数组 动态规划 👍 261 👎 0


package com.company.leetcode.editor.cn;

import java.util.Arrays;

public class No_One024_VideoStitching {
    public static void main(String[] args) {
        Solution solution = new No_One024_VideoStitching().new Solution();
        System.out.println(solution.videoStitching(new int[][]{{0, 2}, {1, 6}, {3, 10}}, 10));
    }

    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        public int videoStitching(int[][] clips, int time) {
            Arrays.sort(clips, (a, b) -> {
                if (a[0] != b[0]) {
                    return a[0] - b[0];
                } else {
                    return b[1] - a[1];
                }
            });
            //第一个不是0，肯定搞不成
            if (clips[0][0] != 0) {
                return -1;
            }
            // 不能在循环外先把第一个选了，这样会跳过第一个就满足条件的情况，除非再在循环外手动加一个是否满足条件的判断
//            int count = 1;//先选第一个
//            int curEnd = clips[0][1];//当前选择区间的end
//            int nextEnd = 0;//下一个将要选择区间的end
//            int idx = 1;//0号位肯定要选，从下一个1号位开始
            //先从什么都不选开始
            int idx = 0;
            int count = 0;
            int curEnd = 0;//因为第一个区间的start一定是0，所以一定满足 clips[0][0] <= curEnd(0) 的条件
            int nextEnd = 0;
            while (idx < clips.length && clips[idx][0] <= curEnd) {
                //找到start小于当前所选区间End的区间里end最大的那个区间
                while (idx < clips.length && clips[idx][0] <= curEnd) {
                    nextEnd = Math.max(nextEnd, clips[idx][1]);
                    idx++;
                }
                count++;//找到end最大的区间，就是找到一个要拼接的区间
                curEnd = nextEnd;
                if (nextEnd >= time) {
                    return count;
                }
            }
            return -1;
        }
    }
//leetcode submit region end(Prohibit modification and deletion)

}