//22212048_김동준

import java.util.Scanner;

public class HW3 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int[] A = new int[n];
        for (int i = 0; i < n; i++)
            A[i] = sc.nextInt();
        sc.close();

        Solution3 sol = new Solution3();
        sol.solution(A, n);
    }
}

class Solution3 {
    public void solution(int[] A, int n) {
        int[] sorted = new int[A.length];
        System.arraycopy(A, 0, sorted, 0, n);
        mergeSort(sorted); //정렬

        int[] Key = new int[n];

        int cur = 0;
        Key[cur] = sorted[0];
        for (int i = 1; i < n; i++) {
            if(Key[cur] < sorted[i]) { //중복값 처리
                Key[++cur] = sorted[i];
            }
        }

        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < n; i++) {
            int target = binarySearch(Key,0,cur,A[i]); // Xp보다 작은 서로 다른 좌표 Xq의 개수 = Xp의 index
            sb.append(target).append(" ");
        }

        System.out.println(sb.toString().trim());
    }

    private int binarySearch(int[] Key, int lo, int hi, int target) {
        while (lo <= hi) {
            int mid = lo + (hi - lo) / 2;
            if (Key[mid] == target) return mid;
            else if (Key[mid] < target) lo = mid + 1;
            else hi = mid - 1;
        }
        return -1;
    }


    public void mergeSort(int[] a) {
        int[] src = a, dst = new int[a.length], tmp;
        int N = a.length;
        for(int n=1;n<N;n*=2){
            for(int i=0;i<N;i+=n*2){
                merge(src,dst,i,i+n-1,Math.min(i+n*2-1,N-1));
            }
            tmp = src; src = dst; dst = tmp;
        }
        if(src!=a) System.arraycopy(src,0,dst,0,N);
    }

    public void merge(int[] in, int[] out, int lo, int mid, int hi) {
        int i=lo, j=mid+1;
        for(int k =lo;k<=hi;k++){
            if(i>mid) out[k] = in[j++];
            else if(j>hi) out[k] = in[i++];
            else if(in[j]<in[i])  out[k] = in[j++];
            else out[k] = in[i++];
        }
    }
}