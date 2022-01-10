//在上次打劫完一条街道之后和一圈房屋后，小偷又发现了一个新的可行窃的地区。这个地区只有一个入口，我们称之为“根”。 除了“根”之外，每栋房子有且只有一个“父“
//房子与之相连。一番侦察之后，聪明的小偷意识到“这个地方的所有房屋的排列类似于一棵二叉树”。 如果两个直接相连的房子在同一天晚上被打劫，房屋将自动报警。 
//
// 计算在不触动警报的情况下，小偷一晚能够盗取的最高金额。 
//
// 示例 1: 
//
// 输入: [3,2,3,null,3,null,1]
//
//     3
//    / \
//   2   3
//    \   \ 
//     3   1
//
//输出: 7 
//解释: 小偷一晚能够盗取的最高金额 = 3 + 3 + 1 = 7. 
//
// 示例 2: 
//
// 输入: [3,4,5,1,3,null,1]
//
//     3
//    / \
//   4   5
//  / \   \ 
// 1   3   1
//
//输出: 9
//解释: 小偷一晚能够盗取的最高金额 = 4 + 5 = 9.
// 
// Related Topics 树 深度优先搜索 动态规划 二叉树 👍 1096 👎 0


package com.company.leetcode.editor.cn;

import java.util.HashMap;
import java.util.Map;

public class No_Three37_HouseRobberIii {
    public static void main(String[] args) {
        Solution solution = new No_Three37_HouseRobberIii().new Solution();
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
        //遍历树：使用递归而不是dp数组的递推
        Map<TreeNode, Integer> memory = new HashMap<>();

        public int rob(TreeNode root) {
            return dp(root);
        }

        //定义：返回从root开始能获得的最大值
        public int dp(TreeNode root) {
            //
            if (root == null) {
                return 0;
            }
            if (memory.containsKey(root)) {
                return memory.get(root);
            }
            //当前root节点能做的选择
            //1.选择盗窃root，就不能碰root.left和root.right，应该转移到子树的子树的状态
            //注意！左右子树能同时都盗窃，没有说只能选一个！所以要取和
            int robCurrent = root.val
                    + (root.left == null ? 0 : dp(root.left.left) + dp(root.left.right))
                    + (root.right == null ? 0 : dp(root.right.left) + dp(root.right.right));
            int notRobCurrent = dp(root.left) + dp(root.right);
            memory.put(root, Math.max(robCurrent, notRobCurrent));
            return memory.get(root);
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