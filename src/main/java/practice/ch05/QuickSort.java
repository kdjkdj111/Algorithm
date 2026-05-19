package practice.ch05;

import java.util.Arrays;
import java.util.Scanner;

public class QuickSort {
    static Comparable[] S = new Integer[]{15,22,13,27,12,10,20,25};
    public static void main(String[] args) {
        sort(S);

        System.out.println(Arrays.toString(S));
    }


    public static void sort(Comparable[] a){
        quicksort(a,0,a.length-1);
    }
    public static void quicksort(Comparable[] a,int low,int high){
        if(high>low){
            int pivotpoint = partition(a,low,high);
            quicksort(a,low,pivotpoint-1);
            quicksort(a,pivotpoint+1,high);
        }
    }
    public static int partition(Comparable[] a,int low,int high){
        Comparable pivotitem = a[low];
        int j=low;
        for(int i=low+1;i<=high;i++){
            if(a[i].compareTo(pivotitem) < 0){
                j +=1;
                exch(a,i,j);
            }
        }
        int pivotpoint = j;
        exch(a,low,pivotpoint);
        return pivotpoint;
    }

    private static void exch(Comparable[] a, int i, int j) {
        Comparable temp = a[i];
        a[i] = a[j];
        a[j] = temp;
    }
}
