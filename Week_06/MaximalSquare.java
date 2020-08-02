class MaximalSquare {
    public int maximalSquare(char[][] matrix) {

        if(matrix == null) return 0;
        int m = matrix.length;
        if(m == 0) return 0;

        int n = matrix[0].length;

        int[][] dp = new int[m+1][n+1];
        int max = 0;
        for(int i=0;i<m;i++){
            for(int j=0;j<n;j++){
                if(matrix[i][j] == '1'){
                    dp[i+1][j+1] = Math.min(Math.min(dp[i][j+1],dp[i+1][j]),dp[i][j]) + 1;
                    max = Math.max(max,dp[i+1][j+1]);
                }
            }
        }
        return max * max;

    }
}