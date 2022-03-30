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
// Related Topics 栈 树 深度优先搜索 二叉搜索树 链表 二叉树 双向链表 👍 408 👎 0


package com.company.leetcode.editor.cn;

public class No_剑指Offer36_ErChaSouSuoShuYuShuangXiangLianBiaoLcof {
    public static void main(String[] args) {
        Solution solution = new No_剑指Offer36_ErChaSouSuoShuYuShuangXiangLianBiaoLcof().new Solution();
        Node n1 = new Node(1);
        Node n2 = new Node(2);
        Node n3 = new Node(3);
        Node n4 = new Node(4);
        Node n5 = new Node(5);
        n2.left = n1;
        n2.right = n3;
        n4.left = n2;
        n4.right = n5;
        System.out.println(solution.treeToDoublyList(n4));

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
        //非递归遍历的模板，但本题不适用
//        public Node treeToDoublyList(Node root) {
//            Node head = null;
//            Node tail = null;
//            //
//            Stack<Node> stack = new Stack<>();
//            Node visited = null;//上次遍历完成的根节点
//            pushLeft(root, stack);
//            while (!stack.isEmpty()) {
//                Node cur = stack.peek();
//                //左子树遍历完了
//                /**
//                 * 这里有个很重要的点，左子树遍历完了，意思是左边要么是null要么被标记了，但是前提是右边没有被标记。
//                 * 因为：第一次遍历到cur节点，会正常执行中序代码，然后遍历右子树。等右子树遍历完成后，会再次遍历到cur节点。
//                 * 如果没有&& visited != cur.right 条件，中序代码将会再次执行。
//                 */
//                if ((cur.left == null || cur.left == visited) && visited != cur.right) {
//                    //中序遍历到cur的代码处理
//                    pushLeft(cur.right, stack);
//                }
//
//                //右子树遍历完了
//                if (cur.right == null || cur.right == visited) {
//                    //后序遍历到cur的代码处理
//                    //当前节点左右中都遍历完了，该出栈了。
//                    visited = cur;
//                    stack.pop();
//                }
//            }
//        }
//
//        public void pushLeft(Node root, Stack<Node> stack) {
//            Node p = root;
//            while (p != null) {
//                stack.push(p);
//                p = p.left;
//            }
//        }

        public Node treeToDoublyList(Node root) {
            //连接成链表返回头结点
            Node newHead = recusive(root);
            //找到最后一个节点，把头尾连上
            Node tail = newHead;
            while (tail != null && tail.right != null) {
                tail = tail.right;
            }
            if (tail != null) {
                newHead.left = tail;
                tail.right = newHead;
            }
            return newHead;
        }

        //将root转化为双向链表，返回head节点
        public Node recusive(Node root) {
            if (root == null) {
                return null;
            }
            if (root.left == null && root.right == null) {
                return root;
            }
            //
            Node leftHead = recusive(root.left);
            Node rightHead = recusive(root.right);
            //
            Node p = leftHead;
            while (p != null && p.right != null) {
                p = p.right;
            }
            //即leftHead不等于null，返回链表的头节点应该是leftHead
            if (p != null) {
                p.right = root;
                root.left = p;
                root.right = rightHead;
                if (rightHead != null) {
                    rightHead.left = root;
                }
                return leftHead;
            } else {
                //leftHead=null,左子树没有东西，返回的头结点只能是root
                root.left = null;
                root.right = rightHead;
                if (rightHead != null) {
                    rightHead.left = root;
                }
                return root;
            }
        }
    }

    //leetcode submit region end(Prohibit modification and deletion)
    static class Node {
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

    ;
}