# 错误反例1

如果链表的长度是len，下面实际上只是反转了[2,len]个节点,而第1个节点还是正常的链接到第二个节点上.

这就导致了翻转后 2-1-2-1-…… 这样无限循环.

本质上是从第n+1个节点开始翻转,会漏掉第一个

    {
        ListNode p = head;
        ListNode n = head.next;
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

# 正确的原地翻转链表

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