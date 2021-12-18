//把一个数组最开始的若干个元素搬到数组的末尾，我们称之为数组的旋转。输入一个递增排序的数组的一个旋转，输出旋转数组的最小元素。例如，数组 [3,4,5,1,2
//] 为 [1,2,3,4,5] 的一个旋转，该数组的最小值为1。 
//
// 示例 1： 
//
// 输入：[3,4,5,1,2]
//输出：1
// 
//
// 示例 2： 
//
// 输入：[2,2,2,0,1]
//输出：0
// 
//
// 注意：本题与主站 154 题相同：https://leetcode-cn.com/problems/find-minimum-in-rotated-
//sorted-array-ii/ 
// Related Topics 数组 二分查找 👍 448 👎 0


package com.company.leetcode.editor.cn;

public class No_剑指_offer_11_XuanZhuanShuZuDeZuiXiaoShuZiLcof {
    public static void main(String[] args) {
        Solution solution = new No_剑指_offer_11_XuanZhuanShuZuDeZuiXiaoShuZiLcof().new Solution();
        System.out.println(String.valueOf(solution.minArray(new int[]{10, 1, 10, 10, 10})));
    }

    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        public int minArray(int[] numbers) {
            int left = 0;
            int right = numbers.length - 1;
            if (numbers[left] < numbers[right]) {
                return numbers[left];
            }
            /**
             * 循环退出条件其实“带不带等于号”都可以，标准答案不带，但是为了方便统一记忆我还是都带上了
             * 本质上是因为 其实left=right时肯定找得到最小值，此时mid=right，触发了right--，即满足了left=right+1的退出条件
             */
            while (left <= right) {
                if (numbers[left] < numbers[right]) {
                    return numbers[left];
                }
                int mid = (left + right) / 2;
                if (numbers[mid] > numbers[left]) {
                    left = mid + 1;
                } else if (numbers[mid] < numbers[left]) {
                    /**
                     * 这里为什么不是right = mid -1?
                     * 因为当mid >right时,mid肯定不是最小的
                     * 但是当mid <right时,mid可能是最小的,不能贸然-1,不然就错失了最小值
                     * 进阶:如果退出条件是left = right+1，这里会不会造成left=right的死循环？
                     * 不会的，因为当length = 3时，right = mid = right -1，还能继续收缩
                     * 当length =2时，right=mid =left = right -1，还能继续收缩
                     * 当length =1时，numbers[mid] == numbers[right] 一定成立，right--，还是会收缩，最终满足left = right+1
                     * 退出条件 left = right + 1 == 小于等于 == 转移条件里不+1，-1时能不能正常退出还是要综合所有转移条件一起看，重点考虑n=3,2,1的特殊情况
                     */
                    right = mid;
                } else if (numbers[mid] == numbers[left]) {
                    left++;
                }
            }
            return numbers[left - 1];
        }
    }
//leetcode submit region end(Prohibit modification and deletion)

}