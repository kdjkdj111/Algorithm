package practice.ch06;

import java.util.Scanner;

public class BinomialCoefficient {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.println("n k 입력> ");
        int n = sc.nextInt();
        int k = sc.nextInt();

        int result = binDynamic(n, k);

        System.out.println(result);
    }

    public static int binDynamic(int n, int k) {
        int[][] arr = new int[n+1][k+1];
        for (int i = 0; i <= n; i++) {
            for (int j = 0; j <= Math.min(k,i); j++) {
                if(i==j){
                    arr[i][j] = 1;
                }else if (j==0){
                    arr[i][j] = 1;
                }else{
                    arr[i][j] = arr[i-1][j-1] + arr[i-1][j];
                }
            }
        }
        return arr[n][k];
    }
}
