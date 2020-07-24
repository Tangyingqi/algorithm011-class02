import java.util.ArrayDeque;
import java.util.Deque;

/**
 * https://leetcode-cn.com/problems/trapping-rain-water/
 */
public class Trap {

    public static int trap(int[] height) {

        int len = height.length;
        if(len < 2){
            return 0;
        }

        int cur = 0,water = 0;
        Deque<Integer> stack = new ArrayDeque<>();
        while(cur < len){

            if(stack.isEmpty() || height[cur] <= height[stack.peekLast()]){
                stack.addLast(cur++);
            }else{
                int pre = stack.pollLast();
                if(!stack.isEmpty()){
                    int minHeight = Math.min(height[cur], height[stack.peekLast()]);
                    int width = cur - stack.peekLast() - 1;
                    water += (minHeight - height[pre]) * width;
                }
            }
        }

        return water;
    }

    public static void main(String[] args) {
        int[] height = {0,1,0,2,1,0,1,3,2,1,2,1};
        int res = trap(height);
        System.out.println(res);

    }

}
