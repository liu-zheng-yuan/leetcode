//给定一个数组 nums 和滑动窗口的大小 k，请找出所有滑动窗口里的最大值。 
//
// 示例: 
//
// 输入: nums = [1,3,-1,-3,5,3,6,7], 和 k = 3
//输出: [3,3,5,5,6,7] 
//解释: 
//
//  滑动窗口的位置                最大值
//---------------               -----
//[1  3  -1] -3  5  3  6  7       3
// 1 [3  -1  -3] 5  3  6  7       3
// 1  3 [-1  -3  5] 3  6  7       5
// 1  3  -1 [-3  5  3] 6  7       5
// 1  3  -1  -3 [5  3  6] 7       6
// 1  3  -1  -3  5 [3  6  7]      7 
//
// 
//
// 提示： 
//
// 你可以假设 k 总是有效的，在输入数组不为空的情况下，1 ≤ k ≤ 输入数组的大小。 
//
// 注意：本题与主站 239 题相同：https://leetcode-cn.com/problems/sliding-window-maximum/ 
// Related Topics 队列 滑动窗口 单调队列 堆（优先队列） 👍 385 👎 0


package com.company.leetcode.editor.cn;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class No_剑指Offer59I_HuaDongChuangKouDeZuiDaZhiLcof {
    public static void main(String[] args) {
        Solution solution = new No_剑指Offer59I_HuaDongChuangKouDeZuiDaZhiLcof().new Solution();
    }

    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        public int[] maxSlidingWindow(int[] nums, int k) {
            List<Integer> res = new ArrayList<>();
            LinkedList<Integer> queue = new LinkedList<>();
            for (int i = 0; i < nums.length; i++) {
                if (i < k) {
                    queueAdd(queue, nums[i]);
                    //第一次滑动窗口里元素有k个时，要记录一下队首值，就是最大值
                    if (i == k - 1) {
                        res.add(queue.peekFirst());
                    }
                    continue;
                }
                //超过滑动窗口上限了，需要从队头移除一个数据，从队尾加入当前的nums[i]
                int needRemove = nums[i - k];
                queueRemove(queue, needRemove);
                queueAdd(queue, nums[i]);
                //计算当前最大值
                res.add(queue.peekFirst());
            }
            return res.stream().mapToInt(i -> i).toArray();
        }

        private void queueRemove(LinkedList<Integer> queue, int needRemove) {
            //只有最大值需要移除窗口的时候才需要真正移除，移除非最大值的时候，非最大值已经被加入的过程中移除了
            if (queue.size() > 0 && queue.peekFirst() == needRemove) {
                queue.pollFirst();
            }
        }

        void queueAdd(LinkedList<Integer> queue, int a) {
            //单调队列，保持加入过程中，队列从头到位是递减的，也就是队头数据为最大
            while (queue.size() > 0 && queue.peekLast() < a) {
                queue.pollLast();
            }
            queue.add(a);
        }

    }
//leetcode submit region end(Prohibit modification and deletion)

}