//一个长度为n-1的递增排序数组中的所有数字都是唯一的，并且每个数字都在范围0～n-1之内。在范围0～n-1内的n个数字中有且只有一个数字不在该数组中，请找出
//这个数字。 
//
// 
//
// 示例 1: 
//
// 输入: [0,1,3]
//输出: 2
// 
//
// 示例 2: 
//
// 输入: [0,1,2,3,4,5,6,7,9]
//输出: 8 
//
// 
//
// 限制： 
//
// 1 <= 数组长度 <= 10000 
// Related Topics 位运算 数组 哈希表 数学 二分查找 👍 248 👎 0


package com.company.leetcode.editor.cn;

public class No_剑指Offer53II_QueShiDeShuZiLcof {
    public static void main(String[] args) {
        Solution solution = new No_剑指Offer53II_QueShiDeShuZiLcof().new Solution();
    }

    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        public int missingNumber1(int[] nums) {
            //理论上所有的元素都是nums[i] = 下标i，如果有不是的，就是缺少的元素
            for (int i = 0; i < nums.length; i++) {
                if (nums[i] != i) {
                    return i;
                }
            }
            //如果所有元素都在应该在的位置上，肯定还缺的一个元素就是nums.length
            //因为理论上元素有[0,n-1]一共n个，而数据长度只有n-1
            return nums.length;
        }

        //排序数组用二分：如果前半部分都是“元素在应该在的位置上”，那么num[mid]必定== mid。那么前半部分不用看了
        //相当于是把数组分成两个部分 1。满足“元素在应该位置上” 2.不满足的
        //要找的是第一个部分的右边界index +1。（右边界是left -1 == right，右边界加一是 left == right+1，且不用判空）
        public int missingNumber(int[] nums) {
            int left = 0;
            int right = nums.length - 1;
            while (left <= right) {
                int mid = (left + right) / 2;
                if (nums[mid] == mid) {
                    //前半部分符合条件 都不用扫描了
                    left = mid + 1;
                } else if (nums[mid] > mid) {
                    right = mid - 1;
                } else if (nums[mid] < mid) {
                    //这个情况应该不会出现 因为元素都在[0,n-1]之间，且有序
                }
            }
            //理论上不用判空，这里就是练习一下套框架
//            if (right == -1 || nums[right] != target)
            return right + 1;
        }
    }
//leetcode submit region end(Prohibit modification and deletion)

}