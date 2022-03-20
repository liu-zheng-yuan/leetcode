//在字符串 s 中找出第一个只出现一次的字符。如果没有，返回一个单空格。 s 只包含小写字母。
//
// 示例 1: 
//
// 
//输入：s = "abaccdeff"
//输出：'b'
// 
//
// 示例 2: 
//
// 
//输入：s = "" 
//输出：' '
// 
//
// 
//
// 限制： 
//
// 0 <= s 的长度 <= 50000 
// Related Topics 队列 哈希表 字符串 计数 👍 194 👎 0


package com.company.leetcode.editor.cn;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;
import java.util.Queue;

public class No_面试题50_DiYiGeZhiChuXianYiCiDeZiFuLcof {
    public static void main(String[] args) {
        Solution solution = new No_面试题50_DiYiGeZhiChuXianYiCiDeZiFuLcof().new Solution();
        System.out.println(solution.firstUniqChar("leetcode"));
    }

    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        public char firstUniqChar(String s) {
            //* 队列的延迟删除（单调队列） 一定要配合 while poll 来维护*
            //其实正常只需要一个Map+2次遍历就行
            //如果要求只用1次遍历，就可以用queue，维护一个只出现一次的元素队列，遍历完队首那个就是
            Queue<Character> queue = new LinkedList<>();
            Map<Character, Integer> map = new HashMap<>();//储存出现的位置
            // 
            for (int i = 0; i < s.length(); i++) {
                char ch = s.charAt(i);
                map.put(ch, map.getOrDefault(ch, 0) + 1);
                if (map.get(ch) == 1) {//如果没出现过，就正常加到map和queue里
                    queue.add(ch);
                } else {
                    //如果出现过，就要维护queue:如果是首元素违反条件，则需要poll，如果非首元素违反条件“延迟删除”（暂时不影响结果，等非首元素因为poll变成了首元素，再通过while删除）
                    //这里不能偷懒用if而不用while，因为延迟删除的存在可能首元素之后若干的元素都是违反条件的。
                    while (!queue.isEmpty() && map.get(queue.peek()) > 1) {
                        queue.poll();
                    }
                }
            }
            return queue.isEmpty() ? ' ' : queue.peek();

        }
    }

//leetcode submit region end(Prohibit modification and deletion)

}