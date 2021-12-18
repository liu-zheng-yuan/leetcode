package com.company.leetcode.editor.cn.solution;

import java.util.*;

public class BFS {

    public static class Node {
    }

    // 计算从起点 start 到终点 target 的最近距离
    public int bfs(Node start, Node target) {
        Queue<Node> q = new LinkedList<>(); // 核心数据结构
        Set<Node> visited = new HashSet<>(); // 避免走回头路

        q.offer(start); // 将起点加入队列
        visited.add(start);
        int step = 0; // 记录扩散的步数

        while (!q.isEmpty()) {
            int curSize = q.size();
            /* 将当前队列中的所有节点向四周扩散 */
            for (int i = 0; i < curSize; i++) {
                Node cur = q.poll();
                /* 划重点：这里判断是否到达终点 */
                if (isTargetNode(cur)) {
                    return step;
                }
                /* 将 cur 的相邻节点加入队列 */
                for (Node x : getAdjNode(cur))
                    if (!visited.contains(x)) {
                        q.offer(x);
                        visited.add(x);
                    }
            }
            /* 划重点：更新步数在这里 */
            step++;
        }
        return 0;
    }

    private List<Node> getAdjNode(Node cur) {
        return new LinkedList<>();
    }

    public boolean isTargetNode(Node node) {
        return true;
    }
}
