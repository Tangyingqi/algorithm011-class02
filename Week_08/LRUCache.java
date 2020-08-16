class LRUCache {

    class LinkedNode{
        private LinkedNode next;
        private LinkedNode prev;
        private int key;
        private int value;
        public LinkedNode(int key,int value){
            this.key = key;
            this.value = value;
        }
        public LinkedNode(){}
    }

    private Map<Integer,LinkedNode> cache;
    private int size;
    private int capacity;
    private LinkedNode head;
    private LinkedNode tail;

    public LRUCache(int capacity) {
        cache = new HashMap(capacity);
        this.size = 0;
        this.capacity = capacity;
        head = new LinkedNode();
        tail = new LinkedNode();
        head.next = tail;
        tail.prev = head;
    }

    private LinkedNode removeTail(){
        LinkedNode tailNode = tail.prev;
        removeNode(tailNode);
        return tailNode;
    }

    private void removeNode(LinkedNode node){
        node.next.prev = node.prev;
        node.prev.next = node.next;
    }

    private void moveToHead(LinkedNode node){
        removeNode(node);
        addToHead(node);
    }

    private void addToHead(LinkedNode node){
        node.prev = head;
        node.next = head.next;
        head.next.prev = node;
        head.next = node;
    }

    public int get(int key) {

        LinkedNode node = cache.get(key);
        if(node == null){
            return -1;
        }

        moveToHead(node);
        return node.value;
    }

    public void put(int key, int value) {

        LinkedNode node = cache.get(key);
        if(node == null){
            node = new LinkedNode(key,value);
            cache.put(key,node);
            size++;
            addToHead(node);
            if(size > capacity){
                LinkedNode tailNode = removeTail();
                cache.remove(tailNode.key);
                size--;
            }
        }else{
            node.value = value;
            moveToHead(node);
        }
    }
}

