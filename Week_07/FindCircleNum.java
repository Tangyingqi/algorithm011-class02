class FindCircleNum {

    class UnionFind{
        private int count;
        private int[] parent;

        public UnionFind(int n){
            count = n;
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
            count--;
        }

        public int getCount(){
            return count;
        }


    }
    public int findCircleNum(int[][] M) {

        if(M == null || M.length == 0) return 0;

        int n = M.length;
        UnionFind uf = new UnionFind(n);

        for(int i = 0;i<n;i++){
            for(int j=0;j<i;j++){
                if(M[i][j] == 1){
                    uf.union(i,j);
                }
            }
        }

        return uf.getCount();


    }
}