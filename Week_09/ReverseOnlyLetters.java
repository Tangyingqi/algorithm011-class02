class ReverseOnlyLetters {
    public String reverseOnlyLetters(String S) {

        if(S == null || S.length() == 0){
            return "";
        }

        int left = 0,right = S.length() - 1;

        StringBuilder sb = new StringBuilder(S);

        while(left < right){

            while(left < right && !Character.isLetter(S.charAt(left))) left++;
            while(left < right && !Character.isLetter(S.charAt(right))) right--;

            sb.setCharAt(left,S.charAt(right));
            sb.setCharAt(right,S.charAt(left));

            left++;
            right--;

        }

        return sb.toString();


    }
}