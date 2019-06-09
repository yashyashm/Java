package objectmgmt;

import instrument.Order;
import mockit.Tested;
import mockit.integration.junit4.JMockit;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import static livebordconstants.Constants.NUMBER_OF_ORDER_OBJECTS_IN_POOL;
import static org.junit.Assert.*;

/**
 * Created by mjalota on 05/06/2019.
 */

@RunWith(JMockit.class)
public class OrderObjectPoolTest {

    @Tested
    OrderObjectPool orderObjectPool;

    @Before
    public void setUp() throws Exception {

    }

    @After
    public void tearDown() throws Exception {
    }

    @Test
    public void startCreatingOrderObjects() throws Exception {
        assertTrue(orderObjectPool.getQueue().size() == NUMBER_OF_ORDER_OBJECTS_IN_POOL);
    }
    @Test
    public void getOrderObject() throws Exception {
        Order order = OrderObjectPool.getOrderObject();
        assertTrue(orderObjectPool.getQueue().size() == NUMBER_OF_ORDER_OBJECTS_IN_POOL - 1 );
        orderObjectPool.putOrderObject(order);

        order = OrderObjectPool.getOrderObject();
        order = OrderObjectPool.getOrderObject();
        assertTrue(orderObjectPool.getQueue().size() == NUMBER_OF_ORDER_OBJECTS_IN_POOL - 2 );
        orderObjectPool.putOrderObject(order);
        orderObjectPool.putOrderObject(order);
        assertTrue(orderObjectPool.getQueue().size() == NUMBER_OF_ORDER_OBJECTS_IN_POOL);

    }

    @Test
    public void putOrderObject() throws Exception {

        assertTrue(orderObjectPool.getQueue().size() == NUMBER_OF_ORDER_OBJECTS_IN_POOL);
        Order order = OrderObjectPool.getOrderObject();
        assertTrue(orderObjectPool.getQueue().size() == NUMBER_OF_ORDER_OBJECTS_IN_POOL - 1 );

        orderObjectPool.putOrderObject(order);
        assertTrue(orderObjectPool.getQueue().size() == NUMBER_OF_ORDER_OBJECTS_IN_POOL);
    }

    @Test
    public void getQueue() throws Exception {
        assertTrue(orderObjectPool.getQueue() != null );
        assertTrue(orderObjectPool.getQueue().size() != 0 );

    }

}