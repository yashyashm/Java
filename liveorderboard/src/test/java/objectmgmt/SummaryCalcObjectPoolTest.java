package objectmgmt;

import mockit.Tested;
import mockit.integration.junit4.JMockit;
import liveboard.SummaryCalc;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import static livebordconstants.Constants.NUMBER_OF_SUMMARY_OBJECTS_IN_POOL;
import static org.junit.Assert.*;

/**
 * Created by mjalota on 08/06/2019.
 */
@RunWith(JMockit.class)
public class SummaryCalcObjectPoolTest {

    @Tested
    SummaryCalcObjectPool summaryCalcObjectPool ;

    @Before
    public void setUp() throws Exception {
    }

    @After
    public void tearDown() throws Exception {
    }

    @Test
    public void startCreatingSummaryObjects() throws Exception {
        assertTrue(summaryCalcObjectPool.getQueue().size() == NUMBER_OF_SUMMARY_OBJECTS_IN_POOL);
    }

    @Test
    public void getSummaryCalcObject() throws Exception {
        SummaryCalc summaryCalc = SummaryCalcObjectPool.getSummaryCalcObject();
        assertTrue(summaryCalcObjectPool.getQueue().size() == NUMBER_OF_SUMMARY_OBJECTS_IN_POOL - 1 );
        summaryCalcObjectPool.putSummaryCalcObject(summaryCalc);

        summaryCalc = SummaryCalcObjectPool.getSummaryCalcObject();
        summaryCalc = SummaryCalcObjectPool.getSummaryCalcObject();
        assertTrue(summaryCalcObjectPool.getQueue().size() == NUMBER_OF_SUMMARY_OBJECTS_IN_POOL - 2 );
        summaryCalcObjectPool.putSummaryCalcObject(summaryCalc);
        summaryCalcObjectPool.putSummaryCalcObject(summaryCalc);
        assertTrue(summaryCalcObjectPool.getQueue().size() == NUMBER_OF_SUMMARY_OBJECTS_IN_POOL);

    }

    @Test
    public void putSummaryCalcObject() throws Exception {
        assertTrue(summaryCalcObjectPool.getQueue().size() == NUMBER_OF_SUMMARY_OBJECTS_IN_POOL);
        SummaryCalc summaryCalc = SummaryCalcObjectPool.getSummaryCalcObject();
        assertTrue(summaryCalcObjectPool.getQueue().size() == NUMBER_OF_SUMMARY_OBJECTS_IN_POOL - 1 );

        summaryCalcObjectPool.putSummaryCalcObject(summaryCalc);
        assertTrue(summaryCalcObjectPool.getQueue().size() == NUMBER_OF_SUMMARY_OBJECTS_IN_POOL);

    }

    @Test
    public void getQueue() throws Exception {
        assertTrue(summaryCalcObjectPool.getQueue() != null );
        assertTrue(summaryCalcObjectPool.getQueue().size() != 0 );

    }

}