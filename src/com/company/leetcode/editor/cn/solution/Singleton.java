package com.company.leetcode.editor.cn.solution;

public class Singleton {

    private static volatile Singleton singleton = new Singleton();


    private Singleton() {

    }

    public Singleton getInstance() {
        return singleton;
    }

    public Singleton getInstance1() {
        if (singleton == null) {
            synchronized (Singleton.class) {
                if (singleton == null) {
                    singleton = new Singleton();
                }
            }
        }
        return singleton;
    }

    public Singleton getInstance2() {
        return Holder.singleton;
    }

    private static class Holder {
        private static Singleton singleton = new Singleton();
    }
}
