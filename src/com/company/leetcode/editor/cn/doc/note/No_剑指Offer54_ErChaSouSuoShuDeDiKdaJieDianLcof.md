# 错误解法

一开始的思路就像labuladong公众号里说的不可能实现的logn解法一样，尝试通过二分法解决问题。但是我的思路更有漏洞

这样会找到第一个“右子树节点数量是k”的根节点，但这样的节点不符合题意：任何一个子树的右子树节点数量是k就满足题意，而bst可能有多个子树的右节点是1，或者2

另外，第K大是倒数第K个，而不是正数第K个，应该是“右子树节点数量是K”

参照labuladong的解法改进逻辑：应该是如果右边子树的总结点数大于k，那就去右边子树找，左边的就可以剪枝不去找了，找了还会想下面一样出错

        int res = -1;
        boolean hasGet = false;//标记是否找到符合条件的第一个res，其他符合右子树节点是1的节点不符合题意

        public int kthLargest(TreeNode root, int k) {
            rescursion(root, k);
            return res;
        }

        //定义：遍历二叉树，返回以root为根的树的节点数量。并且在【右子树节点数量等于k-1时，将root的值付给res】
        private int rescursion(TreeNode root, int k) {
            if (root == null) {
                return 0;
            }
            //本题需要知道做子树递归结果，所以是中序遍历
            int rightNum = rescursion(root.right, k);
            if (rightNum == k-1 && hasGet==false) {
                res = root.val;
                hasGet = true;
                //找到结果，剩下的递归返回结果就不重要了，直接返回0
                return -1;
            }
            int leftNum = rescursion(root.left, k);

            return leftNum + rightNum + 1;
        }

labuladong的logn解法是：如果右边子树节点数大于k，那显然第k大在右边子树，就去右边子树找，然后二分。但是原始树节点也没有维护【以该节点为根的树的节点总数】的信息，不能在
o1的复杂度知道左、右子树的节点总数，所以logn不可行。