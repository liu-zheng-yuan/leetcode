东哥公众号上单链表技巧之一：https://mp.weixin.qq.com/s/dVqXEMKZ6_tuB7J-leLmtg
重点是如何让两个指针同时走到同一个节点:

* 由于长度上A+B = B+A
* 所以两个指针把两个链表都遍历一遍，如果有交点一定能走到，如果没有，null就是相同交点