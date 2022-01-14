//给定两个由一些 闭区间 组成的列表，firstList 和 secondList ，其中 firstList[i] = [starti, endi] 而
//secondList[j] = [startj, endj] 。每个区间列表都是成对 不相交 的，并且 已经排序 。 
//
// 返回这 两个区间列表的交集 。 
//
// 形式上，闭区间 [a, b]（其中 a <= b）表示实数 x 的集合，而 a <= x <= b 。 
//
// 两个闭区间的 交集 是一组实数，要么为空集，要么为闭区间。例如，[1, 3] 和 [2, 4] 的交集为 [2, 3] 。 
//
// 
//
// 示例 1： 
//
// 
//输入：firstList = [[0,2],[5,10],[13,23],[24,25]], secondList = [[1,5],[8,12],[15,
//24],[25,26]]
//输出：[[1,2],[5,5],[8,10],[15,23],[24,24],[25,25]]
// 
//
// 示例 2： 
//
// 
//输入：firstList = [[1,3],[5,9]], secondList = []
//输出：[]
// 
//
// 示例 3： 
//
// 
//输入：firstList = [], secondList = [[4,8],[10,12]]
//输出：[]
// 
//
// 示例 4： 
//
// 
//输入：firstList = [[1,7]], secondList = [[3,10]]
//输出：[[3,7]]
// 
//
// 
//
// 提示： 
//
// 
// 0 <= firstList.length, secondList.length <= 1000 
// firstList.length + secondList.length >= 1 
// 0 <= starti < endi <= 10⁹ 
// endi < starti+1 
// 0 <= startj < endj <= 10⁹ 
// endj < startj+1 
// 
// Related Topics 数组 双指针 👍 231 👎 0


package com.company.leetcode.editor.cn;

import java.util.ArrayList;
import java.util.List;

public class No_Nine86_IntervalListIntersections {
    public static void main(String[] args) {
        Solution solution = new No_Nine86_IntervalListIntersections().new Solution();
    }

    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        //前提：可能出现first里一个大区间 把 second里很多个小区间都覆盖的情况，所以需要双指针依次遍历每个f，s中的区间
        //分析：对于任意两个区间i,j，1.如果i j有交集（包含覆盖的情况）,即交集是[max(i.left,j.left),min(i.right,j.right)] 23.如果i j 没有交集，那么交集就是空
        //如何移动i，j：假设i无限长，j.right < i.right ，则j一直是被i覆盖的，则需移动j++；反之移动 i++
        public int[][] intervalIntersection(int[][] firstList, int[][] secondList) {
            int i = 0;
            int j = 0;
            int len1 = firstList.length;
            int len2 = secondList.length;
            List<int[]> res = new ArrayList<>();
            while (i < len1 && j < len2) {
                int left1 = firstList[i][0];
                int right1 = firstList[i][1];
                int left2 = secondList[j][0];
                int right2 = secondList[j][1];
                //这里只考虑了i 覆盖 j 的情况 没有考虑j覆盖i的情况
//                if (left1 <= left2 && right1 >= right2) {
//                    //完全覆盖
//                    res.add(new int[]{left2, right2});
//                } else if (left2 <= right1) {
//                    //部分重叠
//                    res.add(new int[]{
//                            Math.max(left1, left2),
//                            Math.min(right1, right2)
//                    });
//                } else if (left2 > right1) {
//                    //没有交集
//                }
                //应该先想i j 无交集的情况
                if (right1 < left2 || right2 < left1) {
                    //i j无交集
                } else {
                    //（其实条件就是上面无交集的条件取反   right1 >= left2 && right2 >= left1）
                    res.add(new int[]{
                            Math.max(left1, left2),
                            Math.min(right1, right2)
                    });
                }
                //移动i j
                // 公众号里给的是如下，因为题目里“区间列表里都不想交” 所以不存在[{20,22},{22,24}]这种连着的情况，所以如果i j 右边界相同，直接都跳到下一个进行比较，不会漏
//                if (right2 < right1) {
//                    j++;
//                } else {
//                    i++;
//                }
                if (right2 > right1) {
                    i++;
                } else if (right1 > right2) {
                    j++;
                } else {
                    i++;
                    j++;
                }

            }
            return res.toArray(new int[][]{});
        }
    }
//leetcode submit region end(Prohibit modification and deletion)

}