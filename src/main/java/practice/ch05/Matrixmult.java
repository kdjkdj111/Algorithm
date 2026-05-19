package practice.ch05;

import java.util.Scanner;


public class Matrixmult {
    static final int SIZE = 512;
    public static void main(String[] args) {
        int n = SIZE;
        int[][] A = new int[n][n];
        int[][] B = new int[n][n];
        int[][] C1 = new int[n][n];
        int[][] C2 = new int[n][n];

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                A[i][j] = (int)(Math.random() * 10) + 1;
                B[i][j] = (int)(Math.random() * 10) + 1;
            }
        }

        long startTime1 = System.nanoTime();
        matrixmult(n, A, B, C1);
        long endTime1 = System.nanoTime();

        long startTime2 = System.nanoTime();
        strassen(n, A, B, C2);
        long endTime2 = System.nanoTime();

//        printMatrix(C1);
//        printMatrix(C2);

        System.out.println("일반 곱셈 걸린 시간 : " + (endTime1 - startTime1) + " ns");
        System.out.println("스트라센 걸린 시간  : " + (endTime2 - startTime2) + " ns");
    }

    static void printMatrix(int[][] matrix) {
        int n = matrix.length;

        System.out.println("-------------------------");
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                System.out.printf("%4d ", matrix[i][j]);
            }
            System.out.println();
        }
        System.out.println("-------------------------");
    }

    static void matrixmult(int n, int[][] A, int[][] B, int[][] C) {
        int i,j,k;
        for (i = 0; i < n; i++) {
            for (j = 0; j < n; j++) {
                C[i][j] = 0;
                for(k = 0; k < n; k++) {
                    C[i][j] = C[i][j] + A[i][k] * B[k][j];
                }
            }
        }
    }

    static void  strassen(int n, int[][] A, int[][] B, int[][] C) {
        if(n <= 64){
            matrixmult(n, A, B, C);
        }else{
            int size = n/2;
            int[][] A11 = split(A,0,0);
            int[][] A12 = split(A,0,size);
            int[][] A21 = split(A,size,0);
            int[][] A22 = split(A,size,size);

            int[][] B11 = split(B,0,0);
            int[][] B12 = split(B,0,size);
            int[][] B21 = split(B,size,0);
            int[][] B22 = split(B,size,size);

            int[][] M1 = new int[size][size];
            strassen(size, add(A11, A22), add(B11, B22), M1);

            int[][] M2 = new int[size][size];
            strassen(size, add(A21, A22), B11, M2);

            int[][] M3 = new int[size][size];
            strassen(size, A11, sub(B12, B22), M3);

            int[][] M4 = new int[size][size];
            strassen(size, A22, sub(B21, B11), M4);

            int[][] M5 = new int[size][size];
            strassen(size, add(A11, A12), B22, M5);

            int[][] M6 = new int[size][size];
            strassen(size, sub(A21, A11), add(B11, B12), M6);

            int[][] M7 = new int[size][size];
            strassen(size, sub(A12, A22), add(B21, B22), M7);

            int[][] C11 = add(sub(add(M1, M4), M5), M7);
            int[][] C12 = add(M3, M5);
            int[][] C21 = add(M2, M4);
            int[][] C22 = add(add(sub(M1, M2), M3), M6);

            for (int i = 0; i < size; i++) {
                System.arraycopy(C11[i], 0, C[i], 0, size);
                System.arraycopy(C12[i], 0, C[i], size, size);
                System.arraycopy(C21[i], 0, C[i + size], 0, size);
                System.arraycopy(C22[i], 0, C[i + size], size, size);
            }
        }
    }

    static int[][] split(int[][] parent, int row, int col) {
        int size = parent.length/2;
        int[][] newArray = new int[size][size];
        for (int i = 0; i < size; i++) {
            System.arraycopy(parent[row + i], col, newArray[i], 0, size);
        }
        return newArray;
    }

    static int[][] add(int[][] A, int[][] B) {
        int size = A.length;
        int[][] result = new int[size][size];
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                result[i][j] = A[i][j] + B[i][j];
            }
        }
        return result;
    }

    static int[][] sub(int[][] A, int[][] B) {
        int size = A.length;
        int[][] result = new int[size][size];
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                result[i][j] = A[i][j] - B[i][j];
            }
        }
        return result;
    }


}
