class ReversePairs {

    public int reversePairs(int[] nums) {

        if(nums == null || nums.length == 0){
            return 0;
        }

        return mergeSort(nums,0,nums.length - 1);

    }

    private int mergeSort(int[] nums,int left,int right){

        if(left >= right){
            return 0;
        }

        int mid = left + (right - left) / 2;

        int[] cache = new int[right - left + 1];

        int count = mergeSort(nums,left,mid) + mergeSort(nums,mid+1,right);

        int i = left,p = left,k = 0;

        for(int j = mid + 1;j<=right;j++,k++){

            while(i <= mid && nums[i] <= 2 * (long)nums[j]) i++;
            while(p <= mid && nums[p] < nums[j]) cache[k++] = nums[p++];
            cache[k] = nums[j];
            count += mid - i + 1;
        }
        while(p <= mid) cache[k++] = nums[p++];

        System.arraycopy(cache,0,nums,left,right - left + 1);

        return count;
    }
}