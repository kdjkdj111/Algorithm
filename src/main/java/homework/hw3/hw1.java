package homework.hw3;//22212048 김동준
//Solution3 class는 하단에 있습니다.

import java.util.Arrays;
import java.util.Scanner;

public class HW1{
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String input = sc.nextLine();
        input = input.substring(2, input.length() - 2);
        String[] rows = input.split("],\\[");

        int N = rows.length;
        int[][] arr = new int[N][N];

        for (int i = 0; i < N; i++) {
            String[] cols = rows[i].split(",");
            for (int j = 0; j < N; j++) {
                arr[i][j] = Integer.parseInt(cols[j]);
            }
        }

        Solution1 sol = new Solution1();
        int[] result = sol.solution(arr);

        System.out.println(Arrays.toString(result));
    }
}


class Solution1 {
    public int[] solution(int[][] arr) {
        int[] answer = new int[2];
        int N=arr.length;

        comp(arr,0,0,N,answer);

        return answer;
    }

    void comp(int[][] S, int row, int col, int N, int[] answer) {

        if(N==1){
            answer[S[row][col]]++;
            return;
        }

        if(check(S,row,col,N)){
           if(S[row][col]==0) {
               answer[0]++;
           }else{
               answer[1]++;
           }
        }else{
            int next = N/2;
            comp(S,row,col,next,answer);
            comp(S,row,col+next,next,answer);
            comp(S,row+next,col,next,answer);
            comp(S,row+next,col+next,next,answer);
        }
    }

    boolean check(int[][] S, int row, int col, int N) {
        int check = S[row][col];
        for (int i = row; i < row + N; i++) {
            for (int j = col; j < col + N; j++) {
                if (S[i][j] != check) {
                    return false;
                }
            }
        }
        return true;
    }
}
