//输入一个字符串，打印出该字符串中字符的所有排列。 
//
// 
//
// 你可以以任意顺序返回这个字符串数组，但里面不能有重复元素。 
//
// 
//
// 示例: 
//
// 输入：s = "abc"
//输出：["abc","acb","bac","bca","cab","cba"]
// 
//
// 
//
// 限制： 
//
// 1 <= s 的长度 <= 8 
// Related Topics 字符串 回溯 👍 518 👎 0


package com.company.leetcode.editor.cn;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

public class No_剑指Offer38_ZiFuChuanDePaiLieLcof {
    public static void main(String[] args) {
        Solution solution = new No_剑指Offer38_ZiFuChuanDePaiLieLcof().new Solution();
    }

    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {

        List<String> res = new ArrayList<>();

        public String[] permutation(String s) {
            //Map去重结果
            HashSet<String> set = new HashSet<>();
            //标记已经选过的char，必不可少
            int[] visited = new int[s.length()];
            //index和sb是状态，表示当前位置和已经选择的char
            recusion(s, 0, visited, new StringBuilder(), set);
            return res.toArray(new String[]{});
        }

        private void recusion(String s, int idx, int[] visited, StringBuilder cur, HashSet<String> set) {
            // 边界
            if (idx == s.length()) {
                String one = cur.toString();
                if (!set.contains(one)) {
                    set.add(one);
                    res.add(one);
                }
                return;
            }
            //根据当前情况idx，做选择
            for (int i = 0; i < s.length(); i++) {
                if (visited[i] == 0) {
                    visited[i] = 1;
                    cur.append(s.charAt(i));
                    recusion(s, idx + 1, visited, cur, set);
                    visited[i] = 0;
                    cur.deleteCharAt(cur.length() - 1);
                }
            }


        }
    }
//leetcode submit region end(Prohibit modification and deletion)

}