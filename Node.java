//Name: Yumna Khan ID: YK220040

public class Node<E extends Comparable<E>> {
    private E payload;
    private Node<E> next;
    private Node<E> previous;

    // set up payload method
    public Node(E payload){
        this.payload = payload;
        this.next = null;
        this.previous = null;
    }

    //method for getting payload
    public E getPayload() {
        return payload;
    }

    //method for setting payload
    public void setPayload(E payload){
        this.payload = payload;
    }

    //method for getting next variable
    public Node<E> getNext() {
        return next;
    }

    //method for setting next variable
    public void setNext(Node<E> next){
        this.next = next;
    }
    
    //method for getting previous variable
    public Node<E> getPrevious() {
        return previous;
    }

    //method for setting previous variable
    public void setPrevious (Node<E> previous){
        this.previous = previous;
    }

    //custom to string method
    @Override
    public String toString(){
        return "payload: " + payload;
    }
}
