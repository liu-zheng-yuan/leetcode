//给你一个单链表的头节点 head ，请你判断该链表是否为回文链表。如果是，返回 true ；否则，返回 false 。
//
// 
//
// 示例 1： 
//
// 
//输入：head = [1,2,2,1]
//输出：true
// 
//
// 示例 2： 
//
// 
//输入：head = [1,2]
//输出：false
// 
//
// 
//
// 提示： 
//
// 
// 链表中节点数目在范围[1, 10⁵] 内 
// 0 <= Node.val <= 9 
// 
//
// 
//
// 进阶：你能否用 O(n) 时间复杂度和 O(1) 空间复杂度解决此题？ 
// Related Topics 栈 递归 链表 双指针 👍 1269 👎 0


package com.company.leetcode.editor.cn;

public class No_Two34_PalindromeLinkedList {
    public static void main(String[] args) {
        Solution solution = new No_Two34_PalindromeLinkedList().new Solution();
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
        public boolean isPalindrome(ListNode head) {
            if (head == null) {
                return true;
            }
            //先找到中点
            ListNode slow = head, fast = head;
            while (fast != null && fast.next != null) {
                fast = fast.next.next;
                slow = slow.next;
            }
            //对于奇数个，找到的是中间节点；对于偶数个找到的是中间偏后一位的节点
            //如果是奇数个，需要从中间节点的后一位开始翻转链表，否则，前半部分的链表长度和后半部分就对不齐了；偶数个没有这个情况
            //上面两种结束条件，奇数个对应的结束条件是 ： fast走到最后一个节点，fast.next = null
            if (fast != null && fast.next == null) {
                slow = slow.next;
            }
            //翻转后半部分链表，
            ListNode newHead = reverse(slow);
            ListNode p1 = head, p2 = newHead;
            //因为翻转后：后半部分的链表结尾是null，前半部分的链表的结尾是“原来的slow”（未处理奇数情况slow= slow.next之前），且前后两部分长度相同，所以只需要判断p2走到空，p1肯定也是走到原来的slow
            while (p2 != null) {
                if (p1.val != p2.val) {
                    return false;
                }
                p1 = p1.next;
                p2 = p2.next;
            }
            return true;
        }

        private ListNode reverse(ListNode slow) {
            ListNode pre = null, cur = slow, next = slow;
            while (cur != null) {
                next = cur.next;
                cur.next = pre;
                pre = cur;
                cur = next;
            }
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