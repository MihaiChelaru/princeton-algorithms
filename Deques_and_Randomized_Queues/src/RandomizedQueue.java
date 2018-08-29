import edu.princeton.cs.algs4.StdRandom;

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
        items = (Item[]) new Object[2];
        size = 0;
    }

    /**
     * Returns true if randomized queue is empty
     */
    public boolean isEmpty() {
        return size == 0;
    }

    /**
     * Returns the number of items on the randomized queue, NOT the size of
     * the containing array
     */
    public int size() {
        return size;
    }

    /**
     * Resize the array representing the RandomizedQueue to either accomodate
     * more objects or save space when there are less objects
     */
    private void resize(int capacity) {
        assert capacity >= size;

        Item[] temp = (Item[]) new Object[capacity];
        for (int i = 0; i < size; i++) {
            temp[i] = items[i];
        }
        items = temp;
    }

    /**
     * Adds the item to the queue (does not matter where)
     */
    public void enqueue(Item item) {
        if (item == null) {
            throw new IllegalArgumentException("Cannot add null item to queue");
        }
        items[size] = item;
        size++;
        if (size == items.length) {
            resize(size * 2);
        }
    }

    /**
     * Removes and returns (pops) a random item from the queue using StdRandom
     */
    public Item dequeue() {
        if (size() == 0) {
            throw new NoSuchElementException("Cannot dequeue from empty queue");
        }
        int rand = StdRandom.uniform(0, size);
        Item item = items[rand];
        items[rand] = items[size - 1];
        items[size - 1] = null;
        size--;
        if (size <= items.length / 4) {
            resize(items.length / 2);
        }
        return item;
    }

    /**
     * Returns a random item without removing it from the queue
     */
    public Item sample() {
        if (size() == 0) {
            throw new NoSuchElementException("Cannot sample from empty queue");
        }
        int rand = StdRandom.uniform(0, size);
        return items[rand];
    }

    /**
     * Returns an independent iterator over the items in the queue in a
     * uniformly random order
     */
    public Iterator<Item> iterator() {
        StdRandom.shuffle(items, 0, size);
        return new ListIterator();
    }

    /**
     * Implements next() and hasNext() methods of standard iterators
     */
    private class ListIterator implements Iterator<Item> {
        int currentIndex = 0;

        @Override
        public boolean hasNext() {
            return currentIndex < size;
        }

        @Override
        public Item next() {
            return items[currentIndex++];
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
