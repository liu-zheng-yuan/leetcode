//给定一个数组 nums，编写一个函数将所有 0 移动到数组的末尾，同时保持非零元素的相对顺序。
//
// 示例: 
//
// 输入: [0,1,0,3,12]
//输出: [1,3,12,0,0] 
//
// 说明: 
//
// 
// 必须在原数组上操作，不能拷贝额外的数组。 
// 尽量减少操作次数。 
// 
// Related Topics 数组 双指针 👍 1361 👎 0


package com.company.leetcode.editor.cn;

public class No_Two83_MoveZeroes {
    public static void main(String[] args) {
        Solution solution = new No_Two83_MoveZeroes().new Solution();
    }

    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        public void moveZeroes(int[] nums) {
            //相当于第27题删除元素，之后再把末尾都赋值0
            int slow = 0, fast = 0;
            while (fast < nums.length) {
                if (nums[fast] != 0) {
                    nums[slow] = nums[fast];
                    //这样[0..slow-1]都是没有0的
                    slow++;
                }
                fast++;
            }
            //while退出后，共slow个元素是没有0的，即[0..slow-1]
            //即从第slow 个序号开始是需要填充0
            while (slow < nums.length) {
                nums[slow] = 0;
                slow++;
            }
        }
    }
//leetcode submit region end(Prohibit modification and deletion)

}