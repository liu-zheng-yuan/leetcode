//输入整数数组 arr ，找出其中最小的 k 个数。例如，输入4、5、1、6、2、7、3、8这8个数字，则最小的4个数字是1、2、3、4。
//
// 
//
// 示例 1： 
//
// 输入：arr = [3,2,1], k = 2
//输出：[1,2] 或者 [2,1]
// 
//
// 示例 2： 
//
// 输入：arr = [0,1,2,1], k = 1
//输出：[0] 
//
// 
//
// 限制： 
//
// 
// 0 <= k <= arr.length <= 10000 
// 0 <= arr[i] <= 10000 
// 
// Related Topics 数组 分治 快速选择 排序 堆（优先队列） 👍 396 👎 0


package com.company.leetcode.editor.cn;

import java.util.Arrays;

public class No_剑指Offer40_ZuiXiaoDeKgeShuLcof {
    public static void main(String[] args) {
        Solution solution = new No_剑指Offer40_ZuiXiaoDeKgeShuLcof().new Solution();
        System.out.println(solution.getLeastNumbers(new int[]{3, 2, 1}, 2));
    }

    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        //快排的partition 思路
        //每次选取一个pivot放在升序排列的位置上，如果pivot的位置等于k-1，说明前[0,k-1]一共k个数字就是最小的k个
        //如果pivot的位置比k大，排除后半部分。如果pivot的位置比k小，排除前半部分
        public int[] getLeastNumbers(int[] arr, int k) {
            if (arr.length == 0) {
                return new int[]{};
            }
            int left = 0;
            int right = arr.length - 1;

            while (left <= right) {
                int pivotIndex = partition(arr, left, right);
                if (pivotIndex == k - 1) {
                    //找到了
                    return Arrays.copyOfRange(arr, 0, k);
                } else if (pivotIndex > k - 1) {
                    right = pivotIndex - 1;
                } else if (pivotIndex < k - 1) {
                    left = pivotIndex + 1;
                }
            }
            return new int[]{};
        }

        private int partition(int[] arr, int left, int right) {
            int pivot = arr[left];
            while (left < right) {
                while (left < right && arr[right] >= pivot) {
                    right--;
                }
                if (left < right) {
                    arr[left] = arr[right];
                }
                while (left < right && arr[left] <= pivot) {
                    left++;
                }
                if (left < right) {
                    arr[right] = arr[left];
                }
            }
            //之前pivot在的地方已经被覆盖了，while之后pivot应该在它升序序列中的位置，即left的位置
            arr[left] = pivot;
            return left;
        }
    }
//leetcode submit region end(Prohibit modification and deletion)

}