//请定义一个队列并实现函数 max_value 得到队列里的最大值，要求函数max_value、push_back 和 pop_front 的均摊时间复杂度都
//是O(1)。 
//
// 若队列为空，pop_front 和 max_value 需要返回 -1 
//
// 示例 1： 
//
// 输入: 
//["MaxQueue","push_back","push_back","max_value","pop_front","max_value"]
//[[],[1],[2],[],[],[]]
//输出: [null,null,null,2,1,2]
// 
//
// 示例 2： 
//
// 输入: 
//["MaxQueue","pop_front","max_value"]
//[[],[],[]]
//输出: [null,-1,-1]
// 
//
// 
//
// 限制： 
//
// 
// 1 <= push_back,pop_front,max_value的总操作数 <= 10000 
// 1 <= value <= 10^5 
// 
// Related Topics 设计 队列 单调队列 👍 341 👎 0


package com.company.leetcode.editor.cn;

import java.util.Deque;
import java.util.LinkedList;
import java.util.Queue;

public class No_剑指Offer59II_DuiLieDeZuiDaZhiLcof {
    public static void main(String[] args) {
    }

    //leetcode submit region begin(Prohibit modification and deletion)
    class MaxQueue {
        private Queue<Integer> queue;
        //要用双端队列
        private Deque<Integer> maxQueue;

        public MaxQueue() {
            queue = new LinkedList<>();
            maxQueue = new LinkedList<>();

        }

        public int max_value() {
            if (!maxQueue.isEmpty()) {
                return maxQueue.peek();
            }
            return -1;

        }

        public void push_back(int value) {
            queue.add(value);
            //
            while (!maxQueue.isEmpty() && maxQueue.peekLast() < value) {
                maxQueue.pollLast();//从后面加的时候如果发现队列元素比当前小就pop
            }
            maxQueue.addLast(value);
            //保证队列里从头到尾是单调减的。
        }

        public int pop_front() {
            if (!queue.isEmpty()) {
                int needPop = queue.poll();
                if (maxQueue.peekFirst() == needPop) {
                    maxQueue.pollFirst();
                }
                return needPop;
            }
            return -1;
        }
    }

/**
 * Your MaxQueue object will be instantiated and called as such:
 * MaxQueue obj = new MaxQueue();
 * int param_1 = obj.max_value();
 * obj.push_back(value);
 * int param_3 = obj.pop_front();
 */
//leetcode submit region end(Prohibit modification and deletion)

}