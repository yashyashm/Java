package liveboard;

import instrument.OrderType;

import java.util.concurrent.ConcurrentSkipListMap;

/**
 * Created by mjalota on 08/06/2019.
 */
public class SummaryCalc implements Runnable{
    private static ConcurrentSkipListMap<Double , Double> buyMap = new ConcurrentSkipListMap<Double, Double >();
    private static ConcurrentSkipListMap<Double , Double> sellMap = new ConcurrentSkipListMap<Double, Double >();

    private double quantity ;
    private double price ;
    private OrderType orderType ;

    public SummaryCalc(){}
    public SummaryCalc(double quantity, double price, OrderType orderType) {
        this.quantity = quantity;
        this.price = price;
        this.orderType = orderType ;
    }

    @Override
    public void run() {
        double totalQuantity = 0 ;
        if (this.orderType == OrderType.BUY){
            totalQuantity = buyMap.get(price) == null ? quantity : buyMap.get(price) + quantity ;
            buyMap.put(price, totalQuantity);
        } else if (this.orderType == OrderType.SELL){
            totalQuantity = sellMap.get(price) == null ? quantity : sellMap.get(price) + quantity ;
            sellMap.put(price, totalQuantity);
        }
    }

    public static ConcurrentSkipListMap<Double , Double> getBuyMap(){
        return buyMap;
    }

    public static ConcurrentSkipListMap<Double , Double> getSellMap(){
        return sellMap;
    }

    public void copy(double quantity, double price, OrderType orderType ){
        this.quantity = quantity ;
        this.price = price ;
        this.orderType = orderType ;
    }

    public double getQuantity() {
        return quantity;
    }

    public double getPrice() {
        return price;
    }

    public OrderType getOrderType() {
        return orderType;
    }

}
