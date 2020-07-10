package leetcode;

import java.util.*;

/**
 * @author tangyingqi
 * @date 2020/7/9
 * https://leetcode-cn.com/problems/permutations/
 */
public class Permute {

    List<List<Integer>> output = new LinkedList<>();

    //    public List<List<Integer>> permute(int[] nums) {
    //
    //        List<List<Integer>> res = new LinkedList<>();
    //
    //        List<Integer> output = new LinkedList<>();
    //
    //        for(int num : nums){
    //            output.add(num);
    //        }
    //
    //        int n = nums.length;
    //        backtrack(n,output,res,0);
    //        return res;
    //    }
    //
    //    private void backtrack(int n, List<Integer> output, List<List<Integer>> res, int first) {
    //
    //        if(first == n){
    //            System.out.println("get : " + output);
    //            res.add(new ArrayList<>(output));
    //        }
    //
    //        for(int i=first;i<n;i++){
    //            System.out.println("swap before : " + output + " " + first + " " + i);
    //            Collections.swap(output,first,i);
    //            backtrack(n,output,res,first+1);
    //            System.out.println("swap after : " + output + " " + first + " " + i);
    //            Collections.swap(output,first,i);
    //        }
    //
    //    }

    public List<List<Integer>> permute(int[] nums){

        List<Integer> list = new ArrayList<>();
        if(nums.length == 0){
            return output;
        }
        Set<Integer> used = new HashSet<>();
        dfs(nums,0,list,used);

        return output;
    }

    private void dfs(int[] nums,int depth,List<Integer> list,Set<Integer> used){
        if(nums.length == depth){
            output.add(new ArrayList<>(list));
            return;
        }

        for(int i=0;i<nums.length;i++){
            if(!used.contains(i)){

                list.add(nums[i]);
                used.add(i);
                dfs(nums,depth+1,list,used);

                list.remove(list.size() - 1);
                used.remove(i);
            }

        }
    }

}
