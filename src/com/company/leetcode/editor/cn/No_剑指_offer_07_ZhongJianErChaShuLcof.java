//输入某二叉树的前序遍历和中序遍历的结果，请构建该二叉树并返回其根节点。
//
// 假设输入的前序遍历和中序遍历的结果中都不含重复的数字。 
//
// 
//
// 示例 1: 
//
// 
//Input: preorder = [3,9,20,15,7], inorder = [9,3,15,20,7]
//Output: [3,9,20,null,null,15,7]
// 
//
// 示例 2: 
//
// 
//Input: preorder = [-1], inorder = [-1]
//Output: [-1]
// 
//
// 
//
// 限制： 
//
// 0 <= 节点个数 <= 5000 
//
// 
//
// 注意：本题与主站 105 题重复：https://leetcode-cn.com/problems/construct-binary-tree-from-
//preorder-and-inorder-traversal/ 
// Related Topics 树 数组 哈希表 分治 二叉树 👍 621 👎 0


package com.company.leetcode.editor.cn;

public class No_剑指_offer_07_ZhongJianErChaShuLcof {
    public static void main(String[] args) {
        Solution solution = new No_剑指_offer_07_ZhongJianErChaShuLcof().new Solution();
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
        public TreeNode buildTree(int[] preorder, int[] inorder) {
            if (preorder.length == 0 || inorder.length == 0) {
                return null;
            }
            return recursion(preorder, inorder, 0, preorder.length - 1, 0, inorder.length - 1);

        }

        //定义：在preorder的[preS,preE]和inorder的[inS,inE]范围内,构建并返回二叉树的根节点
        public TreeNode recursion(int[] preorder, int[] inorder, int preS, int preE, int inS, int inE) {
            if (preS > preE || inS > inE) {
                return null;
            }
            //前序遍历的地方
            int rootValue = preorder[preS];
            int index = inS;//找到中序遍历里左右子树的分界点
            for (int i = inS; i <= inE; i++) {
                if (inorder[i] == rootValue) {
                    index = i;
                    break;
                }
            }
            //构建根节点
            TreeNode root = new TreeNode(rootValue);
            //重新划分左右子树对应的数组范围
            //len等于[inS,index)的长度,等于[inS,index-1]的长度,等于[inS+1,index]的长度
            //等于[preS,preS+len)的长度,等于[preS,preS+len-1]的长度,等于[preS+1,preS+len]的长度
            int len = index - inS;

            root.left = recursion(preorder, inorder, preS + 1, preS + len, inS, index - 1);
            root.right = recursion(preorder, inorder, preS + len + 1, preE, index + 1, inE);
            return root;
        }
    }
}