//输入一个链表的头节点，从尾到头反过来返回每个节点的值（用数组返回）。
//
// 
//
// 示例 1： 
//
// 输入：head = [1,3,2]
//输出：[2,3,1] 
//
// 
//
// 限制： 
//
// 0 <= 链表长度 <= 10000 
// Related Topics 栈 递归 链表 双指针 👍 208 👎 0


package com.company.leetcode.editor.cn;

import java.util.ArrayList;
import java.util.List;

public class No_剑指_offer_06_CongWeiDaoTouDaYinLianBiaoLcof {
    public static void main(String[] args) {
        Solution solution = new No_剑指_offer_06_CongWeiDaoTouDaYinLianBiaoLcof().new Solution();
        ListNode n1 = new ListNode(1);
        ListNode n2 = new ListNode(3);
        ListNode n3 = new ListNode(2);

        n1.next = n2;
        n2.next = n3;
        System.out.println(solution.reversePrint(n1));
    }
    //leetcode submit region begin(Prohibit modification and deletion)

    public static class ListNode {
        int val;
        ListNode next;

        ListNode(int x) {
            val = x;
        }
    }

    /**
     * Definition for singly-linked list.
     * public class ListNode {
     * int val;
     * ListNode next;
     * ListNode(int x) { val = x; }
     * }
     */
    class Solution {
        public int[] reversePrint(ListNode head) {
            if (head == null) {
                return new int[]{};
            }
            ListNode p = head;
            ListNode n = head.next;
            //不漏掉第一个节点,防止成环
            p.next = null;
            //
            while (n != null) {
                ListNode tmp = p;
                p = n;
                n = n.next;
                p.next = tmp;
            }
            List<Integer> res = new ArrayList<>();
            while (p != null) {
                res.add(p.val);
                p = p.next;
            }
            return res.stream().mapToInt(Integer::valueOf).toArray();
        }

    }
//leetcode submit region end(Prohibit modification and deletion)

}