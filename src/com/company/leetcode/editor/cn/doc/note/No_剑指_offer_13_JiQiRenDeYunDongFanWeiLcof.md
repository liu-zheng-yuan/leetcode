#### 实质是“求多叉树的总节点个数” --> "由题目限制条件，构成一个多叉决策树" --> "后序遍历所有子树,再加上根节点的1"

# DFS 解法

          int[] rowDelta = new int[]{1,0,-1,0};
          int[] colDelta = new int[]{0,1,0,-1};
    public int movingCount(int m, int n, int k) {
        int[][] flag = new int[m][n];
        flag[0][0] = 1;
        return dfs(m, n, k,0,0,flag);
    }

          public int dfs(int m, int n, int k,int row,int col,int[][] flag) {

              int sum = 0;
              for (int i = 0; i < 4; i++) {
                  int nowRow = row + rowDelta[i];
                  int nowCol = col + colDelta[i];
                  if (isValid(m, n, nowRow, nowCol,flag) && digitalSum(nowRow) + digitalSum(nowCol) <= k) {
                      flag[nowRow][nowCol] = 1;
                      //这里容易迷惑的地方是写成Max，其实是从子节点搜索的节点数之和（加上当前节点）。
                      //因为比较多子树的“最值”比如高度，才是取Max。而这题实际上是”从根节点开始，遍历多叉树所有子节点“，当然应该求和，每次dfs返回的都是”以当前节点为根节点的多叉树的节点个数”，把所有多叉树的子树节点之和加起来再加上根节点就是总节点数
                      //由于选择路径flag不会撤销，决定了决策树不会有重复的节点。
                      sum += dfs(m, n, k, nowRow, nowCol, flag);
                      //这里不能套模板：撤销选择，不然的话从子节点的搜索就会重复统计经过的节点数
                      //flag[nowRow][nowCol] = 0;
                  }
              }
              return sum + 1;

          }

          private boolean isValid(int m, int n, int nowRow, int nowCol,int[][] flag) {
              return nowRow < m && nowCol < n && nowCol >= 0 && nowRow >= 0 && flag[nowRow][nowCol] == 0;
          }


          public int digitalSum(int num) {
              int ans = 0;
              while (num > 0) {
                  ans = ans + num % 10;
                  num = num / 10;
              }
              return ans;
          }

# DFS 套路

回溯问题 == 遍历多叉的决策树，有三个要点

* 路径：已经做过的选择
* 选择列表：当前可以做的选择
* 结束条件：达到决策树底层，无法再做选择的条件

不是所有的dfs都需要visited数组,比如遍历二叉树这样没有父指针不可能走回头路的结构,就不用visited

框架(找到所有解)：

      result = [] //保存全局 遍历到决策树叶子节点的可行路径 
      def backtrack(路径, 选择列表):
          if 满足结束条件:
          result.add(路径)// 一条可行路径
          return
        
          for 选择 in 选择列表:
            # 做选择
            排除不合法的选择
            将该选择从选择列表移除
            路径.add(选择)
            backtrack(路径, 选择列表)
            # 撤销选择
            路径.remove(选择)
            将该选择再加入选择列表

框架(找到存在解)：

    backtrack 返回 true/false
    遍历中 if(backtrack(路径,选择列表)) return true
    这样只要找到一个路径,后续的递归都会被阻断

对比多叉树遍历的框架:

    void traverse(TreeNode root) {
        for (TreeNode child : root.childern)
        // 前序遍历需要的操作 == dfs的做选择
        traverse(child);
        // 后序遍历需要的操作 == dfs的撤销选择
    }

# BFS

适合找最短路径(DFS需要完全遍历完一整个树之后才能确定哪个最短,时间复杂度高O N^N)
BFS空间复杂度高,最坏情况是满二叉树叶子节点数量N/2,也就是O N .(DFS 最坏空间复杂度是树的高度 logN,O logN)

框架:

    // 计算从起点 start 到终点 target 的最近距离
    int BFS(Node start, Node target) {
        Queue<Node> q; // 核心数据结构
        Set<Node> visited; // 避免走回头路
    
        q.offer(start); // 将起点加入队列
        visited.add(start);
        int step = 0; // 记录扩散的步数
    
        while (q not empty) {
            int sz = q.size();
            /* 将当前队列中的所有节点向四周扩散 */
            for (int i = 0; i < sz; i++) {
                Node cur = q.poll();
                /* 划重点：这里判断是否到达终点 */
                if (cur is target)
                    return step;
                /* 将 cur 的相邻节点加入队列 */
                for (Node x : cur.adj())
                    if (x not in visited) {
                        q.offer(x);
                        visited.add(x);
                    }
            }
            /* 划重点：更新步数在这里 */
            step++;
    }

}