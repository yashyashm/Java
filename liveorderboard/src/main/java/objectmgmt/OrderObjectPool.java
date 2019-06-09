package objectmgmt;

import instrument.Order;
import instrument.OrderType;

import java.util.concurrent.ArrayBlockingQueue;
import static livebordconstants.Constants.DEFAULT_PRICE;
import static livebordconstants.Constants.DEFAULT_QUANTITY;
import static livebordconstants.Constants.NUMBER_OF_ORDER_OBJECTS_IN_POOL;

/**
 * Created by mjalota on 05/06/2019.
 */
public class OrderObjectPool {
    private static final ArrayBlockingQueue<Order> queue = new ArrayBlockingQueue<Order>(NUMBER_OF_ORDER_OBJECTS_IN_POOL);

    static{
        startCreatingOrderObjects();
    }

    public static void startCreatingOrderObjects(){
        for (int i = 0; i < NUMBER_OF_ORDER_OBJECTS_IN_POOL; i++){
            Order order = createOrder();
            queue.add(order);
        }
    }

    public static Order  getOrderObject(){
        if ( !queue.isEmpty() ) {
            return queue.poll();
        }
        else {
            return createOrder();
        }
    }
    public static void putOrderObject(Order order){
        queue.add(order);
    }

    private static Order createOrder(){
        Order order  = new Order(DEFAULT_QUANTITY, DEFAULT_PRICE, OrderType.NONE);
        return order ;
    }

    public ArrayBlockingQueue<Order> getQueue(){
        return queue;
    }

}
