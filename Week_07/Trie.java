class Trie {

    class TrieNode {

        private TrieNode[] links;
        private boolean isEnd;

        public TrieNode(){
            links = new TrieNode[26];
        }

        public void put(char ch,TrieNode node){
            links[ch - 'a'] = node;
        }

        public TrieNode get(char ch){
            return links[ch - 'a'];
        }

        public boolean containsKey(char ch){
            return links[ch - 'a'] != null;
        }
        public void setEnd(){
            isEnd = true;
        }

        public boolean isEnd(){
            return isEnd;
        }

    }

    private TrieNode root;

    public Trie() {
        root = new TrieNode();
    }

    /** Inserts a word into the trie. */
    public void insert(String word) {
        TrieNode node = root;

        for(char ch : word.toCharArray()){
            if(!node.containsKey(ch)){
                node.put(ch,new TrieNode());
            }
            node = node.get(ch);
        }
        node.setEnd();
    }

    public TrieNode searchPrefix(String prefix){
        TrieNode node = root;
        for(char ch : prefix.toCharArray()){
            if(node.containsKey(ch)){
                node = node.get(ch);
            }else{
                return null;
            }
        }

        return node;

    }

    public boolean search(String word) {
        TrieNode node = searchPrefix(word);
        return node != null && node.isEnd();
    }

    public boolean startsWith(String prefix) {
        TrieNode node = searchPrefix(prefix);
        return node != null;
    }
}

