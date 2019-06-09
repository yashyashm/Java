package objectmgmt;

import instrument.OrderType;
import liveboard.SummaryCalc;

import java.util.concurrent.ArrayBlockingQueue;

import static livebordconstants.Constants.*;

/**
 * Created by mjalota on 05/06/2019.
 */
public class SummaryCalcObjectPool {
    private static final ArrayBlockingQueue<SummaryCalc> queue = new ArrayBlockingQueue<SummaryCalc>(NUMBER_OF_SUMMARY_OBJECTS_IN_POOL);

    static{
        startCreatingSummaryObjects();
    }

    public static void startCreatingSummaryObjects(){
        for (int i = 0; i < NUMBER_OF_SUMMARY_OBJECTS_IN_POOL; i++){
            SummaryCalc summaryCalc = createSummaryCalc();
            queue.add(summaryCalc);
        }
    }

    public static SummaryCalc  getSummaryCalcObject(){
        if ( !queue.isEmpty() ) {
            return queue.poll();
        }
        else {
            return createSummaryCalc();
        }
    }
    public static void putSummaryCalcObject(SummaryCalc summaryCalc){
        queue.add(summaryCalc);
    }

    private static SummaryCalc createSummaryCalc(){
        SummaryCalc summaryCalc  = new SummaryCalc(DEFAULT_QUANTITY, DEFAULT_PRICE, OrderType.NONE);
        return summaryCalc ;
    }

    public ArrayBlockingQueue<SummaryCalc> getQueue(){
        return queue;
    }

}
