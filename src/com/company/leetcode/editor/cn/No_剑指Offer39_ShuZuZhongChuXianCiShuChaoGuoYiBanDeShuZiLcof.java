//数组中有一个数字出现的次数超过数组长度的一半，请找出这个数字。
//
// 
//
// 你可以假设数组是非空的，并且给定的数组总是存在多数元素。 
//
// 
//
// 示例 1: 
//
// 输入: [1, 2, 3, 2, 2, 2, 5, 4, 2]
//输出: 2 
//
// 
//
// 限制： 
//
// 1 <= 数组长度 <= 50000 
//
// 
//
// 注意：本题与主站 169 题相同：https://leetcode-cn.com/problems/majority-element/ 
//
// 
// Related Topics 数组 哈希表 分治 计数 排序 👍 259 👎 0


package com.company.leetcode.editor.cn;

public class No_剑指Offer39_ShuZuZhongChuXianCiShuChaoGuoYiBanDeShuZiLcof {
    public static void main(String[] args) {
        Solution solution = new No_剑指Offer39_ShuZuZhongChuXianCiShuChaoGuoYiBanDeShuZiLcof().new Solution();
        System.out.println(solution.majorityElement(new int[]{1, 2, 3, 2, 2, 2, 5, 4, 2}));
    }

    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        //1.自创的，快排思路找到中位数，再验证中位数是不是多数元素（本题不一定需要）
        //2.摩尔投票法
        public int majorityElement(int[] nums) {
            int left = 0;
            int right = nums.length - 1;
            int mid = nums.length / 2;
            int res = 0;
            //二分框架
            while (left <= right) {
                int pivotIndex = partition(nums, left, right);//选择最左边作为pivot并放到正确的位置上
                if (pivotIndex == mid) {
                    res = nums[pivotIndex];//还需要验证
                    break;
                } else if (pivotIndex > mid) {
                    right = pivotIndex - 1;
                } else if (pivotIndex < mid) {
                    left = pivotIndex + 1;
                }
            }
            //验证
            int count = 0;
            for (int i = 0; i < nums.length; i++) {
                if (nums[i] == res) {
                    count++;
                }
            }
            if (count >= mid) {
                return res;
            } else {
                return -1;
            }
        }

        public int majorityElement2(int[] nums) {
            //假定任意初始元素就是出现次数最多的，给他设定一个计数器
            //每碰上一个不一样的就把计数器--，一样的就是++
            //如果计数器到0，就把当前元素继续当成最多的来统计
            int count = 0;
            int res = 0;
            for (int i = 0; i < nums.length; i++) {
                if (count == 0) {
                    res = nums[i];
                }
                //
                if (nums[i] == res) {
                    count++;
                } else {
                    count--;
                }
            }
            return res;
        }

        public int partition(int[] nums, int left, int right) {
            int pivot = nums[left];
            while (left < right) {
                while (left < right && nums[right] >= pivot) {
                    right--;
                }
                if (left < right) {
                    nums[left] = nums[right];
                }
                while (left < right && nums[left] <= pivot) {
                    left++;
                }
                if (left < right) {
                    nums[right] = nums[left];
                }
            }
            //把pivot放到应该在的地方
            nums[left] = pivot;
            return left;
        }
    }
//leetcode submit region end(Prohibit modification and deletion)

}