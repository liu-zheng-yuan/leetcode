//给定一个整数数组 nums 和一个正整数 k，找出是否有可能把这个数组分成 k 个非空子集，其总和都相等。
//
// 示例 1： 
//
// 输入： nums = [4, 3, 2, 3, 5, 2, 1], k = 4
//输出： True
//说明： 有可能将其分成 4 个子集（5），（1,4），（2,3），（2,3）等于总和。 
//
// 
//
// 提示： 
//
// 
// 1 <= k <= len(nums) <= 16 
// 0 < nums[i] < 10000 
// 
// Related Topics 位运算 记忆化搜索 数组 动态规划 回溯 状态压缩 👍 464 👎 0


package com.company.leetcode.editor.cn;

public class No_Six98_PartitionToKEqualSumSubsets {
    public static void main(String[] args) {
        Solution solution = new No_Six98_PartitionToKEqualSumSubsets().new Solution();
        System.out.println(solution.canPartitionKSubsets(new int[]{4, 3, 2, 3, 5, 2, 1}, 4));
        System.out.println();
    }

    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {

        public boolean canPartitionKSubsets(int[] nums, int k) {
            //简单情况判断：总和一定要能平分成k份
            if (nums == null || nums.length == 0 || k == 0) {
                return false;
            }
            int sum = 0;
            for (int i = 0; i < nums.length; i++) {
                sum += nums[i];
            }
            if (sum % k != 0) {
                return false;
            }
            //每一份的目标和
            int target = sum / k;
            int[] buckets = new int[k];
            int[] visited = new int[nums.length];
            return recursion(k - 1, 0, nums, buckets, visited, target);
        }

        //定义：对于当前第k个桶，考虑从start开始的nums数组里的元素是否要加入。visited表示之前桶已经用过了不能再用的数字。返回是否可以平分
        private boolean recursion(int k, int start, int[] nums, int[] buckets, int[] visited, int target) {
//            printIn();
//            log("k", k, "start", start, "bucket", buckets, "visited", visited);
            //边界条件【所有子树】：所有桶都选完了
            if (k == -1) {
                for (int i = 0; i < buckets.length; i++) {
                    if (buckets[i] != target) {
//                        printOut();
//                        log("k", k, "start", start, "bucket", buckets, "visited", visited, "return", false);
                        return false;
                    }
                }
//                printOut();
//                log("k", k, "start", start, "bucket", buckets, "visited", visited, "return", true);
                return true;
            }
            //边界条件【单个子树】：对于第k个桶，达到目标target
            if (buckets[k] == target) {

                return recursion(k - 1, 0, nums, buckets, visited, target);
            }
            //边界条件【单个子树】：对于第k个桶，还没达到目标target，nums就用完了
            if (start == nums.length) {
                return false;
            }


            //
            for (int i = start; i < nums.length; i++) {
                //剪枝
                if (buckets[k] + nums[i] > target) {
                    continue;
                }
                //选择范围限制：之前决策子树里选过了后面就不能再选
                if (visited[i] == 1) {
                    continue;
                }
                //做选择
                visited[i] = 1;
                buckets[k] += nums[i];
                if (recursion(k, i + 1, nums, buckets, visited, target)) {
//                    printOut();
//                    log("k", k, "start", start, "bucket", buckets, "visited", visited, "return", true);
                    return true;
                }
                visited[i] = 0;
                buckets[k] -= nums[i];
            }
//            printOut();
//            log("k", k, "start", start, "bucket", buckets, "visited", visited, "return", false);
            return false;
        }


    }
//leetcode submit region end(Prohibit modification and deletion)

}