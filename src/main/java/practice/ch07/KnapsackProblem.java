package practice.ch07;

import java.util.ArrayList;
import java.util.List;

class item{
    int w;
    int p;

    item(int w,int p){
        this.w=w;
        this.p=p;
    }
}

public class KnapsackProblem {
    public static void main(String[] args) {
        List<item> S=new ArrayList<item>();
        S.add(new item(5,50));
        S.add(new item(10,60));
        S.add(new item(20,140));

        int P1 = KnapsackDP.knapsack(S,30);
        int P2 = KnapsackDC.knapsack(S,3,30);

        System.out.println(P1);
        System.out.println(P2);
    }
}

class KnapsackDP{
    public static int knapsack(List<item> S,int W){
        int n = S.size();
        int[][] P =new int[n+1][W+1];

        for(int i=0;i<=n;i++){
            P[i][0] = 0;
        }
        for(int j=0;j<=W;j++){
            P[0][j] = 0;
        }


        for(int i = 1; i <= n; i++){
            for(int j = 1; j <= W; j++){
                if(S.get(i-1).w>j){
                    P[i][j]=P[i-1][j];
                }else{
                    P[i][j]= Math.max(P[i -1][j], S.get(i-1).p + P[i -1][j-S.get(i-1).w]);
                }
            }
        }

        return P[n][W];
    }
}

class KnapsackDC{
    public static int knapsack(List<item> S, int i, int W){
        if(i==0 || W<= 0){
            return  0;
        }

        if(S.get(i-1).w>W){
            return knapsack(S, i-1, W);
        }else{
            return Math.max(knapsack(S,i-1,W),S.get(i-1).p + knapsack(S,i-1,W-S.get(i-1).w));
        }
    }
}
