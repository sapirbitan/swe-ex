
public class EstimatedPi extends Thread {
    private static Integer counter=0;
    //constructor
    public EstimatedPi() {
    }

    //zero the counter
    public void setCounter() {
        counter=0;
    }

    //get the counter
    public Integer getCounter() {
        return counter;
    }

    //pick a random number and if it is inside the circle - increase the counter
    public void run() {
        double x;
        double y;
        x = Math.random();
        y = Math.random();
        if(x * x + y * y < 1) {
            synchronized (counter) {
                counter++;
            }
        }
    }
}
