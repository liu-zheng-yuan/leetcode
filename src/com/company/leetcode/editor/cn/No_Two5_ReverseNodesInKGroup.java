//给你一个链表，每 k 个节点一组进行翻转，请你返回翻转后的链表。
//
// k 是一个正整数，它的值小于或等于链表的长度。 
//
// 如果节点总数不是 k 的整数倍，那么请将最后剩余的节点保持原有顺序。 
//
// 进阶： 
//
// 
// 你可以设计一个只使用常数额外空间的算法来解决此问题吗？ 
// 你不能只是单纯的改变节点内部的值，而是需要实际进行节点交换。 
// 
//
// 
//
// 示例 1： 
//
// 
//输入：head = [1,2,3,4,5], k = 2
//输出：[2,1,4,3,5]
// 
//
// 示例 2： 
//
// 
//输入：head = [1,2,3,4,5], k = 3
//输出：[3,2,1,4,5]
// 
//
// 示例 3： 
//
// 
//输入：head = [1,2,3,4,5], k = 1
//输出：[1,2,3,4,5]
// 
//
// 示例 4： 
//
// 
//输入：head = [1], k = 1
//输出：[1]
// 
//
// 
// 
//
// 提示： 
//
// 
// 列表中节点的数量在范围 sz 内 
// 1 <= sz <= 5000 
// 0 <= Node.val <= 1000 
// 1 <= k <= sz 
// 
// Related Topics 递归 链表 👍 1474 👎 0


package com.company.leetcode.editor.cn;

public class No_Two5_ReverseNodesInKGroup {
    public static void main(String[] args) {
        Solution solution = new No_Two5_ReverseNodesInKGroup().new Solution();
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
        public ListNode reverseKGroup(ListNode head, int k) {
            if (head == null) {
                return null;
            }
            //先找到第k节点（从1开始数），也就是第一组K个节点的下一个节点也就是[head,kp)
            ListNode kP = head;
            for (int i = 0; i < k; i++) {
                if (kP != null) {
                    kP = kP.next;
                } else {
                    return head;//如果剩下的节点数量不够k个，则不用旋转，直接返回当前head
                }
            }
            //只要把当前这一组里的k个节点[head,kp)全翻转了就行
            ListNode pre = null, cur = head, next = head;
            while (cur != kP) {
                next = cur.next;
                cur.next = pre;
                pre = cur;
                cur = next;
            }
            //出来之后cur = kp，pre=这一组里最后一个节点，也就是翻转完之后的第一个节点
            //head是翻转完之后本轮的最后一个，要和递归结果连在一起
            head.next = reverseKGroup(kP, k);

            return pre;
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