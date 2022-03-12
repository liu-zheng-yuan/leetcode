//给你一个二叉树的根节点 root ，判断其是否是一个有效的二叉搜索树。
//
// 有效 二叉搜索树定义如下： 
//
// 
// 节点的左子树只包含 小于 当前节点的数。 
// 节点的右子树只包含 大于 当前节点的数。 
// 所有左子树和右子树自身必须也是二叉搜索树。 
// 
//
// 
//
// 示例 1： 
//
// 
//输入：root = [2,1,3]
//输出：true
// 
//
// 示例 2： 
//
// 
//输入：root = [5,1,4,null,null,3,6]
//输出：false
//解释：根节点的值是 5 ，但是右子节点的值是 4 。
// 
//
// 
//
// 提示： 
//
// 
// 树中节点数目范围在[1, 10⁴] 内 
// -2³¹ <= Node.val <= 2³¹ - 1 
// 
// Related Topics 树 深度优先搜索 二叉搜索树 二叉树 👍 1404 👎 0


package com.company.leetcode.editor.cn;

public class No_Nine8_ValidateBinarySearchTree {
    public static void main(String[] args) {
        Solution solution = new No_Nine8_ValidateBinarySearchTree().new Solution();
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
        public boolean isValidBST(TreeNode root) {
            return recusion(root)[0] == 1L;
        }

        //三合一函数：同时返回1.是不是bst 2.该树中最小值 3.该数中最大值
        public Long[] recusion1(TreeNode root) {
            if (root == null) {
                return new Long[]{1L, Long.MAX_VALUE, Long.MIN_VALUE};
            }
            Long[] leftValue = recusion(root.left);
            Long[] rightValue = recusion(root.right);
            //后序把需要计算的内容，都通过返回值返回，再做判断
            //左子树最大值 要小于root；右子树最小值要大于root
            //计算左右子树合起来之后的最大和最小值
            Long min = Math.min(Math.min(leftValue[1], rightValue[1]), root.val);
            Long max = Math.max(Math.max(leftValue[2], rightValue[2]), root.val);
            if (leftValue[0] == 1 && rightValue[0] == 1 && leftValue[2] < root.val && rightValue[1] > root.val) {
                return new Long[]{1L, min, max};
            }
            return new Long[]{0L, min, max};
        }

        //三合一函数：同时返回1.是不是bst 2.该树中最大值 3.该数中最小值
        public Long[] recusion(TreeNode root) {
            if (root == null) {
                return new Long[]{1L, Long.MIN_VALUE, Long.MAX_VALUE};
            }
            //
            Long[] left = recusion(root.left);
            Long[] right = recusion(root.right);
            Long min = Math.min(Math.min(left[2], right[2]), root.val);
            Long max = Math.max(Math.max(left[1], right[1]), root.val);
            //判断当前根节点是不是bst
            if (left[0] == 1 && right[0] == 1 && root.val > left[1] && root.val < right[2]) {
                return new Long[]{1L, max, min};
            }
            return new Long[]{0L, max, min};//
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