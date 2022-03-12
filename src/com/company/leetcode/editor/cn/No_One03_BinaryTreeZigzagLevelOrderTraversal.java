//给你二叉树的根节点 root ，返回其节点值的 锯齿形层序遍历 。（即先从左往右，再从右往左进行下一层遍历，以此类推，层与层之间交替进行）。
//
// 
//
// 示例 1： 
//
// 
//输入：root = [3,9,20,null,null,15,7]
//输出：[[3],[20,9],[15,7]]
// 
//
// 示例 2： 
//
// 
//输入：root = [1]
//输出：[[1]]
// 
//
// 示例 3： 
//
// 
//输入：root = []
//输出：[]
// 
//
// 
//
// 提示： 
//
// 
// 树中节点数目在范围 [0, 2000] 内 
// -100 <= Node.val <= 100 
// 
// Related Topics 树 广度优先搜索 二叉树 👍 597 👎 0


package com.company.leetcode.editor.cn;

import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class No_One03_BinaryTreeZigzagLevelOrderTraversal {
    public static void main(String[] args) {
        Solution solution = new No_One03_BinaryTreeZigzagLevelOrderTraversal().new Solution();

        TreeNode t1 = new TreeNode(3);
        TreeNode t2 = new TreeNode(9);
        TreeNode t3 = new TreeNode(20);
        TreeNode t4 = new TreeNode(15);
        TreeNode t5 = new TreeNode(7);
        t1.left = t2;
        t1.right = t3;
        t3.left = t4;
        t3.right = t5;
        System.out.println(solution.solve(t1));

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
        public List<List<Integer>> zigzagLevelOrder(TreeNode root) {
            List<List<Integer>> res = new LinkedList<>();
            Queue<TreeNode> queue = new LinkedList<>();
            int step = 0;
            if (root == null) {
                return res;
            }
            queue.add(root);
            while (!queue.isEmpty()) {
                int size = queue.size();
                LinkedList<Integer> level = new LinkedList<>();
                for (int i = 0; i < size; i++) {
                    TreeNode cur = queue.poll();
                    //
                    if (step % 2 == 1) {
                        level.addFirst(cur.val);
                    } else {
                        level.add(cur.val);
                    }
                    //
                    if (cur.left != null) queue.add(cur.left);
                    if (cur.right != null) queue.add(cur.right);
                }
                step++;
                res.add(level);
            }
            return res;
        }


        //魔改一下，解决字节面试题：蛇形的双向链表
        public TreeNode solve(TreeNode root) {
            Queue<TreeNode> queue = new LinkedList<>();
            int step = 0;
            if (root == null) {
                return null;
            }
            queue.add(root);
            TreeNode totalHead = new TreeNode(-1);
            TreeNode totalP = totalHead;
            while (!queue.isEmpty()) {
                int size = queue.size();
                TreeNode newHead = new TreeNode(-1);//本层的虚拟头结点
                TreeNode p = newHead;//已经组织好的链表末尾
                for (int i = 0; i < size; i++) {
                    TreeNode cur = queue.poll();
                    //在改变指针指向前先把下层放入队列中
                    if (cur.left != null) queue.add(cur.left);
                    if (cur.right != null) queue.add(cur.right);
                    //
                    if (step % 2 == 1) {
                        //头插法
                        cur.right = newHead.right;
                        cur.left = newHead;
                        newHead.right = cur;
                        if (cur.right != null) {
                            cur.right.left = cur;
                        }
                        //
                        if (p == newHead) {
                            p = cur;//头插法的链表末尾节点就是最后一个节点，只需要赋值一次给p
                        }
                    } else {
                        //清理干净当前节点
                        cur.left = null;
                        cur.right = null;
                        //尾插法
                        p.right = cur;
                        cur.left = p;
                        p = p.right;
                    }

                }
                step++;
                //把这层生成的链表和全局的结果连起来:尾接
                totalP.right = newHead.right;
                newHead.right.left = totalP;
                totalP = p;//全局链表的指针也要变化：到当前层链表的末尾

            }
            //全局结果出来了，因为是双向链表，需要断绝对虚拟头结点的链接
            totalHead.right.left = null;
            return totalHead.right;
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