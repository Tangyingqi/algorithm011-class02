#### Trie 树

Trie 树也叫字典树，目的是为了查找字符，例如搜索引擎输入几个字母，自动补全成单词，猜测你要搜索的东西。Trie 树每个结点都不存完整的单词，从根节点到最后一个节点路径上的说有节点连接起来，就是对应的字符串。

在[单词搜索II](https://leetcode-cn.com/problems/word-search-ii/)问题中，用 Trie 树这种数据结构，可以更早的进行剪枝。因为 Trie 树可以搜索字典树中是否存在当前搜索字符为前缀的单词，如果不存在就提前终止。
所以这个算法的时间复杂度是，O(M(4*3^(L-1)))  M 为 board 总的格子数，第一次搜索上下左右四个方向，随后是 3 个方向，并且最坏的情况下要搜索单词长度 L - 1 次。

#### 并查集

并查集的出现是为了解决一些连通性的问题，一般用来解决组团配对问题。

它的基本操作有：
- find： 通过一个点，查找最上面的根节点 
- union： 连通两个点，把一个点的根节点的 parent 变为另一个点的根节点。
- isConnected: 是否连通

在 UnionFind 中，一般用一个 int[] 数组存储节点，创建 UnionFind 时会传入一个节点个数，在 UF 的构造函数中，会初始化这个 int[] 数组，把 parent 节点赋值为自己。在初始化的时候，所有节点的 parent 都是自己。

在 find 时，会判断，如果 parent 不是自己，就循环把 parent 的 parent 赋值给 parent，目的是让树高度变得更低，实现路径压缩。下次查找就会降低时间复杂度。

```java
while(parent[p] != p){
    parent[p] = parent[parent[p]];
    p = parent[p];
}

```

#### 双向 BFS 模板

```java

public int twoEndBFS(Node begin,Node end){
    
    Set<Node> beginSet = new HashSet<>();
    beginSet.add(begin);
    
    Set<Node> endSet = new HashSet<>();
    endSet.add(end);
    
    Set<Node> visited = new HashSet<>();
    visted.add(begin);
    
    int step = 1;
    
    while(!beginSet.isEmpty() && !endSet.isEmpty()){
        
        if(beginSet.size() > endSet.size()){
            Set<Node> temp = beginSet;
            beginSet = endSet;
            enSet = temp;
        }
        
        Set<Node> temp = new HashSet<>();
        for(Node node : beginSet){
            Node node = _generateNode();
            if(endSet.contains(node)){
                return step + 1;
            }
            if(!visited.contains(node)){
                temp.add(node);
            }
        }
        beginSet = temp;
        step++;
    }
    return 0;
}
```

上面是我总结的双向 BFS 模板，一般用双向 BFS 都是用于搜索两个点之间的距离问题，双向 BFS 的优势是两边一起进行搜索，在中间相遇时就找到最终的答案。在整个搜索路径上，比单向搜索查找的节点要少。

双向 BFS 用 Set 来代替 Queue 作为存储数据结构，因为要频繁的调用 contains ,set 的时间复杂度是 O(1) 的。判断当拓展节点的一方大于另一方，要进行交换，因为用小的 Set 进行扩展节点搜索空间会更小。


#### 平衡二叉树

在平衡二叉树中，应该重点掌握 2-3 Tree、AVL Tree、B-Tree、Red-black Tree

##### AVL Tree

AVL 树得名于它的发明者 Adelson-Velsky 和 Landis 。

它是一颗严格平衡的二叉树，怎么做到严格平衡呢？它每个节点都保存一份 balace factor {-1,0,1} 要平衡因子的绝对值不大于 1，也就是左子树的高度减去右子树的高度，或相反，高度差不能大于 1.

如果不满足平衡因子，就要进行旋转。

有四种旋转操作：

- 左旋
- 右旋
- 左右旋
- 右左旋

右右子树 -> 左旋
左左子树 -> 右旋
左右子树 -> 左右旋
右左子树 -> 右左旋

带子树的旋转，要挪动子树的位置。

AVL 的不足是每个节点都要存额外的信息，且调整次数频繁。

如果我们希望不要那么严格的平衡，减少调整次数。就出现了红黑树。

##### Red-black Tree

它是一种近似平衡的二叉树，确保任意节点的左子树和右子树的高度差不超过两倍。

红黑树有五条基本性质：

- 每个节点是红色或黑色
- 根节点是黑色
- 每个叶子节点是黑色
- 不能有相邻两个红色节点
- 从任一节点到其每个叶子的所有路径都包含相同数目的黑色节点

##### 比较 AVL 和 Red-black Tree

1.AVL 提供更快的查找，因为严格平衡
2.红黑树插入和删除性能更好，因为旋转的次数少
3.AVL 额外信息比较占空间，因为要存 int 类型，而红黑树只要 1 bit
4.红黑树更多用于 language libraries 如 Java 中的 HashMap。 AVL 更多用于 DB 存储上



