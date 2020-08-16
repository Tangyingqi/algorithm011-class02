public class ReverseBits {

    public int reverseBits(int n) {

        int res = 0;

        for(int i=31;n != 0; n = n >>> 1,i--){
            res += (n & 1) << i;
        }

        return res;

    }
}