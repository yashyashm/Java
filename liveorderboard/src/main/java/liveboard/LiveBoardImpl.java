package liveboard;

import instrument.Order;
import instrument.OrderType;
import objectmgmt.OrderObjectPool;
import objectmgmt.SummaryCalcObjectPool;
import ordermgmt.OrderCache;

import java.util.*;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;


/**
 * Created by mjalota on 05/06/2019.
 */
public class LiveBoardImpl implements LiveBoard {
    public static ScheduledExecutorService sc = Executors.newScheduledThreadPool(1);

    public boolean registerOrder(int userId, double quantity , double price, OrderType orderType){
        Order order = OrderObjectPool.getOrderObject();
        order.copy(quantity, price, orderType);
        if (orderType == OrderType.BUY) {
            OrderCache.getBuyOrdersCache().computeIfAbsent(userId, k -> Collections.synchronizedList(new LinkedList<>())).add(order);
        } else  if (orderType == OrderType.SELL) {
            OrderCache.getSellOrdersCache().computeIfAbsent(userId, k -> Collections.synchronizedList(new LinkedList<>())).add(order);
        }

        SummaryCalc summaryCalc = SummaryCalcObjectPool.getSummaryCalcObject();
        summaryCalc.copy(quantity, price, orderType);

        sc.submit(summaryCalc);
        return true;
    }

    public boolean cancelOrder(int userId, double quantity , double price, OrderType orderType){
        List<Order> list = null ;
        if (orderType == OrderType.BUY) {
            list = OrderCache.getBuyOrdersCache().get(userId);
        } else  if (orderType == OrderType.SELL) {
            list = OrderCache.getSellOrdersCache().get(userId);
        }

        if (list == null ){
            return false;
        }

        // This new Order object will be on stack and not on heap as per the scope analysis.
        int orderNum = list.indexOf(new Order(quantity,price,orderType));
        OrderObjectPool.putOrderObject(list.get(orderNum));
        list.remove(orderNum);

        //subtract the cancel order from summary
        SummaryCalc summaryCalc = SummaryCalcObjectPool.getSummaryCalcObject();
        summaryCalc.copy( -quantity, -price, orderType);
        sc.submit(summaryCalc);

        return true;
    }

    public String getSummary(){
        StringBuilder sb = new StringBuilder();
        sb.append("BUY Order \n");
        for (Map.Entry e :SummaryCalc.getBuyMap().entrySet()){
            sb.append(e.getValue() + " kg for £" + e.getKey() + "\n");
        }

        sb.append("SELL Order \n");
        for (Map.Entry e : SummaryCalc.getSellMap().entrySet()){
            sb.append(e.getValue() + " kg for £" + e.getKey() + "\n");
        }

        return sb.toString();
    }
}
