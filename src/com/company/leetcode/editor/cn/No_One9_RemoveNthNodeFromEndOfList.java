//给你一个链表，删除链表的倒数第 n 个结点，并且返回链表的头结点。
//
// 
//
// 示例 1： 
//
// 
//输入：head = [1,2,3,4,5], n = 2
//输出：[1,2,3,5]
// 
//
// 示例 2： 
//
// 
//输入：head = [1], n = 1
//输出：[]
// 
//
// 示例 3： 
//
// 
//输入：head = [1,2], n = 1
//输出：[1]
// 
//
// 
//
// 提示： 
//
// 
// 链表中结点的数目为 sz 
// 1 <= sz <= 30 
// 0 <= Node.val <= 100 
// 1 <= n <= sz 
// 
//
// 
//
// 进阶：你能尝试使用一趟扫描实现吗？ 
// Related Topics 链表 双指针 👍 1710 👎 0


package com.company.leetcode.editor.cn;

public class No_One9_RemoveNthNodeFromEndOfList {
    public static void main(String[] args) {
        Solution solution = new No_One9_RemoveNthNodeFromEndOfList().new Solution();
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
        public ListNode removeNthFromEnd(ListNode head, int n) {
            /**
             * 删除倒数第n个 ==》 找到倒数第n+1个节点
             * 倒数第k个节点是正数第几个呢？ ==》 从1开始，长度为len的数组，最后一个元素的index是len。倒数第一个是 len，倒数第二个是len-1. 所以倒数第k个是第 len+1-k个元素
             * 从序号1走到序号len+1-k，要走多少步呢？ ==》 [1,len+1-k)中间的间隔是 (len+1-k) - (1)个 ==》一共要走len-k步
             * 怎么控制从1开始走len-k步呢？ ==》fast先走k步，然后slow一起走，等fast走到null时，一共走了len步，所以slow走了len-k步。（因为原数组长len，[1,len]中间有个len-1个间隔，走len个间隔正好走到null）
             * 把上文的k 用n+1代替。
             */
            //bad case 不兼容要删除头结点的情况，所以要搞个虚拟头结点
//            ListNode fast = head, slow = head;
//            for (int i = 0; i < n + 1; i++) {
//                fast = fast.next;
//            }
//            while (fast != null) {
//                fast = fast.next;
//                slow = slow.next;
//            }
//            //这里的slow 指向倒数第n+1个节点
//            slow.next = slow.next.next;
//            return head;
            //搞个虚拟头结点 兼容删除头结点的情况
            ListNode h = new ListNode(-1);
            h.next = head;
            //现在链表长度增加了1，但是以下逻辑不变。因为fast和slow都多走了1，等于没有变
            ListNode fast = h, slow = h;
            for (int i = 0; i < n + 1; i++) {
                fast = fast.next;
            }
            while (fast != null) {
                fast = fast.next;
                slow = slow.next;
            }
            //这里的slow 指向倒数第n+1个节点
            slow.next = slow.next.next;
            return h.next;
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