package instrument;

/**
 * Created by mjalota on 05/06/2019.
 */
public class Order {
    private  double quantity ;
    private  double price   ;
    private  OrderType orderType ;

    // not using builder as all these parameters are mandatory
    public Order(double quantity, double price, OrderType orderType) {
        this.quantity = quantity;
        this.price = price;
        this.orderType = orderType;
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

    @Override
    public String toString() {
        return "Order{" + ", quantity=" + quantity + ", price=" + price +", orderType=" + orderType +'}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Order order = (Order) o;

        if (quantity != order.quantity) return false;
        if (Double.compare(order.price, price) != 0) return false;
        return orderType == order.orderType;
    }

    @Override
    public int hashCode() {
        int result;
        long temp;
        temp = Double.doubleToLongBits(quantity);
        result = (int) (temp ^ (temp >>> 32));
        temp = Double.doubleToLongBits(price);
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        result = 31 * result + orderType.hashCode();
        return result;
    }

    public void copy(double quantity, double price, OrderType orderType ){
        this.quantity = quantity ;
        this.price = price ;
        this.orderType = orderType ;
    }

}
