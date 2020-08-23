class ReverseWordsThree {
    public String reverseWords(String s) {

        if(s == null || s.length() == 0){
            return "";
        }

        String[] stringArray = s.split(" ");

        StringBuilder sb = new StringBuilder();

        for(String string : stringArray){
            sb.append(new StringBuilder(string).reverse().toString()).append(" ");
        }

        return sb.toString().trim();

    }
}