//如何得到一个数据流中的中位数？如果从数据流中读出奇数个数值，那么中位数就是所有数值排序之后位于中间的数值。如果从数据流中读出偶数个数值，那么中位数就是所有数
//值排序之后中间两个数的平均值。 
//
// 例如， 
//
// [2,3,4] 的中位数是 3 
//
// [2,3] 的中位数是 (2 + 3) / 2 = 2.5 
//
// 设计一个支持以下两种操作的数据结构： 
//
// 
// void addNum(int num) - 从数据流中添加一个整数到数据结构中。 
// double findMedian() - 返回目前所有元素的中位数。 
// 
//
// 示例 1： 
//
// 输入：
//["MedianFinder","addNum","addNum","findMedian","addNum","findMedian"]
//[[],[1],[2],[],[3],[]]
//输出：[null,null,null,1.50000,null,2.00000]
// 
//
// 示例 2： 
//
// 输入：
//["MedianFinder","addNum","findMedian","addNum","findMedian"]
//[[],[2],[],[3],[]]
//输出：[null,null,2.00000,null,2.50000] 
//
// 
//
// 限制： 
//
// 
// 最多会对 addNum、findMedian 进行 50000 次调用。 
// 
//
// 注意：本题与主站 295 题相同：https://leetcode-cn.com/problems/find-median-from-data-
//stream/ 
// Related Topics 设计 双指针 数据流 排序 堆（优先队列） 👍 274 👎 0


package com.company.leetcode.editor.cn;

import java.util.PriorityQueue;

public class No_剑指Offer41_ShuJuLiuZhongDeZhongWeiShuLcof {
    public static void main(String[] args) {
        MedianFinder medianFinder = new MedianFinder();
        System.out.println(medianFinder.findMedian());
        medianFinder.addNum(1);
        System.out.println(medianFinder.findMedian());
        medianFinder.addNum(2);
        System.out.println(medianFinder.findMedian());
        medianFinder.addNum(3);
        System.out.println(medianFinder.findMedian());
        medianFinder.addNum(4);
        System.out.println(medianFinder.findMedian());
        medianFinder.addNum(5);
        System.out.println(medianFinder.findMedian());
        medianFinder.addNum(6);
        System.out.println(medianFinder.findMedian());
        medianFinder.addNum(7);
        System.out.println(medianFinder.findMedian());
        medianFinder.addNum(8);
        System.out.println(medianFinder.findMedian());
        medianFinder.addNum(9);
        System.out.println(medianFinder.findMedian());
        medianFinder.addNum(10);
        System.out.println(medianFinder.findMedian());

    }

    //leetcode submit region begin(Prohibit modification and deletion)
    static class MedianFinder {
        PriorityQueue<Integer> maxTop;//存放升序中前半部分的数据
        PriorityQueue<Integer> minTop;//存放升序中后半部分的数据

        /**
         * initialize your data structure here.
         */
        public MedianFinder() {
            maxTop = new PriorityQueue<>((a, b) -> b - a);
            minTop = new PriorityQueue<>((a, b) -> a - b);

        }

        public void addNum(int num) {
            //需要维护两个堆的size差距不大于1，且minTop更多
            //也要维护前半部分的数据都在maxTop里,后半部分都在minTop里，所以放入时需要对比
            //先尝试放入maxTop，取出max再放入minTop
            if (maxTop.isEmpty()) {
                maxTop.add(num);//如果没有数据，先放进去再说
                return;
            }
            //对比应该放入到哪里
            if (num < maxTop.peek()) {
                //应该放入maxTop
                maxTop.add(num);
                //上下逻辑对称的
                if (maxTop.size() > minTop.size()) {
                    minTop.add(maxTop.poll());
                }
            } else {
                //应该放入minTop
                //但如果放入新元素后，minTop里的元素数量不满足 两者最大相差一且minTop更多，则需要将minTop里的元素拿一个放入maxTop（堆顶元素）
                minTop.add(num);
                //上下逻辑对称的
                if (minTop.size() > maxTop.size() + 1) {
                    maxTop.add(minTop.poll());
                }
            }
        }

        public double findMedian() {
            if (maxTop.size() == 0 && minTop.size() == 0) {
                return 0;
            }
            if (maxTop.size() == 0) {
                return minTop.peek();
            }
            if (minTop.size() == 0) {
                return maxTop.peek();
            }
            if ((maxTop.size() + minTop.size()) % 2 == 1) {
                return minTop.peek();
            } else {
                return ((double) (minTop.peek() + maxTop.peek())) / 2;
            }
        }
    }

/**
 * Your MedianFinder object will be instantiated and called as such:
 * MedianFinder obj = new MedianFinder();
 * obj.addNum(num);
 * double param_2 = obj.findMedian();
 */
//leetcode submit region end(Prohibit modification and deletion)

}