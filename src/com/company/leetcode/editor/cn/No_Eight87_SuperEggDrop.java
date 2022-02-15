//给你 k 枚相同的鸡蛋，并可以使用一栋从第 1 层到第 n 层共有 n 层楼的建筑。
//
// 已知存在楼层 f ，满足 0 <= f <= n ，任何从 高于 f 的楼层落下的鸡蛋都会碎，从 f 楼层或比它低的楼层落下的鸡蛋都不会破。 
//
// 每次操作，你可以取一枚没有碎的鸡蛋并把它从任一楼层 x 扔下（满足 1 <= x <= n）。如果鸡蛋碎了，你就不能再次使用它。如果某枚鸡蛋扔下后没有摔碎
//，则可以在之后的操作中 重复使用 这枚鸡蛋。 
//
// 请你计算并返回要确定 f 确切的值 的 最小操作次数 是多少？ 
// 
//
// 示例 1： 
//
// 
//输入：k = 1, n = 2
//输出：2
//解释：
//鸡蛋从 1 楼掉落。如果它碎了，肯定能得出 f = 0 。 
//否则，鸡蛋从 2 楼掉落。如果它碎了，肯定能得出 f = 1 。 
//如果它没碎，那么肯定能得出 f = 2 。 
//因此，在最坏的情况下我们需要移动 2 次以确定 f 是多少。 
// 
//
// 示例 2： 
//
// 
//输入：k = 2, n = 6
//输出：3
// 
//
// 示例 3： 
//
// 
//输入：k = 3, n = 14
//输出：4
// 
//
// 
//
// 提示： 
//
// 
// 1 <= k <= 100 
// 1 <= n <= 10⁴ 
// 
// Related Topics 数学 二分查找 动态规划 👍 733 👎 0


package com.company.leetcode.editor.cn;

import java.util.HashMap;
import java.util.Map;

public class No_Eight87_SuperEggDrop {
    public static void main(String[] args) {
        Solution solution = new No_Eight87_SuperEggDrop().new Solution();
    }

    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        /**
         * 题目要求的是：最坏情况下的 最少步数
         * 最坏情况：跟【搜索空间】有关，与选择无关。穷尽搜索空间之后，最后才能找到（而不是运气好，第一次就蒙对了，这样问没有意义）
         * 最少步数：跟【搜索方法】有关，与选择有关。搜索空间是一定的，搜索方法是有优劣之分的，最傻的是从1楼开始遍历，聪明的是二分，所以问最聪明的方法的最少步数（同样，问最大步数也没有意义，直接遍历就是最大）
         * 此外，本地的状态，k和n，影响了搜索方法：k=1，只能遍历，k足够大，就可以一直二分
         * 思路：
         * 状态：k和n
         * 定义：dp表示 用k个鸡蛋，判断n层楼，最坏情况下的最少步数
         * 选择与转移：对于第k个鸡蛋，我能做的选择：从i = 1-n层楼上都扔一下，结果：1.如果炸了，转移到（k-1，[1..i-1]） 2.如果没炸，转移到（k，[i+1,n]）.向上判断楼层时，可以把第i层当做地板，把i+1层当做1楼，把[i+1,n]看做另一个从地板往上高度为(n-i)的楼，这样状态，就可以用 一个楼高n来表示了
         * 最坏情况 - 根结果有关 - 取结果里最大的Max
         * 最少步数 - 根选择优劣有关 - 取 i = 1-n 遍历中最小的步数Min
         */
        public int superEggDrop(int k, int n) {
            return dp2(k, n);
        }

        Map<String, Integer> memory = new HashMap<>();

        //无二分优化
        private int dp1(int k, int n) {
            //边界条件，n = 0，显然不用测，k=1，只能遍历
            if (n == 0) {
                return 0;
            }
            if (k == 1) {
                return n;
            }
            String key = String.valueOf(k) + "#" + String.valueOf(n);
            if (memory.containsKey(key)) {
                return memory.get(key);
            }
            //转移:需要遍历从1到n层楼上扔鸡蛋，所有的选择结果，找到最小的
            int res = Integer.MAX_VALUE;
            for (int i = 1; i <= n; i++) {
                res = Math.min(
                        res,
                        Math.max(
                                dp(k - 1, i - 1),//炸了，消耗一个鸡蛋，往下找
                                dp(k, n - i)//没炸，不消耗鸡蛋，往上找
                        ) + 1 //当前第i层扔了一下，操作次数++
                );
            }
            memory.put(key, res);
            return res;
        }

        //for循环改二分优化

        /**
         * dp(K - 1, i - 1) 关于i 单调递增 （因为楼层越高，需要查询的次数越多）
         * dp(K, N - i) 关于i单调递减
         * 对i in 【1-n】，求 两者较大值的最小值： Min （Max（dp(k - 1, i - 1), dp(k, n - i)））
         * 等于求函数图像上 两个交叉斜线的交叉点 ：https://mp.weixin.qq.com/s?__biz=MzAxODQxMDM0Mw==&mid=2247484690&idx=1&sn=eea075701a5d96dd5c6e3dc6a993cac5&scene=21#wechat_redirect
         * 即此时的条件是：dp(k - 1, i - 1) == dp(k, n - i)，就是最小值.等于找图像里的“山谷”
         */
        private int dp(int k, int n) {
            //边界条件，n = 0，显然不用测，k=1，只能遍历
            if (n == 0) {
                return 0;
            }
            if (k == 1) {
                return n;
            }
            String key = String.valueOf(k) + "#" + String.valueOf(n);
            if (memory.containsKey(key)) {
                return memory.get(key);
            }
            //转移:需要遍历从1到n层楼上扔鸡蛋，所有的选择结果，找到最小的
            int left = 1, right = n;
            int res = Integer.MAX_VALUE;
            while (left <= right) { //左闭右闭区间的二分搜索退出条件是 left = right +1 ，即 left <= right
                int mid = (left + right) / 2;
                int broken = dp(k - 1, mid - 1);
                int notBroken = dp(k, n - mid);
                if (broken == notBroken) {
                    res = Math.min(res, broken + 1);
                    break;
                } else if (notBroken > broken) { //山谷前半段
                    left = mid + 1;
                    res = Math.min(res, notBroken + 1);   //取较大值里的最小值，此时的较大值是 notBroken状态的值
                } else if (broken > notBroken) { //山谷后半段
                    right = mid - 1;
                    res = Math.min(res, broken + 1); //取较大值里的最小值，此时的较大值是 broken状态的值
                }

            }
            memory.put(key, res);
            return res;
        }

        private int dp2(int k, int n) {
            if (k == 1) return n;
            if (n == 0) return 0;

            //
            String key = String.valueOf(k) + "-" + String.valueOf(n);
            if (memory.containsKey(key)) {
                return memory.get(key);
            }

            int res = Integer.MAX_VALUE;
            for (int i = 1; i <= n; i++) {
                res = Math.min(res, Math.max(dp2(k - 1, i - 1), dp2(k, n - i)) + 1);
            }
            memory.put(key, res);
            return res;
        }
    }
//leetcode submit region end(Prohibit modification and deletion)

}