import org.testng.Assert;
import org.testng.annotations.Test;

public class RandomizedQueueTest {
    @Test()
    public void randomizedQueueConstructorCreatesEmptyDeque() {
        RandomizedQueue<Integer> queue = new RandomizedQueue<>();
        Assert.assertEquals(queue.size(), 0);
        Assert.assertTrue(queue.isEmpty());
    }

    @Test()
    public void enqueueTest() {
        RandomizedQueue<Integer> queue = new RandomizedQueue<>();
        queue.enqueue(1);
        queue.enqueue(2);
        Assert.assertEquals(queue.size(), 2);
    }

    @Test()
    public void dequeueTest() {
        RandomizedQueue<Integer> queue = new RandomizedQueue<>();
        queue.enqueue(1);
        int item1 = queue.dequeue();
        queue.enqueue(2);
        int item2 = queue.dequeue();
        Assert.assertEquals(item1, 1);
        Assert.assertEquals(item2, 2);
        Assert.assertTrue(queue.isEmpty());
    }

    @Test()
    public void sampleTest() {
        RandomizedQueue<Integer> queue = new RandomizedQueue<>();
        queue.enqueue(1);
        queue.enqueue(2);
        queue.sample();
        Assert.assertEquals(queue.size(), 2);
    }

    @Test()
    public void iteratorTest() {
        RandomizedQueue<Integer> queue = new RandomizedQueue<>();
        queue.enqueue(1);
        queue.enqueue(2);
        int sum = 0;
        for (Integer element : queue) {
            sum += element;
        }
        Assert.assertEquals(sum, 3);
    }

}
