package dsa.day6;
import java.io.*;
class Player{
    String name;
    float time;
    int distance;
    Player(String name,int distance,float time){
        this.name = name;
        this.distance = distance;
        this.time = time;
    }
}
public class RaceProblem {
    public static void main(String[] args) throws IOException{
//        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
//        Player p1,p2;
//        System.out.println("Enter (name,distance,time) of player A : ");
//        String name = br.readLine();
//        int distance = Integer.parseInt(br.readLine());
//        float time = Float.parseFloat(br.readLine());
//        p1 = new Player(name,distance,time);
//        System.out.println("Enter (name,distance,time) of player B : ");
//        name = br.readLine();
//        distance = Integer.parseInt(br.readLine());
//        time = Float.parseFloat(br.readLine());
//        p2 = new Player(name,distance,time);

//        Q.1. in first round - A is won by 100m or 10s
//        in second round - same track,same distance ,same speed.. just player B
//        injured at the middle of the track .. now find out the time difference
//        between Player A and player B, total time taken by player B
//        - track length is 1000m

//       Q.2. TL = 1000m
//        A beat B by 200m OR 5 Sec
//        Injured at 200m
//        reduced by 75%
//        _------------------------------------------

        firstSolution();
        secondSolution();
    }
    static void firstSolution() {
        float track = 1000;

        float speedA = 11.11f;
        float speedB = 10f;

        float timeA = track / speedA;

        float timeB1 = 500 / speedB;        // before injury
        float timeB2 = 500 / (speedB / 2);  // after injury (50% speed)

        float totalTimeB = timeB1 + timeB2;

        System.out.println("Q1:");
        System.out.println("Time taken by A: " + timeA + " sec");
        System.out.println("Time taken by B: " + totalTimeB + " sec");
        System.out.println("Time Difference: " + (totalTimeB - timeA) + " sec");
    }

    static void secondSolution() {
        float track = 1000;

        float speedA = 20f;
        float speedB = 16f;

        float timeA = track / speedA;

        float timeB1 = 200 / speedB;           // before injury
        float timeB2 = 800 / (speedB * 0.25f); // after injury (75% reduced)

        float totalTimeB = timeB1 + timeB2;

        System.out.println("\nQ2:");
        System.out.println("Time taken by A: " + timeA + " sec");
        System.out.println("Time taken by B: " + totalTimeB + " sec");
        System.out.println("Time Difference: " + (totalTimeB - timeA) + " sec");
    }

}
