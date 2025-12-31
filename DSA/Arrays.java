package dsa;
import java.util.*;
import java.util.Iterator;
import java.util.stream.Collector;
import java.util.stream.Collectors;

public class Arrays {
    void arrayOperations(){
        int n;
        List<String> cities = new ArrayList<>();
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter no of cities :> ");
        n = sc.nextInt();

        System.out.println("Enter "+n+" cities :> ");
        for(int i=0;i<n;i++){
            cities.add(sc.next());
        }

        System.out.println("Displaying cities :> ");
        for(String city: cities){
            System.out.print(city+", ");
        }

        List<Integer> nums = new ArrayList<>();
        nums.add(100);
        nums.add(-200);
        nums.add(-300);
        nums.add(400);

        int num;
        Iterator iterator = nums.iterator();
        while (iterator.hasNext()){
            num = (Integer) iterator.next();
            if(num<0){
                System.out.println("\nNegative number : "+num);
            }
        }
//        using stream()
        List<Integer> nums1 = nums.stream()
                .filter(x -> x<0)
                .collect(Collectors.toList());
        System.out.println(nums1);

    }
    public static void main(String[] args) {
//      Q. searching prime numbers based on TIme Complexity

        //worst TIme Complexity : Program 01
//        int n=40;
//        for(int i=0;i<40;i++){
//            System.out.println(i+" : "+isPrime(i));   //Time Complexity : O(N^2)
//        }

        Map<Integer,Boolean> map = new HashMap<>();
        int n = 10;
        for(int i=2;i<n;i++){
            isPrime2(i,n,map);
        }

        for(int i=2;i<n;i++){
            if(!map.containsKey(i)){
                map.put(i,true);
            }
        }
        for(Integer key:map.keySet()){
            System.out.println(key+" -> "+map.get(key));
        }
    }
    static boolean isPrime(int x){
        for(int i=2;i<Math.sqrt(x);i++){
            if(x%i == 0){
                return false;
            }
        }
        return true;
    }

    static void isPrime2(int x, int n, Map<Integer,Boolean> map){
        if(map.containsKey(x) && map.get(x)==false){
            return;
        }
        for(int i=x*2; i<n; i+=x){
            map.put(i, false);
        }
    }


}
