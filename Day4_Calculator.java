import java.awt.*;
import java.util.*;
class Calculator extends Thread{
//    int n;
//    Calculator(int n){
//        this.n = n;
//    }
    public synchronized void print(int n){
        for(int i=1;i<=10;i++){
            System.out.println(n+"*"+i+" = "+(n*i));
        }
    }
    @Override
    public void run(){
        try{
            print(5);
            print(7);
        }catch (Exception e){

        }
    }

}
public class Day4_Calculator {
    public static void main(String[] args) {
//        Calculator user1 = new Calculator(5);
//        Calculator user2 = new Calculator(7);
        Calculator user1 = new Calculator();
        user1.start();
    }
}

/*output:
5*1 = 5
5*2 = 10
5*3 = 15
5*4 = 20
5*5 = 25
5*6 = 30
5*7 = 35
5*8 = 40
5*9 = 45
5*10 = 50
7*1 = 7
7*2 = 14
7*3 = 21
7*4 = 28
7*5 = 35
7*6 = 42
7*7 = 49
7*8 = 56
7*9 = 63
7*10 = 70
 */