//传送带上的包裹必须在 days 天内从一个港口运送到另一个港口。
//
// 传送带上的第 i 个包裹的重量为 weights[i]。每一天，我们都会按给出重量（weights）的顺序往传送带上装载包裹。我们装载的重量不会超过船的最
//大运载重量。 
//
// 返回能在 days 天内将传送带上的所有包裹送达的船的最低运载能力。 
//
// 
//
// 示例 1： 
//
// 
//输入：weights = [1,2,3,4,5,6,7,8,9,10], days = 5
//输出：15
//解释：
//船舶最低载重 15 就能够在 5 天内送达所有包裹，如下所示：
//第 1 天：1, 2, 3, 4, 5
//第 2 天：6, 7
//第 3 天：8
//第 4 天：9
//第 5 天：10
//
//请注意，货物必须按照给定的顺序装运，因此使用载重能力为 14 的船舶并将包装分成 (2, 3, 4, 5), (1, 6, 7), (8), (9), (1
//0) 是不允许的。 
// 
//
// 示例 2： 
//
// 
//输入：weights = [3,2,2,4,1,4], days = 3
//输出：6
//解释：
//船舶最低载重 6 就能够在 3 天内送达所有包裹，如下所示：
//第 1 天：3, 2
//第 2 天：2, 4
//第 3 天：1, 4
// 
//
// 示例 3： 
//
// 
//输入：weights = [1,2,3,1,1], D = 4
//输出：3
//解释：
//第 1 天：1
//第 2 天：2
//第 3 天：3
//第 4 天：1, 1
// 
//
// 
//
// 提示： 
//
// 
// 1 <= days <= weights.length <= 5 * 10⁴ 
// 1 <= weights[i] <= 500 
// 
// Related Topics 贪心 数组 二分查找 👍 411 👎 0


package com.company.leetcode.editor.cn;

public class No_One011_CapacityToShipPackagesWithinDDays {
    public static void main(String[] args) {
        Solution solution = new No_One011_CapacityToShipPackagesWithinDDays().new Solution();
    }

    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        /**
         * 自变量x：船一天的运载能力
         * 因变量fx：多少天能运完
         * target：days
         */
        public int shipWithinDays(int[] weights, int days) {
            //自变量范围要看题意
            int max = Integer.MIN_VALUE;
            for (int i = 0; i < weights.length; i++) {
                if (weights[i] > max) {
                    max = weights[i];
                }
            }
            int left = max;
            int right = 50000 * 500;
            //单调递减，求左边界
            while (left <= right) {
                int mid = left + (right - left) / 2;
                int midDays = f(mid, weights);
                if (midDays == days) {
                    right = mid - 1;
                } else if (midDays > days) {
                    left = mid + 1;
                } else if (midDays < days) {
                    right = mid - 1;
                }
            }
            return left;
        }

        private int f(int x, int[] weights) {
            int count = 0;
            int index = 0;
            int curX = x;
            count++;
            while (index < weights.length) {
                //这里默认 船的装载能力的最小值肯定大于任意一个包裹，不然肯定完成不了任务
                if (curX >= weights[index]) {
                    curX -= weights[index];
                    index++;
                } else {
                    curX = x;
                    count++;
                }
            }
            return count;
        }
    }
//leetcode submit region end(Prohibit modification and deletion)

}