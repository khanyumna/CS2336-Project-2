//Name: Yumna Khan ID: YK220040

public class MyLinkedList<E extends Comparable<E>> {
    private Node<E> head;
    private Node<E> tail;

    //construct linked list
    public MyLinkedList() {
        head = null;
        tail = null;
    }

    public MyLinkedList(Node<E> initialNode) {
        head = initialNode;
        tail = initialNode;
    }

    //add node to linked list
    public void add(Node<E> node){
        if (head == null){ // if list is empy, set new node as head and tail
            head = node;
            tail = node;
        }
        else {
            tail.setNext(node); // append new node to end of list
            node.setPrevious(tail);
            tail = node;
        }
    }

    //find the middle of linked list
    private Node<E> findMiddle(Node<E> head){
        if (head == null){
            return null;
        }
        else {
            Node<E> walk = head; //traverses by 1
            Node<E> run = head.getNext(); //traverses by 2
            
            while(run != null){
                run = run.getNext();
                if (run != null){
                    walk = walk.getNext();
                    run = run.getNext();
                }
            } return walk;
        } 
    }

    //method to merge two sorted linked lists
    private Node<E> merge(Node<E> left,Node<E> right){
        if (left == null) { //if left list is empty, return the already sorted right lsit
            return right;
        }
        if (right == null){ //if right list is empty, return sorted left list.
            return left;
        }

        if (left.getPayload().compareTo(right.getPayload()) <= 0){ //compare payloads, merge left list with result of recursion
            left.setNext(merge(left.getNext(), right));            // merging "left.next" with "right".
            left.getNext().setPrevious(left);
            left.setPrevious(null);
            return left;
        }
        else {
            right.setNext(merge(left, right.getNext())); //if payload on right is smaller than left, merge right list with result of 
            right.getNext().setPrevious(right);          // merging 'left' with 'right.next'
            right.setPrevious(null);
            return right;

        }
    }

    public Node<E> mergeSort(Node<E> head){
        if (head == null || head.getNext() == null) { //base case
            return head;
        }

        Node<E> mid = findMiddle(head); //find middle
        Node<E> nextToMid = mid.getNext(); //get node next to the middle, split list in two
        mid.setNext(null); //break original list into two

        //recursively sort left and right lists
        Node<E> left = mergeSort(head);
        Node<E> right = mergeSort(nextToMid);

        //merge two sorted lists using merge function
        return merge(left, right);
    }
    
    public void sort() {
        if (head == null || head == tail){ //base case
            return;
        }
        else {
            head = mergeSort(head); //call mergeSort

            //traverse to the end of sorted list to update tail reference
            Node<E> current = head; 
            while (current.getNext() != null) {
                current = current.getNext();
            }
            tail = current;
        }
    }

    public MyLinkedList<E> searchArea(double area){
        MyLinkedList<E> matchedDrivers = new MyLinkedList<>();
        Node<E> current = head;

        while (current != null){
            if (current.getPayload() instanceof Driver){
                Driver driver = (Driver) current.getPayload();
                if (Math.abs(driver.getArea() - area) < 0.02){ //compare difference of driver area with target area
                    matchedDrivers.add(new Node<>(current.getPayload())); //add new node with matching payload
                }
            }
            current = current.getNext();
        }
        return matchedDrivers;
    }

    public MyLinkedList<E> searchName(String name){
        MyLinkedList<E> matchedDrivers = new MyLinkedList<>();
        Node<E> current = head;

        while (current != null){
            if (current.getPayload() instanceof Driver){
                Driver driver = (Driver) current.getPayload();
                if (driver.getName().equalsIgnoreCase(name)){ //compare drivers name with target name
                    matchedDrivers.add(current); //add node with matching payload
                }
            } current = current.getNext();
        } return matchedDrivers;
    }

    //custom toString method
    @Override
    public String toString() {
        String string = "";
        Node<E> current = head;
        while (current != null) {
            string += current.getPayload() + "\n";
            current = current.getNext();
        }
        return string;
    }
}




