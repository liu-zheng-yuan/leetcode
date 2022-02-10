//给你单链表的头指针 head 和两个整数 left 和 right ，其中 left <= right 。请你反转从位置 left 到位置 right 的链
//表节点，返回 反转后的链表 。
// 
//
// 示例 1： 
//
// 
//输入：head = [1,2,3,4,5], left = 2, right = 4
//输出：[1,4,3,2,5]
// 
//
// 示例 2： 
//
// 
//输入：head = [5], left = 1, right = 1
//输出：[5]
// 
//
// 
//
// 提示： 
//
// 
// 链表中节点数目为 n 
// 1 <= n <= 500 
// -500 <= Node.val <= 500 
// 1 <= left <= right <= n 
// 
//
// 
//
// 进阶： 你可以使用一趟扫描完成反转吗？ 
// Related Topics 链表 👍 1150 👎 0


package com.company.leetcode.editor.cn;

public class No_Nine2_ReverseLinkedListIi {
    public static void main(String[] args) {
        Solution solution = new No_Nine2_ReverseLinkedListIi().new Solution();
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
        public ListNode reverseBetween(ListNode head, int left, int right) {
            //从1开始数，没有第0个的说法
            if (left == 1) {
                return reverseN(head, right);
            }
            //reverseBetween(head.next）返回的是head.next之后的链表翻转后的头节点，还需要把当前头节点接上去
            head.next = reverseBetween(head.next, left - 1, right - 1);
            return head;
        }


        //翻转前n个节点
        ListNode successor = null;

        public ListNode reverseN(ListNode head, int n) {
            //边界情况是1，而不是0，没有前0个的说法
            if (n == 1) {
                successor = head.next;
                return head;
            }
            //翻转当前节点head
            ListNode next = reverseN(head.next, n - 1);
            head.next.next = head;
            head.next = successor;//从最后边界调节返回时，当前head的next就指向了n+1个节点，也就是不会变的后继节点，递归过程中的每个head都会指向后继
            return next;
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