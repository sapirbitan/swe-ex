
public class FindSum extends Thread{

    private int beggining;
    private int[] array;
    private static int sum = 0;
    boolean flag = false;
    //constructor
    public FindSum(int[] arr, int beg) {
        array = arr;
        beggining = beg;
    }

    public int getSum() {
        return sum;
    }

    //calculate the sum of the partial array
    public void run() {
        findPartialSum();
    }
    public void findSum(){
        if(array.length<4) {
            for(int i=0; i<array.length;i++)
                sum+=array[i];
        }
        else {
            FindSum sum1, sum2, sum3, sum4;
            //initializing 4 new partial sums
            sum1 = new FindSum(array, 0);
            sum1.start();
            sum2 = new FindSum(array, 1);
            sum2.start();
            sum3 = new FindSum(array, 2);
            sum3.start();
            sum4 = new FindSum(array, 3);
            sum4.start();
            while (!(sum1.flag && sum2.flag && sum3.flag && sum4.flag)) {
                //while calculating the sum - do nothing
            }
        }
        System.out.println("The sum of this array's items is: "+sum);
        sum=0;//initializng the sum to 0 for next use
    }
    //sum the array's content(every 4th item
    public synchronized void findPartialSum() {
        for (int i=beggining; i<array.length; i+=4) {
            sum+=array[i];
        }
        flag = true;
    }
}

