//给你二叉树的根节点 root ，返回它节点值的 前序 遍历。
//
// 
//
// 示例 1： 
//
// 
//输入：root = [1,null,2,3]
//输出：[1,2,3]
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
//输出：[1,2]
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
// 进阶：递归算法很简单，你可以通过迭代算法完成吗？ 
// Related Topics 栈 树 深度优先搜索 二叉树 👍 725 👎 0


package com.company.leetcode.editor.cn;

import java.util.LinkedList;
import java.util.List;
import java.util.Stack;

public class No_One44_BinaryTreePreorderTraversal {
    public static void main(String[] args) {
        Solution solution = new No_One44_BinaryTreePreorderTraversal().new Solution();
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
        List<Integer> res = new LinkedList<>();

        public List<Integer> preorderTraversal(TreeNode root) {
            //
            Stack<TreeNode> stack = new Stack<>();
            TreeNode visited = new TreeNode(-1);//上一次遍历完成的根节点
            toLeft(root, stack);
            //
            while (!stack.isEmpty()) {
                //不能直接退栈，要先判断是第一次还是第二次访问到root节点
                TreeNode cur = stack.peek();
                //如果是右子树还没有遍历完，即第一次访问到该root节点：中序遍历位置
                if ((cur.left == null || cur.left == visited) && cur.right != visited) {
                    //中序遍历位置
                    //继续将右子树压栈
                    toLeft(cur.right, stack);
                }
                //如果是右子树已经遍历完了，即第二次访问到该节点：右子树已经遍历完了，后序遍历位置
                if (cur.right == null || cur.right == visited) {
                    //后序遍历位置

                    //对于当前cur节点，左右子树已经遍历完成了，所以1.cur退栈 2.记录已完成的visited = cur
                    visited = stack.pop();
                }
            }
            return res;
        }

        public void toLeft(TreeNode root, Stack<TreeNode> stack) {
            while (root != null) {
                stack.push(root);
                //前序位置
                res.add(root.val);
                root = root.left;
            }
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