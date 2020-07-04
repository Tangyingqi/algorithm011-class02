// https://leetcode-cn.com/problems/group-anagrams/
class Solution {

    public static List<List<String>> groupAnagrams(String[] strs) {

        int[] counter = new int[26];
        Map<String, List<String>> map = new HashMap<>();
        for (int i = 0; i < strs.length; i++) {
            Arrays.fill(counter, 0);
            char[] chars = strs[i].toCharArray();
            for (char c : chars) {
                counter[c - 'a']++;
            }

            StringBuilder stringBuilder = new StringBuilder();
            for (int count : counter) {
                stringBuilder.append(count);
            }
            String key = stringBuilder.toString();

            if (!map.containsKey(key)) {
                map.put(key, new ArrayList<>());
            }
            map.get(key).add(strs[i]);

        }
        return new ArrayList<>(map.values());
    }

}