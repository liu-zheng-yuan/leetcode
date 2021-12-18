# 错误解法

试图只通过这一个函数解决问题，能过几个case，但是整个逻辑其实是错的！ 没有考虑到【子结构需要连在一起】，如果root节点的值相同，那left和right的值必须相同，否则就不一致了，而不应该继续recursion(a.left, b)
|| recursion(a.right, b);

    private boolean recursion(TreeNode a, TreeNode b) {
        //退出条件：
        //虽然题目里说空树不是子结构,但是在遍历过程中,所有情况都需要考虑,[且与题目约定不一定相同]
        //下面的逻辑的前提是AB都不是空，那么退出条件里需要判断AB都是空，AB有一个是空的情况
        if (a == null && b == null) {
            return true;//a和b都是叶子节点的情况下，虽然左右子树都是null，但是他们依然有相同的结构和值
        }
        if (a == null && b != null) {
            return false;//同样的位置B有A没有节点，显然不一致
        }
        if (a != null && b == null) {
            return true;//特殊一点，此时是B已经匹配完了，而A还有其他子节点，符合题意，也算true
        }
        //前序遍历位置
        //需要判断当前节点是否跟目标节点的值一样,这关乎我们怎么做选择
        //如果一致:需要继续判断Aleft和Bleft是否一致 [和] Aright和Bright是否一致
        //如果不一致:那么可能A的left[或者]Aright中有与B一致的子结构
        if (a.val == b.val) {
            return recursion(a.left, b.left) && recursion(a.right, b.left);
        } else {
            return recursion(a.left, b) || recursion(a.right, b);
        }
    }
