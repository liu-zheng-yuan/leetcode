//给定一个数组 A[0,1,…,n-1]，请构建一个数组 B[0,1,…,n-1]，其中 B[i] 的值是数组 A 中除了下标 i 以外的元素的积, 即 B[
//i]=A[0]×A[1]×…×A[i-1]×A[i+1]×…×A[n-1]。不能使用除法。 
//
// 
//
// 示例: 
//
// 
//输入: [1,2,3,4,5]
//输出: [120,60,40,30,24] 
//
// 
//
// 提示： 
//
// 
// 所有元素乘积之和不会溢出 32 位整数 
// a.length <= 100000 
// 
// Related Topics 数组 前缀和 👍 212 👎 0


package com.company.leetcode.editor.cn;

public class No_剑指Offer66_GouJianChengJiShuZuLcof {
    public static void main(String[] args) {
        Solution solution = new No_剑指Offer66_GouJianChengJiShuZuLcof().new Solution();
    }

    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        //本题可以引申前缀和(sum[i]=sum[i-1]+nums[i])的概念:
        //构造一个前缀积数组 leftToRight 和后缀积数组 rightToLeft ， leftToRight[i] 表示 [0,i] 之间元素的乘积， rightToLeft[i] 表示 [i,len-1] 之间元素的乘积
        // 则 B[i] = leftToRight[i-1] *rightToLeft[i+1].

        public int[] constructArrRaw(int[] a) {
            //边界条件
            if (a == null || a.length == 0) {
                return new int[]{};
            }
            int len = a.length;
            int[] left2right = new int[len];
            int[] right2left = new int[len];
            //初始化
            left2right[0] = a[0];
            right2left[len - 1] = a[len - 1];
            //计算前缀积和后缀积
            for (int i = 1; i < len; i++) {
                left2right[i] = left2right[i - 1] * a[i];
                right2left[len - 1 - i] = right2left[len - 1 - i + 1] * a[len - 1 - i];
            }
            //计算结果
            int[] b = new int[len];
            for (int i = 0; i < len; i++) {
                if (i == 0) {
                    //i在头部，就是除了a[0]之外的所有元素的积,
                    b[0] = right2left[1];
                } else if (i == len - 1) {
                    //i在尾部，就是除了a[len-1]之外所有元素的积
                    b[len - 1] = left2right[len - 1 - 1];//不包括最后一个元素
                } else {
                    //i在中间
                    b[i] = left2right[i - 1] * right2left[i + 1];
                }
            }
            return b;
        }

        //** 更好的空间复杂度
        //用b数组先当做left2right 保存前缀积，再用 另一个循环计算后缀积。用temp保存每次迭代的后缀积
        //省却了left2right和right2left 两个数组
        public int[] constructArr(int[] a) {
            //边界条件
            if (a == null || a.length == 0) {
                return new int[]{};
            }
            int len = a.length;
            int[] b = new int[len];
            //不能直接赋值a[0]，后面计算后缀积时会把1覆盖掉
            b[0] = 1;
            //先计算前缀积,b[i] = 乘积(a[0,i-1])
            for (int i = 1; i < len; i++) {
                b[i] = b[i - 1] * a[i - 1];
            }
            //反过来计算后缀积并乘上去，temp保存每轮的后缀积
            int temp = 1;
            for (int i = len - 1; i >= 0; i--) {
                b[i] = b[i] * temp;
                temp = temp * a[i];
            }
            return b;
        }
    }
//leetcode submit region end(Prohibit modification and deletion)

}