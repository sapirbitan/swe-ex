
import java.util.Timer;
import java.util.TimerTask;
import java.util.Scanner;
public class Stopper extends Thread {
    public boolean is_running=true;
    public Timer timer = new Timer(is_running);//creating a special timer that stops working when is_running turns to false
    public int seconds_passed=0;
    TimerTask task = new TimerTask() {
        public void run(){
           seconds_passed++;
        }
    };
    public void start(){
            timer.scheduleAtFixedRate(task,0,1000);
    }
    public void runners(){
        Stopper[] runners = new Stopper[3];
        runners[0] = new Stopper();
        runners[1] = new Stopper();
        runners[2] = new Stopper();
        runners[0].start();//first runner
        runners[1].start();//second runner
        runners[2].start();//third runner
        Scanner reader = new Scanner(System.in);
        int num_of_runners = 3;
        System.out.println("press 'ENTER' in order to stop one of the runners");
        while(num_of_runners!=0) {
            String scan = reader.nextLine();
            System.out.println("runner number " + num_of_runners + " finished, time: " + runners[num_of_runners - 1].seconds_passed);
            runners[num_of_runners-1].is_running = false;
            num_of_runners--;
        }
    }
}


