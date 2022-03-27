//一个整型数组 nums 里除两个数字之外，其他数字都出现了两次。请写程序找出这两个只出现一次的数字。要求时间复杂度是O(n)，空间复杂度是O(1)。
//
// 
//
// 示例 1： 
//
// 输入：nums = [4,1,4,6]
//输出：[1,6] 或 [6,1]
// 
//
// 示例 2： 
//
// 输入：nums = [1,2,10,4,1,4,3,3]
//输出：[2,10] 或 [10,2] 
//
// 
//
// 限制： 
//
// 
// 2 <= nums.length <= 10000 
// 
//
// 
// Related Topics 位运算 数组 👍 581 👎 0


package com.company.leetcode.editor.cn;

public class No_剑指Offer56I_ShuZuZhongShuZiChuXianDeCiShuLcof {
    public static void main(String[] args) {
        Solution solution = new No_剑指Offer56I_ShuZuZhongShuZiChuXianDeCiShuLcof().new Solution();
    }

    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        //任意数对同一个数两次求异或（半加）还是原来的数
        //所以需要分组异或：每个组里只有唯一一个出现一次的数字 === 即将两个只出现一次的数据分到两个组里 === 这两个数字不同，所以他们的二进制肯定会有一位不一样 === 找到“不一样的那个bit”根据bit是否为1来将所有数据分为两个组
        //而对所有数据异或的结果正是”这两个只出现一次的数据的异或结果“，又因为”bit不同则异或结果为1“
        //所以需要找到的mask为：“所有数据异或结果中”为1的bit。可能有很多个，选第一个找到的就行
        //再根据mask & n 是否为1 来将数据划分为两组，这样两个只出现一次的数据一定分布再两个组里

        public int[] singleNumbers(int[] nums) {
            //用于将所有的数异或起来
            int k = 0;

            for(int num: nums) {
                k ^= num;
            }

            //获得k中最低位的1
            int mask = 1;

            //mask = k & (-k) 这种方法也可以得到mask，具体原因百度 哈哈哈哈哈
            while((k & mask) == 0) {
                mask <<= 1;
            }

            int a = 0;
            int b = 0;

            for(int num: nums) {
                if((num & mask) == 0) {
                    a ^= num;
                } else {
                    b ^= num;
                }
            }

            return new int[]{a, b};

        }
    }
//leetcode submit region end(Prohibit modification and deletion)

}