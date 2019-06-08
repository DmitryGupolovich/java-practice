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

    @Before
    public void setUp() {
        queue = new QueueImpl<>();
        other = new QueueImpl<>();
    }

    @After
    public void tearDown() {
        queue = null;
        other = null;
    }

    @Test
    public void testPeek() {
        String message = "Test1";
        queue.add(message);
        Assert.assertEquals(queue.peek(), message);
    }

    @Test
    public void testPop() {
        queue.add("testPop");
        Assert.assertEquals("testPop", queue.pop());
    }

    @Test
    public void testIndexOf() {
        String whatFind = "whatFind";
        queue.add(whatFind);
        Assert.assertEquals(0, queue.indexOf(new Predicate<String>() {
            @Override
            public boolean test(String searchFor) {
                if (searchFor.contains(whatFind))
                    return true;
                return false;
            }
        }));

    }

    @Test
    public void testNotFoundIndexOf() {
        String whatFind = "whatFind2";
        queue.add("whatFind");
        Assert.assertEquals(-1, queue.indexOf(new Predicate<String>() {
            @Override
            public boolean test(String searchFor) {
                if (searchFor.contains(whatFind))
                    return true;
                return false;
            }
        }));

    }
    @Test
    public void addAll() {
        other.add("otherTest1");
        other.add("otherTest2");

        queue.addAll(other);
        Assert.assertNotNull(queue);
        Assert.assertEquals("otherTest1", queue.pop());
        Assert.assertEquals("otherTest2", queue.pop());

    }
}