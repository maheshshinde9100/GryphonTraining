package dsa;
import java.util.*;
class Channel{ //day 3
    List<CustomerSubscriber> subscribers;
    String videoTitle;
    Channel(){
        this.subscribers = new ArrayList<>();
    }
    void subscribe(CustomerSubscriber sub){
        subscribers.add(sub);
    }
    void upload(String videoTitle){
        this.videoTitle = videoTitle;
        notifySubscribers();
    }
    void notifySubscribers(){
        for(CustomerSubscriber sub: subscribers){
            sub.update();
        }
    }
}
class CustomerSubscriber{
    String name;
    Channel worldAffairs;
    CustomerSubscriber(String name){
        this.name = name;
        worldAffairs = new Channel();
    }
    void subscribeChannel(Channel ch){
        worldAffairs = ch;
    }
    void update(){
        System.out.println("Hello "+name+" : video uploaded : "+worldAffairs.videoTitle);
    }
}

public class YouTube {
    public static void main(String[] args) {
        Channel worldAffairs = new Channel();
        CustomerSubscriber cs1 = new CustomerSubscriber("Mahesh");
        CustomerSubscriber cs2 = new CustomerSubscriber("Pratap");
        CustomerSubscriber cs3 = new CustomerSubscriber("Parth");
        CustomerSubscriber cs4 = new CustomerSubscriber("Vedant");
        CustomerSubscriber cs5 = new CustomerSubscriber("Peter");

        worldAffairs.subscribe(cs1);
        worldAffairs.subscribe(cs2);
        worldAffairs.subscribe(cs3);
        worldAffairs.subscribe(cs4);
        worldAffairs.subscribe(cs5);

        cs1.subscribeChannel(worldAffairs);
        cs2.subscribeChannel(worldAffairs);
        cs3.subscribeChannel(worldAffairs);
        cs4.subscribeChannel(worldAffairs);
        cs5.subscribeChannel(worldAffairs);

        worldAffairs.upload("WorldWide - Avatar and Ash Collection");
    }
}
