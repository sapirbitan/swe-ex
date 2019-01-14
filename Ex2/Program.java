import ex1.FindMax;
import ex1.FindSum;
import ex1.Stopper;
import ex1.EstimatedPi;

import java.util.Scanner;

public class Program {

    public static void main(String[] args) {
        Stopper running = new Stopper();
        Scanner in = new Scanner(System.in);  // Reading from System.in
        int chosen_case = 0;
        boolean flag = true;
        while (flag) {
            System.out.println("what would you like to do ?");
            System.out.println("Press 1 to test stopper");
            System.out.println("Press 2 to test FindSum");
            System.out.println("Press 3 to test FindMax");
            System.out.println("Press 4 to test EstimatedPi");
            System.out.println("Press 9 to exit");
            chosen_case = in.nextInt();
            switch (chosen_case) {
                case 1: {
                    running.runners();
                }
                break;
                case 2: {
                    System.out.println("What size is your array?");
                    int size = in.nextInt();
                    int[] temp_array = new int[size];
                    System.out.println("Please enter the content of the array");
                    while(size !=0) {
                        int _input = in.nextInt();
                        size--;
                        temp_array[size] = _input;
                    }
                    FindSum sum = new FindSum(temp_array,-1);
                    sum.findSum();
                }
                break;
                case 3: {
                    System.out.println("What size is your array?");
                    int size = in.nextInt();
                    int[] temp_array = new int[size];
                    System.out.println("Please enter the content of the array");
                    while(size !=0) {
                        int _input = in.nextInt();
                        size--;
                        temp_array[size] = _input;
                    }
                    FindMax max = new FindMax(temp_array,-1);
                    max.findMax();
                }
                break;
                case 4: {
                    System.out.println("Enter number of dots");
                    int num_of_dots = in.nextInt();
                    EstimatedPi[] dotArray = new EstimatedPi[num_of_dots];	//creating an array of threads
                    for(int i=0; i<num_of_dots; i++) {
                        dotArray[i] = new EstimatedPi();
                        dotArray[i].start();//running each thread
                    }
                    //wait for everyone to finish calculating
                    try {
                        for(int i=0; i<num_of_dots; i++)
                            dotArray[i].join();
                    }

                    //if something went wrong
                    catch(Exception e) {
                        System.out.println("Error");
                    }
                    double counter = dotArray[num_of_dots-1].getCounter();
                    double estimated_pi = (counter/num_of_dots)*4;//calculate pi according to the given formula
                    System.out.println("The estimated pi is "+ estimated_pi);
                    dotArray[num_of_dots-1].setCounter();//initializng the counter to 0 for next use
                }
                break;
                case 9:{
                    flag = false;
                }
                break;
                default: {
                    System.out.println("you've pressed the wrong key, please try again");
                }
                break;
            }
        }
    }
}
