import java.util.Iterator;
import java.util.NoSuchElementException;

public class Deque<Item> implements Iterable<Item> {

    private Node first;
    private Node last;
    private int size;

    /**
     * Inner class Node for implementing a doubly linked list as the basis
     * for the parent Deque class
     */
    private class Node {
        private Item item;
        private Node next;
        private Node previous;
    }

    /**
     * Deque constructor, initializes an empty Deque
     */
    public Deque() {
        this.first = null;
        this.last = null;
        this.size = 0;
    }

    /**
     * Checks if Deque() contains no elements
     *
     * @return true if size == 0, else false
     */
    public boolean isEmpty() {
        return size == 0;
    }

    /**
     * Checks the number of elements in the Deque data structure
     *
     * @return size of Deque
     */
    public int size() {
        return size;
    }

    /**
     * Adds an element to the front of the Deque
     *
     * @param item to be added to the front
     */
    public void addFirst(Item item) {
        if (item == null) {
            throw new IllegalArgumentException("Item cannot be null");
        }
        Node oldFirst = first;
        first = new Node();
        first.item = item;
        first.next = oldFirst;
        first.previous = null;
        if (oldFirst != null) {
            oldFirst.previous = first;
        } else {
            last = first;
        }
        size++;
    }

    /**
     * Adds an element to the end of the Deque
     *
     * @param item to be added to the end
     */
    public void addLast(Item item) {
        if (item == null) {
            throw new IllegalArgumentException("Item cannot be null");
        }
        Node oldLast = last;
        last = new Node();
        last.item = item;
        last.next = null;
        last.previous = oldLast;
        if (oldLast != null) {
            oldLast.next = last;
        } else {
            first = last;
        }
        size++;
    }

    /**
     * Pops the first element of the Deque, removing it and also returning it
     *
     * @return first element of Deque
     */
    public Item removeFirst() {
        if (isEmpty()) {
            throw new NoSuchElementException();
        }
        Node oldFirst = first;
        Item firstValue = oldFirst.item;
        first = oldFirst.next;
        if (first != null) {
            first.previous = null;
        }
        if (oldFirst == last) {
            last = null;
        }
        oldFirst = null;
        size--;
        return firstValue;
    }

    /**
     * Pops the last element of the Deque, removing it and also returning it
     *
     * @return last element of Deque
     */
    public Item removeLast() {
        if (isEmpty()) {
            throw new NoSuchElementException();
        }
        Node oldLast = last;
        Item lastValue = oldLast.item;
        last = oldLast.previous;
        if (last != null) {
            last.next = null;
        }
        if (oldLast == first) {
            first = null;
        }
        oldLast = null;
        size--;
        return lastValue;
    }

    /**
     * Iterator implementation to make Deque an iterable class
     *
     * @return Iterator of the type declared when the Deque is first
     * constructed that allows iterating over the elements in the Deque from
     * first to last
     */
    public Iterator<Item> iterator() {
        return new ListIterator();
    }

    private class ListIterator implements Iterator<Item> {
        private Node current = first;

        @Override
        public boolean hasNext() {
            return current != null;
        }

        @Override
        public void remove() {
            throw new UnsupportedOperationException();
        }

        @Override
        public Item next() {
            if (!hasNext()) throw new NoSuchElementException();
            Item item = current.item;
            current = current.next;
            return item;
        }
    }

    /**
     * toString helper to facilitate testing
     */
    @Override
    public String toString() {
        StringBuilder s = new StringBuilder();
        for (Item item : this) {
            s.append(item + " ");
        }
        return s.toString();
    }

    /**
     * Optional test client
     */
    public static void main(String[] args) {
        Deque<Integer> deque = new Deque<>();
        deque.addFirst(5);
        System.out.println(deque.toString());
        deque.removeFirst();
        System.out.println(deque.toString());
        deque.addFirst(9);
        deque.addLast(6);
        System.out.println(deque.toString());
        deque.removeLast();
        System.out.println(deque.toString());
    }
}
