class CountSubstrings {
    public int countSubstrings(String s) {

        if(s == null || s.length() == 0) return 0;

        int n = s.length();

        boolean[][] dp = new boolean[n][n];

        for(int i=0;i<n;i++) dp[i][i] = true;

        int count = n;

        for(int i = n - 1;i >=0 ;i--){
            for(int j = i+1;j < n; j++){

                if(s.charAt(i) == s.charAt(j)){
                    if(j-i == 1){
                        dp[i][j] = true;
                    }else{
                        dp[i][j] = dp[i+1][j-1];
                    }
                }else{
                    dp[i][j] = false;
                }

                if(dp[i][j]){
                    count++;
                }
            }
        }

        return count;

    }
}