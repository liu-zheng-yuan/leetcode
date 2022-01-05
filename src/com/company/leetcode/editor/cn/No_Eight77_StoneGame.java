//Alice 和 Bob 用几堆石子在做游戏。一共有偶数堆石子，排成一行；每堆都有 正 整数颗石子，数目为 piles[i] 。
//
// 游戏以谁手中的石子最多来决出胜负。石子的 总数 是 奇数 ，所以没有平局。 
//
// Alice 和 Bob 轮流进行，Alice 先开始 。 每回合，玩家从行的 开始 或 结束 处取走整堆石头。 这种情况一直持续到没有更多的石子堆为止，此
//时手中 石子最多 的玩家 获胜 。 
//
// 假设 Alice 和 Bob 都发挥出最佳水平，当 Alice 赢得比赛时返回 true ，当 Bob 赢得比赛时返回 false 。 
//
// 
//
// 示例 1： 
//
// 
//输入：piles = [5,3,4,5]
//输出：true
//解释：
//Alice 先开始，只能拿前 5 颗或后 5 颗石子 。
//假设他取了前 5 颗，这一行就变成了 [3,4,5] 。
//如果 Bob 拿走前 3 颗，那么剩下的是 [4,5]，Alice 拿走后 5 颗赢得 10 分。
//如果 Bob 拿走后 5 颗，那么剩下的是 [3,4]，Alice 拿走后 4 颗赢得 9 分。
//这表明，取前 5 颗石子对 Alice 来说是一个胜利的举动，所以返回 true 。
// 
//
// 示例 2： 
//
// 
//输入：piles = [3,7,2,3]
//输出：true
// 
//
// 
//
// 提示： 
//
// 
// 2 <= piles.length <= 500 
// piles.length 是 偶数 
// 1 <= piles[i] <= 500 
// sum(piles[i]) 是 奇数 
// 
// Related Topics 数组 数学 动态规划 博弈 👍 383 👎 0


package com.company.leetcode.editor.cn;

public class No_Eight77_StoneGame {
    public static void main(String[] args) {
        Solution solution = new No_Eight77_StoneGame().new Solution();
        System.out.println(solution.stoneGame(new int[]{4, 2, 10, 9}));
    }

    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        //定义：dp[i][j][0]表示piles[i..j]里 先手 能去得的最大值 dp[i][j][1]表示 后手能取得的最大值
        //状态：数组起始和结束位置，先手、后手
        //选择：取第i个或者取第j个
        //转移方程：1.dp[i][j][0] = Max（取i，取j） 2.dp[i][j][1] = Max（取i，取j）
        //即 dp[i][j][0] = Max(piles[i]+dp[i+1][j][1],piles[j] + dp[i][j-1][1])  先手先确定，然后后手选择根据先手结果确定
        //   如果先手选左边 ，后手面对的情况就是piles[i+1..j]+先手 即dp[i+1][j][0] (dp[i][j][1] = dp[i][j - 1][0] + piles[j]是错的，因为对于piles[i+1..j] 不一定非要选最右边啊）
        //   如果先手选右边 ，后手面对的情况就是piles[i..j-1]+先手 即dp[i][j-1][0] （dp[i][j][1] = dp[i + 1][j][0] + piles[i]是错的）
        //边界条件：i>j时没有可选piles，结果都是0；i = j时，只有一个piles，先手必赢，dp[0] = piles[i] dp[1] = 0

        //# 解释：我作为先手，面对 piles[i...j] 时，有两种选择：
        //# 要么我选择最左边的那一堆石头，然后面对 piles[i+1...j]  【缩小了状态空间】
        //# 但是此时轮到对方，相当于我变成了后手；
        //# 要么我选择最右边的那一堆石头，然后面对 piles[i...j-1] 【缩小了状态空间】
        //# 但是此时轮到对方，相当于我变成了后手。

        //# 解释：我作为后手，要等先手先选择，有两种情况：
        //# 如果先手选择了最左边那堆，给我剩下了 piles[i+1...j]
        //# 此时轮到我，我变成了先手；
        //# 如果先手选择了最右边那堆，给我剩下了 piles[i...j-1]
        //# 此时轮到我，我变成了先手。

        public boolean stoneGame1(int[] piles) {
            int[][][] dp = new int[piles.length][piles.length][2];
            //边界条件
            for (int i = 0; i < piles.length; i++) {
                dp[i][i][0] = piles[i];
                dp[i][i][1] = 0;
            }
            //由于当前状态依赖 正下方+正左方 状态，所以是i从大到小遍历
            for (int i = piles.length - 1; i >= 0; i--) {
                //j小于i的情况没有意义，j=i的情况是边界条件
                for (int j = i + 1; j < piles.length; j++) {
                    //因为先手选择会影响后手选择：先手选了左，后手只能选右
                    //所以要先判断先手选左还是选右更大，再确定后手怎么选
                    int firstLeft = piles[i] + dp[i + 1][j][1];
                    int firstRight = piles[j] + dp[i][j - 1][1];
                    if (firstLeft > firstRight) {
                        //后手只能选right
                        dp[i][j][0] = firstLeft;
//                        dp[i][j][1] = dp[i][j - 1][0] + piles[j]; 是错的，因为先后手转换后 不一定非要选第j个，也可以选第i+1
                        dp[i][j][1] = dp[i + 1][j][0];//应该用先后手转换的思路，用dp定义解决
                    } else {
                        //后手只能选left
                        dp[i][j][0] = firstRight;
//                        dp[i][j][1] = dp[i + 1][j][0] + piles[i];是错的，因为先后手转换后 不一定非要选第i+1个，也可以选第j
                        dp[i][j][1] = dp[i][j - 1][0];//应该用先后手转换的思路，用dp定义解决

                    }
                }
            }
            return dp[0][piles.length - 1][0] > dp[0][piles.length - 1][1];
        }


        //状态压缩
        public boolean stoneGame(int[] piles) {
            int[][] dp = new int[piles.length][2];
            //边界条件
            for (int i = 0; i < piles.length; i++) {
                dp[i][0] = piles[i];
                dp[i][1] = 0;
            }
            //由于当前状态依赖 正下方+正左方 状态，所以是i从大到小遍历
            for (int i = piles.length - 2; i >= 0; i--) {
                //j小于i的情况没有意义，j=i的情况是边界条件
                for (int j = i + 1; j < piles.length; j++) {
                    //因为先手选择会影响后手选择：先手选了左，后手只能选右
                    //所以要先判断先手选左还是选右更大，再确定后手怎么选
                    int firstLeft = piles[i] + dp[j][1];
                    int firstRight = piles[j] + dp[j - 1][1];
                    if (firstLeft > firstRight) {
                        //后手只能选right
                        //状态压缩时，需要把后手赋值操作放在先手赋值之前，避免先手赋值的时候覆盖了上一轮i计算出来的老状态值（这个值是后手需要的）
                        dp[j][1] = dp[j][0];//应该用先后手转换的思路，用dp定义解决
                        dp[j][0] = firstLeft;
//                        dp[i][j][1] = dp[i][j - 1][0] + piles[j]; 是错的，因为先后手转换后 不一定非要选第j个，也可以选第i+1
                    } else {
                        //后手只能选left
                        dp[j][1] = dp[j - 1][0];//应该用先后手转换的思路，用dp定义解决
                        dp[j][0] = firstRight;
//                        dp[i][j][1] = dp[i + 1][j][0] + piles[i];是错的，因为先后手转换后 不一定非要选第i+1个，也可以选第j

                    }
                }
            }
            return dp[piles.length - 1][0] > dp[piles.length - 1][1];
        }
    }
//leetcode submit region end(Prohibit modification and deletion)

}