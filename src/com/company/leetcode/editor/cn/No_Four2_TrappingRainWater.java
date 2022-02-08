//给定 n 个非负整数表示每个宽度为 1 的柱子的高度图，计算按此排列的柱子，下雨之后能接多少雨水。
//
// 
//
// 示例 1： 
//
// 
//
// 
//输入：height = [0,1,0,2,1,0,1,3,2,1,2,1]
//输出：6
//解释：上面是由数组 [0,1,0,2,1,0,1,3,2,1,2,1] 表示的高度图，在这种情况下，可以接 6 个单位的雨水（蓝色部分表示雨水）。 
// 
//
// 示例 2： 
//
// 
//输入：height = [4,2,0,3,2,5]
//输出：9
// 
//
// 
//
// 提示： 
//
// 
// n == height.length 
// 1 <= n <= 2 * 10⁴ 
// 0 <= height[i] <= 10⁵ 
// 
// Related Topics 栈 数组 双指针 动态规划 单调栈 👍 3016 👎 0


package com.company.leetcode.editor.cn;

public class No_Four2_TrappingRainWater {
    public static void main(String[] args) {
        Solution solution = new No_Four2_TrappingRainWater().new Solution();
        System.out.println(solution.trap(new int[]{0, 1, 0, 2, 1, 0, 1, 3, 2, 1, 2, 1}));

    }

    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        //对于当前index，能接的雨水量：Min{[0..index)最高的高度，(index,Len-1]第一个比{左边最高高度}高的高度} - index的高度 (错的)
        //特例：如果左边或者右边没有比index高的高度，那接雨水数是0
        //这么写是错的：单从左边或者右边固定的递推方向，始终是有问题的；应该用最高的地方将原集合分为左右两个部分，然后分别从左向右和从右向左用以上算法才有用
//        public int trap(int[] height) {
//            int count = 0;
//            for (int i = 0; i < height.length; i++) {
//                //找到左边最高，再找到右边的【比{左边最高}第一个高的】
//                int leftHigh = findLeft(height, i);
//                int rightHigh = findRight(height, i, leftHigh);
//
//                count += Math.max(Math.min(leftHigh, rightHigh) - height[i], 0);
//
//            }
//            return count;
//
//        }
//
//        private int findLeft(int[] height, int idx) {
//            int maxHigh = -1;
//            int i = idx - 1;
//            //等于的不算，必须找到第一个更大的
//            while (i >= 0) {
//                if (height[i] > maxHigh) {
//                    maxHigh = height[i];
//                }
//                i--;
//            }
//            return maxHigh;
//        }
//
//        private int findRight(int[] height, int idx, int leftHigh) {
//            int firstHigh = -1;
//            int i = idx + 1;
//            //等于的不算，必须找到第一个更大的
//            while (i < height.length && height[i] <= leftHigh) {
//                i++;
//            }
//            if (i < height.length) {
//                firstHigh = height[i];
//            }
//            return firstHigh;
//        }

        //暴力：每次找左边最大值，右边最大值的时候会有重复计算
        public int trap1(int[] height) {
            int count = 0;
            int n = height.length;
            //头尾肯定不能接，不用遍历：方便找最大值
            for (int i = 1; i < n - 1; i++) {
                int leftHigh = findMaxvalue(height, 0, i - 1);
                int rightHigh = findMaxvalue(height, i + 1, n - 1);
                count += Math.max(Math.min(leftHigh, rightHigh) - height[i], 0);

            }

            return count;
        }

        //查表：先计算出两边最大值 空间On
        public int trap2(int[] height) {
            int count = 0;
            int n = height.length;
            //定义leftMax[i]:[0,i-1]中最大值,rightMax同理
            int[] leftMax = new int[n];
            int[] rightMax = new int[n];
            //初始化
            leftMax[0] = 0;
            rightMax[n - 1] = 0;
            //
            for (int i = 1; i < n; i++) {
                leftMax[i] = Math.max(leftMax[i - 1], height[i - 1]);
            }
            for (int i = n - 2; i >= 0; i--) {
                rightMax[i] = Math.max(rightMax[i + 1], height[i + 1]);
            }
            //头尾肯定不能接，不用遍历：方便找最大值
            for (int i = 1; i < n - 1; i++) {
                //查表找最大值
                int leftHigh = leftMax[i];
                int rightHigh = rightMax[i];
                count += Math.max(Math.min(leftHigh, rightHigh) - height[i], 0);
            }

            return count;
        }

        //双指针+状态压缩：leftMax和rightMax里都是用过一次不会再用了，没必要全部保存下来，而是用单个变量代替，类似状态压缩的思想。优化空间复杂度
        public int trap(int[] height) {
            int count = 0;
            int n = height.length;
            int leftMax = 0;
            int rightMax = 0;
            int left = 0;
            int right = n - 1;
            while (left < right) {
                leftMax = Math.max(leftMax, height[left]);//height[0..left]中最高柱子的高度
                rightMax = Math.max(rightMax, height[right]);//height[right..end]的最高柱子的高度。
                //定义与之前不一样，但是没有关系：
                //如果已经知道left < right，那么结果只与更小的leftMax有关，而rightMax是不是右边的最大值对结果无影响
                if (leftMax < rightMax) {
                    //能装多少水只与left有关
                    count += leftMax - height[left];
                    left++;
                } else {
                    //能装多少水只与right有关
                    count += rightMax - height[right];
                    right--;
                }
            }
            return count;
        }

        private int findMaxvalue(int[] height, int left, int right) {
            int maxHigh = -1;
            int resIdx = -1;
            //
            for (int i = left; i <= right; i++) {
                if (height[i] > maxHigh) {
                    maxHigh = height[i];
                    resIdx = i;
                }
            }
            return resIdx == -1 ? -1 : height[resIdx];
        }

    }
//leetcode submit region end(Prohibit modification and deletion)

}