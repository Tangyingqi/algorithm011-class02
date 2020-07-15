/**
 * https://leetcode-cn.com/problems/number-of-islands/
 */
public class NumIslands {

    int n,m;

    public int numIslands(char[][] grid) {
        int count = 0;
        n = grid.length;
        if(n == 0){
            return 0;
        }

        m = grid[0].length;
        for(int i = 0;i<n;i++){
            for(int j = 0;j<m;j++){
                if(grid[i][j] == '1'){
                    dfs(grid,i,j);
                    count++;
                }
            }
        }

        return count;
    }

    private void dfs(char[][] grid,int i,int j){
        if(i < 0 || j < 0 || i >= n || j >= m || grid[i][j] != '1'){
            return;
        }

        grid[i][j] = '0';
        dfs(grid,i+1,j);
        dfs(grid,i-1,j);
        dfs(grid,i,j+1);
        dfs(grid,i,j-1);
    }

    public static void main(String[] args) {
        char[][] grid = {{'1', '1', '1', '1', '0'}, {'1', '1', '0', '1', '0'}, {'1', '1', '0', '0', '0'}, {'0', '0', '0', '0', '0'}};
        NumIslands numIslands = new NumIslands();
        int count = numIslands.numIslands(grid);
        System.out.println(count);
    }
}
