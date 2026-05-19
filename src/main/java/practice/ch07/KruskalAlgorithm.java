package practice.ch07;

import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;



public class KruskalAlgorithm {
    public static void main(String[] args) {
        int n = 5;

        List<Edge> E = new ArrayList<>();

        E.add(new Edge(1, 2, 1)); // v1 - v2 (가중치 1)
        E.add(new Edge(1, 3, 3)); // v1 - v3 (가중치 3)
        E.add(new Edge(2, 3, 3)); // v2 - v3 (가중치 3)
        E.add(new Edge(2, 4, 6)); // v2 - v4 (가중치 6)
        E.add(new Edge(3, 4, 4)); // v3 - v4 (가중치 4)
        E.add(new Edge(3, 5, 2)); // v3 - v5 (가중치 2)
        E.add(new Edge(4, 5, 5)); // v4 - v5 (가중치 5)

        List<Edge> F = kruskal(n,7,E);

        System.out.println(F);
    }

    static List<Edge> kruskal(int n, int m, List<Edge> E) {
        int i,j;
        int p,q; //집합의 대표
        Edge e;
        List<Edge> F = new ArrayList<>();
        int[] set = new int[n];

        PriorityQueue<Edge> pq = new PriorityQueue<>((e1, e2) -> e1.weight - e2.weight);
        pq.addAll(E);

        set = initial(n);
        while(F.size() < n-1){
            e = pq.poll();
            i = e.u;
            j = e.v;
            p=find(set,i);
            q=find(set,j);
            if(!equal(set,p,q)){union(set,p,q); F.add(e);}
        }
        return F;
    }

    static int[] initial(int n){
        int[] set=new int[n+1];
        for(int i=1;i<=n;i++){
            set[i]=i;
        }
        return set;
    }

    static int find(int[] set, int index){
        if(set[index]==index){ return index;}
        return find(set, set[index]);
    }

    static void union(int[] set, int p, int q) {
        int root1 = find(set, p);
        int root2 = find(set, q);

        set[root2] = root1;
    }

    static boolean equal(int[] set, int p, int q){
        if(set[p]==set[q]){ return true; }
        return false;
    }

}
