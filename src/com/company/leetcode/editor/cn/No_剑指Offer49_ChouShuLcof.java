//我们把只包含质因子 2、3 和 5 的数称作丑数（Ugly Number）。求按从小到大的顺序的第 n 个丑数。
//
// 
//
// 示例: 
//
// 输入: n = 10
//输出: 12
//解释: 1, 2, 3, 4, 5, 6, 8, 9, 10, 12 是前 10 个丑数。 
//
// 说明: 
//
// 
// 1 是丑数。 
// n 不超过1690。 
// 
//
// 注意：本题与主站 264 题相同：https://leetcode-cn.com/problems/ugly-number-ii/ 
// Related Topics 哈希表 数学 动态规划 堆（优先队列） 👍 313 👎 0


package com.company.leetcode.editor.cn;

import java.util.HashSet;
import java.util.PriorityQueue;

public class No_剑指Offer49_ChouShuLcof {
    public static void main(String[] args) {
        Solution solution = new No_剑指Offer49_ChouShuLcof().new Solution();
    }

    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        // 隐含的递推公式：后面的丑数一定由前面的丑数乘以2，或者乘以3，或者乘以5得来。例如，8,9,10,12一定是1, 2, 3, 4, 5, 6乘以2,3,5三个质数中的某一个得到。（题目规定1算丑数，就当是种子了）
        //然后1.可以通过堆+set去重，找到第n个:需要记录每个pre乘以2/3/5之后的状态，每次取最小值 就是当前丑数
        //2.通过研究递推公式+三指针+动态规划，找到第n个：相当于3个数组，分别是能被2、3、5整除的递增数组，且每个数组的第一个数都为1。维护三个指针，将三个数组合并为一个严格递增的数组。就是传统的双指针法，只是这题是三个指针。这样就一边移指针，一边算各个数组的下一个数，一边merge，就变成了题解的动态规划法的代码。
        public int nthUglyNumber1(int n) {
            PriorityQueue<Long> uglyHeap = new PriorityQueue<>();//小顶
            HashSet<Long> seen = new HashSet<>();//去重的
            //初始化
            uglyHeap.add(1L);
            int res = 0;
            for (int i = 0; i < n; i++) {
                //根据当前cur生成下一个：依次乘以2/3/5 取最小的那个
                Long cur = uglyHeap.poll();
                res = cur.intValue();
                for (int factor : new int[]{2, 3, 5}) {
                    Long nextUply = factor * cur;
                    if (!seen.contains(nextUply)) {
                        uglyHeap.add(nextUply);
                        seen.add(nextUply);
                    }
                }
            }
            return res;
        }

        public int nthUglyNumber(int n) {
            int[] dp = new int[n];
            dp[0] = 1;
            //
            int p2 = 0, p3 = 0, p5 = 0;
            for (int i = 1; i < n; i++) {
                int num2 = dp[p2] * 2;
                int num3 = dp[p3] * 3;
                int num5 = dp[p5] * 5;
                dp[i] = Math.min(Math.min(num2, num3), num5);
                //推进三个指针
                if (dp[i] == num2) {
                    p2++;
                }
                if (dp[i] == num3) {
                    p3++;
                }
                if (dp[i] == num5) {
                    p5++;
                }
            }

            return dp[n - 1];
        }
    }
//leetcode submit region end(Prohibit modification and deletion)

}