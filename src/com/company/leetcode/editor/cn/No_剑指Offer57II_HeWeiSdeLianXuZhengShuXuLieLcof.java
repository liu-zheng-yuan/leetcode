//输入一个正整数 target ，输出所有和为 target 的连续正整数序列（至少含有两个数）。
//
// 序列内的数字由小到大排列，不同序列按照首个数字从小到大排列。 
//
// 
//
// 示例 1： 
//
// 输入：target = 9
//输出：[[2,3,4],[4,5]]
// 
//
// 示例 2： 
//
// 输入：target = 15
//输出：[[1,2,3,4,5],[4,5,6],[7,8]]
// 
//
// 
//
// 限制： 
//
// 
// 1 <= target <= 10^5 
// 
//
// 
// Related Topics 数学 双指针 枚举 👍 398 👎 0


package com.company.leetcode.editor.cn;

import java.util.ArrayList;
import java.util.List;

public class No_剑指Offer57II_HeWeiSdeLianXuZhengShuXuLieLcof {
    public static void main(String[] args) {
        Solution solution = new No_剑指Offer57II_HeWeiSdeLianXuZhengShuXuLieLcof().new Solution();
    }

    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        //需要数学公式求和：sum = (r+l)*(r-l+1) /2 区间所有元素的和
        //注意到当l或r固定时，sum关于l或r是单调的。可以用双指针的思路
        //sum < target:left ++ >target:right--,== target 把[l,r]所有元素都放入result
        public int[][] findContinuousSequence(int target) {
            //起始点不是数组index，而是实际的数，所以从12开始
            int left = 1;
            int right = 2;
            List<int[]> result = new ArrayList<>();
            while (left < right) {//题目规定至少2个数，所以当区间只有一个数时退出
                int sum = (right + left) * (right - left + 1) / 2;
                if (sum == target) {
                    int[] res = new int[right - left + 1];
                    for (int i = left; i <= right; ++i) {
                        res[i - left] = i;
                    }
                    result.add(res);
                    //这里还没完，找到一个之后变更起点left，继续找下一个
                    left++;
                } else if (sum < target) {
                    //如果 sum<target 则说明指针 r 还可以向右拓展使得 sum 增大，此时指针 r 向右移动，即 r+=1
                    // ** 不要错当成二分的left++ 和right-- **
                    right++;
                } else if (sum > target) {
                    //如果 sum>target 则说明以 l 为起点不存在一个 r 使得 sum=target ，此时要枚举下一个起点，指针 l 向右移动，即l+=1
                    left++;
                }
            }
            return result.toArray(new int[result.size()][]);

        }
    }
//leetcode submit region end(Prohibit modification and deletion)

}