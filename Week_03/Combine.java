
// https://leetcode-cn.com/problems/combinations/
public class Combine {

    List<List<Integer>> output = new LinkedList<>();

    private List<List<Integer>> combine(int n,int k){

        if(n == 0 || k ==0 || k > n){
            return output;
        }
        LinkedList<Integer> list = new LinkedList<>();
        backtrack(n,k,1,list);

        return output;
    }

    private void backtrack(int n, int k, int start, LinkedList<Integer> list) {

        if(k == list.size()){
            output.add(new LinkedList<>(list));
            return;
        }

        for(int i=start;i <= n-(k - list.size()) +1 ;i++){
            list.add(i);
            backtrack(n,k,i+1,list);
            list.removeLast();
        }
    }

}
