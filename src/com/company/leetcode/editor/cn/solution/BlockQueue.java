package com.company.leetcode.editor.cn.solution;

public class BlockQueue {
    private int[] arr;
    private int size;//这个不能少，用来标记是否真的满了 光用head和tail无法分辨
    //队列需要先入先出，所以put和take分别需要两个指针指向位置
    private int head;//下一个能取数的位置
    private int tail;//下一个能放入数字的位置
    private Object lock = new Object();

    public BlockQueue(int size) {
        arr = new int[size];
        this.size = size;
        head = 0;
        tail = 0;
    }

    public void put(int n) throws InterruptedException {
        synchronized (lock) {
            //不满足条件时
            while (size == arr.length) {
                lock.wait();//释放锁，等待条件满足
            }
            //满足条件时，往尾部put
            arr[tail] = n;
            //尝试将tail向后推进，如果队列装满，则索引归零,类似于取模的思路
            if (++tail == arr.length) {
                tail = 0;
            }
            //
            size++;
            lock.notifyAll();
        }

    }


    public int take() throws InterruptedException, Exception {
        synchronized (lock) {
            while (size == 0) {
                lock.wait();
            }
            //
            int res = arr[head];
            //消费完所有数据后从0开始
            if (++head == arr.length) {
                head = 0;
            }
            //
            size--;
            this.notifyAll();
            return res;
        }
    }
}
