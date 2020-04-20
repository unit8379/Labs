package rpis82.ezhov.oop.model;

public class Node {
    private Service value;
    private Node next;
    private Node previous;

    public Node(Service value) { this.value = value; }

    public Node(Service value, Node next) {
        this.value = value;
        this.next = next;
    }

    public Node(Service value, Node next, Node previous) {
        this.value = value;
        this.next = next;
        this.previous = previous;
    }

    public void setNext(Node next) {
        this.next = next;
    }

    public void setPrevious(Node previous) {
        this.previous = previous;
    }

    public void setValue(Service value) { this.value = value; }

    public Node getNext() { return next; }

    public Node getPrevious() { return previous; }

    public Service getValue() { return value; }
}
