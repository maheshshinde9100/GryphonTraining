package dsa;

import java.util.Scanner;

public class Day4_String {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String str = sc.nextLine();

        char[] ch = str.toCharArray();
        StringBuffer sb = new StringBuffer();
        StringBuffer finalStr = new StringBuffer();

        for (int i = ch.length - 1; i >= 0; i--) {
            if (ch[i] != ' ') {
                sb.append(ch[i]);
            } else {
                finalStr.append(reverseStr(sb.toString()));
                finalStr.append(" ");
                sb.setLength(0);
            }
        }

        finalStr.append(reverseStr(sb.toString()));
        System.out.println("Final String: " + finalStr);
    }

    static String reverseStr(String str) {
        char[] ch = str.toCharArray();
        int start = 0;
        int end = ch.length - 1;

        while (start < end) {
            char temp = ch[start];
            ch[start] = ch[end];
            ch[end] = temp;
            start++;
            end--;
        }
        return new String(ch);
    }
}
