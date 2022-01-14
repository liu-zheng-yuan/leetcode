//在一条环路上有 N 个加油站，其中第 i 个加油站有汽油 gas[i] 升。
//
// 你有一辆油箱容量无限的的汽车，从第 i 个加油站开往第 i+1 个加油站需要消耗汽油 cost[i] 升。你从其中的一个加油站出发，开始时油箱为空。 
//
// 如果你可以绕环路行驶一周，则返回出发时加油站的编号，否则返回 -1。 
//
// 说明: 
//
// 
// 如果题目有解，该答案即为唯一答案。 
// 输入数组均为非空数组，且长度相同。 
// 输入数组中的元素均为非负数。 
// 
//
// 示例 1: 
//
// 输入: 
//gas  = [1,2,3,4,5]
//cost = [3,4,5,1,2]
//
//输出: 3
//
//解释:
//从 3 号加油站(索引为 3 处)出发，可获得 4 升汽油。此时油箱有 = 0 + 4 = 4 升汽油
//开往 4 号加油站，此时油箱有 4 - 1 + 5 = 8 升汽油
//开往 0 号加油站，此时油箱有 8 - 2 + 1 = 7 升汽油
//开往 1 号加油站，此时油箱有 7 - 3 + 2 = 6 升汽油
//开往 2 号加油站，此时油箱有 6 - 4 + 3 = 5 升汽油
//开往 3 号加油站，你需要消耗 5 升汽油，正好足够你返回到 3 号加油站。
//因此，3 可为起始索引。 
//
// 示例 2: 
//
// 输入: 
//gas  = [2,3,4]
//cost = [3,4,3]
//
//输出: -1
//
//解释:
//你不能从 0 号或 1 号加油站出发，因为没有足够的汽油可以让你行驶到下一个加油站。
//我们从 2 号加油站出发，可以获得 4 升汽油。 此时油箱有 = 0 + 4 = 4 升汽油
//开往 0 号加油站，此时油箱有 4 - 3 + 2 = 3 升汽油
//开往 1 号加油站，此时油箱有 3 - 3 + 3 = 3 升汽油
//你无法返回 2 号加油站，因为返程需要消耗 4 升汽油，但是你的油箱只有 3 升汽油。
//因此，无论怎样，你都不可能绕环路行驶一周。 
// Related Topics 贪心 数组 👍 814 👎 0


package com.company.leetcode.editor.cn;

public class No_One34_GasStation {
    public static void main(String[] args) {
        Solution solution = new No_One34_GasStation().new Solution();
    }

    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        //一般选择：遍历从0-n的每个起点，诶个走一遍，找到一个起点使得：路途中sum(gas[i]) - sum(cost[i]) 保持大于0
        //贪心选择： 规律：如果从i “恰好” 到不了j，则从i-j中任意一点都到不了j。
        //恰好的含义是：能走到j-1，都保持油量大于0，但是到j，存油量小于0.相当于中间的i-j都可以跳过了
        public int canCompleteCircuit(int[] gas, int[] cost) {
            int idx = 0;
            int curOil = 0; //定义：“到达” idx 处的剩余油量。初始状态等于是空降到idx = 0 ，所以油量是0
            int n = gas.length;
            int start = 0;
            //无解情况
            int sum = 0;
            for (int i = 0; i < n; i++) {
                sum += gas[i] - cost[i];
            }
            if (sum < 0) {
                return -1;
            }
            while (idx < n) {
                //检查从idx开始恰好能走到哪里
                while (idx < n && curOil >= 0) {
                    curOil += gas[idx];
                    curOil -= cost[idx];
                    idx++;
                }
                //如果走出来时，已经到末尾了，说明能走一圈
                if (idx == n) {
                    return start;
                }
                //恰好走到这个idx时，无法从上一个idx-1 走到现在的idx。
                //那就要从这个idx作为起点开始走,起点的curOil = 0
                //这样会有个问题，就是假如无解的情况下，也能通过每次不断前进一步来蹭到终点，这要预先排除
                curOil = 0;
                start = idx;
            }
            return -1;

        }
    }
//leetcode submit region end(Prohibit modification and deletion)

}