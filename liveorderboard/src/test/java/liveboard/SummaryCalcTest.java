package liveboard;

import instrument.Order;
import instrument.OrderType;
import mockit.Tested;
import mockit.integration.junit4.JMockit;
import org.junit.Test;
import org.junit.runner.RunWith;

import static org.junit.Assert.*;

/**
 * Created by mjalota on 08/06/2019.
 */
@RunWith(JMockit.class)
public class SummaryCalcTest {

    @Tested
    SummaryCalc summaryCalc ;


    @Test
    public void copy() throws Exception {
        summaryCalc.copy(2, 2.3, OrderType.BUY);
        assertTrue(summaryCalc.getQuantity() == 2);
        assertTrue(summaryCalc.getPrice() == 2.3);
        assertTrue(summaryCalc.getOrderType() == OrderType.BUY);

    }

}