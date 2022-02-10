//请你设计并实现一个满足 LRU (最近最少使用) 缓存 约束的数据结构。
//
// 实现 LRUCache 类： 
//
// 
// 
// 
// LRUCache(int capacity) 以 正整数 作为容量 capacity 初始化 LRU 缓存 
// int get(int key) 如果关键字 key 存在于缓存中，则返回关键字的值，否则返回 -1 。 
// void put(int key, int value) 如果关键字 key 已经存在，则变更其数据值 value ；如果不存在，则向缓存中插入该组 
//key-value 。如果插入操作导致关键字数量超过 capacity ，则应该 逐出 最久未使用的关键字。 
// 
//
// 函数 get 和 put 必须以 O(1) 的平均时间复杂度运行。 
// 
// 
//
// 
//
// 示例： 
//
// 
//输入
//["LRUCache", "put", "put", "get", "put", "get", "put", "get", "get", "get"]
//[[2], [1, 1], [2, 2], [1], [3, 3], [2], [4, 4], [1], [3], [4]]
//输出
//[null, null, null, 1, null, -1, null, -1, 3, 4]
//
//解释
//LRUCache lRUCache = new LRUCache(2);
//lRUCache.put(1, 1); // 缓存是 {1=1}
//lRUCache.put(2, 2); // 缓存是 {1=1, 2=2}
//lRUCache.get(1);    // 返回 1
//lRUCache.put(3, 3); // 该操作会使得关键字 2 作废，缓存是 {1=1, 3=3}
//lRUCache.get(2);    // 返回 -1 (未找到)
//lRUCache.put(4, 4); // 该操作会使得关键字 1 作废，缓存是 {4=4, 3=3}
//lRUCache.get(1);    // 返回 -1 (未找到)
//lRUCache.get(3);    // 返回 3
//lRUCache.get(4);    // 返回 4
// 
//
// 
//
// 提示： 
//
// 
// 1 <= capacity <= 3000 
// 0 <= key <= 10000 
// 0 <= value <= 10⁵ 
// 最多调用 2 * 10⁵ 次 get 和 put 
// 
// Related Topics 设计 哈希表 链表 双向链表 👍 1902 👎 0


package com.company.leetcode.editor.cn;

import java.util.HashMap;
import java.util.Map;

public class No_One46_LruCache {
    public static void main(String[] args) {
        LRUCache lRUCache = new No_One46_LruCache().new LRUCache(2);
        lRUCache.get(2);
        lRUCache.put(2, 6);
        lRUCache.get(1);
        lRUCache.put(1, 5);
        lRUCache.put(1, 2);
        lRUCache.get(1);
        lRUCache.get(2);
    }

    //leetcode submit region begin(Prohibit modification and deletion)
    class LRUCache {
        private int capacity;
        private Map<Integer, Node> map;
        private Node head, tail;

        public LRUCache(int capacity) {
            this.capacity = capacity;
            map = new HashMap<>();
            head = new Node(-1, -1);
            tail = new Node(-1, -1);
            head.next = tail;//初始化时头尾也要连起来
            tail.prev = head;
        }

        public int get(int key) {
            if (!map.containsKey(key)) {
                return -1;
            }
            //1.找到Node的指针
            Node cur = map.get(key);
            //2.从双链表里取出来：前后节点连起来
            cur.prev.next = cur.next;
            cur.next.prev = cur.prev;
            //3.放到最前面
            cur.next = head.next;
            cur.prev = head;
            //前后要接上
            head.next = cur;
            cur.next.prev = cur;
            return cur.value;
        }

        public void put(int key, int value) {
            //1.如果key已经存在，则直接修改对应Node，并移动到最前面
            if (map.containsKey(key)) {//这种情况别忘了
                Node cur = map.get(key);
                cur.value = value;
                get(key);//就算是移动到最前面
                return;
            }
            //2.如果超出范围要删除
            if (map.size() == capacity) {
                //删除最少使用的
                Node delete = tail.prev;
                delete.prev.next = tail;
                tail.prev = delete.prev;
                map.remove(delete.key);
            }
            //3.新加入一个节点,加入到最前面
            Node cur = new Node(key, value);
            map.put(key, cur);
            //
            cur.prev = head;
            cur.next = head.next;
            head.next = cur;
            cur.next.prev = cur;
        }
    }

    class Node {
        int key;
        int value;
        //双向链表
        Node next;
        Node prev;

        Node(int key, int value) {
            this.key = key;
            this.value = value;
        }
    }

/**
 * Your LRUCache object will be instantiated and called as such:
 * LRUCache obj = new LRUCache(capacity);
 * int param_1 = obj.get(key);
 * obj.put(key,value);
 */
//leetcode submit region end(Prohibit modification and deletion)

}