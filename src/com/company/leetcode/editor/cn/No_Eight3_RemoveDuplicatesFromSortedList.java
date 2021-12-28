//存在一个按升序排列的链表，给你这个链表的头节点 head ，请你删除所有重复的元素，使每个元素 只出现一次 。
//
// 返回同样按升序排列的结果链表。 
//
// 
//
// 示例 1： 
//
// 
//输入：head = [1,1,2]
//输出：[1,2]
// 
//
// 示例 2： 
//
// 
//输入：head = [1,1,2,3,3]
//输出：[1,2,3]
// 
//
// 
//
// 提示： 
//
// 
// 链表中节点数目在范围 [0, 300] 内 
// -100 <= Node.val <= 100 
// 题目数据保证链表已经按升序排列 
// 
// Related Topics 链表 👍 704 👎 0


package com.company.leetcode.editor.cn;

public class No_Eight3_RemoveDuplicatesFromSortedList {
    public static void main(String[] args) {
        Solution solution = new No_Eight3_RemoveDuplicatesFromSortedList().new Solution();
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
        public ListNode deleteDuplicates(ListNode head) {
            //这题可以使用虚拟头结点来避免head == null，但是注意val不要填的和真实数据重复了，不然虚拟头结点就影响了真实结果
            ListNode newHead = new ListNode(-9999999);
            newHead.next = head;
            ListNode slow = newHead, fast = newHead;
            while (fast != null) {
                if (slow.val != fast.val) {
                    slow.next = fast;
                    slow = fast;
                }
                fast = fast.next;
            }
            //注意：如果末尾元素有重复，需要断掉slow跟后面元素的链接,比如 1 2 3 3 的情况
            slow.next = null;
            return newHead.next;
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