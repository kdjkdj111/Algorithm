package practice.ch06;

public class MatrixMaximumRoute {
    public static void main(String[] args) {
        int[][] matrix = {{6,7,12,5},{5,3,11,18},{7,17,3,3},{8,10,14,9}};

        int max = routeSum(matrix);

        System.out.println(max);
    }

    static int routeSum(int[][] matrix) {
        int answer = 0;
        int[][] result=new int[matrix.length][matrix.length];
        result[0][0]= matrix[0][0];
        for (int i = 1; i < matrix.length; i++){
            for (int j = 0; j < matrix[i].length; j++){
                if(j==0){
                    result[i][j]=result[i-1][j]+ matrix[i][j];
                }else{
                    result[i][j]=Math.max(result[i-1][j]+ matrix[i][j],result[i][j-1]+ matrix[i][j]);
                }
            }
        }
        int n = matrix.length-1;
        answer= result[n][n];

        return answer;
    }
}
