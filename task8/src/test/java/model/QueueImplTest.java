package model;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.function.Predicate;

/**
 * Created by User on 13.05.2019.
 */
public class QueueImplTest {

    private QueueImpl<String> queue;
    private QueueImpl<String> other;
    private int size;

    @Before
    public void setUp() {
        queue = new QueueImpl<>();
        queue.add("Test1");
        queue.add("Test2");
        queue.add("goodQueue");
        size = 3;

        other = new QueueImpl<>();
        other.add("otherTest1");
        other.add("otherTest2");

    }

    @After
    public void tearDown() {
        queue = null;
        other = null;
    }

    @Test
    public void testPeek() {
        String message = "hello";
        queue.add(message);
        size++;
        Assert.assertEquals(queue.peek(), message);
        Assert.assertEquals(3, size);
    }

    @Test
    public void testPop() {
        Assert.assertNull("Pop в пустой очереди возвращает null", queue.pop());
    }

    @Test
    public void testAdd() {
        queue.add("MyTest");
        Assert.assertEquals("MyTest", queue.pop());
    }

    @Test
    public void testIndexOf() {
        String whatFind = "whatFind";
        Assert.assertEquals(2, queue.indexOf(new Predicate<String>() {
            @Override
            public boolean test(String searchFor) {
                if (searchFor.contains(whatFind))
                    return true;
                return false;
            }
        }));
    }

    @Test
    public void testIsEmpty() {
        Assert.assertEquals(0, size);
    }

    @Test
    public void addAll() {
        for (int i = 0; i <2 ; i++) {
            queue.add(other.pop());
        }


    }
}