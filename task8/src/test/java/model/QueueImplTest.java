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

    private QueueImpl<String> queue = new QueueImpl<>();

    @Before
    public void setUp() throws Exception {
        queue = new QueueImpl<>();
        queue.add("Test1");
        queue.add("Test2");
        queue.add("goodQueue");
    }

    @After
    public void tearDown() throws Exception {
        queue = null;
    }

    @Test
    public void testPeek() throws Exception {
        String message = "hello";
        queue.add(message);
        int size = queue.size();
        Assert.assertEquals(queue.peek(), message);
        Assert.assertEquals(queue.size(), size);
    }

    @Test
    public void testPop() throws Exception {
        Assert.assertNull("Pop в пустой очереди возвращает null", queue.pop());
    }

    @Test
    public void testAdd() throws Exception {
        queue.add("MyTest");
        Assert.assertEquals("MyTest", queue.pop());
    }

    @Test
    public void testIndexOf() throws Exception {
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
    public void testIsEmpty() throws Exception {
        Assert.assertTrue(queue.isEmpty());
        Assert.assertEquals(queue.size(), 0);
    }
}