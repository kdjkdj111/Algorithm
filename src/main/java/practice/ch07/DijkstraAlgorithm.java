package practice.ch07;

import java.util.Arrays;
import java.util.PriorityQueue;

public class DijkstraAlgorithm {
    static final int INF = 9999999;

    public static void main(String[] args) {
        int n = 5; // 정점의 개수 (v1 ~ v5)

        int[][] W = {
                // v1,  v2,  v3,  v4,  v5  (도착지)
                {   0,   7,   4,   6,   1 }, // v1 (0번 인덱스)에서 출발
                { INF,   0, INF, INF, INF }, // v2 (1번 인덱스)에서 출발 (나가는 간선 없음)
                { INF,   2,   0,   5, INF }, // v3 (2번 인덱스)에서 출발
                { INF,   3, INF,   0, INF }, // v4 (3번 인덱스)에서 출발
                { INF, INF, INF,   1,   0 }  // v5 (4번 인덱스)에서 출발
        };

        int startNode = 0; // v1에서 출발 (0번 인덱스)
        int[] result = dijkstra(n, W, startNode);


        for (int i = 0; i < n; i++) {
            System.out.println("v1 -> v" + (i + 1) + " : " + result[i]);
        }
    }

    public static int[] dijkstra(int n, int[][] W, int start) {
         int[] distance =  new int[n]; //start부터 각 노드까지의 거리 배열
        Arrays.fill(distance, INF);
        distance[start] = 0;

        PriorityQueue<Node> pq = new PriorityQueue<>((a, b) -> a.cost - b.cost);
        pq.add(new Node(start,0));

        while (!pq.isEmpty()) {
            Node current = pq.poll();
            int u = current.vertex;
            int cost = current.cost;
            //전 단계에서 newCost로 넣어놨던게, 다른 노드에 의해서 min이 아닐 수 있음. 그렇다면 continue.
            if(distance[u] < cost) { continue; }

            for(int v = 0; v < n; v++){ //추가된 정점에서 인접한 노드들을 전부 봄.
                if(W[u][v] != INF && W[u][v] > 0){
                    int newCost = distance[u] + W[u][v];
                    if(newCost < distance[v]){
                        distance[v] = newCost;
                        pq.add(new Node(v, newCost));
                    }
                }
            }
        }
        return distance;
    }
}
