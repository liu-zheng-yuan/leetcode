//给定一个已排序的链表的头 head ， 删除原始链表中所有重复数字的节点，只留下不同的数字 。返回 已排序的链表 。
//
// 
//
// 示例 1： 
//
// 
//输入：head = [1,2,3,3,4,4,5]
//输出：[1,2,5]
// 
//
// 示例 2： 
//
// 
//输入：head = [1,1,1,2,3]
//输出：[2,3]
// 
//
// 
//
// 提示： 
//
// 
// 链表中节点数目在范围 [0, 300] 内 
// -100 <= Node.val <= 100 
// 题目数据保证链表已经按升序 排列 
// 
// Related Topics 链表 双指针 👍 815 👎 0


package com.company.leetcode.editor.cn;

public class No_Eight2_RemoveDuplicatesFromSortedListIi {
    public static void main(String[] args) {
        Solution solution = new No_Eight2_RemoveDuplicatesFromSortedListIi().new Solution();
        ListNode n1 = new ListNode(1);
        ListNode n2 = new ListNode(2);
        ListNode n3 = new ListNode(3);
        ListNode n4 = new ListNode(3);
        ListNode n5 = new ListNode(4);
        ListNode n6 = new ListNode(4);
        ListNode n7 = new ListNode(5);
        n1.next = n2;
        n2.next = n3;
        n3.next = n4;
        n4.next = n5;
        n5.next = n6;
        n6.next = n7;
        System.out.println(solution.deleteDuplicates(n1));
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
        public ListNode deleteDuplicates1(ListNode head) {
            ListNode newHead = new ListNode(-99999);
            newHead.next = head;
            ListNode slow = newHead, fast = newHead;
            while (fast != null) {
                //每次当看似满足条件时，都需要验证：即将连入的fast节点和后面节点是否重复，如果重复，则fast继续往前走，知道【fast和fast后面节点不重复】未知
                while (fast.next != null && fast.next.val == fast.val) {
                    fast = fast.next;//找到下一个非重复出现的元素，如 1,1,2  需要跳到2.如 1,2,3 则停在1
                }
                if (slow.next == fast) {
                    slow = slow.next;
                } else {
                    slow.next = fast.next;
                }
                fast = fast.next;
            }
            slow.next = null;
            return newHead.next;
        }

        public ListNode deleteDuplicates(ListNode head) {
            ListNode newHead = new ListNode(-9999);
            ListNode slow = newHead;//指向已经形成的链表的最后一个
            ListNode fast = newHead;//指向1.没有重复的节点 2.重复的节点的最后一个
            while (fast != null) {
                //此时fast处于”后续可能重复的节点的第一个“，需要移动到”重复节点的最后一个“
                while (fast.next != null && fast.val == fast.next.val) {
                    fast = fast.next;
                }
                //如果slow到fast之间没有重复节点
                if (slow.next == fast) {
                    slow = slow.next;
                } else {
                    //如果有重复节点了，就要删除之间的重复节点:即slow.next指向下一个“可能”不重复的节点，就等于删除中间的重复节点
                    slow.next = fast.next;//因为这里的fast指向的是重复节点的最后一个，所以slow.next要指向fast的下一个（即后面的“可能不重复”的节点）
                    //这里不能直接slow = slow.next 因为这里的fast.next可能也是重复节点（22 33 这种情况），需要等下一轮判断一下后面是否有重复
                }
                //fast从当前“重复节点的最后一个”移动到”后序可能重复的节点的第一个“
                fast = fast.next;
            }
            slow.next = null;
            return newHead.next;
        }
    }

    //leetcode submit region end(Prohibit modification and deletion)
    public static class ListNode {
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