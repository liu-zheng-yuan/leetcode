//在一个数组 nums 中除一个数字只出现一次之外，其他数字都出现了三次。请找出那个只出现一次的数字。
//
// 
//
// 示例 1： 
//
// 输入：nums = [3,4,3,3]
//输出：4
// 
//
// 示例 2： 
//
// 输入：nums = [9,1,7,9,7,9,7]
//输出：1 
//
// 
//
// 限制： 
//
// 
// 1 <= nums.length <= 10000 
// 1 <= nums[i] < 2^31 
// 
//
// 
// Related Topics 位运算 数组 👍 304 👎 0


package com.company.leetcode.editor.cn;

public class No_剑指Offer56II_ShuZuZhongShuZiChuXianDeCiShuIiLcof {
    public static void main(String[] args) {
        Solution solution = new No_剑指Offer56II_ShuZuZhongShuZiChuXianDeCiShuIiLcof().new Solution();
    }

    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        //1.统计每个数字每个比特位上1出现的次数
        //2。每位统计值对3取余就是 “只出现一次数字“的对应位上的数（1/0）
        //3.32bit转成int
        public int singleNumber(int[] nums) {
            int[] bits = new int[32];
            for (int n : nums) {
                //依次判断n的每一位
                for (int j = 0; j < 32; j++) {
                    bits[j] += n & 1;
                    n = n >> 1;
                }
            }
            //将 bits 各元素对 3 求余，则结果为 “只出现一次的数字” 的各二进制位。
            for (int i = 0; i < 32; i++) {
                bits[i] = bits[i] % 3;
            }
            //转化为int
            int res = 0;
            for (int i = 0; i < bits.length; i++) {
                res = res << 1;
                //把当前处理的这个bit改成应该的 1/0
                res = res | bits[31 - i];//bits长度是31
            }
            return res;
        }
    }
//leetcode submit region end(Prohibit modification and deletion)

}