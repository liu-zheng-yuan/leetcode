//请实现一个函数按照之字形顺序打印二叉树，即第一行按照从左到右的顺序打印，第二层按照从右到左的顺序打印，第三行再按照从左到右的顺序打印，其他行以此类推。
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
//  [20,9],
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
// Related Topics 树 广度优先搜索 二叉树 👍 157 👎 0


package com.company.leetcode.editor.cn;

import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class No_剑指Offer32III_CongShangDaoXiaDaYinErChaShuIiiLcof {
    public static void main(String[] args) {
        Solution solution = new No_剑指Offer32III_CongShangDaoXiaDaYinErChaShuIiiLcof().new Solution();
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
            //其实就是比之前两道层序遍历的题，多记录了一个层号level，level是单数就正，偶数就反着
            Queue<TreeNode> queue = new LinkedList<>();
            List<List<Integer>> res = new LinkedList<>();
            if (root == null) {
                return res;
            }
            //
            queue.add(root);
            int level = 1;//记录层号
            while (!queue.isEmpty()) {
                //遍历当前层
                List<Integer> curRes = new LinkedList<>();
                int curSize = queue.size();
                int idx = 0;
                while (idx < curSize) {
                    //当前节点
                    TreeNode cur = queue.poll();
                    curRes.add(cur.val);
                    if (cur.left != null) {
                        queue.offer(cur.left);
                    }
                    if (cur.right != null) {
                        queue.offer(cur.right);
                    }
                    idx++;
                }
                //这一层处理完了
                if (level % 2 == 1) {
                    //奇数层不变
                } else {
                    //偶数层逆序
                    List<Integer> reversed = new LinkedList<>();
                    for (int i = curRes.size() - 1; i >= 0; i--) {
                        reversed.add(curRes.get(i));
                    }
                    curRes = reversed;
                }
                //这一层处理完的结果加入res
                res.add(curRes);
                level++;
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