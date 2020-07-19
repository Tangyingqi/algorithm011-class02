
// https://leetcode-cn.com/problems/search-a-2d-matrix/
class SearchMatrix {
    public boolean searchMatrix(int[][] matrix, int target) {

        if(matrix.length == 0){
            return false;
        }

        int n = matrix.length;
        int m = matrix[0].length;
        int left = 0,right = m * n - 1;

        while(left <= right){
            int mid = left + (right - left) / 2;
            int res = matrix[mid / m][mid % m];
            if(res == target){
                return true;
            }
            if(res > target){
                right = mid - 1;
            }else{
                left = mid + 1;
            }
        }

        return false;

    }
}