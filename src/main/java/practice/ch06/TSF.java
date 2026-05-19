package practice.ch06;

public class TSF {
    static int INF = 9999999;
    public static void main(String[]args){

        int n = 4;
        int[][] W = {
                {  0,   0,   0,   0,   0  }, // 0번 인덱스 (사용 안 함)
                {  0,   0,   2,   9, INF  }, // v1에서 출발 (1번 행)
                {  0,   1,   0,   6,   4  }, // v2에서 출발 (2번 행)
                {  0, INF,   7,   0,   8  }, // v3에서 출발 (3번 행)
                {  0,   6,   3, INF,   0  }  // v4에서 출발 (4번 행)
        };

        int[][] P = new int[n+1][1<<(n+1)];
        int minlength = INF;

        minlength =travel(4,W,P);

        System.out.println("최소 순회 비용: " + minlength);

        System.out.print("최적 방문 경로: 1 ");

        int curr = 1;
        int fullMask = 0;
        for (int j = 2; j <= n; j++) {
            fullMask |= (1 << j);//2,3,4
        }

        while (fullMask != 0) {
            int nextNode = P[curr][fullMask]; // 다음으로 갈 도시 확인
            System.out.print("-> " + nextNode + " "); //방문
            curr = nextNode; //현재 위치를 방문한 위치로

            fullMask = fullMask & ~(1 << nextNode); //off
        }
        System.out.println("-> 1");
    }

    public static int travel(int n, int[][] W, int[][] P){
        int i,j,k; //i는 현재 노드, j는 i의 직전 노드, k는 subset 요소의 개수
        int[][] D=new int[n+1][1<<(n+1)];

        for(i=2;i<=n;i++) D[i][0] = W[i][1];
        for(k=1;k<=n-2;k++){ //subset포함 정점. 초기 1개 부터 시작.
            for(int mask = 0; mask < (1<<(n+1)); mask++){ //모든 subset
                if((mask & (1<<1)) !=0) continue; //1이 subset에 포함되었으면 pass
                if (Integer.bitCount(mask) != k) continue; //bitCount bit 1의 갯수를 구해주는 함수. k개가 아니면 현재 고려대상 x
                //-> 2번의 필터로 k개의 원소를 가지고 있는 집합만 계산

                for(i=2;i<=n;i++){
                    if((mask & (1<<i)) !=0) continue; //i가 현재 subset에 포함되었으면 pass
                    //여기까지 k의 갯수가 1부터 올라가는 subset이 정해지고 현재 보고있는 정점 i까지 정해진 상태.

                    int minVal = INF;
                    int minJ = 0;

                    for(j=2;j<=n;j++){ //j는 i직전 노드. j를 바꿔가며 min을 찾아야함.
                        if((mask & (1<<j)) !=0) { //mask(subset)에 j가 포함되어 있다면.
                            int prevMask = mask & ~(1 << j); //j가 없는 subset

                            int cost = W[i][j] + D[j][prevMask];
                            if(cost < minVal){
                                minVal = cost;
                                minJ = j;
                            }
                        }
                    }
                    D[i][mask] = minVal;
                    P[i][mask] = minJ;
                }//i의 for문 : 시작 정점을 돌아가며 작은 subset부터 계산
            }//subset의 for문: 거치는 subset을 늘려가며 계산. 앞선 for문에서 D가 이미 계산되어 있음.
        }//모든 경우 계산 끝.

        int fullMask = 0;
        for (j = 2; j <= n; j++) {
            fullMask |= (1 << j); //1을 제외한 모든 bit를 on. -> 1이 제외된 모든 노드 subset
        }

        int minVal = INF;
        int minJ = 0;

        for (j = 2; j <= n; j++) {
            int prevMask = fullMask & ~(1 << j); //1과 j만 꺼진 상태
            int cost = W[1][j] + D[j][prevMask];

            if (cost < minVal) {
                minVal = cost;
                minJ = j;
            }
        }

        D[1][fullMask] = minVal;
        P[1][fullMask] = minJ;

        return D[1][fullMask];
    }
}
