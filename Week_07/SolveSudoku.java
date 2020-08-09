class Solution {
    private char[][] board;

    public void solveSudoku(char[][] board) {

        this.board = board;
        int m = board.length;
        int n = board[0].length;

        boolean[][] rowUsed = new boolean[9][10];
        boolean[][] colUsed = new boolean[9][10];
        boolean[][][] boxUsed = new boolean[3][3][10];

        for(int i=0;i<9;i++){
            for(int j=0;j<9;j++){
                if(board[i][j] != '.'){
                    int num = board[i][j] - '0';
                    rowUsed[i][num] = true;
                    colUsed[j][num] = true;
                    boxUsed[i/3][j/3][num] = true;
                }
            }
        }

        dfs(0,0,rowUsed,colUsed,boxUsed);


    }

    private boolean dfs(int row,int col,boolean[][] rowUsed,boolean[][] colUsed,boolean[][][] boxUsed){

        if(col == board[0].length){
            col = 0;
            row += 1;
            if(row == board.length){
                return true;
            }
        }

        if(board[row][col] == '.'){

            for(int i=1;i<=9;i++){
                boolean canUsed = !(rowUsed[row][i] || colUsed[col][i] || boxUsed[row/3][col/3][i]);
                if(canUsed){
                    board[row][col] = (char)(i + '0');
                    rowUsed[row][i] = true;
                    colUsed[col][i] = true;
                    boxUsed[row/3][col/3][i] = true;
                    if(dfs(row,col+1,rowUsed,colUsed,boxUsed)){
                        return true;
                    }

                    board[row][col] = '.';
                    rowUsed[row][i] = false;
                    colUsed[col][i] = false;
                    boxUsed[row/3][col/3][i] = false;

                }
            }

        }else{
            return dfs(row,col+1,rowUsed,colUsed,boxUsed);
        }

        return false;

    }
}