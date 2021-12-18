//给定一个二叉搜索树, 找到该树中两个指定节点的最近公共祖先。
//
// 百度百科中最近公共祖先的定义为：“对于有根树 T 的两个结点 p、q，最近公共祖先表示为一个结点 x，满足 x 是 p、q 的祖先且 x 的深度尽可能大（
//一个节点也可以是它自己的祖先）。” 
//
// 例如，给定如下二叉搜索树: root = [6,2,8,0,4,7,9,null,null,3,5] 
//
// 
//
// 
//
// 示例 1: 
//
// 输入: root = [6,2,8,0,4,7,9,null,null,3,5], p = 2, q = 8
//输出: 6 
//解释: 节点 2 和节点 8 的最近公共祖先是 6。
// 
//
// 示例 2: 
//
// 输入: root = [6,2,8,0,4,7,9,null,null,3,5], p = 2, q = 4
//输出: 2
//解释: 节点 2 和节点 4 的最近公共祖先是 2, 因为根据定义最近公共祖先节点可以为节点本身。 
//
// 
//
// 说明: 
//
// 
// 所有节点的值都是唯一的。 
// p、q 为不同节点且均存在于给定的二叉搜索树中。 
// 
//
// 注意：本题与主站 235 题相同：https://leetcode-cn.com/problems/lowest-common-ancestor-of-
//a-binary-search-tree/ 
// Related Topics 树 深度优先搜索 二叉搜索树 二叉树 👍 189 👎 0


package com.company.leetcode.editor.cn;

public class No_剑指Offer68I_ErChaSouSuoShuDeZuiJinGongGongZuXianLcof {
    public static void main(String[] args) {
        Solution solution = new No_剑指Offer68I_ErChaSouSuoShuDeZuiJinGongGongZuXianLcof().new Solution();
        TreeNode n1 = new TreeNode(2);
        TreeNode n2 = new TreeNode(1);
        n1.left = n2;
        System.out.println(solution.lowestCommonAncestor(n1, new TreeNode(2), new TreeNode(1)).val);
        System.out.println();
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
        public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
            //因为题目传入的可能是真实树上的pq节点，交换节点val会改变原有的树，所以只传入两个int，约定前一个比后一个小即可，
//            if (p.val > q.val) {
//                int t = p.val;
//                p.val = q.val;
//                q.val = t;
//                //保证p小，q大，简化逻辑
//            }
            int min = Math.min(p.val, q.val);
            int max = Math.max(p.val, q.val);
            return recursion(root, min, max);
        }

        public TreeNode recursion(TreeNode root, int p, int q) {
            //结束条件:只要root等于p或者q，说明root一定是公共祖先
            if (root.val == p || root.val == q) {
                return root;
            }
            //由于一定存在祖先，所以不可能遍历到root是null
            //bst特殊，能够直接判断出pq都在左子树还是都在右子树还是在两边，不需要像普通二叉树那样后序遍历得到结果，能直接砍掉一半的树，直接O（logn）
            //所以直接前序判断即可,前面已经保证p小，q大
            if (p < root.val && q > root.val) {
                return root;
            }
            if (p < root.val && q < root.val) {
                return recursion(root.left, p, q);
            } else {
                //pq都在右子树上
                return recursion(root.right, p, q);
            }
        }
    }

    //leetcode submit region end(Prohibit modification and deletion)
    public static class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode(int x) {
            val = x;
        }
    }
}