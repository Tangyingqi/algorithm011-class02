class ValidSudoku {
    public boolean isValidSudoku(char[][] board) {

        if(board == null || board.length == 0){
            return false;
        }

        int rowCount = board.length;
        int colCount = board[0].length;

        int[][] row = new int[9][10];
        int[][] col = new int[9][10];
        int[][] box = new int[9][10];

        for(int i=0;i<rowCount;i++){
            for(int j = 0;j<colCount;j++){
                if(board[i][j] != '.'){
                    int num = board[i][j] - '0';
                    row[i][num]++;
                    col[j][num]++;
                    box[(i /3) * 3 + j / 3][num]++;

                    if(row[i][num] > 1 || col[j][num] > 1 || box[(i /3) * 3 + j / 3][num] > 1){
                        return false;
                    }
                }
            }
        }

        return true;

    }
}