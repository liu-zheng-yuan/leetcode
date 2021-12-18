//请实现 copyRandomList 函数，复制一个复杂链表。在复杂链表中，每个节点除了有一个 next 指针指向下一个节点，还有一个 random 指针指
//向链表中的任意节点或者 null。 
//
// 
//
// 示例 1： 
//
// 
//
// 输入：head = [[7,null],[13,0],[11,4],[10,2],[1,0]]
//输出：[[7,null],[13,0],[11,4],[10,2],[1,0]]
// 
//
// 示例 2： 
//
// 
//
// 输入：head = [[1,1],[2,1]]
//输出：[[1,1],[2,1]]
// 
//
// 示例 3： 
//
// 
//
// 输入：head = [[3,null],[3,0],[3,null]]
//输出：[[3,null],[3,0],[3,null]]
// 
//
// 示例 4： 
//
// 输入：head = []
//输出：[]
//解释：给定的链表为空（空指针），因此返回 null。
// 
//
// 
//
// 提示： 
//
// 
// -10000 <= Node.val <= 10000 
// Node.random 为空（null）或指向链表中的节点。 
// 节点数目不超过 1000 。 
// 
//
// 
//
// 注意：本题与主站 138 题相同：https://leetcode-cn.com/problems/copy-list-with-random-
//pointer/ 
//
// 
// Related Topics 哈希表 链表 👍 353 👎 0


package com.company.leetcode.editor.cn;

import java.util.HashMap;
import java.util.Map;

public class No_剑指_offer_35_FuZaLianBiaoDeFuZhiLcof {
    public static void main(String[] args) {
        Solution solution = new No_剑指_offer_35_FuZaLianBiaoDeFuZhiLcof().new Solution();
        Node n1 = new Node(7);
        Node n2 = new Node(13);
        Node n3 = new Node(11);
        Node n4 = new Node(10);
        Node n5 = new Node(1);
        n1.next = n2;
        n2.next = n3;
        n3.next = n4;
        n4.next = n5;
        n2.random = n1;
        n3.random = n5;
        n4.random = n3;
        n5.random = n1;
        System.out.println(solution.copyRandomList(n1));
    }

    //leetcode submit region begin(Prohibit modification and deletion)
/*
// Definition for a Node.
class Node {
    int val;
    Node next;
    Node random;

    public Node(int val) {
        this.val = val;
        this.next = null;
        this.random = null;
    }
}
*/
    class Solution {
        public Node copyRandomList(Node head) {
            if (head == null) {
                return null;
            }
            Map<Integer, Integer> oldNode2Index = new HashMap<>();
            Map<Integer, Node> index2newNode = new HashMap<>();
            //遍历一遍老链表，储存每个节点Hash对应的下标索引 - oldNode2Index 反查
            Node p = head;
            Integer idx = 0;
            while (p != null) {
                Integer oldNodeHashCode = p.hashCode();
                oldNode2Index.put(oldNodeHashCode, idx);
                idx++;
                p = p.next;
            }
            //复制新链表，创建新链表下标索引和节点地址的关系 index2newNode，此时random还没有被赋值
            Node newHead = new Node(-1);
            p = head;
            Node cur = newHead;
            idx = 0;
            while (p != null) {
                cur.next = new Node(p.val);
                cur = cur.next;
                index2newNode.put(idx, cur);//创建索引
                idx++;
                p = p.next;
            }
            //根据反查表和正查表，填充random
            Node p1 = head, p2 = newHead.next;
            while (p1 != null) {
                if (p1.random == null) {
                    p2.random = null;
                } else {
                    Integer newIdx = oldNode2Index.get(p1.random.hashCode());
                    Node newRandom = index2newNode.get(newIdx);
                    p2.random = newRandom;
                }
                p1 = p1.next;
                p2 = p2.next;
            }
            return newHead.next;
        }
    }
//leetcode submit region end(Prohibit modification and deletion)

}

class Node {
    int val;
    Node next;
    Node random;

    public Node(int val) {
        this.val = val;
        this.next = null;
        this.random = null;
    }

    public Node(int val, Node next, Node random) {
        this.val = val;
        this.next = next;
        this.random = random;
    }
}