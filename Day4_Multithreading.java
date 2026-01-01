/*
* new -> start() -> runnable -> run() -> running ->
* sleep()/wait() -> waiting -> stop() -> destroyed
*/
/*
step1: import the java.lang package
step2 : A) by extending Thread class
        B) by implementing Runnable interface (best approach)
step3 : intialize run() method
step4 : give the public access to the run() method
step5 : implementing the run method/ application
step6 : create the number of threads/users
step7 : start the Threads (call the start() method)
 */
class Threading extends Thread{
    @Override
    public void run(){
        try{
            for(int i=1;i<=5;i++) {
                System.out.println("bye");
                Thread.sleep(2000);
            }
        }catch (InterruptedException e){

        }
    }

}
public class Day4_Multithreading {
    public static void main(String[] args) {
        Threading obj = new Threading();
        Thread thread = new Thread(obj);
        thread.start();

        try{
            for(int i=1;i<=5;i++) {
                System.out.println("Hello");
                Thread.sleep(2000);
            }
        }catch (InterruptedException e){

        }

    }
}
