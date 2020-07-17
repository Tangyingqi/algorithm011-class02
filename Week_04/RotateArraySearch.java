https://leetcode-cn.com/problems/search-in-rotated-sorted-array/

public class RotateArraySearch {

    public static int search(int[] nums, int target) {

        if(nums == null){
            return -1;
        }

        int lo = 0,hi = nums.length - 1;
        while (lo < hi){
            int mid = lo + (hi - lo) / 2;
            if(nums[0] <= nums[mid] && (target > nums[mid] || target < nums[0])){
                lo = mid + 1;
            }else if(target > nums[mid] && target < nums[0]){
                lo = mid + 1;
            }else{
                hi = mid;
            }
        }

        return lo == hi && nums[lo] == target ? lo : -1;
    }

    public static void main(String[] args) {
        int[] nums = {1,3};
        int ans = search(nums,3);
        System.out.println(ans);
    }
}