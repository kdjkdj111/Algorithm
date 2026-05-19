package practice.ch07;

import java.util.PriorityQueue;


public class PrimMST_minHeap {
    public static void main(String[] args) {
        int n = 5;

        int INF = 9999999;

        int[][] W = {
                {0,   1,   3, INF, INF  }, // v1과 연결된 간선들
                {1,   0,   3,   6, INF  }, // v2와 연결된 간선들
                {3,   3,   0,   4,   2  }, // v3과 연결된 간선들
                {INF,   6,   4,   0,   5  }, // v4와 연결된 간선들
                {INF, INF,   2,   5,   0  }  // v5와 연결된 간선들
        };

        int totalCost = prim(W);
        System.out.println(totalCost);
    }

    public static int prim(int[][] graph){
        int n=graph.length; //그래프(nxn)의 열은 정점의 개수임.
        boolean[] visited = new boolean[n];
        PriorityQueue<Node> pq = new PriorityQueue<>((a,b)-> a.cost-b.cost); //인접한 vertex와 해당 vertex로 가는 가중치를 q에 삽입

        pq.add(new Node(0, 0));//0번 정점 삽입
        int totalCost = 0;
        int count = 0;

        while(!pq.isEmpty() && count<n){ //queue가 비지 않고, count. 노드의 총 갯수만큼만 반복
            Node cur = pq.poll();
            int u = cur.vertex;
            int cost = cur.cost;

            if(visited[u]) continue; //cycle 방지.

            totalCost += cost;
            visited[u] = true;
            count++; //현재 노드 반영하고

            for(int v=0 ; v<n;v++){
                if(graph[u][v]!=0 && !visited[v]){//u와 연결되어있고, 방문하지 않았다면
                    pq.add(new Node(v, graph[u][v]));
                }
            }
        }
        return totalCost;
    }
}

