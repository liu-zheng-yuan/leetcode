//输入一个整数数组，判断该数组是不是某二叉搜索树的后序遍历结果。如果是则返回 true，否则返回 false。假设输入的数组的任意两个数字都互不相同。
//
// 
//
// 参考以下这颗二叉搜索树： 
//
//      5
//    / \
//   2   6
//  / \
// 1   3 
//
// 示例 1： 
//
// 输入: [1,6,3,2,5]
//输出: false 
//
// 示例 2： 
//
// 输入: [1,3,2,6,5]
//输出: true 
//
// 
//
// 提示： 
//
// 
// 数组长度 <= 1000 
// 
// Related Topics 栈 树 二叉搜索树 递归 二叉树 单调栈 👍 384 👎 0


package com.company.leetcode.editor.cn;

public class No_剑指Offer33_ErChaSouSuoShuDeHouXuBianLiXuLieLcof {
    public static void main(String[] args) {
        Solution solution = new No_剑指Offer33_ErChaSouSuoShuDeHouXuBianLiXuLieLcof().new Solution();
        boolean res = solution.verifyPostorder(new int[]{4, 8, 6, 12, 16, 14, 10});
        System.out.println(res);
    }

    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        public boolean verifyPostorder(int[] postorder) {
            return recursion(postorder, 0, postorder.length - 1);
        }

        //定义：判断[left,right]里的元素是否是bst的后续遍历结果
//    其实也就意味着：最后一个right是root节点的话，[left,idx-1]都比root小， [idx,right-1]都比root大。idx是第一个比root大的元素位置
        private boolean recursion(int[] postorder, int left, int right) {
            //边界条件2：根据单个节点肯定是bst推断出来的
            if (left == right) {
                return true;
            }
            //边界条件1：根据[left,idx-1] [idx,right-1] 闭区间元素个数不能为0推到出来的：right-left+1 > 0 == left < right+1
            //对于[8,6]这个序列，idx = 0，所以下一轮递归是left = 0，right=-1 == 即左子树为空，bst中子树为空也是可以的，所以也是true
            if (left < 0 || right < 0 || left > right) {
                return true;
            }
            //前序遍历位置：判断是否满足 左小右大条件
            int root = postorder[right];
            int idx = right;//第一个比root大的位置
            for (int i = left; i < right; i++) {
                if (postorder[i] > root) {
                    idx = i;
                    break;
                }
                //如果没有比root更大的，最后idx=right即root本身
            }
            //判断[left,idx-1]都比root小， [idx,right-1]都比root大
            boolean rightBig = true;//由于前一步已经保证了 [left,idx-1]都比root小，后面只要判断 [idx,right-1]都比root大
            for (int i = idx; i < right; i++) {
                if (postorder[i] < root) {
                    rightBig = false;
                    break;
                }
            }
            return rightBig
                    && recursion(postorder, left, idx - 1)
                    && recursion(postorder, idx, right - 1);


        }


    }
//leetcode submit region end(Prohibit modification and deletion)

}