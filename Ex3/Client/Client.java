
import java.io.DataInputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.net.Socket;
import java.util.Scanner;

public class Client {
    private String cur_method;
    private Socket socket;
    private DataInputStream _input;
    private PrintStream _output;
    private Scanner user_input;

    public void print(){
        try {
            System.out.println("The message " +cur_method + " was sent to the server");
            System.out.println("Server's reply: \"" + _input.readLine() + "\"");
        } catch (Exception ioEXception) {}
    }

    public void SendingMessage(String ip){
        try {
            socket = new Socket(ip,7000);
            System.out.println("Connection has been made:" + socket.getLocalAddress() + " : " + socket.getLocalPort());
            _input = new DataInputStream(socket.getInputStream());
            _output = new PrintStream(socket.getOutputStream());
            System.out.println("What method would you like to use ?");
            user_input = new Scanner(System.in);
            String input = user_input.nextLine();
            cur_method=input;
            while (!cur_method.equals("goodbye")){
                _output.println(cur_method);
                print();
                input = user_input.nextLine();
                cur_method=input;
            }
                _output.println(cur_method);
             } catch (Exception connectionFailed) {
            System.out.println("Couldn't connect"); System.err.println(connectionFailed);
             }
        finally {
            try {
                socket.close();
                System.out.println("Client disconnected !");
            }  catch (IOException ioExcept){}
        }
    }
    public static void main(String[] args) {
        Client client1 = new Client();
        client1.SendingMessage("127.0.0.1");
    }
}
