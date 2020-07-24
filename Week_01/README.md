#### LeetCode

在 LeetCode 上刷题，最大的误区是一遍刷完觉得自己掌握了。其实远远不够。老师强调“过遍数”，多刷几遍，其实就是刻意练习，对应于体育运动，先分解姿势，每个姿势练足够多的遍数形成肌肉记忆，才能更好的掌握一道题目。通过对多个题目的理解，才能对一些算法和数据结构掌握得更好。

刷题不要死磕，如果一个题目想几分钟觉得没有思路，就去看答案，学习他人更好的解题思路。这一步相当于主动反馈，看高手是怎样做的。很多算法是很难凭空想象出来的，所以我们不要执着于发明算法，而要去学习优秀的算法。

对于算法，题做的多了，就会有解题的感觉，很快找到思路。这是我们要达到的目标。因为我们的计算机无外乎几种方式做计算: if else loop recursion 。所以万变不离其宗，很多时候我们都要去找到重复的子问题，然后通过这几种方式找到解。

#### Array

数组会分配一段连续的存储空间，把数据存到这段空间里。它的大小是固定的，如果超出要进行扩容操作。

它的查询的时间复杂度是 O(1), 对查询是非常友好的，但是增加和删除的时间复杂度是 O(n)。因为增加和删除，它的后面的元素都要进行挪动。

#### Linked List

链表有几种类型：单链表、双向链表、循环链表。

单链表是指，有一个 next 指针指向下一个节点，双链表多了一个 previous 指针指向上一个节点。循环链表是一个环状结构。

链表是通过指针相连，所以它的增加和删除操作非常简单，变换一下指针就好了。时间复杂度是 O(1) 但是它的查找的时间复杂的是 O(n) 因为要遍历链表。

#### Sikp List

跳表的出现平衡了上面数组和链表的优势和劣势，插入/删除/搜索都是 O(log n) 复杂度。

跳表的实现思路是，在原始链表一维查找慢的情况下，变成多维，增加多级索引，这样就不用一个一个遍历，而是“跳”着遍历。


#### Stack

它是先入后出的，插入和删除都是 O(1) 查询是 O(n) 因为是无序的。

栈一般可以用来解决一些匹配问题，比如括号匹配问题。

#### Queue

它是先进先出的，类比现实中的排队。跟 Stack 一样，插入和删除都是 O(1) 查询是 O(n)。

Queue 提供了两组 API :
- add(e)-offer(e)
- remove-poll
- element-peek

它们的区别是错误处理，前者会抛出异常，后者会返回一个特殊值告诉调用者队列的情况。


#### Deque（Double-End Queue）

双端队列,两端都可以进出的 Queue。一般是推荐用 Deque 来替代上面 Stack 和 Queue 的使用，因为 Deque 相当于二者的结合，功能更强大。

它同时有 Stack 和 Queue 的方法

在 Java 中推荐使用 Deque（实现类 ArrayDeque）来替代 Stack 因为，Stack 继承的是 Vector 它的设计是不完善的，因为它是数组，有扩容问题，如果频繁扩容性能会比价低。
而 Deque 实现类都是链表，所以性能会更好。

#### Priority Queue

优先队列，它跟上面的数据结构不同，它可以取出的时候按照设定的优先级顺序取出。它底层的数据结构复杂多样，可能是 heap、红黑树、AVL 等。

它的插入和取出的时间复杂度都是 O(log n)。

#### Refactoring a Deque

```java
Deque<String> deque = new LinkedList<>();

        deque.addFirst("a");
        deque.addFirst("b");
        deque.addFirst("c");
        System.out.println(deque);

        String str = deque.getFirst();
        System.out.println(str);
        System.out.println(deque);

        while (deque.size() > 0){
            System.out.println(deque.removeFirst());
        }

        System.out.println(deque);
```


#### PriorityQueue Source Code Analysis


创建 PriorityQueue 有多种构造方法，其中可以传入 initialCapacity（如果不传入默认是 11）或 Comparator<? super E> comparator，用于比较元素。还可以传入已有的集合元素。

如果传入一个集合，会新建一个数组，然后把值 copy 进数组，然后进行堆化操作（heapify）

```java
  private void initFromCollection(Collection<? extends E> c) {
        initElementsFromCollection(c);
        heapify();
    }

private void heapify() {
        for (int i = (size >>> 1) - 1; i >= 0; i--)
            siftDown(i, (E) queue[i]);
    }
```

PriorityQueue 的底层数据结构是一个堆。

##### 添加

add(E e) 调用 offer 方法，所以两个方法是一样的，首先要判断如果原数组大小满了，就要进行扩容。调用 grow()方法。

在 grow() 方法中，如果老数组的 length < 64 就扩容一倍，反之扩容之前的一半大小。然后把原数组拷贝到新数组。


```java
 private void siftUp(int k, E x) {
        if (comparator != null)
            siftUpUsingComparator(k, x);
        else
            siftUpComparable(k, x);
    }
```
在 offer() 方法中，如果插入的不是一个空数组，要调用 siftUp() 方法，传入要插入的位置和值，如果 comparator 不为 null 就用 comparator 进行排序，否则用元素自身的比较器。

```java
private void siftUpUsingComparator(int k, E x) {
        while (k > 0) {
        // 相当于 k / 2 找到父节点的位置
            int parent = (k - 1) >>> 1;
            // 拿到父节点
            Object e = queue[parent];
            if (comparator.compare(x, (E) e) >= 0)
                break;
            queue[k] = e;
            k = parent;
        }
        queue[k] = x;
    }
```
这段逻辑是，先找到父节点，因为数据结构是堆，存储在数组中，所以每个节点的父节点即为该节点的 index/2 ，每个节点的子节点是 index * 2 ， index * 2 + 1。 

拿到父节点的值，与要插入的值进行比较，如果该节点大于父节点，直接插入传入的位置，也就是数组的最后。如果该节点小于父节点，就把父节点变为子节点（赋值给 queue[k]），循环直到找到该节点的父节点，然后赋值给对应的位置。


##### 取出

poll 方法，取出 queue[0] 元素，然后将 queue[size-1]插入到 queue[0] 中，然后下沉保持二叉堆特性。

```java
 private void siftDownUsingComparator(int k, E x) {
        int half = size >>> 1;
        // 循环一半的次数就可以了
        while (k < half) {
            int child = (k << 1) + 1;
            // 找到第一个子节点
            Object c = queue[child];
            // 找到右边的子节点
            int right = child + 1;
            // 比较两个子节点，找到更小的那个节点
            if (right < size &&
                comparator.compare((E) c, (E) queue[right]) > 0)
                c = queue[child = right];
                // 子节点中更小的那个跟传入的 x比较
                // 如果 x < c 说明传入的 x 比两个子节点都小，可以直接放在头部
                // 反之进入循环
            if (comparator.compare(x, (E) c) <= 0)
                break;
            queue[k] = c;
            k = child;
        }
        queue[k] = x;
    }
```

首先传入的值是 0 和 最后一个节点，先找到 0 这个位置的两个子节点，然后找到其中最小的子节点 c，用传入的最后一个节点 x 跟  c 作比较，如果 x < c 那么可以直接将 x 放在 0 的位置上。

如果 x > c ，那么把 c 放在 0 的位置，然后循环不断的找子节点，直到找到 x 的子节点，把 x 放入对应的位置。

