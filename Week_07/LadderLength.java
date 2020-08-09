class LadderLength {
    public int ladderLength(String beginWord, String endWord, List<String> wordList) {

        Set<String> wordSet = new HashSet(wordList);
        Set<String> startSet = new HashSet<>();
        Set<String> endSet = new HashSet<>();

        if (wordSet.size() == 0 || !wordSet.contains(endWord)) {
            return 0;
        }

        startSet.add(beginWord);
        endSet.add(endWord);

        Set<String> visited = new HashSet<>();
        visited.add(beginWord);

        int len = 1;

        while(!startSet.isEmpty() && !endSet.isEmpty()){

            if(startSet.size() > endSet.size()){
                Set<String> set = startSet;
                startSet = endSet;
                endSet = set;
            }
            Set<String> temp = new HashSet();
            for(String word : startSet){

                char[] charArray = word.toCharArray();
                for(int i=0;i<charArray.length;i++){

                    char origin = charArray[i];
                    for(char k = 'a' ;k <= 'z';k++){
                        if(origin == k) continue;

                        charArray[i] = k;
                        String newWord = String.valueOf(charArray);

                        if(endSet.contains(newWord)){
                            return len + 1;
                        }

                        if(!visited.contains(newWord) && wordSet.contains(newWord)){
                            temp.add(newWord);
                            visited.add(newWord);
                        }
                        charArray[i] = origin;
                    }
                }

            }
            startSet = temp;
            len++;
        }

        return 0;

    }
}