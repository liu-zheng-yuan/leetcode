//输入一个非负整数数组，把数组里所有数字拼接起来排成一个数，打印能拼接出的所有数字中最小的一个。
//
// 
//
// 示例 1: 
//
// 输入: [10,2]
//输出: "102" 
//
// 示例 2: 
//
// 输入: [3,30,34,5,9]
//输出: "3033459" 
//
// 
//
// 提示: 
//
// 
// 0 < nums.length <= 100 
// 
//
// 说明: 
//
// 
// 输出结果可能非常大，所以你需要返回一个字符串而不是整数 
// 拼接起来的数字可能会有前导 0，最后结果不需要去掉前导 0 
// 
// Related Topics 贪心 字符串 排序 👍 409 👎 0


package com.company.leetcode.editor.cn;

import java.util.Arrays;

public class No_剑指Offer45_BaShuZuPaiChengZuiXiaoDeShuLcof {
    public static void main(String[] args) {
        Solution solution = new No_剑指Offer45_BaShuZuPaiChengZuiXiaoDeShuLcof().new Solution();
        System.out.println(solution.minNumber(new int[]{20, 1}));
    }

    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        //1.先统一转化成字符串数组
        //2.先比较最高位字符，小的在前，大的在后，如果相等继续比较
        //3.再比较次低位字符，小的在前，大的在后
        //4.如果相同，依次比较次一位，直到双指针都移动到两个字符串末尾

        //上述自己想的逻辑比较复杂，实际上只需要s1+s2 < s2+s1 则不交换s1 s2的位置，反之则交换
        public String minNumber(int[] nums) {
            String[] strings = new String[nums.length];
            for (int i = 0; i < nums.length; i++) {
                strings[i] = String.valueOf(nums[i]);
            }
            //排序比较
            Arrays.sort(strings, (s1, s2) -> {
//                //起始比较的最高位
//                int i = s1.length() - 1;
//                int j = s2.length() - 1;
//                while (i > 0 || j > 0) {
//                    char c1 = s1.charAt(i);
//                    char c2 = s2.charAt(j);
//
//                    if (c1 < c2) {
//                        return -1;//排序不变，小的在前
//                    } else if (c2 < c1) {
//                        return 123;//正数就是排序变化，s2在前，同样符合小的在前
//                    } else if (c1 == c2) {
//                        //继续比较次一位
//                        if (i > 0) {
//                            i--;
//                        }
//                        if (j > 0) {
//                            j--;
//                        }
//                    }
//                }
//                //当i == 0 && j==0时，会漏一次比较
//                char c1 = s1.charAt(i);
//                char c2 = s2.charAt(j);
//                if (c1 < c2) {
//                    return -1;//排序不变，小的在前
//                } else if (c2 < c1) {
//                    return 1;//正数就是排序变化，s2在前，同样符合小的在前
//                } else {
//                    return 0;//如果这都没有比出来结果，说明s1和s2每一位都一样，随便那个在前
//
//                }

                return (s1 + s2).compareTo(s2 + s1);
            });
            //
            StringBuilder sb = new StringBuilder();
//            for (int i = strings.length - 1; i >= 0; i--) { 之前排序的过程已经是把高位的放在数据前面，不需要再倒叙了
//                sb.append(strings[i]);
//            }
            for (int i = 0; i < nums.length; i++) {
                sb.append(strings[i]);
            }
            return sb.toString();
        }
    }
//leetcode submit region end(Prohibit modification and deletion)

}