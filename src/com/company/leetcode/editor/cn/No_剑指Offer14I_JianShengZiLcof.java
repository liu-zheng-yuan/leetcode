//给你一根长度为 n 的绳子，请把绳子剪成整数长度的 m 段（m、n都是整数，n>1并且m>1），每段绳子的长度记为 k[0],k[1]...k[m-1] 。
//请问 k[0]*k[1]*...*k[m-1] 可能的最大乘积是多少？例如，当绳子的长度是8时，我们把它剪成长度分别为2、3、3的三段，此时得到的最大乘积是18
//。 
//
// 示例 1： 
//
// 输入: 2
//输出: 1
//解释: 2 = 1 + 1, 1 × 1 = 1 
//
// 示例 2: 
//
// 输入: 10
//输出: 36
//解释: 10 = 3 + 3 + 4, 3 × 3 × 4 = 36 
//
// 提示： 
//
// 
// 2 <= n <= 58 
// 
//
// 注意：本题与主站 343 题相同：https://leetcode-cn.com/problems/integer-break/ 
// Related Topics 数学 动态规划 👍 386 👎 0


package com.company.leetcode.editor.cn;

public class No_剑指Offer14I_JianShengZiLcof {
    public static void main(String[] args) {
        Solution solution = new No_剑指Offer14I_JianShengZiLcof().new Solution();
    }

    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        public int cuttingRope(int n) {
            int[] dp = new int[n + 1];//dp[i]表示长度为i的绳子剪成m段后的最大乘积
            //由于题目规定n>1，所以n =1 没有意义结果就是0，而不是不切就等于1
            dp[0] = 0;
            dp[1] = 0;
            //由于m要求>1,所以必须切一刀以上，里面的for循环用来遍历切这一刀的位置
            //切了这一刀之后，left = 1，剩下的right = n-i，有两种“选择”：1,继续切（切多少次不知道），后面的结果就是dp(n-i) 2.不切，后面的结果就是n-i

            for (int i = 2; i <= n; i++) {
                //遍历切第一道的位置
                //对于长度为3的绳子，线段开头是0，结尾是3，只能在1,2的下标的地方切，不能在下标0和3的地方切（那样还是长度3）
                //实际上从1开始切没有意义，不会增长乘积，应该从2开始切
                for (int j = 1; j <= i - 1; j++) {
                    dp[i] = Math.max(dp[i], Math.max(j * (i - j), j * dp[i - j]));
                }
            }
            return dp[n];
        }


        //定义：长n的绳子，最少分成2段，可能的最大乘积。
        public int recusion(int n) {
            //边界条件，切的过程中会出现
            if (n == 1) {
                return 1;
            }
            if (n == 2) {
                return 1;
            }
            //
            int res = 0;//保存每种选择能产生的最大值
            //对于长n的绳子，从下标1到下标n-1，一共n-1种状态都能选择“切”或者不切“。比如对于长度为3的绳子，线段开头是0，结尾是3，只能在1,2的下标的地方切，不能在下标0和3的地方切（那样还是长度3）
            for (int i = 1; i <= n - 1; i++) {
                //切完后左边的长度
                int left = i;
                //右边的长度
                int right = n - i;
                //按当前切法切了之后的“结果”
                int curRes = recusion(left) * recusion(right);
                //
                res = Math.max(curRes, res);
            }
            return res;

        }
    }
//leetcode submit region end(Prohibit modification and deletion)

}