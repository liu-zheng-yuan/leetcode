//给你一个链表的头节点 head ，旋转链表，将链表每个节点向右移动 k 个位置。
//
// 
//
// 示例 1： 
//
// 
//输入：head = [1,2,3,4,5], k = 2
//输出：[4,5,1,2,3]
// 
//
// 示例 2： 
//
// 
//输入：head = [0,1,2], k = 4
//输出：[2,0,1]
// 
//
// 
//
// 提示： 
//
// 
// 链表中节点的数目在范围 [0, 500] 内 
// -100 <= Node.val <= 100 
// 0 <= k <= 2 * 10⁹ 
// 
// Related Topics 链表 双指针 👍 726 👎 0


package com.company.leetcode.editor.cn;

public class No_Six1_RotateList {
    public static void main(String[] args) {
        Solution solution = new No_Six1_RotateList().new Solution();
    }
    //leetcode submit region begin(Prohibit modification and deletion)

    /**
     * Definition for singly-linked list.
     * public class ListNode {
     * int val;
     * ListNode next;
     * ListNode() {}
     * ListNode(int val) { this.val = val; }
     * ListNode(int val, ListNode next) { this.val = val; this.next = next; }
     * }
     */
    class Solution {
        public ListNode rotateRight(ListNode head, int k) {
            //边界条件
            if (k == 0 || head == null) {
                return head;
            }
            int n = 0;//统计长度
            ListNode p = head;
            while (p != null) {
                p = p.next;
                n++;
            }
            k = k % n;//超出n的k没有意义
            //这里k的意义就是把从倒数第k个元素到最后[n-k,n)这么多元素，移动到最前面
            // 所以要找到倒数第K+1个元素，才能挪动
            ListNode slow = head;
            ListNode fast = head;
            for (int i = 0; i < k + 1; i++) {
                fast = fast.next;
            }
            while (fast != null) {
                fast = fast.next;
                slow = slow.next;
            }
            //newHead有可能是null，case [1] k =1
            ListNode newHead = slow.next;//从这个元素开始截断，会被移动到最前面
            ListNode oldTail = slow.next;//遍历到原末尾
            while (oldTail != null && oldTail.next != null) {
                oldTail = oldTail.next;
            }
            //拼装最后链表：如果slow节点之后没有需要挪动的节点，即不需要旋转，则直接返回原头head
            if (slow.next == null) {
                return head;
            } else {
                slow.next = null;
                oldTail.next = head;
                return newHead;
            }
        }
    }

    //leetcode submit region end(Prohibit modification and deletion)
    public class ListNode {
        int val;
        ListNode next;

        ListNode() {
        }

        ListNode(int val) {
            this.val = val;
        }

        ListNode(int val, ListNode next) {
            this.val = val;
            this.next = next;
        }
    }
}