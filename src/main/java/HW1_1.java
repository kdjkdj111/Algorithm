//22212048_김동준

import java.util.Arrays;

public class HW1 {
    public static void main(String[] args) {
        Solution1 S = new Solution1();
        int[] numbers = {5, 0, 2, 7};
        System.out.println("입력 = " + Arrays.toString(numbers));
        System.out.println("출력 = " + Arrays.toString(S.solution(numbers)));
    }
}
class Solution1 {
    public int[] solution(int[] numbers) {
        int N = numbers.length;
        int[] C = new int[201]; //더해서 만들어질 수 있는 수: 0 ~ 200.
        int size = 0; //unique한 값의 수

        for (int i = 0; i < N; i++) {
            for (int j = i+1; j < N; j++) {
                int insert = numbers[i] + numbers[j];
                if (C[insert] == 0) { C[insert]++; size ++; } //C에서 1인 값 = 더해서 만들어진 값
            }
        }

        int[] result = new int[size];
        int cur=0;

        for (int i = 0; i < C.length; i++) {
            if(C[i] != 0){
                result[cur++] = i;
            }
        }
        return result;
    }
}
