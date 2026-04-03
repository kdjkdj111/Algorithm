//22212048_김동준
package HomeWork;


import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

public class HW1_1_1 {
    public static void main(String[] args) {
        Solution1_2 S = new Solution1_2();
        int[] numbers = {2,1,3,4,1};
        System.out.println("입력 = " + Arrays.toString(numbers));
        System.out.println("출력 = " + Arrays.toString(S.solution(numbers)));
    }
}
class Solution1_2 {
    public int[] solution(int[] numbers) {

        for (int i = 1; i < numbers.length; i++) {
            for (int j = i; j>0&&numbers[j]<numbers[j-1]; j--) {
                int temp = numbers[j];
                numbers[j] = numbers[j-1];
                numbers[j-1] = temp;
            }
        }

        int[] C =  new int[numbers[numbers.length-1]+numbers[numbers.length-2]+1];
        int unique=0;

        for(int i =0;i<numbers.length;i++){
            for(int j = i+1;j<numbers.length;j++){
                if(C[numbers[j]+numbers[i]] ==0 ){
                    C[numbers[j]+numbers[i]]++;
                    unique++;
                }
            }
        }

        int[] result = new int[unique];
        int cur =0;

        for(int i =0;i<C.length;i++){
            if(C[i]!=0){
                result[cur++]=i;
            }
        }

        return result;
    }


}
