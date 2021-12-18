package com.company.leetcode.editor.cn.solution;

import java.util.HashMap;
import java.util.Map;

public class 滑动窗口 {

    /* 滑动窗口算法框架 */
    //适合两个字符串做比较、子串问题、包含问题
    void slidingWindow(String s, String t) {
        //需要维护的状态变量
        Map<Character, Integer> need = new HashMap<>();
        Map<Character, Integer> window = new HashMap<>();
        int valid = 0;
        //先填充：需要满足的字符串t 分别有哪些字符
        for (char c : t.toCharArray()) need.put(c, need.getOrDefault(c, 0) + 1);

        //滑动数组的l，r起始都是0
        //索引左闭右开区间[left, right)称为一个「窗口」
        int left = 0, right = 0;
        while (right < s.length()) {
            // c 是将移入窗口的字符
            char c = s.charAt(right);
            // 右移窗口
            right++;
            // 进行窗口内数据的一系列更新
            // ... 更新状态字段：这些状态字段用来在O（1）的时间内判断出 子串是否满足要求
            /*** debug 输出的位置 ***/
            System.out.printf("window: [%d, %d)\n", left, right);
            /********************/

            // 判断左侧窗口是否要收缩
            while (isLeftNeedShrink(need, window)) {
                // d 是将移出窗口的字符
                char d = s.charAt(left);
                // 左移窗口
                left++;
                // 进行窗口内数据的一系列更新
                // ... 更新状态字段 与上文更新的状态一一对应，做反操作
            }
        }
    }

    private boolean isLeftNeedShrink(Map<Character, Integer> need, Map<Character, Integer> window) {
        return true;
    }
}
