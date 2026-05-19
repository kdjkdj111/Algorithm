package homework.hw3;//22212048 김동준
//Solution3 class는 하단에 있습니다.

import java.util.Scanner;

public class HW3 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        String input = sc.nextLine();

        input = input.replaceAll("\\s", "");

        input = input.substring(2, input.length() - 2);

        String[] rows = input.split("\\],\\[");

        int[][] triangle = new int[rows.length][];

        for (int i = 0; i < rows.length; i++) {
            String[] cols = rows[i].split(",");
            triangle[i] = new int[cols.length];
            for (int j = 0; j < cols.length; j++) {
                triangle[i][j] = Integer.parseInt(cols[j]);
            }
        }

        Solution3 sol = new Solution3();
        int result = sol.solution(triangle);

        System.out.println(result);
    }
}

class Solution3 {
     int solution(int[][] triangle) {
        int answer = 0;
        int[][] result=new int[triangle.length][triangle.length];
        result[0][0]=triangle[0][0];
        for (int i = 1; i < triangle.length; i++){
            for (int j = 0; j < triangle[i].length; j++){
                if(j==0){
                    result[i][j]=result[i-1][j]+triangle[i][j];
                }else{
                    result[i][j]=Math.max(result[i-1][j-1]+triangle[i][j],result[i-1][j]+triangle[i][j]);
                }
            }
        }

        answer=result[result.length-1][0];
        for(int i=1;i<result[result.length-1].length;i++){
            if(result[result.length-1][i]>answer){
                answer=result[result.length-1][i];
            }
        }
        return answer;
    }
}