//给定一个二叉树的根节点 root ，返回它的 中序 遍历。
//
// 
//
// 示例 1： 
//
// 
//输入：root = [1,null,2,3]
//输出：[1,3,2]
// 
//
// 示例 2： 
//
// 
//输入：root = []
//输出：[]
// 
//
// 示例 3： 
//
// 
//输入：root = [1]
//输出：[1]
// 
//
// 示例 4： 
//
// 
//输入：root = [1,2]
//输出：[2,1]
// 
//
// 示例 5： 
//
// 
//输入：root = [1,null,2]
//输出：[1,2]
// 
//
// 
//
// 提示： 
//
// 
// 树中节点数目在范围 [0, 100] 内 
// -100 <= Node.val <= 100 
// 
//
// 
//
// 进阶: 递归算法很简单，你可以通过迭代算法完成吗？ 
// Related Topics 栈 树 深度优先搜索 二叉树 👍 1281 👎 0


package com.company.leetcode.editor.cn;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

public class No_Nine4_BinaryTreeInorderTraversal {
    public static void main(String[] args) {
        Solution solution = new No_Nine4_BinaryTreeInorderTraversal().new Solution();
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
        public List<Integer> inorderTraversal(TreeNode root) {
            List<Integer> res = new ArrayList<>();
            Stack<TreeNode> stack = new Stack<>();
            TreeNode visited = new TreeNode(-1);//指向最近刚遍历完成的子树的根节点，注意初始化不能为null 需要是一个不存在的节点。
            pushLeft(root, stack);

            while (!stack.isEmpty()) {
                TreeNode cur = stack.peek();
                //左子树遍历完成后的逻辑
                //visited != cur.right 一定要有，用来避免右子树遍历完成后第二次访问当前cur节点时，误判而进入“左子树完成后的中序遍历逻辑”的情况
                //即下面的逻辑只有第一次访问cur的时候能运行
                if ((cur.left == null || cur.left == visited) && visited != cur.right) {
                    res.add(cur.val);
                    pushLeft(cur.right, stack);
//                    visited = cur.left; 这里不需要更新visited
                }
                //右子树遍历完成后的逻辑
                if (cur.right == null || cur.right == visited) {
                    visited = stack.pop();
                }
            }
            return res;
        }

        public void pushLeft(TreeNode root, Stack<TreeNode> stack) {
            TreeNode p = root;
            while (p != null) {
                stack.add(p);
                p = p.left;
            }
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