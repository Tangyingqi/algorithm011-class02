class LeastInterval {
    public int leastInterval(char[] tasks, int n) {

        if(tasks == null || tasks.length == 0){
            return 0;
        }

        int[] counter = new int[26];
        for(char task : tasks){
            counter[task - 'A']++;
        }
        Arrays.sort(counter);
        int i=25;
        while(i >= 0 && counter[i] == counter[25]) i--;

        return Math.max(tasks.length,(counter[25] - 1) * (n + 1) + 25 - i);


    }
}