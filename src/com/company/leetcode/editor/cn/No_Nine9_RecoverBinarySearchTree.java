//给你二叉搜索树的根节点 root ，该树中的 恰好 两个节点的值被错误地交换。请在不改变其结构的情况下，恢复这棵树 。
//
// 
//
// 示例 1： 
//
// 
//输入：root = [1,3,null,null,2]
//输出：[3,1,null,null,2]
//解释：3 不能是 1 的左孩子，因为 3 > 1 。交换 1 和 3 使二叉搜索树有效。
// 
//
// 示例 2： 
//
// 
//输入：root = [3,1,4,null,null,2]
//输出：[2,1,4,null,null,3]
//解释：2 不能在 3 的右子树中，因为 2 < 3 。交换 2 和 3 使二叉搜索树有效。 
//
// 
//
// 提示： 
//
// 
// 树上节点的数目在范围 [2, 1000] 内 
// -2³¹ <= Node.val <= 2³¹ - 1 
// 
//
// 
//
// 进阶：使用 O(n) 空间复杂度的解法很容易实现。你能想出一个只使用 O(1) 空间的解决方案吗？ 
// Related Topics 树 深度优先搜索 二叉搜索树 二叉树 👍 656 👎 0


package com.company.leetcode.editor.cn;

public class No_Nine9_RecoverBinarySearchTree {
    public static void main(String[] args) {
        Solution solution = new No_Nine9_RecoverBinarySearchTree().new Solution();
    }
    //leetcode submit region begin(Prohibit modification and deletion)

    /**
     * Definition for a binary tree node.
     * public class TreeNode {
     * int val;
     * TreeNode left;
     * TreeNode right;
     * TreeNode() {}
     * TreeNode(int val) { this.val = val; }
     * TreeNode(int val, TreeNode left, TreeNode right) {
     * this.val = val;
     * this.left = left;
     * this.right = right;
     * }
     * }
     */
    class Solution {
        //找到中序遍历中 两个 错位的节点 == 第一个升序顺序错乱最大值节点，和最后一个升序顺序错乱的最小值节点
        //第一个最大值节点
        TreeNode firstMax = null;
        //最后一个最小值节点
        TreeNode lastMin = null;
        //前一个节点
        TreeNode prev = new TreeNode(Integer.MIN_VALUE);

        public void recoverTree(TreeNode root) {
            //中序遍历中保存上一个节点的值，发现不符合“前小于后”则将当前cur放入needSwap
            recusion(root);
            if (firstMax != null && lastMin != null) {
                int t = lastMin.val;
                lastMin.val = firstMax.val;
                firstMax.val = t;
            }
        }

        private void recusion(TreeNode root) {
            if (root == null) {
                return;
            }
            recusion(root.left);
            //即升序关系错乱的情况，可能会有1-2次：1次 对应 两个节点在中序顺序中连在一次的情况；2次 对应两个节点在中序顺序中不连在一起的情况
            //本逻辑可以同时处理1次和2次的情况，因为每次都更新lastMin和firstMax这两个变量
            if (prev.val > root.val) {
                lastMin = root;//"最后一个错乱的最小值节点” ：所以lastMin一直在更新，
                if (firstMax == null) {
                    firstMax = prev;//“第一个错乱的最大值节点”：“第一个”所以只要第一次发现错乱了就赋值，且因为第一次发现时，root是小节点，pre是大节点，所以firstMax 是pre
                }
            }
            prev = root;
            recusion(root.right);

        }
    }

    //leetcode submit region end(Prohibit modification and deletion)
    public class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode() {
        }

        TreeNode(int val) {
            this.val = val;
        }

        TreeNode(int val, TreeNode left, TreeNode right) {
            this.val = val;
            this.left = left;
            this.right = right;
        }
    }
}