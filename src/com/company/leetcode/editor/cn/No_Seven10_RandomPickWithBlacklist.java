//给定一个包含 [0，n) 中不重复整数的黑名单 blacklist ，写一个函数从 [0, n) 中返回一个不在 blacklist 中的随机整数。
//
// 对它进行优化使其尽量少调用系统方法 Math.random() 。 
//
// 提示: 
//
// 
// 1 <= n <= 1000000000 
// 0 <= blacklist.length < min(100000, N) 
// [0, n) 不包含 n ，详细参见 interval notation 。 
// 
//
// 示例 1： 
//
// 
//输入：
//["Solution","pick","pick","pick"]
//[[1,[]],[],[],[]]
//输出：[null,0,0,0]
// 
//
// 示例 2： 
//
// 
//输入：
//["Solution","pick","pick","pick"]
//[[2,[]],[],[],[]]
//输出：[null,1,1,1]
// 
//
// 示例 3： 
//
// 
//输入：
//["Solution","pick","pick","pick"]
//[[3,[1]],[],[],[]]
//输出：[null,0,0,2]
// 
//
// 示例 4： 
//
// 
//输入： 
//["Solution","pick","pick","pick"]
//[[4,[2]],[],[],[]]
//输出：[null,1,3,1]
// 
//
// 输入语法说明： 
//
// 输入是两个列表：调用成员函数名和调用的参数。Solution的构造函数有两个参数，n 和黑名单 blacklist。pick 没有参数，输入参数是一个列表
//，即使参数为空，也会输入一个 [] 空列表。 
// Related Topics 哈希表 数学 二分查找 排序 随机化 👍 82 👎 0


package com.company.leetcode.editor.cn;

import java.util.*;
import java.util.stream.Collectors;

public class No_Seven10_RandomPickWithBlacklist {
    public static void main(String[] args) {
        Solution solution = new No_Seven10_RandomPickWithBlacklist().new Solution(2, new int[]{1});
    }

    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        /**
         * 设总长为l，黑名单长为bl，那么有效元素总数为vl
         * 由于需要概率相等的随机 ==》 vl个元素需要紧凑排列在数组上才能用 random(vl)
         * 由于原数组上[0,vl)上的元素有可能在黑名单中 ==》需要把[0,vl)中的黑元素逻辑上移动到其他地方，比如数组末尾
         * ===> 用一个Map表示“[0,vl)中黑元素-被移到的数组下标”，如果随机数r是黑元素，则取Map[r]这个下标的元素
         * 注意细节！！！
         * 1.只是逻辑上被转移了，实际上[vl,n)上的元素可能是好元素，也可能是黑元素
         * 2.初始化转移Map时要注意，如果[vl,n)上的元素是黑元素，则不能转移，一直要找到一个好元素，才能逻辑上转移
         */

        private Map<Integer, Integer> map = new HashMap<>();
        private Set<Integer> set = new HashSet<>();
        int vl;
        private Random random = new Random();

        public Solution(int n, int[] blacklist) {
            /**
             * 这样写会超时：修改为
             * 初始化Map时，不要遍历全集vl，而是遍历更小的blackList，只把有需要的（即小于vl）的黑元素，逻辑上转移到末尾
             * 大于vl的黑元素，不用转移，实际上已经在末尾了
             */
//            vl = n - blacklist.length;
//            set.addAll(Arrays.stream(blacklist).boxed().collect(Collectors.toList()));
//            //
//            int lastEle = n - 1;//可交换的末尾元素，随着原来越多的元素被交换到末尾，lastEle也逐渐变小收缩
//            for (int i = 0; i < vl; i++) {
//                if (set.contains(i)) {
//                    //交换到末尾 只有末尾元素不是黑元素，才能交换
//                    while (set.contains(lastEle)) {
//                        lastEle--;
//                    }
//                    //找到一个可以交换的
//                    map.put(i, lastEle);
//                    //这个位置已经用过了，顺延一位
//                    lastEle--;
//
//                }
//            }
            vl = n - blacklist.length;
            set.addAll(Arrays.stream(blacklist).boxed().collect(Collectors.toList()));
            //
            int lastEle = n - 1;//可交换的末尾元素，随着原来越多的元素被交换到末尾，lastEle也逐渐变小收缩
            for (int b : blacklist) {
                if (b >= vl) {
                    continue;
                }
                //交换到末尾 只有末尾元素不是黑元素，才能交换
                while (set.contains(lastEle)) {
                    lastEle--;
                }
                //找到一个可以交换的
                map.put(b, lastEle);
                //这个位置已经用过了，顺延一位
                lastEle--;
            }
        }

        public int pick() {
            int randomInt = random.nextInt(vl);
            if (set.contains(randomInt)) {
                return map.get(randomInt);
            }
            return randomInt;

        }
    }

/**
 * Your Solution object will be instantiated and called as such:
 * Solution obj = new Solution(n, blacklist);
 * int param_1 = obj.pick();
 */
//leetcode submit region end(Prohibit modification and deletion)

}