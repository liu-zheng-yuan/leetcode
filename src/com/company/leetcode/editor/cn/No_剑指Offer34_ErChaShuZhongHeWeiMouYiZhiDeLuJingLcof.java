//给你二叉树的根节点 root 和一个整数目标和 targetSum ，找出所有 从根节点到叶子节点 路径总和等于给定目标和的路径。
//
// 叶子节点 是指没有子节点的节点。 
//
// 
//
// 示例 1： 
//
// 
//
// 
//输入：root = [5,4,8,11,null,13,4,7,2,null,null,5,1], targetSum = 22
//输出：[[5,4,11,2],[5,8,4,5]]
// 
//
// 示例 2： 
//
// 
//
// 
//输入：root = [1,2,3], targetSum = 5
//输出：[]
// 
//
// 示例 3： 
//
// 
//输入：root = [1,2], targetSum = 0
//输出：[]
// 
//
// 
//
// 提示： 
//
// 
// 树中节点总数在范围 [0, 5000] 内 
// -1000 <= Node.val <= 1000 
// -1000 <= targetSum <= 1000 
// 
//
// 注意：本题与主站 113 题相同：https://leetcode-cn.com/problems/path-sum-ii/ 
// Related Topics 树 深度优先搜索 回溯 二叉树 👍 267 👎 0


package com.company.leetcode.editor.cn;

import java.util.LinkedList;
import java.util.List;

public class No_剑指Offer34_ErChaShuZhongHeWeiMouYiZhiDeLuJingLcof {
    public static void main(String[] args) {
        Solution solution = new No_剑指Offer34_ErChaShuZhongHeWeiMouYiZhiDeLuJingLcof().new Solution();
        TreeNode n1 = new TreeNode(5);
        TreeNode n2 = new TreeNode(4);
        TreeNode n3 = new TreeNode(11);
        TreeNode n4 = new TreeNode(7);
        TreeNode n5 = new TreeNode(2);
        n1.left = n2;
        n2.left = n3;
        n3.left = n4;
        n3.right = n5;
        System.out.println(solution.pathSum(n1, 22));
        System.out.println();
    }
    //leetcode submit region begin(Prohibit modification and deletion)

    /**
     * Definition for a binary tree node.
     * public class TreeNode {
     * int val;
     * TreeNode left;
     * TreeNode right;
     * TreeNode() {}
     * TreeNode(int val) { this.val = val; }
     * TreeNode(int val, TreeNode left, TreeNode right) {
     * this.val = val;
     * this.left = left;
     * this.right = right;
     * }
     * }
     */
    class Solution {

        private List<List<Integer>> res = new LinkedList<>();

        public List<List<Integer>> pathSum(TreeNode root, int target) {
            LinkedList<Integer> trace = new LinkedList<>();
            if (root == null) { //强制加入root节点，就需要判空
                return res;
            }
            //正常套路应该是在做选择前将选择加入路径列表，但本题强制从根节点开始，根节点一定要选择，所以在主函数上手动加入根节点
            trace.add(root.val);
            recursion(root, trace, target);
            return res;
        }

        //定义：前序遍历二叉树，到叶子节点时将符合条件的track加入res，无返回值
        private void recursion(TreeNode root, LinkedList<Integer> trace, int target) {
            //结束条件:正常前序遍历的条件,实际上只有初始root为null时可能会有用，下面做选择时已经排除了left right为null的情况
            if (root == null) {
                return;
            }
            //前序遍历逻辑:叶子节点时判断现有track
            if (root.left == null && root.right == null) {
                long sum = 0;
                for (Integer element : trace) {
                    sum += element;
                }
                if (target == sum) {
                    res.add(new LinkedList<>(trace));
                }
                return;//没必要再进下一层递归，反正都是root == null，return
            }
            //当前选择，left|right，记得撤销trace
            if (root.left != null) {
                //选择下一个left的val加入trace
                trace.add(root.left.val);//正常套路应该是在做选择前将选择加入路径列表，但本题强制从根节点开始，根节点一定要选择，所以在主函数上手动加入根节点
                recursion(root.left, trace, target);
                trace.removeLast();
            }
            if (root.right != null) {
                trace.add(root.right.val);
                recursion(root.right, trace, target);
                trace.removeLast();
            }
        }
    }

    //leetcode submit region end(Prohibit modification and deletion)
    public static class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode() {
        }

        TreeNode(int val) {
            this.val = val;
        }

        TreeNode(int val, TreeNode left, TreeNode right) {
            this.val = val;
            this.left = left;
            this.right = right;
        }
    }
}