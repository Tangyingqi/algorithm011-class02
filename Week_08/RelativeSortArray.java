class RelativeSortArray {

    public int[] relativeSortArray(int[] arr1, int[] arr2) {

        int[] counter = new int[1001];

        for(int arr : arr1){
            counter[arr]++;
        }
        int i = 0;
        for(int n : arr2){
            while(counter[n]-- > 0){
                arr1[i++] = n;
            }
        }

        for(int j = 0;j<counter.length;j++){
            while(counter[j]-- > 0){
                arr1[i++] = j;
            }
        }

        return arr1;

    }
}