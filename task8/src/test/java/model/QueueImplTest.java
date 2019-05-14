package model;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

/**
 * Created by User on 13.05.2019.
 */
public class QueueImplTest {

    private QueueImpl<String> queue;

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
    public void testpeek() throws Exception {

    }

    @Test
    public void testpop() throws Exception {
    }

    @Test
    public void testadd() throws Exception {
        queue.add("MyTest");
        Assert.assertEquals("MyTest", queue.pop());
    }

    @Test
    public void testfindIndex() throws Exception {

    }

    @Test
    public void testindexOf() throws Exception {

    }

    @Test
    public void testaddAll() throws Exception {

    }

    @Test
    public void testIsEmpty() throws Exception {
        Assert.assertTrue(queue.isEmpty());
    }
}