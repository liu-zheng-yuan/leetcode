//输入一棵二叉搜索树，将该二叉搜索树转换成一个排序的循环双向链表。要求不能创建任何新的节点，只能调整树中节点指针的指向。
//
// 
//
// 为了让您更好地理解问题，以下面的二叉搜索树为例： 
//
// 
//
// 
//
// 
//
// 我们希望将这个二叉搜索树转化为双向循环链表。链表中的每个节点都有一个前驱和后继指针。对于双向循环链表，第一个节点的前驱是最后一个节点，最后一个节点的后继是
//第一个节点。 
//
// 下图展示了上面的二叉搜索树转化成的链表。“head” 表示指向链表中有最小元素的节点。 
//
// 
//
// 
//
// 
//
// 特别地，我们希望可以就地完成转换操作。当转化完成以后，树中节点的左指针需要指向前驱，树中节点的右指针需要指向后继。还需要返回链表中的第一个节点的指针。 
//
// 
//
// 注意：本题与主站 426 题相同：https://leetcode-cn.com/problems/convert-binary-search-tree-
//to-sorted-doubly-linked-list/ 
//
// 注意：此题对比原题有改动。 
// Related Topics 栈 树 深度优先搜索 二叉搜索树 链表 二叉树 双向链表 👍 354 👎 0


package com.company.leetcode.editor.cn;

import java.util.ArrayList;
import java.util.List;

public class No_剑指_offer_36_ErChaSouSuoShuYuShuangXiangLianBiaoLcof {
    public static void main(String[] args) {
        Solution solution = new No_剑指_offer_36_ErChaSouSuoShuYuShuangXiangLianBiaoLcof().new Solution();
        Node node = new Node(1);
        Node res = solution.treeToDoublyList(node);
        System.out.println(res);
    }

    //leetcode submit region end(Prohibit modification and deletion)
    public static class Node {
        public int val;
        public Node left;
        public Node right;

        public Node() {
        }

        public Node(int _val) {
            val = _val;
        }

        public Node(int _val, Node _left, Node _right) {
            val = _val;
            left = _left;
            right = _right;
        }
    }

    //leetcode submit region begin(Prohibit modification and deletion)
/*
// Definition for a Node.
class Node {
    public int val;
    public Node left;
    public Node right;

    public Node() {}

    public Node(int _val) {
        val = _val;
    }

    public Node(int _val,Node _left,Node _right) {
        val = _val;
        left = _left;
        right = _right;
    }
};
*/
    class Solution {
        public Node treeToDoublyList(Node root) {
            if (root == null) {
                return null;
            }
            return recursion(root).get(0);
        }

        //定义：将以root为根的BST转化为双向链表，并返回0：链表起始节点，1：链表结束节点
        public List<Node> recursion(Node root) {
            //退出条件
            if (root == null) {
                List<Node> res = new ArrayList<>();
                res.add(null);
                res.add(null);
                return res;
            }

            /************************************/
            List<Node> leftNodes = recursion(root.left);//此时左子树已经转化完成
            Node leftStart = leftNodes.get(0);
            Node leftEnd = leftNodes.get(1);
            //
            List<Node> rightNodes = recursion(root.right);//此时右子树已经转化完成
            Node rightStart = rightNodes.get(0);
            Node rightEnd = rightNodes.get(1);
            //后序遍历位置
            //根节点和左边链表连起来
            if (leftStart != null && leftEnd != null) {
                leftEnd.right = root;
                root.left = leftEnd;
            } else {
                leftStart = root;
                leftEnd = root;
            }
            //根节点和右边链表连起来
            if (rightStart != null && rightEnd != null) {
                rightStart.left = root;
                root.right = rightStart;
            } else {
                rightStart = root;
                rightEnd = root;
            }
            //按题目要求，把转换完成的链表首尾相连
            leftStart.left = rightEnd;
            rightEnd.right = leftStart;
            //
            List<Node> res = new ArrayList<>();
            res.add(leftStart);
            res.add(rightEnd);
            return res;

        }
    }
}