
public class MyLinkedList {
    Node first_node;
    int num_of_nodes;
    MyLinkedList(){}//default constructor
    public void setFirst_node(Node _first_node) {
        first_node = _first_node;
        num_of_nodes++;
    }

    public void setNum_of_nodes(int _num_of_nodes) {
        num_of_nodes = _num_of_nodes;
    }

    public void addNode(Node n){
        Node temp_node = new Node();
        if(num_of_nodes == 0)
            first_node =n;//edge case
        else {
            temp_node = first_node;
            for (int i = 0; i < num_of_nodes - 1; i++) {
                temp_node = temp_node.next_node;
            }//getting to the end of the list
            temp_node.next_node = n;//making the last node - the n node
        }
        num_of_nodes++;//adding 1 node to the number of nodes in the list
    }
    public void addNode(Node n, int index){
        if(index>num_of_nodes){
            System.out.println("you're index is not on the list");
            addNode(n);
        }
        else {
            Node temp_node = new Node();
            temp_node = first_node;
            for (int i = 0; i < index-2; i++) {//assuming the indexes start from 1 and not 0
                temp_node = temp_node.next_node;
            }//getting to the the node that is *before* the wanted node
            Node temp_node2 = new Node();//this will be the node that we're taking it's place
            temp_node2 = temp_node.next_node;//assigning the wanted node to become the next node
            temp_node.next_node = n;//now the wanted node is "pointing" to our node
            n.next_node = temp_node2;//the 'n' node's next node is now the node that was in place 'index'
            num_of_nodes++;//adding 1 node to the number of nodes in the list
        }
    }
    public void removeNode(){
        Node temp_node = new Node();
        temp_node= first_node;
        for(int i=0; i<num_of_nodes-1;i++) {
            temp_node = temp_node.next_node;
        }//getting to the node before the end of the list
        temp_node.next_node = null;
        num_of_nodes--;//reducing 1 node from the number of nodes in the list
    }
    public void removeNode(int index){
        if(index>num_of_nodes){
            System.out.println("you're index is not on the list");
        }
        else {
            Node temp_node = new Node();
            temp_node = first_node;
            for (int i = 0; i < index-2; i++) {//assuming the indexes start from 1 and not 0
                temp_node = temp_node.next_node;
            }//getting to the the node that is *before* the wanted node
            Node temp_node2 = new Node();//this will be the node that we're taking it's place
            temp_node2 = temp_node.next_node;//the node we want to delete
            temp_node.next_node = temp_node2.next_node;//now the node 'index-1' is pointing to 'index+1'
            num_of_nodes--;//reducing 1 node from the number of nodes in the list
        }
    }
    public void printList(){
        Node temp_node = new Node();
        temp_node = first_node;
        for(int i=0; i<num_of_nodes;i++){
            System.out.println("node number " + (i+1) + " is " + temp_node.getValue());
            temp_node = temp_node.next_node;
        }
    }

    public void reverse(){
        Node node1 = null;
        Node node2 = new Node();
        Node node3 = null;
        node2 = first_node;
        //since at the end of every loop node2 is equal to node3 which is node2.next_node -
        //we make sure we're going over all of the list
        while(node2 != null) {
            node3 = node2.next_node;
            node2.next_node = node1;
            node1 = node2;
            node2 = node3;
        }
        first_node = node1; //the new head of the reverse linked list
    }    
}

