package com.company.leetcode.editor.cn.solution;

public class 下标与间隙 {
    /**
     * 1 对于[a,b):区间内元素的个数为b-a个，元素之间的间隔的个数（即链表需要next的数量）为 b-a个，意味着从a开始next b-a 下，指针能到达b。
     * 2 对于[a,b]：区间内元素个数为 b-a + 1 （如上再加上b本身），元素之间的间隔的个数（即链表需要next的数量）为 b-a个，意味着从a开始next b-a 下，指针能到达b。
     * 3 对于长度len：[a,a+len)区间中元素的个数为len个，间隔数为len个，意味着从a开始next len下能到a+len下标的元素
     * 4 对于长度len：[a,a+len]区间中元素的个数为len+1个，间隔数为len个，意味着从a开始next len下能到a+len下标的元素
     * 4 对于长度len：[a+1,a+len]区间中元素的个数为len个，间隔数为len个，意味着从a开始next len下能到a+len下标的元素
     *
     * 5 倒数第K个节点：即正数第 n-k个节点，下标从0开始。（带入个例子，倒数第1个节点，就是最后一个节点，下边肯定是n-1）
     *
     * 6.推论：如何找到倒数第K个节点。根据（1,2,3）因为[0,n)一共有n个间隙，走完n个间隙刚好就是 下标n也就是null。先走k步之后，剩下n-k步。慢指针走长度为n-k，根据（3），所在下标是n-k。根据（5）正好是倒数第k个节点。
     */
}
