
class Solution {
    public static List<Integer> topKFrequent(int[] nums, int k) {

        Map<Integer,Integer> map = new HashMap<>();
        for (int num : nums) {
            map.put(num,map.getOrDefault(num,0) + 1);
        }

        PriorityQueue<Map.Entry<Integer,Integer>> maxPQ = new PriorityQueue<>((o1, o2) -> o2.getValue() - o1.getValue());
        for (Map.Entry entry : map.entrySet()){
            maxPQ.offer(entry);
        }

        List<Integer> result = new ArrayList<>();
        while (result.size() < k){
            result.add(maxPQ.poll().getKey());
        }

        return result;
    }
}