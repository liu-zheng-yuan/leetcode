//0,1,···,n-1这n个数字排成一个圆圈，从数字0开始，每次从这个圆圈里删除第m个数字（删除后从下一个数字开始计数）。求出这个圆圈里剩下的最后一个数字。
// 
//
// 例如，0、1、2、3、4这5个数字组成一个圆圈，从数字0开始每次删除第3个数字，则删除的前4个数字依次是2、0、4、1，因此最后剩下的数字是3。 
//
// 
//
// 示例 1： 
//
// 
//输入: n = 5, m = 3
//输出: 3
// 
//
// 示例 2： 
//
// 
//输入: n = 10, m = 17
//输出: 2
// 
//
// 
//
// 限制： 
//
// 
// 1 <= n <= 10^5 
// 1 <= m <= 10^6 
// 
// Related Topics 递归 数学 👍 575 👎 0


package com.company.leetcode.editor.cn;

public class No_剑指Offer62_YuanQuanZhongZuiHouShengXiaDeShuZiLcof {
    public static void main(String[] args) {
        Solution solution = new No_剑指Offer62_YuanQuanZhongZuiHouShengXiaDeShuZiLcof().new Solution();
    }

    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        //函数 f(n, m)，该函数的返回值为最终留下的元素的序号。
        //** 从index = 0开始 ，数m个数 ---> 下标是 m-1
        //** 从index = 0开始，下标是m ----> 从0开始数m+1个数。（区间[0,m]内一共m+1个元素）
        //** 从index = i开始，下标是m ----> 从i开始数m+1个数。

        /**
         * 1.先删除第 m%n 个元素（缩减问题规模），即【下标m-1】的元素，剩下的数组长度为n-1
         * 2.对剩下的数组调用递归函数f(n-1,m) （用递归函数的定义解决问题，避免深入递归），返回x
         * 3.x指的是从 【下标m-1】 开始数【x+1】个元素，就是最终会剩下的元素。（由于有环存在，超出n的长度后会从头开始）
         * 4.所以：原始数组中剩下的元素的下标是f(n, m) =[(m-1)%n+x+1]%n =  (m + x) % n
         */
        public int lastRemaining(int n, int m) {
            if (n == 1) {
                return 0;
            }
            //
            int x = lastRemaining(n - 1, m);
            return (m + x) % n;

        }
    }
//leetcode submit region end(Prohibit modification and deletion)

}