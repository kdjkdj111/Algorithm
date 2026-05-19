package practice.ch06;

import java.util.Scanner;

public class ShortestPath_Floyd {
    public static void main(String[] args) {
        Scanner sc=new Scanner(System.in);
        int n= 5; //정점의 수

        int[][] W = {{0,0,0,0,0,0},
                    {0,0,1,100,1,5},
                    {0,9,0,3,2,100},
                    {0,100,100,0,4,100},
                    {0,100,100,2,0,3},
                    {0,3,100,100,100,0}}; //입력 경로 배열

        int[][] D = new int[n+1][n+1]; //출력 경로 배열
        int[][] P = new int[n+1][n+1]; //경로 중 가장 큰 노드 저장 배열

        floyd(W,D,P);

        path(P,5,3);


    }

    public static void path(int[][] P,int q,int r){
        if(P[q][r] != 0){
            path(P,q,P[q][r]);
            System.out.println(" v"+P[q][r]);
            path(P,P[q][r],r);
        }
    }

    public static void floyd(int[][] W, int[][] D,int[][] P){
        int i,j,k;
        for(i=1;i<W.length;i++){ //D배열 초기화
            for(j=1;j<W.length;j++){
                D[i][j]=W[i][j];
                P[i][j]= 0; //인접 노드라 사이 노드는 없음.
            }
        }

        for(k=1;k<W.length;k++){ //0~n 까지의 정점을 거칠 때
            for(i=1;i<W.length;i++){ // i부터
                for(j=1;j<W.length;j++){ //j까지의 경로를 매번 구함
                    if(D[i][k] + D[k][j] < D[i][j]){
                        D[i][j]=D[i][k] + D[k][j];
                        P[i][j]= k;
                    }
                }
            }
        }
    }
}
