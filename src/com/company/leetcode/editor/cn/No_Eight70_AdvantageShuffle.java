//给定两个大小相等的数组 A 和 B，A 相对于 B 的优势可以用满足 A[i] > B[i] 的索引 i 的数目来描述。
//
// 返回 A 的任意排列，使其相对于 B 的优势最大化。 
//
// 
//
// 示例 1： 
//
// 输入：A = [2,7,11,15], B = [1,10,4,11]
//输出：[2,11,7,15]
// 
//
// 示例 2： 
//
// 输入：A = [12,24,8,32], B = [13,25,32,11]
//输出：[24,32,8,12]
// 
//
// 
//
// 提示： 
//
// 
// 1 <= A.length = B.length <= 10000 
// 0 <= A[i] <= 10^9 
// 0 <= B[i] <= 10^9 
// 
// Related Topics 贪心 数组 排序 👍 161 👎 0


package com.company.leetcode.editor.cn;

import java.util.Arrays;
import java.util.PriorityQueue;

public class No_Eight70_AdvantageShuffle {
    public static void main(String[] args) {
        Solution solution = new No_Eight70_AdvantageShuffle().new Solution();
    }

    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        public int[] advantageCount(int[] nums1, int[] nums2) {
            //类比田忌赛马
            //具体策略：都按战斗力排序，按照排名一一对比，如果A能赢B，就排A[i]去跟B[i]比，如果不能赢，就从A里找个最低的去跟B[i]比
            //同时，为了保证B的顺序不变，所以排序的时候需要用个结构体保存index
            //“找个最低的跟B比” 可以通过双指针实现
            //比完之后，当前这个坑要么放原来的A[i]要么放还没用掉的A中的最小值，需要额外用一个数组存最后结果的顺序

            int[] res = new int[nums1.length];//按顺序储存最终结果
            //为了1.保证nums排序后带有index信息 2.能迅速找到最大的nums，所以采用大顶堆存nums2
            // int[0] 存数值 int[1]存index
            PriorityQueue<int[]> b = new PriorityQueue<>((int[] a1, int[] a2) -> {
                return a2[0] - a1[0];//降序排列
            });
            for (int i = 0; i < nums2.length; i++) {
                b.add(new int[]{nums2[i], i});
            }
            Arrays.sort(nums1);//A也需要排序，升序排序，最大的在right
            //双指针，升序排序，从A最大的开始
            int left = 0;
            int right = nums1.length - 1;
            while (left <= right) {
                int[] curMaxNums2 = b.poll();
                if (nums1[right] > curMaxNums2[0]) {
                    //能赢过 当前序号A数字不变
                    res[curMaxNums2[1]] = nums1[right];
                    right--;
                } else {
                    //不能赢，找个A里当前没用过的最菜的和B比
                    res[curMaxNums2[1]] = nums1[left];
                    left++;
                }
            }
            return res;
        }
    }
//leetcode submit region end(Prohibit modification and deletion)

}