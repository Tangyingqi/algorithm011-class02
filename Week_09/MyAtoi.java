class MyAtoi {
    public int myAtoi(String str) {

        if(str == null || str.length() == 0){
            return 0;
        }

        char[] ch = str.toCharArray();

        int index = 0;
        int len = ch.length;
        int sign = 1;
        int res = 0;

        while(index < len && ch[index] == ' ') index++;

        if(index == len) return 0;

        if(ch[index] == '+' || ch[index] == '-'){
            sign = ch[index] == '+' ? 1 : -1;
            index++;
        }

        while(index < len){
            int num = ch[index] - '0';

            if(num < 0 || num > 9){
                break;
            }

            if(Integer.MAX_VALUE / 10 < res || (Integer.MAX_VALUE / 10 == res && Integer.MAX_VALUE % 10 < num)){
                return sign == 1 ? Integer.MAX_VALUE : Integer.MIN_VALUE;
            }

            res = res * 10 + num;
            index++;
        }

        return res * sign;

    }
}