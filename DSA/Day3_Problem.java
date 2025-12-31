package dsa;

import java.util.Scanner;

public class Day3_Problem {
    public static void main(String[] args) {
        Scanner sc =new Scanner(System.in);
        System.out.println("Enter length of Array : ");
        int n = sc.nextInt();
        int[] arr = new int[n];
        System.out.println("Enter "+n+" elements : ");
        for(int i=0;i<n;i++){
            arr[i] = sc.nextInt();
        }

        System.out.println("Enter L and R : ");
        int l = sc.nextInt();
        int r = sc.nextInt();
        for(int i=l;i<r;i++){
            arr[i] = arr[i-l+1]*arr[l];
        }

        printArray(arr);

        //Type2 query
        int sum  =0;
        while(l<r){
            sum += arr[l]+arr[r];
            l++;
            r--;
        }
        System.out.println("SUM : "+sum);
    }
    static void printArray(int[] arr){
        for(int i=0;i<arr.length;i++){
            System.out.print(arr[i]+" ");
        }
        System.out.println();
    }

}
