class ReverseStr {
    public String reverseStr(String s, int k) {

        if(s.length() == 0){
            return "";
        }
        char[] ch = s.toCharArray();
        int len = ch.length;
        for(int i=0;i<s.length();i += 2*k){

            int left = i,right = i + k - 1 >= len ? len - 1 : i + k - 1;

            while(left < right){
                char temp = ch[left];
                ch[left] = ch[right];
                ch[right] = temp;
                left++;
                right--;
            }
        }

        return new String(ch);

    }
}