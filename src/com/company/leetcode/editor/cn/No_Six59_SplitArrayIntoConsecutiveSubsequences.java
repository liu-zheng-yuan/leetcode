//给你一个按升序排序的整数数组 num（可能包含重复数字），请你将它们分割成一个或多个长度至少为 3 的子序列，其中每个子序列都由连续整数组成。
//
// 如果可以完成上述分割，则返回 true ；否则，返回 false 。 
//
// 
//
// 示例 1： 
//
// 
//输入: [1,2,3,3,4,5]
//输出: True
//解释:
//你可以分割出这样两个连续子序列 : 
//1, 2, 3
//3, 4, 5
// 
//
// 示例 2： 
//
// 
//输入: [1,2,3,3,4,4,5,5]
//输出: True
//解释:
//你可以分割出这样两个连续子序列 : 
//1, 2, 3, 4, 5
//3, 4, 5
// 
//
// 示例 3： 
//
// 
//输入: [1,2,3,4,4,5]
//输出: False
// 
//
// 
//
// 提示： 
//
// 
// 1 <= nums.length <= 10000 
// 
// Related Topics 贪心 数组 哈希表 堆（优先队列） 👍 352 👎 0


package com.company.leetcode.editor.cn;

import java.util.HashMap;
import java.util.Map;

public class No_Six59_SplitArrayIntoConsecutiveSubsequences {
    public static void main(String[] args) {
        Solution solution = new No_Six59_SplitArrayIntoConsecutiveSubsequences().new Solution();
        System.out.println(solution.isPossible(new int[]{1, 2, 3, 3, 4, 4, 5, 5}));
    }

    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        //状态：index
        //选择：对于当前的数字，可以选择 1.跟在已经形成的序列后面 2.以自己开头和后面的元素一个新序列，如果都不行，那就是断了
        //贪心：如果能选1 就尽量接在已有之后（不知道为啥https://mp.weixin.qq.com/s?__biz=MzAxODQxMDM0Mw==&mid=2247491005&idx=1&sn=36cdcb0098aca81c3c4061baf2474b82&scene=21#wechat_redirect）
        //转移方程（条件）：
        //1.记录已经形成的序列对所有数字的需求（hashMap），如果curNum对应的需求大于0，说明可以接在某个已有序列之后（接之后，要更新两个map的状态）
        //2.记录从index（初始是0）到末尾，所有数字出现的频率（hashMap），如果curNum之后3个数字的频率都大于0，说明可以组成至少3个的序列
        public boolean isPossible(int[] nums) {
            Map<Integer, Integer> int2Freq = new HashMap<>();//从index（初始是0）到末尾，所有数字出现的频率（hashMap）
            Map<Integer, Integer> int2Need = new HashMap<>();//已经形成的序列对数字的需求（hashMap）
            //
            for (int n : nums) {
                int2Freq.put(n, int2Freq.getOrDefault(n, 0) + 1);
            }
            //
            for (int num : nums) {
                //如果没有freq了，说明已经被其他序列用过了，跳过即可
                if (int2Freq.getOrDefault(num, 0) == 0) {
                    continue;
                }
                //先贪心选择跟在已有序列之后
                if (int2Need.getOrDefault(num, 0) > 0) {
                    //选择跟在某个序列之后，对当前num的需求就要降低，并且出现频率也要降低
                    int2Need.put(num, int2Need.getOrDefault(num, 0) - 1);
                    int2Freq.put(num, int2Freq.getOrDefault(num, 0) - 1);
                    //新序列对num+1就有了需求
                    int2Need.put(num + 1, int2Need.getOrDefault(num + 1, 0) + 1);
                } else if (int2Freq.getOrDefault(num, 0) > 0
                        && int2Freq.getOrDefault(num + 1, 0) > 0
                        && int2Freq.getOrDefault(num + 2, 0) > 0) {
                    //判断能不能自己开始序列，需要后序三个数都存在
                    int2Freq.put(num, int2Freq.getOrDefault(num, 0) - 1);
                    int2Freq.put(num + 1, int2Freq.getOrDefault(num + 1, 0) - 1);
                    int2Freq.put(num + 2, int2Freq.getOrDefault(num + 2, 0) - 1);
                    //对n+3有了需求
                    int2Need.put(num + 3, int2Need.getOrDefault(num + 3, 0) + 1);
                } else {
                    return false;
                }
            }
            return true;


        }
    }
//leetcode submit region end(Prohibit modification and deletion)

}