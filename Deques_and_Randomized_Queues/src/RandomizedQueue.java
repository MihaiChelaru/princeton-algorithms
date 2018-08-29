import java.util.Iterator;
import java.util.ListIterator;
import java.util.NoSuchElementException;

public class RandomizedQueue<Item> implements Iterable<Item> {
    private int size;
    private Item[] items;

    /**
     * Construct an empty randomized queue
     */
    public RandomizedQueue() {

    }

    /**
     * Returns true if randomized queue is empty
     */
    public boolean isEmpty() {
        return true;
    }

    /**
     * Returns the number of items on the randomized queue, NOT the size of
     * the containing array
     */
    public int size() {
        return size;
    }

    /**
     * Adds the item to the queue (does not matter where)
     */
    public void enqueue(Item item) {
        if (item == null) {
            throw new IllegalArgumentException("Cannot add null item to queue");
        }
    }

    /**
     * Removes and returns (pops) a random item from the queue using StdRandom
     */
    public Item dequeue() {
        if (size() == 0) {
            throw new NoSuchElementException("Cannot dequeue from empty queue");
        }
        return null;
    }

    /**
     * Returns a random item without removing it from the queue
     */
    public Item sample() {
        if (size() == 0) {
            throw new NoSuchElementException("Cannot sample from empty queue");
        }
        return null;
    }

    /**
     * Returns an independent iterator over the items in the queue in a
     * uniformly random order
     */
    public Iterator<Item> iterator() {
        return new ListIterator();
    }

    /**
     * Implements next() and hasNext() methods of standard iterators
     */
    private class ListIterator implements Iterator<Item> {

        @Override
        public boolean hasNext() {
            return false;
        }

        @Override
        public Item next() {
            return null;
        }

        @Override
        public void remove() {
            throw new UnsupportedOperationException("Remove not supported");
        }
    }

    /**
     * Early test client before writing unit tests
     */
    public static void main(String[] args) {

    }
}
