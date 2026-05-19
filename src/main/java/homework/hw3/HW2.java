package homework.hw3;//22212048 김동준

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class HW2 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("정수 n과 k를 입력? ");
        int n = scanner.nextInt();// n : 1~n
        int k = scanner.nextInt();// k : 집합

        List<List<Integer>> result = new ArrayList<>();
        List<Integer> current = new ArrayList<>();

        combine(1,n,k,current,result);

        for(int i = 0; i < result.size(); i++){
            System.out.print(result.get(i)+" ");
        }

    }

    static void combine(int start,int n, int k,List<Integer> current, List<List<Integer>> result){

        if(current.size()==k){
            result.add(new ArrayList<>(current));
            return;
        }

        for(int i = start; i <= n; i++){
            current.add(i);
            combine(i+1,n,k,current,result);
            current.removeLast();
        }
    }
}


