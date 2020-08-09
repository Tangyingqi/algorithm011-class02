class SolveNQueens {
    public List<List<String>> solveNQueens(int n) {

        List<List<String>> res = new ArrayList();
        if(n == 0){
            return res;
        }

        boolean[] used = new boolean[n];
        boolean[] dia1 = new boolean[2*n-1];
        boolean[] dia2 = new boolean[2*n-1];

        dfs(n,0,new ArrayList(),used,dia1,dia2,res);

        return res;

    }

    private void dfs(int n,int row,List<String> list,boolean[] used,boolean[] dia1,boolean[] dia2,List<List<String>> res){

        if(row == n){
            res.add(new ArrayList(list));
            return;
        }

        for(int col = 0;col <n;col++){

            if(used[col] || dia1[row + col] || dia2[row - col + n - 1]) continue;

            char[] c = new char[n];
            Arrays.fill(c,'.');
            c[col] = 'Q';
            String colString = String.valueOf(c);

            list.add(colString);
            used[col] = true;
            dia1[row + col] = true;
            dia2[row - col + n - 1] = true;

            dfs(n,row + 1,list,used,dia1,dia2,res);
            list.remove(list.size() - 1);
            used[col] = false;
            dia1[row + col] = false;
            dia2[row - col + n - 1] = false;

        }
    }
}