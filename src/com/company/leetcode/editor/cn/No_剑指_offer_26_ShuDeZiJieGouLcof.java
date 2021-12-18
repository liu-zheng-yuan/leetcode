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
// Related Topics 树 深度优先搜索 二叉树 👍 405 👎 0


package com.company.leetcode.editor.cn;

public class No_剑指_offer_26_ShuDeZiJieGouLcof {
    public static void main(String[] args) {
        Solution solution = new No_剑指_offer_26_ShuDeZiJieGouLcof().new Solution();
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
        public boolean isSubStructure(TreeNode A, TreeNode B) {
            if (A == null || B == null) {
                return false;
            }
            return traverse(A, B);
        }

        private boolean traverse(TreeNode a, TreeNode b) {
            if (a == null) {
                return false;
            }
            //前序遍历：判断以a作为根节点的子结构，与B是否一致
            if (recursion(a, b)) {
                return true;
            }
            return traverse(a.left, b) || traverse(a.right, b);


        }

        private boolean recursion(TreeNode a, TreeNode b) {
            //退出条件：
            //虽然题目里说空树不是子结构,但是在遍历过程中,所有情况都需要考虑,[且与题目约定不一定相同]
            //下面的逻辑的前提是AB都不是空，那么退出条件里需要判断AB都是空，AB有一个是空的情况
            if (a == null && b == null) {
                return true;//a和b都是叶子节点的情况下，虽然左右子树都是null，但是他们依然有相同的结构和值
            }
            if (a == null && b != null) {
                return false;//同样的位置B有A没有节点，显然不一致
            }
            if (a != null && b == null) {
                return true;//特殊一点，此时是B已经匹配完了，而A还有其他子节点，符合题意，也算true
            }
            //前序遍历位置
            //需要判断当前节点是否跟目标节点的值一样,这关乎我们怎么做选择
            //如果一致:需要继续判断Aleft和Bleft是否一致 [和] Aright和Bright是否一致
            //如果不一致:那么可能A的left[或者]Aright中有与B一致的子结构
            if (a.val == b.val) {
                return recursion(a.left, b.left) && recursion(a.right, b.right);
            } else {
                return false;
            }
        }


    }
}