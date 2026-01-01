import java.util.*;
class Bus extends Thread{
    static int availableTickets = 5;
    HashMap<String,Integer> ticketBooked = new HashMap<>();
    String userName;
    int userInputTickets;
    Bus(String userName,int userInputTickets){
        this.userName = userName;
        this.userInputTickets = userInputTickets;
    }
    public void userInput(){
//        System.out.println("Available Tickets : "+availableTickets);
//        System.out.println("Enter your name : ");
//        String userName = sc.next();
        System.out.println("Hello "+userName+", Available tickets : "+availableTickets);
//        System.out.println("How many tickets you want to book? : ");
        bookTicket(userName,userInputTickets);
    }
    public void bookTicket(String userName,int userInputTickets){
        if(userInputTickets <= availableTickets){
            ticketBooked.put(userName,userInputTickets);
            availableTickets = availableTickets - userInputTickets;
            System.out.println("\nCongratulations "+userName+", your "+userInputTickets+" seats are booked...");
        }else{
            System.out.println("\nNumber of tickets you requested is not available at this time... Try Next time");
        }
    }
    @Override
    public void run(){
        try{
            userInput();
            Thread.sleep(2000);
        }catch (InterruptedException e){

        }
    }

}
public class Day4_BusTicketBooking {
    public static void main(String[] args) {
        Bus user1 = new Bus("mahesh",1);
        Bus user2 = new Bus("peter",2);
        Bus user3 = new Bus("Steve",4);
        try{
            user1.start();
            Thread.sleep(2000);
            user2.start();
            Thread.sleep(2000);
            user3.start();
            Thread.sleep(2000);
        }catch (Exception e){

        }
    }
}
