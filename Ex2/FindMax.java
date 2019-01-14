
import java.util.Scanner;

public class FindMax extends Thread {
    private int beggining;
    private int[] array;
    private static int max;
    boolean flag = false;

    //constructor for this thread data
    public FindMax(int[] arr,int beg) {
        array = arr;
        beggining = beg;
    }

    //get the max
    public int getMax() {
        return max;
    }

    public void run() {
        findPartialMax();
    }
    public void findMax(){
        max = array[0];
        if(array.length<4){
            for(int i=0;i<array.length;i++)
                if(array[i]>max)
                    max=array[i];
        }
        else {
            FindMax max1, max2, max3, max4;
            //initializing 4 new partial sums
            max1 = new FindMax(array, 0);
            max1.start();
            max2 = new FindMax(array, 1);
            max2.start();
            max3 = new FindMax(array, 2);
            max3.start();
            max4 = new FindMax(array, 3);
            max4.start();
            while (!(max1.flag && max2.flag & max3.flag && max4.flag)) {
                //while calculating the sum - do nothing
            }
        }
        System.out.println("The biggest number in this array is: "+max);
        max=0;//initializng the sum to 0 for next use
    }


    public synchronized void findPartialMax() {
        for (int i=beggining; i<array.length; i+=4) {
            if (array[i]>max) {
                max=array[i];
            }
            flag = true;
        }
    }
}
