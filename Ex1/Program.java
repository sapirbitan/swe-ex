
import java.util.Scanner;

public class Program {
    public static void main(String[] args){

        MyLinkedList myList = new MyLinkedList();

        Scanner in = new Scanner(System.in);  // Reading from System.in
        int chosen_case = 0;
        while (chosen_case != 7) {
            System.out.println("what would you like to do ?");
            System.out.println ("Press 1 to add a node at the end of the list");
            System.out.println("Press 2 to add a node in a specific location");
            System.out.println("Press 3 to delete the last node");
            System.out.println("Press 4 to delete a specific node");
            System.out.println("Press 5 to print the list");
            System.out.println("Press 6 to reverse the list");
            System.out.println("Press 7 to exit the program ");
            chosen_case = in.nextInt();
            switch (chosen_case) {
                case 1: {
                    System.out.println("what is the value of the node ?");
                    int val = in.nextInt();//get the value
                    Node temp = new Node();
                    temp.setValue(val);//create the node
                    myList.addNode(temp);//insert the node
                }
                break;
                case 2: {
                    System.out.println("what is the value of the node ?");
                    int val = in.nextInt();//get the value
                    Node temp = new Node();
                    temp.setValue(val);
                    System.out.println("where would you want to insert the node ?");
                    val = in.nextInt();//get the location
                    myList.addNode(temp, val);
                }
                break;
                case 3: {
                    myList.removeNode();
                }
                break;
                case 4: {
                    System.out.println("which node would you like to delete ?");
                    int val = in.nextInt();//get the location
                    myList.removeNode(val);
                }
                break;
                case 5: {
                    myList.printList();
                }
                break;
                case 6: {
                    myList.reverse();
                }
                break;
                case 7: System.out.println("Goodbye !");
                    break;
                default:
                    System.out.println("you've pressed the wrong key, please try again");
                    break;
            }
        }
    }
}
