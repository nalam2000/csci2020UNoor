package dataStructures;

public class Queue {
    class Node {
        public String value;
        public Node next;
    }

    Node head = null; // Initiliaze as empty
    // TODO: Add the node class, and implement all of the required methods here

    public void enqueue(String newVal) {
        Node newNode = new Node();
        newNode.value = newVal;

        if (head == null) {
            head = newNode;
        } else {

            Node current = head;
            while (current.next != null)
                current = current.next;
            if (current.next == null) {
                current.next = newNode;
            }

        }
    }

    public String frontElement() {
        return head.value;

    }

    public boolean isEmpty() {
        if (head == null) {
            return true;
        } else {
            return false;
        }

    }

    public String dequeue() {
        Node prevHead;
        if (head == null) {
            return ("List is already empty");
        } else {

            prevHead = head;
            head = head.next;
        }
        return prevHead.value;
    }

    public String toString() {
        String result = "[ ";

        Node current = head;
        while (current != null) {
            result += current.value + " ";
            current = current.next;

        }

        return result + "]";
    }
}
