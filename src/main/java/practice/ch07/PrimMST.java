package practice.ch07;

import java.util.ArrayList;
import java.util.List;

public class PrimMST {
    public static void main(String[] args) {
        int n = 5;

        int INF = 9999999;

        int[][] W = {
                {  0,   0,   0,   0,   0,   0  }, // 0번 인덱스 (사용 안 함)
                {  0,   0,   1,   3, INF, INF  }, // v1과 연결된 간선들
                {  0,   1,   0,   3,   6, INF  }, // v2와 연결된 간선들
                {  0,   3,   3,   0,   4,   2  }, // v3과 연결된 간선들
                {  0, INF,   6,   4,   0,   5  }, // v4와 연결된 간선들
                {  0, INF, INF,   2,   5,   0  }  // v5와 연결된 간선들
        };


        List<Edge> F = prim(5, W);

        System.out.println(F);

        int totalCost = 0;
        for (Edge e : F) {
            // e.u에서 e.v로 가는 길의 가중치를 W 배열에서 찾아 더합니다.
            totalCost += W[e.u][e.v];
        }
        System.out.println(totalCost);
    }


    static List<Edge> prim(int n, int[][] W){
        int i, candidate = 0, min;
        int[] nearest = new int[n+1];
        int[] distance = new int[n+1];
        List<Edge> F = new ArrayList<Edge>();
        Edge e;

        for(i=2; i<=n; i++){//가까운건 노드 1로 하고 거리는 W인접으로
            nearest[i] = 1;
            distance[i] = W[1][i];
        }
        for(int repeat=0;repeat<n-1;repeat++){
            min = Integer.MAX_VALUE;
            //최소를 찾아서. near과 distance 수정
            for(i=2;i<=n;i++){
                if(0<distance[i] && distance[i]<min){
                    min = distance[i];
                    candidate = i; //추가할 정점의 index
                }
            }
            e = new Edge(nearest[candidate],candidate);
            F.add(e);
            distance[candidate] = -1;

            for(i=2;i<=n;i++){
                if(distance[i] > W[candidate][i]){
                    distance[i] = W[candidate][i];
                    nearest[i] = candidate;
                }
            }
        }
        return F;
    }
}
