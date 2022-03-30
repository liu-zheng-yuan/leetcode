//输入两棵二叉树A和B，判断B是不是A的子结构。(约定空树不是任意一个树的子结构)
//
// B是A的子结构， 即 A中有出现和B相同的结构和节点值。 
//
// 例如: 
//给定的树 A: 
//
// 3 
// / \ 
// 4 5 
// / \ 
// 1 2 
//给定的树 B： 
//
// 4 
// / 
// 1 
//返回 true，因为 B 与 A 的一个子树拥有相同的结构和节点值。 
//
// 示例 1： 
//
// 输入：A = [1,2,3], B = [3,1]
//输出：false
// 
//
// 示例 2： 
//
// 输入：A = [3,4,5,1,2], B = [4,1]
//输出：true 
//
// 限制： 
//
// 0 <= 节点个数 <= 10000 
// Related Topics 树 深度优先搜索 二叉树 👍 517 👎 0


package com.company.leetcode.editor.cn;

public class No_剑指Offer26_ShuDeZiJieGouLcof {
    public static void main(String[] args) {
        Solution solution = new No_剑指Offer26_ShuDeZiJieGouLcof().new Solution();
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
    //先看Aroot和B是不是一致的（递归对比） 如果不是再看左子树、右子树（先序遍历）
    class Solution {
        public boolean isSubStructure(TreeNode A, TreeNode B) {
            if (A == null || B == null) {
                return false;
            }
            return isSame(A, B) || isSubStructure(A.left, B) || isSubStructure(A.right, B);
        }

        public boolean isSame(TreeNode A, TreeNode B) {
            //如果B对比完了，说明B是子结构，直接返回true
            if (B == null) {
                return true;
            }
            //如果A先比完，则说明不是 或者值不同肯定也不是
            if (A == null || A.val != B.val) {
                return false;
            }
            //递归比较左右子树
            return isSame(A.left, B.left) && isSame(A.right, B.right);
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