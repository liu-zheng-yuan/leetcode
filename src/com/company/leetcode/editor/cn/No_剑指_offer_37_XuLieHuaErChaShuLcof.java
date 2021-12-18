//请实现两个函数，分别用来序列化和反序列化二叉树。
//
// 你需要设计一个算法来实现二叉树的序列化与反序列化。这里不限定你的序列 / 反序列化算法执行逻辑，你只需要保证一个二叉树可以被序列化为一个字符串并且将这个字
//符串反序列化为原始的树结构。 
//
// 提示：输入输出格式与 LeetCode 目前使用的方式一致，详情请参阅 LeetCode 序列化二叉树的格式。你并非必须采取这种方式，你也可以采用其他的方
//法解决这个问题。 
//
// 
//
// 示例： 
//
// 
//输入：root = [1,2,3,null,null,4,5]
//输出：[1,2,3,null,null,4,5]
// 
//
// 
//
// 注意：本题与主站 297 题相同：https://leetcode-cn.com/problems/serialize-and-deserialize-
//binary-tree/ 
// Related Topics 树 深度优先搜索 广度优先搜索 设计 字符串 二叉树 👍 249 👎 0


package com.company.leetcode.editor.cn;

import java.util.LinkedList;

public class No_剑指_offer_37_XuLieHuaErChaShuLcof {
    public static void main(String[] args) {
        Codec solution = new No_剑指_offer_37_XuLieHuaErChaShuLcof().new Codec();
        TreeNode root = solution.deserialize("1,2,#,#,3,4,#,#,5,#,#");
        System.out.println();
        String serStr = solution.serialize(root);
        System.out.println(serStr);
    }
    //leetcode submit region begin(Prohibit modification and deletion)

    // Your Codec object will be instantiated and called as such:
// Codec codec = new Codec();
// codec.deserialize(codec.serialize(root));
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
    public class Codec {
        private StringBuilder sb = new StringBuilder();

        // Encodes a tree to a single string.
        public String serialize(TreeNode root) {

            serializeRecursion(root);
            return sb.toString();
        }

        private void serializeRecursion(TreeNode root) {
            //边界
            if (root == null) {
                sb.append("#");
                sb.append(",");
                return;
            }
            //前序
            sb.append(root.val);
            sb.append(",");
            serializeRecursion(root.left);
            serializeRecursion(root.right);
        }

        // Decodes your encoded data to tree.
        public TreeNode deserialize(String data) {
            if (data == null || data.isEmpty()) {
                return null;
            }
            LinkedList<String> chars = new LinkedList<>();
            for (String s : data.split(",")) {
                chars.addLast(s);
            }
            return deserializeRecursion(chars);
        }

        private TreeNode deserializeRecursion(LinkedList<String> chars) {
            if (chars.isEmpty()) {
                return null;
            }
            String ch = chars.removeFirst();//chars长度减一
            if (ch.equals("#")) {
                return null;
            }
            //前序
            //只要取出第一个节点当做根节点,构建node,剩下的交给递归
            TreeNode root = new TreeNode(Integer.parseInt(ch));
            //由于list隐式记录了当前处理的字符idx和上限len，所以这里不需要判断左子树和右子树的分界点，只需要递归就行
            root.left = deserializeRecursion(chars);//经过这个递归,左子树的节点已经被消耗干净了
            root.right = deserializeRecursion(chars);
            return root;
        }
    }
}