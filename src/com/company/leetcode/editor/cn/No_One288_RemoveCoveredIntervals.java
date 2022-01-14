  //给你一个区间列表，请你删除列表中被其他区间所覆盖的区间。 
//
// 只有当 c <= a 且 b <= d 时，我们才认为区间 [a,b) 被区间 [c,d) 覆盖。 
//
// 在完成所有删除操作后，请你返回列表中剩余区间的数目。 
//
// 
//
// 示例： 
//
// 
//输入：intervals = [[1,4],[3,6],[2,8]]
//输出：2
//解释：区间 [3,6] 被区间 [2,8] 覆盖，所以它被删除了。
// 
//
// 
//
// 提示： 
//
// 
// 1 <= intervals.length <= 1000 
// 0 <= intervals[i][0] < intervals[i][1] <= 10^5 
// 对于所有的 i != j：intervals[i] != intervals[j] 
// 
// Related Topics 数组 排序 👍 57 👎 0

  
  package com.company.leetcode.editor.cn;

  import java.util.Arrays;

  public class No_One288_RemoveCoveredIntervals{
      public static void main(String[] args) {
           Solution solution = new No_One288_RemoveCoveredIntervals().new Solution();
      }
      //leetcode submit region begin(Prohibit modification and deletion)
class Solution {
    public int removeCoveredIntervals(int[][] intervals) {
        int count = 0;
        if (intervals.length <= 1) {
            return intervals.length;
        }
        //按左端点升序
        Arrays.sort(intervals, (a, b) -> {
            if (a[0] == b[0]) {
                return b[1] - a[1];
            } else {
                return a[0] - b[0];
            }
        });
        //当前区间的左右边界，为了后续合并大区间以及判断是否重叠
        int curLeft = intervals[0][0];
        int curRight = intervals[0][1];//先默认从0开始，因为数组元素小于1的情况已经被排除了
        for (int i = 1; i < intervals.length; i++) {
            //1.下个区间与当前区间重叠
            int nextLeft = intervals[i][0];
            int nextRight = intervals[i][1];
            if (curLeft <= nextLeft && nextRight <= curRight) {
                count++;
            } else if (curLeft <= nextLeft && nextRight > curRight) {
                //2.有重叠，需要合并成更大的区间
                curRight = nextRight;
            } else if (curRight <= nextLeft) {
                //3.完全不想交的
                curLeft = nextLeft;
                curRight = nextRight;
            }
        }
        return intervals.length - count;
    }
}
//leetcode submit region end(Prohibit modification and deletion)

  }