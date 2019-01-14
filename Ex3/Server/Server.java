
import java.io.DataInputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Vector;

public class Server {
    private String cur_method;
    private String cur_index;
    private String cur_value;
    private Vector vector;

    public Server() {vector = new Vector(0);}//constructor

    public void serverWorking()throws IOException {
        ServerSocket server_socket = new ServerSocket(7000);
        while (true) {//waiting for new clients
            final Socket socket = server_socket.accept();//waiting for request
            new Thread(new Runnable() {//start new thread for each client
                public void run() {
                    System.out.println("Connection established:" + socket.getLocalAddress() + " : " + socket.getLocalPort());
                    try {
                       DataInputStream _input = new DataInputStream(socket.getInputStream());
                       PrintStream _output = new PrintStream(socket.getOutputStream());
                       String newMessage = _input.readLine();
                       getUserRequest(newMessage);
                       while (cur_method != "goodbye") {
                          requestProcess(cur_method, cur_index, cur_value, _output);
                          newMessage = _input.readLine();
                          getUserRequest(newMessage);
                       }
                    } catch (IOException e) {
                        System.err.println(e);
                    } finally {
                        try {
                            socket.close();
                            System.out.println("Server is disconnected");
                        } catch (IOException e) {
                            System.err.println(e);
                        }
                    }
                }
            }).start();

        }
    }
    public void requestProcess(String message, String _index, String value, PrintStream _output){
        boolean is_index_null = (_index != null);
        boolean is_value_null = (value != null);
        switch (message) {
            case "get": {
                if (!is_index_null) {
                    _output.println("Error ! Index is null");
                    break;
                }
                else {
                    _output.println(get().toString());
                    break;
                }
            }
            case "set": {
                if (!is_index_null || !is_value_null) {
                    _output.println("Error ! Index or value is null");
                    break;
                }
                else {
                    boolean res = set();
                    if (res){
                        _output.println("Index " + Integer.parseInt(cur_index) + " was updated to " + Integer.parseInt(cur_value));
                        break;
                    }
                    else {
                        _output.println("Error ! There's no such index in the server's data");
                        break;
                    }
                }
            }
            case "add": {
                if (!is_index_null){
                    _output.println("Error ! Value is null");
                }
                else{
                    add();
                    _output.println(Integer.parseInt(cur_index) + " was added to the vector");
                    break;
                }
            }
            case "remove": {
                if (!is_index_null){
                    _output.println("Error ! Index is null");
                    break;
                }
                else {
                    if (remove()){
                        _output.println("Index " + Integer.parseInt(cur_index) + " was removed");
                        break;
                    }
                    else {
                        _output.println("Error ! There's no such index in the server's data");
                        break;
                    }
                }
            }
            case "print": {
                _output.println(print());
                break;
            }
            default: {
                _output.println("Bad Request - unknown method");
                break;
            }
        }
    }

    public synchronized void getUserRequest(String message){
        //In order to get the client's input we'll need to split it using the whitespaces between the inputs:
        //<name_of_method> <index/if exists> <value/if exists>
        String[] splitted_message = message.split("\\s+");
        if (splitted_message.length > 0)
            cur_method = splitted_message[0];
        else
            {cur_method = null;}
        if (splitted_message.length > 1)
            cur_index = splitted_message[1];
        else
            cur_index = null;
        if (splitted_message.length > 2)
            cur_value = splitted_message[2];
        else
            cur_value = null;
    }

    public synchronized Integer get(){
        return (Integer) vector.get(Integer.parseInt(cur_index));
    }

    public synchronized boolean set(){
        try {
            vector.set(Integer.parseInt(cur_index), Integer.parseInt(cur_value));
            return true;
        }
        catch (Exception outOfBound) {
            return false;
        }
    }

    public synchronized void add(){
        vector.add(Integer.parseInt(cur_index));
    }

    public synchronized boolean remove(){
        try {
            vector.remove(Integer.parseInt(cur_index));
            return true;
        } catch (Exception outOfBound) {
            return false;
        }
    }

    public synchronized String print(){
        String vector_to_string = "The vector contains: ";
        if(vector.size() == 0)
            return ("The vector is empty");
        for(int i=0; i<vector.size();i++) {
            vector_to_string =  vector_to_string + vector.elementAt(i).toString() + " ";
        }
        return vector_to_string;
    }

    public static void main(String[] args) throws IOException {
        Server server = new Server();
        server.serverWorking();
    }
}
