package practice.ch05;

import java.util.Scanner;

public class BinarySearch {
    static int x;
    static int[] S = new int[]{1, 12, 123, 4244, 12315};
    static int n = S.length;
    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);
        x = sc.nextInt();
        
        int locationout = location(0,n-1);

        System.out.println(locationout);
    }

    static int location(int low, int high){
        int mid;
        if(low>high) return -1;
        else{
            mid = (low+high)/2;
            if(x==S[mid]){
              return mid;
            }else if(x<S[mid]){
                return location(low, mid-1);
            }else{
                return location(mid+1, high);
            }
        }
    }


}
