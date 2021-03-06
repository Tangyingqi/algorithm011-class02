#### 深度优先搜索&广度优先搜索

深度优先搜索用栈来实现，可以手动维护一个栈，或者用递归的形式，程序帮我们维护一个栈。

典型的我们遍历一颗二叉树用深度优先搜索是这样实现的

```java
private void dfs(TreeNode node,int level,List<List<Integer>> res){

    if(node == null){
        return;
    }

    if(res.size() <= level){
        res.add(new ArrayList());
    }

    res.get(level).add(node.val);

    if(node.left != null) dfs(node.left,level+1,res);
    if(node.right != null) dfs(node.right,level+1,res);
}
```
首先在递归程序中，要先写递归终止条件。然后加入这一层的元素，接着判断左右子树是否为空，下探到下一层遍历子树。我们需要一个 level 来确定下探到第几层，根据它来把我们的节点加入对应的层。程序执行起来，步骤是先遍历最左边，一直下探到节点的左子树为空，然后回到上一层，看是否有右子树，如果有加入容器，如果没有继续回到上一层。


对比深度优先搜索用栈来实现，广度优先搜索是用队列实现，它是一层一层扩散出去，扫描每一层，这种方式更符合我们人脑的工作方式。用队列这种先入先出的容器适合做这样的工作。

看一下广度优先搜索来实现一颗树的遍历

```java
public List<List<Integer>> levelOrder(Node root) {

    List<List<Integer>> result = new ArrayList<>();
        Queue<Node> queue = new LinkedList<>();

        if (root == null){
            return result;
        }

        queue.add(root);
        while(!queue.isEmpty()){
            List<Integer> level= new ArrayList<>();
            int size = queue.size();
            for (int i = 0; i< size;i++){
                Node node = queue.poll();
                level.add(node.val);
                queue.addAll(node.children);
            }

            result.add(level);
        }
        return result;
    }
}
```

这是一颗多叉树，与二叉树不同的是，在加入下面一层节点的时候，要加入它所有的 children 节点。用广度优先搜索就类似我们的 loop 循环遍历，首先创建一个队列，加入它的根节点，然后循环直到这个队列为空，在每一层我们都把节点从队列中依次取出放入一个容器，然后加入他的子孩子。

DFS 和 BFS 不只用于简单的遍历一颗树，或者说有时候问题不会是直接告诉我们要遍历一颗树，实际上，在很多时候，我们要稍微转化一下思路，很多问题比如括号生成问题，它的所有可能最后都会发散出去成为一颗树，那么问题的解也就是我们要遍历这颗状态树，这个时候就可以考虑用 DFS 或 BFS 来遍历找到我们要的问题的解。

#### 贪心算法

每一步选择中都采取最优的选择，期望达到全局最优的算法。贪心算法跟动态规划算法的不同在于，贪心做出选择是不能回退的。而动态规划会保留走过的路径，并根据以前的结果对当前的结果进行选择，有回退的功能。

贪心法在有些时候能简单快速的得到问题的解，但有些时候用贪心法达不到我们想要的效果。所以要在合适的时机选择用贪心法。比如硬币的例子，当硬币集合有 [20，10，5，1] 求最少几个硬币拼出 36 就可以用贪心法，先取最大的进行匹配。原因是硬币集合中都是整除关系。如果它们之间没有整除关系，比如 [10,9,1] 求拼出 18，这个时候如果用贪心就不对了。

那什么情况下能用贪心？

简单的说就是问题能分解成子问题，子问题的最优解最终能递推到问题的最优解。

贪心算法写起来简单并且复杂度肯定是最低的，因为每次只取它的最优解，不用去考虑其他解，也不用记录中间的解，不需要考虑回溯分治，所以贪心算法重要的是如何证明是能用贪心的。


#### 二分查找

能用二分查找的前提：

- 目标函数单调性（单调递增或递减）或者说是有序的
- 存在上下界
- 能够通过索引访问

二分查找写起来很简单，一般都是直接套用模板，写起来都差不多。但是细节是魔鬼，很多地方比如 while 里面是小于还是小于等于，mid 是 +1 还是直接赋值，最后返回是 left 还是 right。这些都需要注意。

还有一类二分查找是半有序的，也就是一个数组中间阶段进行翻转，这种要注意找到判断的边界。


##### 二分查找旋转数组无序位置

```java
public static int findHalfOrderPosition(int[] nums){

    if(nums == null){
        return -1;
    }
    // 无序数组的话，无序的位置肯定不在第 0 位，所以从 1 开始，避免数组越界
    int left = 1,right = nums.length - 1;
    
    while(left <= right){
        int mid = left + (right - left) / 2;
        if(nums[mid] < nums[mid - 1] && nums[mid] < nums[mid + 1]) {
            return mid;
        } else if (nums[mid] > nums[0] && nums[mid] > nums[right]) {
            left = mid + 1;
        } else {
            right = mid - 1;
        }
    }
    return -1;
}
```