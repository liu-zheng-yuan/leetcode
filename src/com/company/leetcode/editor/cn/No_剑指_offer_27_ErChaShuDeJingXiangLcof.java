//请完成一个函数，输入一个二叉树，该函数输出它的镜像。
//
// 例如输入： 
//
// 4 
// / \ 
// 2 7 
// / \ / \ 
//1 3 6 9 
//镜像输出： 
//
// 4 
// / \ 
// 7 2 
// / \ / \ 
//9 6 3 1 
//
// 
//
// 示例 1： 
//
// 输入：root = [4,2,7,1,3,6,9]
//输出：[4,7,2,9,6,3,1]
// 
//
// 
//
// 限制： 
//
// 0 <= 节点个数 <= 1000 
//
// 注意：本题与主站 226 题相同：https://leetcode-cn.com/problems/invert-binary-tree/ 
// Related Topics 树 深度优先搜索 广度优先搜索 二叉树 👍 192 👎 0


package com.company.leetcode.editor.cn;

public class No_剑指_offer_27_ErChaShuDeJingXiangLcof {
    public static void main(String[] args) {
        Solution solution = new No_剑指_offer_27_ErChaShuDeJingXiangLcof().new Solution();
    }
    //leetcode submit region begin(Prohibit modification and deletion)

    //leetcode submit region end(Prohibit modification and deletion)
    public static class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode(int x) {
            val = x;
        }
    }

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
        //定义:交换以root为根的左右子树上所有的节点
        public TreeNode mirrorTree(TreeNode root) {
            if (root == null) return null;
            //前序遍历位置
            //当前节点需要交换 【以左右子节点为根的子树】，这样当前节点的活已经干完了
            TreeNode temp = root.left;
            root.left = root.right;
            root.right = temp;
            //由于前序遍历时已经交换了左右指针,虽然这里的递归函数能返回根节点,但是也不再需要了
            mirrorTree(root.right);
            mirrorTree(root.left);
            return root;
        }
    }
}