package liveboard;

import instrument.OrderType;

/**
 * Created by mjalota on 08/06/2019.
 */
public interface LiveBoard {
    public boolean registerOrder(int userId, double quantity, double price, OrderType orderType);

    public boolean cancelOrder(int userId, double quantity, double price, OrderType orderType);

    public String getSummary();

}
