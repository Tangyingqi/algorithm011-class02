class Anagram {

    public boolean isAnagram(String s, String t) {

        int len1 = s.length();
        int len2 = t.length();

        if(len1 != len2){
            return false;
        }

        int[] counter = new int[26];

        for(int i =0;i<len1;i++){
            counter[s.charAt(i) - 'a']++;
            counter[t.charAt(i) - 'a']--;
        }

        for(int count : counter){
            if(count > 0){
                return false;
            }
        }

        return true;

    }
}