//给你一个整数 n ，求恰由 n 个节点组成且节点值从 1 到 n 互不相同的 二叉搜索树 有多少种？返回满足题意的二叉搜索树的种数。
//
// 
//
// 示例 1： 
//
// 
//输入：n = 3
//输出：5
// 
//
// 示例 2： 
//
// 
//输入：n = 1
//输出：1
// 
//
// 
//
// 提示： 
//
// 
// 1 <= n <= 19 
// 
// Related Topics 树 二叉搜索树 数学 动态规划 二叉树 👍 1576 👎 0


package com.company.leetcode.editor.cn;

import java.util.HashMap;

public class No_Nine6_UniqueBinarySearchTrees {
    public static void main(String[] args) {
        Solution solution = new No_Nine6_UniqueBinarySearchTrees().new Solution();
    }

    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        /**
         * 本题实际上只用到了BST的一个性质：左边都比root小，右边都比root大
         * 实际上是个动态规划的题：每次从[1,n]中选一个根节点k，root左边的[1,k-1]就是左子树,root右边的[k+1,n]就是右子树，可以用动态规划的定义去解决重复子问题
         * dp(n) = SUM( dp(k-1) * dp(n-k) ) | for k in [1,n]
         */
        HashMap<Integer,Integer> map = new HashMap();
        public int numTrees(int n) {
            if (n == 0 || n == 1) {
                return 1;
            }
            if (map.containsKey(n)) {
                return map.get(n);
            }
            int res = 0;
            for (int k = 1; k <= n; k++) {
                res += numTrees(k - 1) * numTrees(n - k);
            }
            map.put(n, res);
            return res;

        }
    }
//leetcode submit region end(Prohibit modification and deletion)

}