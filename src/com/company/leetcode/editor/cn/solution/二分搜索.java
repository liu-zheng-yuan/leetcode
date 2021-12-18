package com.company.leetcode.editor.cn.solution;

public class 二分搜索 {
    //https://mp.weixin.qq.com/s?__biz=MzAxODQxMDM0Mw==&mid=2247485044&idx=1&sn=e6b95782141c17abe206bfe2323a4226&scene=21
    //寻找一个数（基本的二分搜索）
    int binarySearch(int[] nums, int target) {
        int left = 0;
        int right = nums.length - 1; // 注意

        while (left <= right) {
            int mid = left + (right - left) / 2;
            if (nums[mid] == target)
                return mid;
            else if (nums[mid] < target)
                left = mid + 1; // 注意
            else if (nums[mid] > target)
                right = mid - 1; // 注意
        }
        return -1;
    }

    //寻找左侧边界的二分搜索-左闭右开
    int left_bound_1(int[] nums, int target) {
        if (nums.length == 0) return -1;
        int left = 0;
        int right = nums.length; // 注意

        while (left < right) { // 注意
            int mid = (left + right) / 2;
            if (nums[mid] == target) {
                right = mid; //注意这里没有+1或者-1，所以最后返回left也不用+1 -1
            } else if (nums[mid] < target) {
                left = mid + 1;
            } else if (nums[mid] > target) {
                right = mid; // 注意
            }
        }
        /**
         * 搜索区间是[0,length) => right初始值是length;退出条件是 left = right => left的取值范围是 [0,length]
         * 所以两种特殊情况分别对应的是:(左侧区间的实质是:比target小的数字的个数)
         * 1.target比所有数都小:left一直转移到 0的极端情况,由于mid = target和mid > target都是right = mid,所以最后并不能确定转移到0时由于num[0]=target还是target比所有数都小
         * 2.target比所有数都大:left 一直转移到 length的极端情况,由于只有当target > mid时,left会从左边+1逼近,所以当left达到上限length,一定是target比所有数都大
         */
        if (left == nums.length) return -1;
        if (nums[left] != target) return -1;
        return left;
    }

    //寻找左侧边界的二分搜索-左闭右闭
    int left_bound(int[] nums, int target) {
        int left = 0, right = nums.length - 1;
        // 搜索区间为 [left, right]
        while (left <= right) {
            int mid = left + (right - left) / 2;
            if (nums[mid] < target) {
                // 搜索区间变为 [mid+1, right]
                left = mid + 1;
            } else if (nums[mid] > target) {
                // 搜索区间变为 [left, mid-1]
                right = mid - 1;
            } else if (nums[mid] == target) {
                // 收缩右侧边界
                right = mid - 1;
            }
        }
        /**
         * 检查出界情况：两者的区别是，从左侧逼近第一个符合条件的一定是左边界，从右侧逼近需要处理把左侧边界也收缩掉的特殊情况
         * left >= nums.length : 当target比所有数都大，left = length时的情况 （right = length -1）  因为不断扩展左边界，index = length -1 就是左边界的情况能找到，但是由于退出条件导致无target的情况下left会超过length，所以需要处理这种情况
         * nums[left] != target ：当target比所有数都小，right = -1 时的情况 （left = 0） 因为不断收缩右边界，可能明明index = 0就是最左边界了，但是由于0的左侧没有数字了，还是被收缩掉了，这种特殊情况需要额外判断
         */
        if (left >= nums.length || nums[left] != target)
            return -1;
        return left;
    }

    //寻找右侧边界的二分搜索-左闭右开
    int right_bound_1(int[] nums, int target) {
        if (nums.length == 0) return -1;
        int left = 0, right = nums.length;

        while (left < right) {
            int mid = (left + right) / 2;
            if (nums[mid] == target) {
                left = mid + 1; //注意这里+1，所以会导致最后退出时，left（和right）在右边界的后一位，需要left - 1
            } else if (nums[mid] < target) {
                left = mid + 1;
            } else if (nums[mid] > target) {
                right = mid; //保证了从右侧逼近不会超过0
            }
        }
        /**
         * 搜索区间是[0,length) => right初始值是length;退出条件是 left = right => left的取值范围是 [0,length] => 所以left -1 的范围是[-1,length-1]
         * 所以两种特殊情况分别对应的是:(右侧区间的实质是:比target大的数字的个数)
         * 1.target比所有数都大:left 一直转移到 length的极端情况,由于不管是mid=target还是mid<target,都是left会+1,所以不管num[length-1]是不是右边界,退出时一定是left=length,所以需要额外判断一下num[length-1]是不是右边界
         * 2.target比所有数都小:left 一直转移到 0的极端情况,如果num[0]是taget,那left一定是0+1而不是0,所以如果left是0,一定是target不存在
         */
        if (left == 0) return -1;
        if (nums[left - 1] != target) return -1;
        return left - 1; // 注意

    }

    //寻找右侧边界的二分搜索-左闭右闭
    int right_bound_2(int[] nums, int target) {
        int left = 0, right = nums.length - 1;
        while (left <= right) {
            int mid = left + (right - left) / 2;
            if (nums[mid] < target) {
                left = mid + 1; // 搜索区间变为 [mid+1, right]
            } else if (nums[mid] > target) {
                right = mid - 1;// 搜索区间变为 [left, mid-1]
            } else if (nums[mid] == target) {
                // 收缩左侧边界
                left = mid + 1;
            }
        }
        /**
         * 搜索区间是[0,length-1] => right初始值是length-1;退出条件是 left = right+1 => left的取值范围是 [0,length]  => 所以left -1 的范围是[-1,length-1]
         * 所以两种特殊情况分别对应的是:(右侧区间的实质是:比target大的数字的个数)
         * 1.target比所有数都大:left 一直转移到 length的极端情况,由于不管是mid=target还是mid<target,都是left会+1,所以不管num[length-1]是不是右边界,退出时一定是left=length,所以需要额外判断一下num[length-1]是不是右边界
         * 2.target比所有数都小:left 一直转移到 0的极端情况,如果num[0]是taget,那left一定是0+1而不是0,所以如果left是0,一定是target不存在
         */
        if (left == 0 || nums[left - 1] != target)
            return -1;
        return left - 1;
    }

    /**
     * 之所以左侧边界循环退出后left不用-1，而右侧边界循环退出后需要-1：
     * 左侧边界：从right不断收缩，且退出条件是left=right+1，类似left不动，等right减到满足退出条件
     * 右侧边界：从left不断收缩，且退出条件”还是“left=right+1，类似right不动，等left加到满足条件，这时left一定比右边界大1才能退出
     */
}
