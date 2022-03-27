//输入一个递增排序的数组和一个数字s，在数组中查找两个数，使得它们的和正好是s。如果有多对数字的和等于s，则输出任意一对即可。
//
// 
//
// 示例 1： 
//
// 输入：nums = [2,7,11,15], target = 9
//输出：[2,7] 或者 [7,2]
// 
//
// 示例 2： 
//
// 输入：nums = [10,26,30,31,47,60], target = 40
//输出：[10,30] 或者 [30,10]
// 
//
// 
//
// 限制： 
//
// 
// 1 <= nums.length <= 10^5 
// 1 <= nums[i] <= 10^6 
// 
// Related Topics 数组 双指针 二分查找 👍 169 👎 0


package com.company.leetcode.editor.cn;

public class No_剑指Offer57_HeWeiSdeLiangGeShuZiLcof {
    public static void main(String[] args) {
        Solution solution = new No_剑指Offer57_HeWeiSdeLiangGeShuZiLcof().new Solution();
    }

    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        //1.先确定一个数，再对剩下的数做二分搜索：优化-如果s-固定数 < 固定数，则不用找，升序的后面肯定没有
        //2.双指针，如果left+right 《 s ，则left++，否则right--
        public int[] twoSum1(int[] nums, int target) {
            for (int i = 0; i < nums.length; i++) {
                int a = nums[i];
                int b = target - a;
                if (b < a) {
                    continue;//肯定不可能
                }
                if (i + 1 < nums.length) {//确保二分搜索区间一定存在
                    int bIndex = binarySearch11(nums, i + 1, nums.length - 1, b);
                    if (bIndex != -1) {
                        return new int[]{nums[i], nums[bIndex]};
                    }
                }
            }
            return null;
        }

        private int binarySearch11(int[] nums, int left, int right, int goal) {
            while (left <= right) {
                int mid = (left + right) / 2;
                if (nums[mid] == goal) {
                    return mid;
                } else if (nums[mid] > goal) {
                    right = mid - 1;
                } else if (nums[mid] < goal) {
                    left = mid + 1;
                }
            }
            return -1;
        }

        public int[] twoSum(int[] nums, int target) {
            int left = 0, right = nums.length - 1;
            //跟二分不一样，如果[left,right]区间里只有一个元素，肯定不成立，因为要找两个元素
            while (left < right) {
                int sum = nums[left] + nums[right];
                if (sum == target) {
                    return new int[]{nums[left], nums[right]};
                } else if (sum > target) {
                    right--;
                } else if (sum < target) {
                    left++;
                }
            }
            return null;
        }
    }
//leetcode submit region end(Prohibit modification and deletion)

}