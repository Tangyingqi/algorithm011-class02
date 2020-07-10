
//https://leetcode-cn.com/problems/permutations-ii/
public class PermuteUnique {

    private List<List<Integer>> permuteUnique(int[] nums){

        List<List<Integer>> res = new ArrayList<>();
        int length = nums.length;
        List<Integer> list = new ArrayList<>(length);
        if(length == 0){
            return res;
        }
        boolean[] used = new boolean[length];
        dfs(nums,list,res,used);

        return res;
    }

    private void dfs(int[] nums, List<Integer> list, List<List<Integer>> res, boolean[] used) {

        if(list.size() == nums.length){
            res.add(new ArrayList<>(list));
            return;
        }

        for(int i=0;i<nums.length;i++){
            if(used[i]){
                continue;
            }

            if(i > 0 && nums[i] == nums[i-1] && !used[i-1]){
                continue;
            }

            list.add(nums[i]);
            used[i] = true;

            dfs(nums,list,res,used);

            list.remove(list.size() - 1);
            used[i] = false;

        }
    }

}