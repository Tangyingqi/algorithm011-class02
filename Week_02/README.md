#### HashMap

HashMap 是一个键值对的数据结构，它的底层数据结构是一个数组，一个元素存入首先会计算它的 hash 值，然后存入对应的数组位置。当 hash 值相同，就会发生 hash 碰撞，在这个位置就会通过升维的方式，形成一个链表。

当行成链表后，查询就会退化成 O(N) 的，因为要遍历链表。

##### 寻址算法
在计算 hash 值时，HashMap 的算法是拿到 key 的 hashCode 跟与它右移 16 位后做一个异或运算。
```java
static final int hash(Object key) {
        int h;
        return (key == null) ? 0 : (h = key.hashCode()) ^ (h >>> 16);
    }
```

这样做的目的是让高 16 位和低 16 位都参与计算，相当于同时保留了高低 16 位的特征，从而减少 hash 冲突。

##### put 方法


```java
final V putVal(int hash, K key, V value, boolean onlyIfAbsent,
                   boolean evict) {
        Node<K,V>[] tab; Node<K,V> p; int n, i;
        // 当 table 为 null 时，通过调用 resize 方法创建数组，默认大小是 16
        if ((tab = table) == null || (n = tab.length) == 0)
            n = (tab = resize()).length;
        // 通过寻址算法找到这个元素的位置，如果是空的，新建一个 Node，传入值
        if ((p = tab[i = (n - 1) & hash]) == null)
            tab[i] = newNode(hash, key, value, null);
        else {
            Node<K,V> e; K k;
            // 通过比较发现要插入数据的 key 和这个位置的 key 相等，就覆盖掉原值
            if (p.hash == hash &&
                ((k = p.key) == key || (key != null && key.equals(k))))
                e = p;
            // 如果这个位置是一个红黑树，就向树里添加元素
            else if (p instanceof TreeNode)
                e = ((TreeNode<K,V>)p).putTreeVal(this, tab, hash, key, value);
            else {
            // 走到这里，说明这个位置是一个链表
                for (int binCount = 0; ; ++binCount) {
                    if ((e = p.next) == null) {
                        // 如果这个节点的下一个节点是 null 就新建一个节点，数据存放在这个节点上
                        p.next = newNode(hash, key, value, null);
                        // 判断如果 >= 7 就要进行树化，也就是由链表变为红黑树
                        if (binCount >= TREEIFY_THRESHOLD - 1) // -1 for 1st
                            treeifyBin(tab, hash);
                        break;
                    }
                    // 如果这个节点的 hash 值和 key 都相同，就放在这个节点上
                    if (e.hash == hash &&
                        ((k = e.key) == key || (key != null && key.equals(k))))
                        break;
                    p = e;
                }
            }
            // 传入的值进行赋值操作，返回老的值
            if (e != null) { // existing mapping for key
                V oldValue = e.value;
                if (!onlyIfAbsent || oldValue == null)
                    e.value = value;
                afterNodeAccess(e);
                return oldValue;
            }
        }
        ++modCount;
        // 如果 size 大于阈值，就要进行 resize 操作
        if (++size > threshold)
            resize();
        afterNodeInsertion(evict);
        return null;
    }
```

##### get 方法

```java
final Node<K,V> getNode(int hash, Object key) {
        Node<K,V>[] tab; Node<K,V> first, e; int n; K k;
        // 判断桶里有元素，并且找到第一个节点赋值给 first
        if ((tab = table) != null && (n = tab.length) > 0 &&
            (first = tab[(n - 1) & hash]) != null) {
            // 如果第一个节点就是我们要找的，直接返回
            if (first.hash == hash && // always check first node
                ((k = first.key) == key || (key != null && key.equals(k))))
                return first;
            // 下一个节点不为 null
            if ((e = first.next) != null) {
                // 如果这个节点是一个红黑树，就进树里面取数据
                if (first instanceof TreeNode)
                    return ((TreeNode<K,V>)first).getTreeNode(hash, key);
                // 循环遍历链表，找到目标数据
                do {
                    if (e.hash == hash &&
                        ((k = e.key) == key || (key != null && key.equals(k))))
                        return e;
                } while ((e = e.next) != null);
            }
        }
        return null;
    }
```


#### Tree

类比我们生活中的一些问题，比如下棋，不同的走法相当于不同的子节点，它会像一颗树一样扩散出去。

我们常用的二叉搜索树（BST-Binary Search Tree）,它的特点是左子树小于根节点，右子树大于根节点。这里要注意，是所有的左子树和右子树。因为 BST 有序的特性，那么查找就从 O(N) 变为了 O(logN)。

##### BST Add

添加操作，首先要找到这个节点的位置，类似于二分查找，首先与根节点进行比较，如果小于走左边，如果大于走右边。最终找到位置，加入该节点。

##### BST Remove

移除操作稍微复杂一些，如果要移除的节点没有子节点，就直接移除掉，父节点指向 null 就好了。如果该节点同时有左子树和右子树，那么就要找到跟这个节点的值想近，并且刚刚大于它的那个节点，也就是右子树里面最左边的节点，把这个节点提上来。


##### 树的遍历

树的遍历一般都是用递归实现，因为树这个结构不容易像其他结构那样循环遍历，而它的特点是子树扩散下去都是重复的，就很适合用递归来进行遍历。