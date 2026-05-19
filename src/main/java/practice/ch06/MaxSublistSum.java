package practice.ch06;

public class MaxSublistSum {
    public static void main(String[] args) {
        double[] A = {2.3, 3.2, -4.5, 2.1, -5.3, 3.6, 4.1, -2.3, 3.5, -4.5};
        double max = maxSublist_Print(A,A.length);
        System.out.println(max);
    }

    static double maxSublist(double[] A, int n){
        double[] B;
        double max;

        B = new double[n];
        B[0] = A[0];
        max = B[0];

        for (int i = 1; i<n;i++){
            B[i] = (B[i-1] < 0) ? A[i] :  B[i-1] + A[i];
            if(B[i] > max){
                max = B[i];
            }
        }

        return max;
    }

    static double maxSublist_Print(double[] A, int n){
        double[] B;
        int[] count;
        double max; //합
        int maxIndex;

        B = new double[n];
        count = new int[n];

        B[0] = A[0];
        count[0] = 1;

        max = B[0];
        maxIndex = 0;

        for (int i = 1; i<n;i++){
            if(B[i-1]<0){
                B[i] = A[i];
                count[i] = 1;
            }else{
                B[i] = B[i-1] + A[i];
                count[i] = count[i-1] + 1;
            }
            if(B[i] > max){
                max = B[i];
                maxIndex = i;
            }
        }

        for(int i=0;i<count[maxIndex];i++){
            System.out.print(A[maxIndex-count[maxIndex]+1+i] + " ");
        }
        System.out.println();

        return max;
    }
}
