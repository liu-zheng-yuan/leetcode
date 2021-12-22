//珂珂喜欢吃香蕉。这里有 N 堆香蕉，第 i 堆中有 piles[i] 根香蕉。警卫已经离开了，将在 H 小时后回来。
//
// 珂珂可以决定她吃香蕉的速度 K （单位：根/小时）。每个小时，她将会选择一堆香蕉，从中吃掉 K 根。如果这堆香蕉少于 K 根，她将吃掉这堆的所有香蕉，然后
//这一小时内不会再吃更多的香蕉。 
//
// 珂珂喜欢慢慢吃，但仍然想在警卫回来前吃掉所有的香蕉。 
//
// 返回她可以在 H 小时内吃掉所有香蕉的最小速度 K（K 为整数）。 
//
// 
//
// 
// 
//
// 示例 1： 
//
// 输入: piles = [3,6,7,11], H = 8
//输出: 4
// 
//
// 示例 2： 
//
// 输入: piles = [30,11,23,4,20], H = 5
//输出: 30
// 
//
// 示例 3： 
//
// 输入: piles = [30,11,23,4,20], H = 6
//输出: 23
// 
//
// 
//
// 提示： 
//
// 
// 1 <= piles.length <= 10^4 
// piles.length <= H <= 10^9 
// 1 <= piles[i] <= 10^9 
// 
// Related Topics 数组 二分查找 👍 234 👎 0


package com.company.leetcode.editor.cn;

public class No_Eight75_KokoEatingBananas {
    public static void main(String[] args) {
        Solution solution = new No_Eight75_KokoEatingBananas().new Solution();

    }

    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        /**
         * 题目问的是最小的K ==》 自变量是速度K，且求左边界
         * H小时内 ==》 target = H，且因变量的含义是吃完的耗时 ==》h = f(X) 含义是：如果按x根每小时的速度，吃完所有香蕉的耗时
         */
        public int minEatingSpeed(int[] piles, int h) {
            //注意：这里上下界就不再是数组的上下界了，而是自变量X的上下界！！！
            int left = 1;//显然不能为0
            int right = 1000000000 + 1;//每次最多吃一个piles里的最大堆，吃多了没意义

            //注意 fx是单调递减的，所以>H和<h的操作需要对调
            while (left <= right) {
                int mid = left + (right - left) / 2;
                int midH = f(mid, piles);
//                System.out.printf("left = %s,right = %d,midH = %d\n", left, right, midH);
                if (midH == h) {
                    right = mid - 1;
                } else if (midH > h) {
                    left = mid + 1;
                } else if (midH < h) {
                    right = mid - 1;
                }
            }
            //不用结尾验证，因为泛化问题的实际情况一般不会无解
            //求左边界就返回left
            return left;

        }

        //如果按x根每小时的速度，吃完所有香蕉的耗时
        private int f(int x, int[] piles) {
            int count = 0;
            for (int cur : piles) {
//                if (x >= cur) {
//                    count++;
//                } else {
//                    //要吃多次,
//                    if (cur % x == 0) {
//                        count += cur / x;
//                    } else {
//                        count += cur / x + 1;
//                    }
//                }
                count += cur / x;
                if (cur % x > 0) {
                    count++;
                }

            }
            return count;
        }
    }
//leetcode submit region end(Prohibit modification and deletion)

}