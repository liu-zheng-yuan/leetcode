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
        ListNode h1 = new ListNode(1);
        ListNode h2 = new ListNode(2);
        ListNode h3 = new ListNode(3);
        ListNode h4 = new ListNode(4);
        ListNode h5 = new ListNode(5);
        h1.next = h2;
        h2.next = h3;
        h3.next = h4;
        h4.next = h5;
        System.out.println(solution.removeNthFromEnd(h1, 2));

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
        public ListNode removeNthFromEnd1(ListNode head, int n) {
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

        /**
         * 1 对于[a,b):区间内元素的个数为b-a个，元素之间的间隔的个数（即链表需要next的数量）为 b-a个，意味着从a开始next b-a 下，指针能到达b。
         * 2 对于[a,b]：区间内元素个数为 b-a + 1 （如上再加上b本身），元素之间的间隔的个数（即链表需要next的数量）为 b-a个，意味着从a开始next b-a 下，指针能到达b。
         * 3 对于长度len：[a,a+len)区间中元素的个数为len个，间隔数为len个，意味着从a开始next len下能到a+len下标的元素
         * 4 对于长度len：[a,a+len]区间中元素的个数为len+1个，间隔数为len个，意味着从a开始next len下能到a+len下标的元素
         * <p>
         * 5 倒数第K个节点：即正数第 n-k个节点，下标从0开始。（带入个例子，倒数第1个节点，就是最后一个节点，下边肯定是n-1）
         * <p>
         * 6.推论：如何找到倒数第K个节点。根据（1,2,3）因为[0,n)一共有n个间隙，走完n个间隙刚好就是 下标n也就是null。先走k步之后，剩下n-k步。慢指针走长度为n-k，根据（3），所在下标是n-k。根据（5）正好是倒数第k个节点。
         */
        public ListNode removeNthFromEnd(ListNode head, int n) {
            ListNode newHead = new ListNode(-1);
            newHead.next = head;
            //正常应该是找倒数第k+1个节点，然后就能删除第k个节点。即找到正数第 n-（k+1）个节点。
            //(这个思路是错的，fast需要多走一步，slow指针也需要多走一步，抵消了)由于增加虚拟头结点，从虚拟头结点起跳需要多一跳，所以就是找到正数第n-（K+1）+1个节点，就是正数第n-k个节点。
            //** 增加 虚拟头节点不会影响fast和slow 的计数，因为fast和slow都需要多走一步，而找到倒数第k个看的是两个指针的相对距离K
            //所以需要提前走 K 步就能找到倒数第K个节点（本题是提前走K+1步找到倒数第K+1个节点）
            ListNode fast = newHead;
            ListNode slow = newHead;
            for (int i = 0; i < n + 1; i++) {
                fast = fast.next;
            }
            //
            while (fast != null) {
                fast = fast.next;
                slow = slow.next;
            }
            slow.next = slow.next.next;
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