package ordermgmt;

import instrument.Order;

import java.util.List;
import java.util.concurrent.ConcurrentHashMap;

import static livebordconstants.Constants.NUMBER_OF_ORDER_OBJECTS_IN_POOL;

/**
 * Created by mjalota on 05/06/2019.
 */
public class OrderCache {
    private static ConcurrentHashMap<Integer, List<Order>> buyOrders = new ConcurrentHashMap<Integer, List<Order>>(NUMBER_OF_ORDER_OBJECTS_IN_POOL);
    private static ConcurrentHashMap<Integer, List<Order>> sellOrders = new ConcurrentHashMap<Integer, List<Order>>(NUMBER_OF_ORDER_OBJECTS_IN_POOL);

    public static  ConcurrentHashMap<Integer, List<Order>> getBuyOrdersCache(){
        return buyOrders ;
    }
    public static  ConcurrentHashMap<Integer, List<Order>> getSellOrdersCache(){
        return sellOrders ;
    }

}
