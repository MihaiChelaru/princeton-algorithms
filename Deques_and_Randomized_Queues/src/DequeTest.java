import org.testng.annotations.Test;
import org.testng.Assert;

public class DequeTest {
    @Test()
    public void dequeConstructorCreatesEmptyDeque() {
        Deque deque = new Deque();
        Assert.assertEquals(deque.size(), 0);
        Assert.assertTrue(deque.isEmpty());
    }

    @Test()
    public void dequeToStringTest() {
        Deque<Integer> deque = new Deque();
        Assert.assertEquals(deque.toString(), "");
        deque.addFirst(1);
        Assert.assertEquals(deque.toString(), "1 ");
    }

    @Test()
    public void addFirstTest() {
        Deque<Integer> deque = new Deque<>();
        deque.addFirst(1);
        Assert.assertEquals(deque.toString(), "1 ");
        Assert.assertEquals(deque.size(), 1);
    }

    @Test()
    public void addLastTest() {
        Deque<Integer> deque = new Deque<>();
        deque.addLast(2);
        Assert.assertEquals(deque.toString(), "2 ");
        Assert.assertEquals(deque.size(), 1);
    }

    @Test()
    public void addLastThenAddFirstShouldPrintInCorrectOrder() {
        Deque<Integer> deque = new Deque<>();
        deque.addFirst(1);
        deque.addLast(2);
        Assert.assertEquals(deque.toString(), "1 2 ");
    }

}
