class Solution {

    class TrieNode{

        private Map<Character,TrieNode> map;
        private boolean isEnd;

        public TrieNode(){
            map = new HashMap<>();
        }

        public void put(char ch,TrieNode node){
            map.put(ch,node);
        }

        public TrieNode get(char ch){
            return map.get(ch);
        }

        public boolean containsKey(char ch){
            return map.containsKey(ch);
        }

        public void setEnd(){
            isEnd = true;
        }

        public boolean isEnd(){
            return isEnd;
        }

    }
    private TrieNode root;
    private char[][] board;
    private int rowCount;
    private int colCount;
    private static final int[][] directions = new int[][]{{1,0},{-1,0},{0,1},{0,-1}};

    public void putWord(String word){

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

    public boolean search(String word){
        TrieNode node = searchPrefix(word);
        return node != null && node.isEnd();
    }

    public boolean startWith(String prefix){
        TrieNode node = searchPrefix(prefix);
        return node != null;
    }

    public List<String> findWords(char[][] board, String[] words) {

        root = new TrieNode();
        this.board = board;
        rowCount = board.length;
        colCount = board[0].length;
        for(String word: words){
            putWord(word);
        }
        StringBuilder sb = new StringBuilder();
        boolean[][] used = new boolean[rowCount][colCount];
        Set<String> res = new HashSet();

        for(int i=0;i<rowCount;i++){
            for(int j=0;j<colCount;j++){
                findHelper(i,j,sb,used,res);
            }
        }


        return new ArrayList(res);
    }

    private void findHelper(int i,int j,StringBuilder sb,boolean[][] used,Set<String> res){

        sb.append(board[i][j]);
        String word = sb.toString();

        if(used[i][j] || !startWith(word)){
            sb.deleteCharAt(sb.length() - 1);
            return;
        }

        used[i][j] = true;
        if(search(word)){
            res.add(word);

            if(!startWith(word)){
                used[i][j] = false;
                sb.deleteCharAt(sb.length() - 1);
                return ;
            }
        }

        for(int k=0;k<4;k++){
            int newX = i + directions[k][0];
            int newY = j + directions[k][1];

            if(newX >= 0 && newY >= 0 && newX < rowCount && newY < colCount){
                findHelper(newX,newY,sb,used,res);
            }

        }

        used[i][j] = false;
        sb.deleteCharAt(sb.length() - 1);

    }
}