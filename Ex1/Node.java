public class Node {
    private int value;
    public Node next_node;

    Node(){}//default constructor
    public void setValue(int _value) {
        value = _value;
    }

    public void setNext_node(Node _next_node) {
        next_node = _next_node;
    }

    public int getValue(){
        return  value;
    }
    public static void main(String[] args) {
    }


}
