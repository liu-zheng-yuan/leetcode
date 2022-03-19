//输入两个整数序列，第一个序列表示栈的压入顺序，请判断第二个序列是否为该栈的弹出顺序。假设压入栈的所有数字均不相等。例如，序列 {1,2,3,4,5} 是某栈
//的压栈序列，序列 {4,5,3,2,1} 是该压栈序列对应的一个弹出序列，但 {4,3,5,1,2} 就不可能是该压栈序列的弹出序列。 
//
// 
//
// 示例 1： 
//
// 输入：pushed = [1,2,3,4,5], popped = [4,5,3,2,1]
//输出：true
//解释：我们可以按以下顺序执行：
//push(1), push(2), push(3), push(4), pop() -> 4,
//push(5), pop() -> 5, pop() -> 3, pop() -> 2, pop() -> 1
// 
//
// 示例 2： 
//
// 输入：pushed = [1,2,3,4,5], popped = [4,3,5,1,2]
//输出：false
//解释：1 不能在 2 之前弹出。
// 
//
// 
//
// 提示： 
//
// 
// 0 <= pushed.length == popped.length <= 1000 
// 0 <= pushed[i], popped[i] < 1000 
// pushed 是 popped 的排列。 
// 
//
// 注意：本题与主站 946 题相同：https://leetcode-cn.com/problems/validate-stack-sequences/ 
// Related Topics 栈 数组 模拟 👍 300 👎 0


package com.company.leetcode.editor.cn;

import java.util.HashMap;
import java.util.Map;
import java.util.Stack;

public class No_剑指Offer31_ZhanDeYaRuDanChuXuLieLcof {
    public static void main(String[] args) {
        Solution solution = new No_剑指Offer31_ZhanDeYaRuDanChuXuLieLcof().new Solution();
        System.out.println(solution.validateStackSequences(new int[]{1, 0}, new int[]{1, 0}));
    }

    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        //遍历pop序列，遇到一个辅助栈里没有的元素，就对pushed序列一直执行入栈操作，直到当前元素入栈（或者到头就是不合法），再补上当前元素退栈。
        //如果遇到辅助栈里有的元素，就退栈，直到将当前元素退栈（如果到底说明序列不合法）
        //最终成功遍历完pop序列后，且辅助栈中没有元素说明合法
        public boolean validateStackSequences(int[] pushed, int[] popped) {
            Stack<Integer> stack = new Stack<>();
            Map<Integer, Boolean> map = new HashMap<>();//标记元素是否在栈里
            int popIdx = 0;
            int pushIdx = 0;
            //
            while (popIdx < popped.length) {
                int curPop = popped[popIdx];
                //遇见不再辅助栈里的新元素，需要继续入栈新元素
                if (!map.getOrDefault(curPop, false)) {
                    while (pushIdx < pushed.length) {
                        stack.add(pushed[pushIdx]);
                        map.put(pushed[pushIdx], true);
                        if (pushed[pushIdx] == curPop) {
                            //一直入栈到了需要pop的元素，就不用继续了,但是下标需要继续增加，便于下次迭代
                            pushIdx++;
                            break;
                        } else {
                            pushIdx++;
                        }
                    }
                    //检测如果pushIdx到头了，都没把curPop放入辅助栈里，说明不合法(需要排除最后一个元素刚好是要push进入的情况)
                    if (pushIdx == pushed.length && pushed[pushIdx - 1] != curPop) {
                        return false;
                    }
                }
                //辅助栈里有当前需要pop的curPop了，正常执行pop流程
                // 需要一直pop到curPop为止，如果到底了还没有，也是不合法
                int lastPop = 0;//记录最后一个pop的元素，方便后面做判断
                while (!stack.isEmpty()) {
                    lastPop = stack.pop();
                    map.put(lastPop, false);//一直退栈
                    if (lastPop == curPop) {
                        break;
                    }
                }
                if (stack.isEmpty() && lastPop != popped[popIdx]) {
                    return false;
                }
                //
                popIdx++;
            }
            //
            if (stack.isEmpty() && popIdx == popped.length) {
                return true;
            } else {
                return false;
            }
        }
    }
//leetcode submit region end(Prohibit modification and deletion)

}