package dsa.day6;
class Node{
    int data;
    Node next;
    Node(int data){
        this.data = data;
        this.next = null;
    }
}
public class LinkedList {
    public static void main(String[] args) {
        Node head = new Node(10);
        head.next = new Node(20);
        head.next.next = new Node(30);
        head.next.next.next = new Node(50);

        //create a cycle : node 5 points back to node 2.
        head.next.next.next.next = head.next;
//        traverse(head);
        boolean isCycle = detectCycle(head);
        System.out.println("Cycle detected ? -> "+isCycle);
        traverse(head);
    }
    public static void traverse(Node head){
            Node temp = head;
            while(temp!=null){
                System.out.print(temp.data+", ");
                temp = temp.next;
            }
        }
        public static boolean detectCycle(Node head){
        Node slow = head;
        Node fast = head.next;
        int n = 0;
        while(fast!=null && fast.next!=null){
            slow = slow.next;
            fast = fast.next.next;
            if(slow == fast){
                n = 1;
                removeCycle(slow,head);
                break;
            }
        }
        if(n==1){
            return true;
        }
        return false;
        }
        static void removeCycle(Node meetingPoint,Node head){
//        start a pointer from the head,
//        and use the meeting point from the cycle.
            //move both pointer one step at a time until
            //there .next references match
            //this start of the cycle.
            Node temp = head;
            while(meetingPoint.next != temp.next){
                temp = temp.next;
                meetingPoint = meetingPoint.next;
            }
//            break the cycle by setting the .next of the
            //last node to null
            meetingPoint.next = null;
        }
}
