package liveboard;

import instrument.OrderType;
import liveboard.LiveBoardImpl;
import mockit.Tested;
import mockit.integration.junit4.JMockit;
import ordermgmt.OrderCache;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import static java.lang.Thread.sleep;
import static org.junit.Assert.*;

/**
 * Created by mjalota on 05/06/2019.
 */
@RunWith(JMockit.class)
public class LiveBoardImplTest {

    @Tested
    LiveBoardImpl liveBoardImpl;

    @Before
    public void setUp() throws Exception {
        OrderCache.getBuyOrdersCache().clear();
        OrderCache.getSellOrdersCache().clear();
    }

    @After
    public void tearDown() throws Exception {

    }

    @Test
    public void registerOrder() throws Exception {
        int userId = 13 ;
        boolean res = liveBoardImpl.registerOrder(userId, 3.3, 5.6, OrderType.NONE.BUY);
        assertTrue(res );

        res = liveBoardImpl.registerOrder(userId, 3.3, 5.6, OrderType.NONE.BUY);
        assertTrue(res );

        res = liveBoardImpl.registerOrder(userId, 3.3, 5.6, OrderType.NONE.BUY);
        assertTrue(res );

        assertTrue(OrderCache.getBuyOrdersCache().size()== 1);
        assertTrue(OrderCache.getBuyOrdersCache().get(userId).size()== 3);
    }

    @Test
    public void cancelOrder() throws Exception {
        int userId = 13 ;
        boolean res = liveBoardImpl.registerOrder(userId, 3.3, 5.6, OrderType.NONE.BUY);
        assertTrue(res );

        res = liveBoardImpl.registerOrder(userId, 3.3, 5.6, OrderType.NONE.BUY);
        assertTrue(res );

        res = liveBoardImpl.registerOrder(userId, 3.3, 5.6, OrderType.NONE.BUY);
        assertTrue(res );

        liveBoardImpl.cancelOrder(userId, 3.3, 5.6, OrderType.NONE.BUY);
        assertTrue(OrderCache.getBuyOrdersCache().size()== 1);
        assertTrue(OrderCache.getBuyOrdersCache().get(userId).size()== 2);
    }

    @Test
    public void getSummary() throws Exception {
        int userId = 13 ;
        boolean res = liveBoardImpl.registerOrder(userId, 3.3, 5.6, OrderType.NONE.BUY);
        assertTrue(res );

        res = liveBoardImpl.registerOrder(userId, 3.3, 5.16, OrderType.NONE.BUY);
        assertTrue(res );

        res = liveBoardImpl.registerOrder(userId, 3.3, 2.6, OrderType.NONE.BUY);
        assertTrue(res );

        userId = 14 ;
        res = liveBoardImpl.registerOrder(userId, 3.3, 4.6, OrderType.NONE.SELL);
        assertTrue(res );

        res = liveBoardImpl.registerOrder(userId, 3.3, 5.6, OrderType.NONE.SELL);
        assertTrue(res );

        res = liveBoardImpl.registerOrder(userId, 3.3, 10.6, OrderType.NONE.SELL);
        assertTrue(res );

        String str =  liveBoardImpl.getSummary();
        assertTrue(str!= null);
        String[] strArray = str.split("\n");
        assertTrue(strArray[0].contains("BUY Order"));
        assertTrue(strArray[1].contains("3.3 kg for £2.6"));
        assertTrue(strArray[2].contains("3.3 kg for £5.16"));
        assertTrue(strArray[3].contains("3.3 kg for £5.6"));
        assertTrue(strArray[4].contains("SELL Order"));
        assertTrue(strArray[5].contains("3.3 kg for £4.6"));
        assertTrue(strArray[6].contains("3.3 kg for £5.6"));
        assertTrue(strArray[7].contains("3.3 kg for £10.6"));
    }

}