//从上到下按层打印二叉树，同一层的节点按从左到右的顺序打印，每一层打印到一行。
//
// 
//
// 例如: 
//给定二叉树: [3,9,20,null,null,15,7], 
//
//     3
//   / \
//  9  20
//    /  \
//   15   7
// 
//
// 返回其层次遍历结果： 
//
// [
//  [3],
//  [9,20],
//  [15,7]
//]
// 
//
// 
//
// 提示： 
//
// 
// 节点总数 <= 1000 
// 
//
// 注意：本题与主站 102 题相同：https://leetcode-cn.com/problems/binary-tree-level-order-
//traversal/ 
// Related Topics 树 广度优先搜索 二叉树 👍 160 👎 0


package com.company.leetcode.editor.cn;

import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class No_剑指Offer32II_CongShangDaoXiaDaYinErChaShuIiLcof {
    public static void main(String[] args) {
        Solution solution = new No_剑指Offer32II_CongShangDaoXiaDaYinErChaShuIiLcof().new Solution();
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
        public List<List<Integer>> levelOrder(TreeNode root) {
            List<List<Integer>> res = new LinkedList<>();
            Queue<TreeNode> queue = new LinkedList<>();
            if (root == null) {
                return res;
            }
            //第一个
            queue.offer(root);
            while (!queue.isEmpty()) {
                int curSize = queue.size();
                int idx = 0;
                List<Integer> levelRes = new LinkedList<>();
                while (idx < curSize) {
                    TreeNode cur = queue.poll();
                    //当前节点处理逻辑
                    levelRes.add(cur.val);
                    //子节点加入队列
                    if (cur.left != null) {
                        queue.offer(cur.left);
                    }
                    if (cur.right != null) {
                        queue.offer(cur.right);
                    }
                    idx++;
                }
                res.add(levelRes);
            }
            return res;

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