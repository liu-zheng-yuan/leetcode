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
// Related Topics 树 数组 哈希表 分治 二叉树 👍 729 👎 0


package com.company.leetcode.editor.cn;

public class No_剑指Offer07_ZhongJianErChaShuLcof {
    public static void main(String[] args) {
        Solution solution = new No_剑指Offer07_ZhongJianErChaShuLcof().new Solution();
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
        public TreeNode buildTree(int[] preorder, int[] inorder) {
            return build(preorder, inorder, 0, preorder.length - 1, 0, inorder.length - 1);
        }

        public TreeNode build(int[] preorder, int[] inorder, int pL, int pR, int iL, int iR) {
            if (pL > pR || iL > iR) {
                return null;
            }
            //先找到根节点
            int rootValue = preorder[pL];
            int rootIIdx = 0;//根节点再inorder中的位置
            for (int i = iL; i <= iR; i++) {
                if (inorder[i] == rootValue) {
                    rootIIdx = i;
                    break;
                }
            }
            //计算左子树和右子树长度
            int leftLen = rootIIdx - iL;//[iL,rootIIdx)
            int rightLen = iR - rootIIdx;//[rootIIdx+1,iR]
            //
            TreeNode root = new TreeNode(rootValue);
            root.left = build(preorder, inorder, pL + 1, pL + leftLen, iL, rootIIdx - 1);
            root.right = build(preorder, inorder, pL + leftLen + 1, pR, rootIIdx + 1, iR);
            return root;
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