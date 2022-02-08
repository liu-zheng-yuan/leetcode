//把一个数组最开始的若干个元素搬到数组的末尾，我们称之为数组的旋转。 
//
// 给你一个可能存在 重复 元素值的数组 numbers ，它原来是一个升序排列的数组，并按上述情形进行了一次旋转。请返回旋转数组的最小元素。例如，数组 [3
//,4,5,1,2] 为 [1,2,3,4,5] 的一次旋转，该数组的最小值为1。 
//
// 示例 1： 
//
// 
//输入：[3,4,5,1,2]
//输出：1
// 
//
// 示例 2： 
//
// 
//输入：[2,2,2,0,1]
//输出：0
// 
//
// 注意：本题与主站 154 题相同：https://leetcode-cn.com/problems/find-minimum-in-rotated-
//sorted-array-ii/ 
// Related Topics 数组 二分查找 👍 490 👎 0


package com.company.leetcode.editor.cn;

public class No_剑指Offer11_XuanZhuanShuZuDeZuiXiaoShuZiLcof {
    public static void main(String[] args) {
        Solution solution = new No_剑指Offer11_XuanZhuanShuZuDeZuiXiaoShuZiLcof().new Solution();
        System.out.println(solution.minArray(new int[]{3, 4, 5, 1, 2}));
    }

    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        public int minArray(int[] numbers) {
            int left = 0;
            int right = numbers.length - 1;
            //由于是旋转过来的，numbers[right] 不管right怎么减小，只要不超过旋转点，他的值 一定小于等于 [left,旋转点]
            while (left <= right) {
                int target = numbers[right];//动态计算每一轮的target，记得要先算，不然后面right会被改变
                int mid = (left + right) / 2;
                int curValue = numbers[mid];
                if (curValue > target) {
                    left = mid + 1;//闭区间的需要+1，开区间的不用
                } else if (curValue < target) {
                    //说明mid可能是最小值，不能贸然right = mid -1，可能会错过最小值
                    right = mid;
                } else if (curValue == target) {
                    //因为是从右边找左边界
                    //所以退出时一定是left = right + 1,且left在最小值上，此时right已经越过了最小值
                    //所以不管此时是不是right = 最小值，都要right--
                    right--;
                }
            }
            //不存在没有找到的情况
            return numbers[left];
        }
    }
//leetcode submit region end(Prohibit modification and deletion)

}