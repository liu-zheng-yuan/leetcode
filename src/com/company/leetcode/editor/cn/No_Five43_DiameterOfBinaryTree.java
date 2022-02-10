//给定一棵二叉树，你需要计算它的直径长度。一棵二叉树的直径长度是任意两个结点路径长度中的最大值。这条路径可能穿过也可能不穿过根结点。
//
// 
//
// 示例 : 
//给定二叉树 
//
//           1
//         / \
//        2   3
//       / \     
//      4   5    
// 
//
// 返回 3, 它的长度是路径 [4,2,1,3] 或者 [5,2,1,3]。 
//
// 
//
// 注意：两结点之间的路径长度是以它们之间边的数目表示。 
// Related Topics 树 深度优先搜索 二叉树 👍 902 👎 0


package com.company.leetcode.editor.cn;

public class No_Five43_DiameterOfBinaryTree {
    public static void main(String[] args) {
        Solution solution = new No_Five43_DiameterOfBinaryTree().new Solution();
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
        public int diameterOfBinaryTree(TreeNode root) {
            traverse(root);
            return maxRes;
        }

        int maxRes = 0;
        //返回节点最深深度
        public int traverse(TreeNode root) {
            //对于当前节点需要算出左右子树的最深深度，之和就是当前节点的直径，但不一定是全局最大直径，还需要遍历每个子节点
            if (root == null) {
                return 0;
            }
            //
            int leftDepth = traverse(root.left);
            int rightDepth = traverse(root.right);
            //计算当前深度和 左右深度最大和
            int curDepth = Math.max(leftDepth, rightDepth) + 1;
            if (leftDepth + rightDepth > maxRes) {
                maxRes = leftDepth + rightDepth;
            }
            return curDepth;
        }
    }

    //leetcode submit region end(Prohibit modification and deletion)
    public static class TreeNode {
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