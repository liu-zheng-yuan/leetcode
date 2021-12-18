//请实现一个函数，用来判断一棵二叉树是不是对称的。如果一棵二叉树和它的镜像一样，那么它是对称的。
//
// 例如，二叉树 [1,2,2,3,4,4,3] 是对称的。 
//
// 1 
// / \ 
// 2 2 
// / \ / \ 
//3 4 4 3 
//但是下面这个 [1,2,2,null,3,null,3] 则不是镜像对称的: 
//
// 1 
// / \ 
// 2 2 
// \ \ 
// 3 3 
//
// 
//
// 示例 1： 
//
// 输入：root = [1,2,2,3,4,4,3]
//输出：true
// 
//
// 示例 2： 
//
// 输入：root = [1,2,2,null,3,null,3]
//输出：false 
//
// 
//
// 限制： 
//
// 0 <= 节点个数 <= 1000 
//
// 注意：本题与主站 101 题相同：https://leetcode-cn.com/problems/symmetric-tree/ 
// Related Topics 树 深度优先搜索 广度优先搜索 二叉树 👍 265 👎 0


package com.company.leetcode.editor.cn;

public class No_剑指_offer_28_DuiChengDeErChaShuLcof {
    public static void main(String[] args) {
        Solution solution = new No_剑指_offer_28_DuiChengDeErChaShuLcof().new Solution();
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
        public boolean isSymmetric(TreeNode root) {
            if (root == null) return true;
            return recursion(root.left, root.right);

        }

        //定义：a和b是理论上镜像的对称节点，判断以a\b为根的子树是否是镜像的
        //相当于双指针,每次递归都是两个[对称节点指针]在移动
        public boolean recursion(TreeNode a, TreeNode b) {
            //退出条件:
            //1.同时达到null,还是对称的
            if (a == null && b == null) {
                return true;
            }
            //2.有一个达到null,另一个没有,肯定不对称了
            if (a == null || b == null) {
                return false;
            }

            //前序遍历
            if (a.val == b.val) {
                //镜像的对称原理：左节点的右子树 = 右节点的左子树；左节点的左子树 = 右节点的右子树
                return recursion(a.left, b.right) && recursion(a.right, b.left);
            } else {
                return false;
            }
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