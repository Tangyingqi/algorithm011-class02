#### 位运算

##### XOR 异或

异或可以理解为不进位加法，相同的位为 0，不同的位为 1 。

- x^0 = x
- x^1s = ~x
- x^(~x) = 1s
- x^x = 0


1s 为全 1

所有正整数按位取反之后是其本身 +1 后的负数，如 ~3 = -4
所有负整数按位取反之后是其本身 +1 的绝对值，如 ~-3 = 2

##### 位运算常用操作

- 将 x 最右边 n 位清零： x & (~0 << n)
- 获取 x 的第 n 位值 ： (x >> n) & 1
- 获取 x 的第 n 位幂值 :  x & (1 << n)
- 仅将第 n 位置为 0 : x & (~1 << n)
- 将 x 最高位至第 n 位（含）清零： x & ((1 << n) - 1)
- 清零最低位的 1 ： x & (x - 1)
- 得到最低位的 1 ： x & -x



#### 布隆过滤器

布隆过滤器是由一个很长的二进制数组和一系列随机映射函数组成，它的主要作用是检查一个元素是否在集合中。

优点是能非常快速的查询，并且占用空间小。
缺点是有一定的误识别率和删除困难。

比如一个节点 A 经过 3 个随机映射函数映射到数组 1 和 10 的位置，会把这两个位置的值置为 1，节点 B 经过函数映射到 2 和 8 的位置。此时 1、2、8、10 位置都是 1，检查节点 C，经过映射函数映射到数组 1 和 8 这两个位置恰巧都是 1 但是 C 并不存在，这时就出现了错误识别。

但是布隆过滤器一般用于最外层缓存，用于快速过滤，这一层是为了挡住大量的无效的数据，就算漏进去少量的无效数据，下一层也能继续检查。所以，布隆过滤器能极大的减缓下层的压力。

应用：比特币网络、分布式系统、Redis 缓存、垃圾邮件评论过滤。

#### LRU Cache

Least Recently Used 最近最少使用的，它是一种淘汰策略，当数据的 size 超过了 capacity 就要淘汰掉那些最近最少使用的数据。

LRU 它的底层数据结构是一个 HashMap + 双向链表。

HashMap 可以快速定位查找的元素，实现查找达到 O(1) 的时间复杂度，双向链表的作用是保存一个存储的顺序，

为什么使用双向链表，使用单链表行吗？不行，因为在我们添加元素和获取元素的时候，都要操作链表中的节点，比如添加元素发现 size 超过了 capacity 就要删除末尾的节点，如果是单链表就很麻烦，要遍历一遍链表。在单链表中，要找到某个节点的位置进行操作都要遍历整个链表，才能找到它的前后节点进行操作，增加时间复杂度。而在双链表中，每个节点都知道它的前后节点，要删除这个节点就非常方便。

#### 实现 API

**put(int key,int value)** : 判断 map 中是否存在该 key 的数据，如果不存在，新建一个 node 加入 map，并且将新建 node 添加到链表的头部，并且要判断 size 如果大于 capacity 要删除最后一个节点。如果存在，更新节点的值，并且把该节点挪到链表的头部。

**get(int key)** :  判断 map 中是否存在，如果不存在返回 -1 如果存在，更新节点都到头结点，然后返回节点的值。


在实现时，可以增加两个虚拟节点 head 和 tail ，方便操作，并且减少很多判断。

```java
public class LRUCache {

    class LinkedNode{

        private int key;
        private int value;
        private LinkedNode next;
        private LinkedNode prev;

        public LinkedNode(int key,int value){
            this.key = key;
            this.value = value;
        }

        public LinkedNode(){}
    }

    private Map<Integer,LinkedNode> cache;
    private LinkedNode head;
    private LinkedNode tail;
    private int size;
    private int capacity;

    public LRUCache(int capacity){
        cache = new HashMap<>(capacity);
        head = new LinkedNode();
        tail = new LinkedNode();
        head.next = tail;
        tail.prev = head;
        this.size = 0;
        this.capacity = capacity;

    }

    public void put(int key,int value){
        LinkedNode node = cache.get(key);
        if(node == null){
            node = new LinkedNode(key,value);
            cache.put(key,node);
            addToHead(node);
            size++;
            if(size > capacity){
                LinkedNode tail = removeTail();
                cache.remove(tail.key);
                size--;
            }
        }else{
            node.value = value;
            moveToHead(node);
        }
    }

    public int get(int key){
        LinkedNode node = cache.get(key);
        if(node == null){
            return -1;
        }

        moveToHead(node);
        return node.value;

    }

    private LinkedNode removeTail() {
        LinkedNode node = tail.prev;
        removeNode(node);
        return node;
    }

    private void moveToHead(LinkedNode node) {
        removeNode(node);
        addToHead(node);
    }

    private void removeNode(LinkedNode node) {
        node.prev.next = node.next;
        node.next.prev = node.prev;
    }

    private void addToHead(LinkedNode node) {
        node.prev = head;
        node.next = head.next;
        head.next.prev = node;
        head.next = node;
    }


}

```

#### 排序算法

排序可以分为两大类，比较类排序，非比较类排序。

比较类包括：冒泡排序、快速排序、插入排序、希尔排序、选择排序、堆排序、归并排序

非比较类包括：计数排序、桶排序、基数排序

比较类的意思是，可以传 comparator 进去，然后就可以比较任意对象。它的时间复杂度无法突破 O(nlogn)。非比较类相反，不能比较对象，一般只能比较正整数。所以用得比较少。

比较类排序中，冒泡排序、选择排序、插入排序都是比较慢的，因为要嵌套循环遍历交换，时间复杂度是 O(n^2)。

- 选择排序：每次找最小值，然后放到数组的起始位置
- 插入排序：从前向后逐步构建有序序列，对于未排序数据，在已排序的序列中从后向前扫描，找到相应位置插入。跟玩扑克牌时的排序类似。
- 冒泡排序：每次查看相邻元素，如果逆序，交换。

选择排序和冒泡排序可以认为是相反的操作，选择是每次找到最小值放在最前面，冒泡是每次遍历完一次，最大值放在最后面。

**选择排序代码：**
```java
 public static void selectionSort(int[] arr){

        int len = arr.length;

        for(int i = 0;i < len - 1; i++){
            int minIndex = i;
            for(int j = i + 1;j < len;j++){
                if(arr[j] < arr[minIndex]){
                    minIndex = j;
                }
            }
            int temp = arr[i];
            arr[i] = arr[minIndex];
            arr[minIndex] = temp;
        }
    }
```

**插入排序代码**
```java
 public static void insertSort(int[] arr){
        int key;
        int j;
        for (int i = 1; i < arr.length; i++) {
            key = arr[i];
            j = i - 1;
            while (key < arr[j]) {
                arr[j + 1] = arr[j];
                if ((j--) == 0) {
                    break;
                }
            }
            arr[j + 1] = key;
        }
    }
```

**冒泡排序代码**
```java
 public static void bubbleSort(int[] arr){

        int len = arr.length;

        for(int i=0;i<len-1;i++){
            for(int j=0;j<len-1-i;j++){
                if(arr[j] > arr[j+1]) {
                    int temp = arr[j+1];
                    arr[j+1] = arr[j];
                    arr[j] = temp;
                }
            }
        }
    }
```

##### 快速排序

快排是比较高级的算法，用的也是最多的，它是时间复杂度是 O(nlogn)

它的原理是，每次找到一个 pivot 也就是一个数组中的位置，pivot 左边的数据一定是小于它的，右边的数据都大于它。然后依次对 pivot 左右两边的继续快排，最终使得整个数组有序。

所以快排的关键是找到 pivot ,也就是 partition 操作。

```java
 public static void quickSort(int[] array,int begin,int end){

      if(end <= begin){
          return;
      }

      int pivot = partition(array,begin,end);
      quickSort(array,begin,pivot-1);
      quickSort(array,pivot+1,end);

    }

    private static int partition(int[] array, int begin, int end) {

        int pivot = end,counter = begin;
        for(int i = begin; i < end; i++){
            if(array[i] < array[pivot]){
                int temp = array[i];
                array[i] = array[counter];
                array[counter] = temp;
                counter++;
            }
        }

        int temp = array[counter];
        array[counter] = array[pivot];
        array[pivot] = temp;

        return counter;


    }
```

这段代码的 partition，首先定义一个 pivot 在末尾位置，counter 在开始的位置。counter 最终到达的位置，其实就是我们进行 partition 要得到的 pivot 的位置。

遍历数组，碰到元素小于 pivot 位置的值时，交换这个元素和 counter 位置的元素，这一步是保证，counter 左边的值都小于 pivot。

这块一开始有点难懂，可以想象，当我们进入 if 里面，也就是遇到了比 pivot 小的元素，就交换，一开始可能要经历自己跟自己交换。随后当后面的元素都大于 pivot ，就不进入 if 判断，counter 停留在大于 pivot 元素的位置。再次遇到小于 pivot 的元素，交换，也就是把小于 pivot 的元素挪过去了，然后 counter++，依然保持 counter 左边的元素都是小于 pivot 的。

遍历完整个数组，交换 pivot 和 counter 的值。


#### 归并排序

归并排序可以很容易看出分治的思想，先找到中间值，然后递归，最后进行 merge 操作，在 merge 操作里面相当于合并两个数组，把小的值放前面。

```java
 public static void mergeSort(int[] array,int left,int right){

        if(right <= left) return;

        int mid = (left + right) >> 1;

        mergeSort(array,left,mid);
        mergeSort(array,mid+1,right);
        merge(array,left,mid,right);
    }

    private static void merge(int[] array, int left, int mid, int right) {

        int[] temp = new int[right - left + 1];
        int i = left,j = mid + 1,k = 0;

        while(i <= mid && j <= right){
            temp[k++] = array[i] <= array[j] ? array[i++] : array[j++];
        }

        while(i <= mid) temp[k++] = array[i++];
        while(j <= right) temp[k++] = array[j++];

        for(int p = 0;p < temp.length;p++){
            array[left + p] = temp[p];
        }
    }

```