package leetcode;

import java.util.*;

/**
 *
 * https://leetcode-cn.com/problems/word-ladder/
 */
public class LadderLength {

    public int ladderLength(String beginWord, String endWord, List<String> wordList) {

        Set<String> wordSet = new HashSet<>(wordList);
        if(wordSet.size() == 0 || !wordSet.contains(endWord)){
            return 0;
        }

        wordSet.remove(beginWord);

        Queue<String> queue = new LinkedList<>();
        queue.add(beginWord);

        Set<String> visited = new HashSet<>();
        visited.add(beginWord);

        int step = 1;

        while(!queue.isEmpty()){

            int size = queue.size();
            for(int i = 0;i<size;i++){
                String curWord = queue.poll();
                char[] charArray = curWord.toCharArray();
                for(int j=0;j<charArray.length;j++){
                    char originChar = charArray[j];

                    for(char k = 'a' ; k<= 'z';k++){
                        if(originChar == k){
                            continue;
                        }

                        charArray[j] = k;
                        String newWord = String.valueOf(charArray);
                        if(wordSet.contains(newWord)){

                            if(newWord.equals(endWord)){
                                return step +1;
                            }

                            if(!visited.contains(newWord)){
                                queue.add(newWord);
                                visited.add(newWord);
                            }
                        }
                    }
                    charArray[j] = originChar;
                }

            }
            step++;
        }

        return 0;

    }

    // 双向队列
    public int ladderLength2(String beginWord, String endWord, List<String> wordList){
        Set<String> beginSet = new HashSet<>();
        Set<String> endSet = new HashSet<>();

        int len = 1;
        int strLen = beginWord.length();
        Set<String> visited = new HashSet<>();

        beginSet.add(beginWord);
        endSet.add(endWord);

        while(!beginSet.isEmpty() && !endSet.isEmpty()){

            if(beginSet.size() > endSet.size()){
                Set<String> set = beginSet;
                beginSet = endSet;
                endSet = set;
            }

            Set<String> temp = new HashSet<String>();
            for (String word : beginSet) {
                char[] chs = word.toCharArray();

                for (int i = 0; i < chs.length; i++) {
                    for (char c = 'a'; c <= 'z'; c++) {
                        char old = chs[i];
                        chs[i] = c;
                        String target = String.valueOf(chs);

                        if (endSet.contains(target)) {
                            return len + 1;
                        }

                        if (!visited.contains(target) && wordList.contains(target)) {
                            temp.add(target);
                            visited.add(target);
                        }
                        chs[i] = old;
                    }
                }
            }

            beginSet = temp;
            len++;

        }
        return 0;
    }

    public static void main(String[] args) {
        String beginWord = "ymain";
        String endWord = "oecij";
        List<String> wordList = new ArrayList<>();
        String[] wordListArray = new String[]{"ymann","yycrj","oecij","ymcnj","yzcrj","yycij","xecij","yecij","ymanj","yzcnj","ymain"};
        Collections.addAll(wordList, wordListArray);
        LadderLength solution = new LadderLength();
        int res = solution.ladderLength2(beginWord, endWord, wordList);
        System.out.println(res);
    }

}
