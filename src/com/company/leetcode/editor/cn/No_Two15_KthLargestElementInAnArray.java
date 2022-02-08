//给定整数数组 nums 和整数 k，请返回数组中第 k 个最大的元素。 
//
// 请注意，你需要找的是数组排序后的第 k 个最大的元素，而不是第 k 个不同的元素。 
//
// 
//
// 示例 1: 
//
// 
//输入: [3,2,1,5,6,4] 和 k = 2
//输出: 5
// 
//
// 示例 2: 
//
// 
//输入: [3,2,3,1,2,4,5,5,6] 和 k = 4
//输出: 4 
//
// 
//
// 提示： 
//
// 
// 1 <= k <= nums.length <= 10⁴ 
// -10⁴ <= nums[i] <= 10⁴ 
// 
// Related Topics 数组 分治 快速选择 排序 堆（优先队列） 👍 1446 👎 0


package com.company.leetcode.editor.cn;

import java.util.Random;

public class No_Two15_KthLargestElementInAnArray {
    public static void main(String[] args) {
        Solution solution = new No_Two15_KthLargestElementInAnArray().new Solution();
        System.out.println(solution.findKthLargest(new int[]{2, 1}, 1));
    }

    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        /**
         * 快排的思路
         * 第K个最大的：第一个最大的是第 n-1个元素，第k个最大的就是第 n-k个元素（正数）。
         * 或者这么想：去掉这最后k个元素，还剩n-k个元素，序列是[0,n-k)其中一共有n-k个元素（n-k-0 = length），那么去掉的这k个元素的最后一个也就是第k个元素，即[0,n-k)之后的元素，就是序号为n-k的元素，就是第k个最大的元素
         */
        public int findKthLargest(int[] nums, int k) {
            //先打乱一下，让平均时间复杂度降到n
            shuffle(nums);
            //
            int left = 0;
            int right = nums.length - 1;
            int target = nums.length - k;//正数第n-k个元素就是目标
            while (left <= right) {
                int pIndex = partition(nums, left, right);//将pivot放到nums中合适位置并返回下标
                if (pIndex == target) {
                    return nums[pIndex];
                } else if (target < pIndex) {
                    right = pIndex - 1;
                } else if (target > pIndex) {
                    left = pIndex + 1;
                }
            }
            return Integer.MIN_VALUE;
        }

        private int partition(int[] nums, int left, int right) {
            int pivot = nums[left];//默认选第一个作为pivot
            while (left < right) {
                //只要找到第一个不符合条件的元素，就立刻把符合条件的换过去
                //一定要从right开始，因为left初始就是pivot，一定符合条件
                while (left < right && nums[right] >= pivot) {
                    right -= 1;
                }
                //不符合right条件的，都移到另一边
                //第一次到这里，pivot被覆盖了
                if (left < right) {
                    nums[left] = nums[right];
                }
                while (left < right && nums[left] <= pivot) {
                    left += 1;
                }
                if (left < right) {
                    nums[right] = nums[left];
                }
            }
            //pivot放到正确的位置
            nums[left] = pivot;
            return left;
        }

        //关键是能随机到全排列的所有情况：n！ = n * （n-1） * （n-2）
        private void shuffle(int[] nums) {
            Random random = new Random();
            //第一次随机的范围 [0,n-1]
            //第二次随机的范围 [1,n-1]
            //.... [2,n-1]
            //就是符合n！
            for (int i = 0; i < nums.length; i++) {
                int randInt = i + random.nextInt(nums.length - i);
                //
                int t = nums[i];
                nums[i] = nums[randInt];
                nums[randInt] = t;
            }

        }
    }
//leetcode submit region end(Prohibit modification and deletion)

}