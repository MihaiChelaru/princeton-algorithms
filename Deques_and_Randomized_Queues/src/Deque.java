public class Deque implements Iterable<Item> {

    private Node first;
    private Node last;
    private int size;

    /**
     * Deque constructor, initializes the first and last elements to null,
     * and sets the starting size to 0, signifying an empty Deque
     */
    public Deque() {
        this.first = null;
        this.last = null;
        this.size = 0;
    }

    /**
     * Inner class Node for implementing a singly linked list as the basis
     * for the parent Deque class
     */
    private class Node{

        Item value;
        Item next;

        Node() {

        }
    }

    /**
     * Checks if Deque() contains no elements
     * @return true if size == 0, else false
     */
    public boolean isEmpty() {
        return this.size == 0;
    }

    /**
     * Checks the number of elements in the Deque data structure
     * @return size of Deque
     */
    public int size() {
        return this.size;
    }

    /**
     * Adds an element to the front of the Deque
     * @param item to be added to the front
     */
    public void addFirst(Item item) {
        if (item == null) {
            throw new IllegalArgumentException("Item cannot be null");
        }
    }

    /**
     * Adds an element to the end of the Deque
     * @param item to be added to the end
     */
    public void addLast(Item item) {

    }

    /**
     * Pops the first element of the Deque, removing it and also returning it
     * @return first element of Deque
     */
    public Item removeFirst() {

    }

    /**
     * Pops the last element of the Deque, removing it and also returning it
     * @return last element of Deque
     */
    public Item removeLast() {

    }

    /**
     * Iterator implementation to make Deque an iterable class
     * @return Iterator of the type declared when the Deque is first
     * constructed that allows iterating over the elements in the Deque from
     * first to last
     */
    public Iterator<Item> iterator() {

    }

    /**
     * Optional test client
     * @param args
     */
    public static void main(String[] args) {

    }
}
