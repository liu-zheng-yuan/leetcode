//给定一棵二叉搜索树，请找出其中第k大的节点。
//
// 
//
// 示例 1: 
//
// 输入: root = [3,1,4,null,2], k = 1
//   3
//  / \
// 1   4
//  \
//   2
//输出: 4 
//
// 示例 2: 
//
// 输入: root = [5,3,6,2,4,null,null,1], k = 3
//       5
//      / \
//     3   6
//    / \
//   2   4
//  /
// 1
//输出: 4 
//
// 
//
// 限制： 
//
// 1 ≤ k ≤ 二叉搜索树元素个数 
// Related Topics 树 深度优先搜索 二叉搜索树 二叉树 👍 223 👎 0


package com.company.leetcode.editor.cn;

import javax.print.DocFlavor;
import java.util.LinkedList;

public class No_剑指Offer54_ErChaSouSuoShuDeDiKdaJieDianLcof {
    public static void main(String[] args) {
        Solution solution = new No_剑指Offer54_ErChaSouSuoShuDeDiKdaJieDianLcof().new Solution();
    }
    //leetcode submit region begin(Prohibit modification and deletion)

    /**
     * Definition for a binary tree node.
     * public class TreeNode {
     * int val;
     * TreeNode left;
     * TreeNode right;
     * TreeNode(int x) { val = x; }
     * }
     */
    class Solution {
        int res = -1;
        int count = 0;

        public int kthLargest(TreeNode root, int k) {
            LinkedList<Integer> trace = new LinkedList<>();
            rescursion(root, trace, k);
            return res;
        }

        //定义：遍历二叉树，返回以root为根的树的节点数量。并且在【左子树节点数量等于k-1时，将root的值付给res】
        private void rescursion(TreeNode root, LinkedList<Integer> trace, int k) {
            if (root == null) {
                return;
            }
            rescursion(root.right, trace, k);
            //中序遍历逻辑
            count++;
            if (count == k) {
                res = root.val;
                return;
            }
            rescursion(root.left, trace, k);
        }


    }

    //leetcode submit region end(Prohibit modification and deletion)
    public class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode(int x) {
            val = x;
        }
    }
}