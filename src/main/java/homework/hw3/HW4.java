package homework.hw3;//22212048 김동준
//solution1: DP방식
//solution2: BFS방식
import java.util.*;

public class HW4 {
    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);
        System.out.print("정수 N을 입력 > ");
        int N = sc.nextInt();

        int count= solution1(N);

        System.out.println(count);
    }


    public static int solution1(int N){

        int[] arr = new int[N+1];

        for(int i = 2; i <= N; i++) {
            arr[i] = arr[i-1]+1;
            if(i%5==0){
                arr[i] = Math.min(arr[i], arr[i / 5] + 1);
            }
            if(i%3==0){
                arr[i] = Math.min(arr[i], arr[i / 3] + 1);
            }
            if(i%2==0){
                arr[i] = Math.min(arr[i], arr[i / 2] + 1);
            }
        }
        return arr[N];
    }


    public static int solution2(int N){
        if (N == 1) return 0;

        int count = 0;
        Queue<Integer> queue = new ArrayDeque<>();
        boolean[] visited = new boolean[N + 1];

        queue.add(N);
        visited[N] = true;

        while (!queue.isEmpty()) {
            count++;
            int size = queue.size();

            for (int i = 0; i < size; i++) {
                int x = queue.remove();

                int[] next = {
                        (x % 5 == 0) ? x / 5 : 0,
                        (x % 3 == 0) ? x / 3 : 0,
                        (x % 2 == 0) ? x / 2 : 0,
                        x - 1
                };

                for (int n : next) {
                    if (n == 0) continue;
                    if (n == 1) return count;

                    if (!visited[n]) {
                        queue.add(n);
                        visited[n] = true;
                    }
                }
            }
        }
        return count;
    }
}
