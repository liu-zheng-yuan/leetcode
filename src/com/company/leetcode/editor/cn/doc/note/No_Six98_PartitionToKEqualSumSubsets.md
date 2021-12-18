# 第一种解法：站在nums的角度上

![](C:/Users/lzy/Desktop/IMG_20211215_095549.jpg)

思路：对于nums里的每个n，每次做选择都可以选择放在k个桶里。总复杂度 k^n。树的高度是n，宽度是k。 加上剪枝能过，但是时间复杂度较高

    {
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
            return recursion(0, buckets, nums, target);
        }

        //定义：bucket保存第k个桶的总和，idx是当前遍历的nums索引，target是目标和，返回是否能平分成k
        private boolean recursion(int idx, int[] buckets, int[] nums, int target) {
            //边界条件：idx遍历到nums末尾，检查buckets是否都满足等于taget
            if (idx == nums.length) {
                for (int i = 0; i < buckets.length; i++) {
                    if (buckets[i] != target) {
                        return false;
                    }
                }
                return true;
            }

            //做选择：idx放入第i个桶
            for (int i = 0; i < buckets.length; i++) {
                //剪枝优化：如果当前bucket[i]+nums[idx]超过了总和超过了target 那剩下的分支都不用找了，肯定不符合最终要求
                if (buckets[i]+nums[idx] > target) {
                    continue;
                }
                buckets[i] += nums[idx];
                //函数返回boolean，便于快速找到结果就返回，剪枝剩下无用分支
                if (recursion(idx + 1, buckets, nums, target)) {
                    return true;
                }
                buckets[i] -= nums[idx];//回溯
            }
            return false;//当前idx下，nums[idx]装入哪个桶都不行
        }
    }

# 第二种解法：站在K的角度上

思路：对于每个桶K，都考虑每个num是否装进当前桶里。这样，每个num可以做的选择只有“装入”和“不装入”两种。 最坏复杂度是“对每个桶K，每个num都要做两种选择”，即 2^n * k。明显比第一种复杂度低。

进阶：对于一个桶，就相当于普通的组合问题 ==》“一个数组里选出几个数使它们和为target” ==》本题要划分成K个数组，就是K次组合问题 ==》 决策树 就是多(k)个二叉树（2^N）

    {

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