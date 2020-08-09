class EmbraceArea {

    class UnionFind{

        private int[] parent;

        public UnionFind(int n){
            parent = new int[n];
            for(int i=0;i<n;i++){
                parent[i] = i;
            }
        }

        public int find(int p){

            while(parent[p] != p){
                parent[p] = parent[parent[p]];
                p = parent[p];
            }

            return p;
        }

        public void union(int p,int q){

            int rootP = find(p);
            int rootQ = find(q);
            if(rootP == rootQ) return;
            parent[rootP] = rootQ;

        }

        public boolean isConnection(int p,int q){
            return find(p) == find(q);
        }

    }
    private int row;
    private int col;
    private int[][] directions = new int[][]{{1,0},{-1,0},{0,1},{0,-1}};

    public void solve(char[][] board) {

        if(board == null || board.length == 0) return;

        row = board.length;
        col = board[0].length;
        UnionFind uf = new UnionFind(row * col + 1);
        int dummyNode = row * col;

        for(int i=0;i<row;i++){
            for(int j=0;j<col;j++){
                if(board[i][j] == 'O'){
                    if(i ==0 || j ==0 || i == row - 1 || j == col - 1){
                        uf.union(getNode(i,j),dummyNode);
                    }else{
                        for(int k = 0;k<4;k++){
                            int newX = i + directions[k][0];
                            int newY = j + directions[k][1];
                            if(newX >= 0 && newY >=0 && newX <= row && newY <= col){
                                if(board[newX][newY] == 'O'){
                                    uf.union(getNode(i,j),getNode(newX,newY));
                                }
                            }
                        }
                    }
                }
            }
        }

        for(int i = 0;i< row;i++){
            for(int j = 0;j<col;j++){
                if(board[i][j] == 'O' && !uf.isConnection(getNode(i,j),dummyNode)){
                    board[i][j] = 'X';
                }
            }
        }

    }

    private int getNode(int i,int j){
        return i * col + j;
    }
}