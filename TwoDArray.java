package dsa;

import java.util.Scanner;

public class TwoDArray {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter length of 2d array : ");
        int n = sc.nextInt();
        int[][] mat = new int[n][n];
        int[][] mat2 = new int[n][n];
        System.out.println("Enter N*N matrix : ");
        for(int i=0;i<n;i++){
            for(int j=0;j<n;j++){
                mat[i][j] = sc.nextInt();
            }
        }
        int k = n-1;
        for(int i=0;i<n;i++){
            for(int j=0;j<n;j++){
                mat2[j][k] = mat[i][j];
            }
            k--;
        }

        System.out.println("\nOutput Matrix : \n");
        for(int i=0;i<n;i++){
            for(int j=0;j<n;j++){
                System.out.print(mat2[i][j]+" ");
            }
            System.out.println();
        }
    }
}
