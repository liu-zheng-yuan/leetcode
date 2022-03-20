//在数组中的两个数字，如果前面一个数字大于后面的数字，则这两个数字组成一个逆序对。输入一个数组，求出这个数组中的逆序对的总数。
//
// 
//
// 示例 1: 
//
// 输入: [7,5,6,4]
//输出: 5 
//
// 
//
// 限制： 
//
// 0 <= 数组长度 <= 50000 
// Related Topics 树状数组 线段树 数组 二分查找 分治 有序集合 归并排序 👍 664 👎 0


package com.company.leetcode.editor.cn;

public class No_剑指Offer51_ShuZuZhongDeNiXuDuiLcof {
    public static void main(String[] args) {
        Solution solution = new No_剑指Offer51_ShuZuZhongDeNiXuDuiLcof().new Solution();
    }

    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        //归并排序中，合并两个数组的过程
        //每当遇到 左子数组当前元素 > 右子数组当前元素 时，意味着 「左子数组当前元素 至 末尾元素」 与 「右子数组当前元素」 构成了若干 「逆序对」 。
        //只需要在正常的归并排序中，增加一个count计数
        int count = 0;
        int[] temp;

        public int reversePairs(int[] nums) {
            temp = new int[nums.length];//用于归并排序
            mergeSort(nums, 0, nums.length - 1);
            return count;
        }

        private void mergeSort(int[] nums, int left, int right) {
            if (left < right) {
                int mid = (left + right) / 2;
                mergeSort(nums, left, mid);
                mergeSort(nums, mid + 1, right);//mid是可以取到的，所以左右两部分不能重叠
                merge(nums, left, mid, mid + 1, right);//两个排序好的数组做merge
            }
        }

        private void merge(int[] nums, int ll, int lr, int rl, int rr) {
            //左右两部分数组的起始idx
            int i = ll;
            int j = rl;
            int idx = 0;//当前归并后的索引从0开始
            while (i <= lr && j <= rr) {
                if (nums[i] <= nums[j]) {
                    temp[idx] = nums[i];
                    i++;
                } else {
                    //每当遇到 左子数组当前元素 > 右子数组当前元素 时，意味着 「左子数组当前元素 至 末尾元素」 与 「右子数组当前元素」 构成了若干 「逆序对」 。
                    count += lr - i + 1;//[i,lr]区间中一共有ll-i+1个元素
                    temp[idx] = nums[j];
                    j++;
                }
                idx++;
            }
            //剩下的
            while (i <= lr) {
                temp[idx] = nums[i];
                idx++;
                i++;
            }
            while (j <= rr) {
                temp[idx] = nums[j];
                idx++;
                j++;
            }
            //从temp拷贝到应该在的地方
            for (int k = 0; k < idx; k++) {
                nums[ll] = temp[k];
                ll++;
            }

        }
    }
//leetcode submit region end(Prohibit modification and deletion)

}