import com.sun.xml.internal.ws.util.StringUtils;
import junit.framework.TestCase;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;

/**
 * Created by hurricup on 23.02.2016.
 */
public class PriceManagerTest extends TestCase {
    DateFormat df = new SimpleDateFormat("dd.MM.yyyy hh:mm:ss");

    public void testMainExample(){
        PricesManager pricesManager = new PricesManager();

        // was
        pricesManager.addPrice(0, "122856", 1,1, getDate("01.01.2013 00:00:00"), getDate("31.01.2013 23:59:59"), 11000);
        pricesManager.addPrice(0, "122856", 2,1, getDate("10.01.2013 00:00:00"), getDate("20.01.2013 23:59:59"), 99000);
        pricesManager.addPrice(0, "6654", 1,2, getDate("01.01.2013 00:00:00"), getDate("31.01.2013 00:00:00"), 5000);

        // added
        pricesManager.addPrice(0, "122856", 1,1, getDate("20.01.2013 00:00:00"), getDate("20.02.2013 23:59:59"), 11000);
        pricesManager.addPrice(0, "122856", 2,1, getDate("15.01.2013 00:00:00"), getDate("25.01.2013 23:59:59"), 92000);
        pricesManager.addPrice(0, "6654", 1,2, getDate("12.01.2013 00:00:00"), getDate("13.01.2013 00:00:00"), 4000);

        // test manager
        PricesManager testPricesManager = new PricesManager();
        testPricesManager.addPrice(0, "122856", 1, 1, getDate("01.01.2013 00:00:00"), getDate("20.02.2013 23:59:59"), 11000);
        testPricesManager.addPrice(0, "122856", 2, 1, getDate("10.01.2013 00:00:00"), getDate("15.01.2013 00:00:00"), 99000);
        testPricesManager.addPrice(0, "122856", 2, 1, getDate("15.01.2013 00:00:00"), getDate("25.01.2013 23:59:59"), 92000);

        testPricesManager.addPrice(0, "6654", 1, 2, getDate("01.01.2013 00:00:00"), getDate("12.01.2013 00:00:00"), 5000);
        testPricesManager.addPrice(0, "6654", 1, 2, getDate("12.01.2013 00:00:00"), getDate("13.01.2013 00:00:00"), 4000);
        testPricesManager.addPrice(0, "6654", 1, 2, getDate("13.01.2013 00:00:00"), getDate("31.01.2013 00:00:00"), 5000);

        assertPriceManagersEqual(testPricesManager, pricesManager);
    }

    public void testSample1()
    {
        PricesManager pricesManager = new PricesManager();

        // was
        pricesManager.addPrice(0, "test", 1, 1, getDate("01.01.2013 00:01:00"), getDate("01.01.2013 00:10:00"), 10000);

        // added
        pricesManager.addPrice(0, "test", 1, 1, getDate("01.01.2013 00:05:00"), getDate("01.01.2013 00:06:00"), 20000);

        // test set
        PricesManager testPricesManager = new PricesManager();
        testPricesManager.addPrice(0, "test", 1, 1, getDate("01.01.2013 00:01:00"), getDate("01.01.2013 00:05:00"), 10000);
        testPricesManager.addPrice(0, "test", 1, 1, getDate("01.01.2013 00:05:00"), getDate("01.01.2013 00:06:00"), 20000);
        testPricesManager.addPrice(0, "test", 1, 1, getDate("01.01.2013 00:06:00"), getDate("01.01.2013 00:10:00"), 10000);

        assertPriceManagersEqual(testPricesManager, pricesManager);
    }

    public void testSample2()
    {
        PricesManager pricesManager = new PricesManager();

        // was
        pricesManager.addPrice(0, "test", 1, 1, getDate("01.01.2013 00:01:00"), getDate("01.01.2013 00:10:00"), 10000);
        pricesManager.addPrice(0, "test", 1, 1, getDate("01.01.2013 00:10:00"), getDate("01.01.2013 00:20:00"), 20000);

        // added
        pricesManager.addPrice(0, "test", 1, 1, getDate("01.01.2013 00:08:00"), getDate("01.01.2013 00:15:00"), 30000);

        // test set
        PricesManager testPricesManager = new PricesManager();
        testPricesManager.addPrice(0, "test", 1, 1, getDate("01.01.2013 00:01:00"), getDate("01.01.2013 00:08:00"), 10000);
        testPricesManager.addPrice(0, "test", 1, 1, getDate("01.01.2013 00:08:00"), getDate("01.01.2013 00:15:00"), 30000);
        testPricesManager.addPrice(0, "test", 1, 1, getDate("01.01.2013 00:15:00"), getDate("01.01.2013 00:20:00"), 20000);

        assertPriceManagersEqual(testPricesManager, pricesManager);
    }

    public void testSample3()
    {
        PricesManager pricesManager = new PricesManager();

        // was
        pricesManager.addPrice(0, "test", 1, 1, getDate("01.01.2013 00:01:00"), getDate("01.01.2013 00:10:00"), 10000);
        pricesManager.addPrice(0, "test", 1, 1, getDate("01.01.2013 00:10:00"), getDate("01.01.2013 00:20:00"), 20000);
        pricesManager.addPrice(0, "test", 1, 1, getDate("01.01.2013 00:20:00"), getDate("01.01.2013 00:30:00"), 30000);

        // added
        pricesManager.addPrice(0, "test", 1, 1, getDate("01.01.2013 00:08:00"), getDate("01.01.2013 00:15:00"), 40000);
        pricesManager.addPrice(0, "test", 1, 1, getDate("01.01.2013 00:15:00"), getDate("01.01.2013 00:25:00"), 50000);

        // test set
        PricesManager testPricesManager = new PricesManager();
        testPricesManager.addPrice(0, "test", 1, 1, getDate("01.01.2013 00:01:00"), getDate("01.01.2013 00:08:00"), 10000);
        testPricesManager.addPrice(0, "test", 1, 1, getDate("01.01.2013 00:08:00"), getDate("01.01.2013 00:15:00"), 40000);
        testPricesManager.addPrice(0, "test", 1, 1, getDate("01.01.2013 00:15:00"), getDate("01.01.2013 00:25:00"), 50000);
        testPricesManager.addPrice(0, "test", 1, 1, getDate("01.01.2013 00:25:00"), getDate("01.01.2013 00:30:00"), 30000);

        assertPriceManagersEqual(testPricesManager, pricesManager);
    }

    public void testRightExtension()
    {
        PricesManager pricesManager = new PricesManager();

        // was
        pricesManager.addPrice(1, "test", 1, 1, getDate("01.01.2013 00:01:00"), getDate("01.01.2013 00:10:00"), 10000);

        // added
        pricesManager.addPrice(2, "test", 1, 1, getDate("01.01.2013 00:05:00"), getDate("01.01.2013 00:15:00"), 10000);

        // test set
        PricesManager testPricesManager = new PricesManager();
        testPricesManager.addPrice(1, "test", 1, 1, getDate("01.01.2013 00:01:00"), getDate("01.01.2013 00:15:00"), 10000);

        assertPriceManagersEqual(testPricesManager, pricesManager);
    }

    public void testLeftExtension()
    {
        PricesManager pricesManager = new PricesManager();

        // was
        pricesManager.addPrice(1, "test", 1, 1, getDate("01.01.2013 00:01:00"), getDate("01.01.2013 00:10:00"), 10000);

        // added
        pricesManager.addPrice(2, "test", 1, 1, getDate("31.12.2012 00:05:00"), getDate("01.01.2013 00:08:00"), 10000);

        // test set
        PricesManager testPricesManager = new PricesManager();
        testPricesManager.addPrice(1, "test", 1, 1, getDate("31.12.2012 00:05:00"), getDate("01.01.2013 00:10:00"), 10000);

        assertPriceManagersEqual(testPricesManager, pricesManager);
    }

    public void testAddition()
    {
        PricesManager pricesManager = new PricesManager();

        // was
        pricesManager.addPrice(1, "test", 1, 1, getDate("01.01.2013 00:01:00"), getDate("01.01.2013 00:10:00"), 10000);

        // added
        pricesManager.addPrice(2, "test", 1, 1, getDate("31.12.2012 00:05:00"), getDate("31.12.2012 00:08:00"), 10000);

        // test set a bit weird
        PricesManager testPricesManager = new PricesManager();
        testPricesManager.addPrice(2, "test", 1, 1, getDate("31.12.2012 00:05:00"), getDate("31.12.2012 00:08:00"), 10000);
        testPricesManager.addPrice(1, "test", 1, 1, getDate("01.01.2013 00:01:00"), getDate("01.01.2013 00:10:00"), 10000);

        assertPriceManagersEqual(testPricesManager, pricesManager);
    }


    public void testNewConsumesOld()
    {
        PricesManager pricesManager = new PricesManager();

        // was
        pricesManager.addPrice(1, "test", 1, 1, getDate("01.01.2013 00:05:00"), getDate("01.01.2013 00:10:00"), 10000);

        // added
        pricesManager.addPrice(2, "test", 1, 1, getDate("01.01.2013 00:01:00"), getDate("01.01.2013 00:15:00"), 10000);

        // test set a bit weird
        PricesManager testPricesManager = new PricesManager();
        testPricesManager.addPrice(1, "test", 1, 1, getDate("01.01.2013 00:01:00"), getDate("01.01.2013 00:15:00"), 10000);

        assertPriceManagersEqual(testPricesManager, pricesManager);
    }

    public void testOldConsumesNew()
    {
        PricesManager pricesManager = new PricesManager();

        // was
        pricesManager.addPrice(2, "test", 1, 1, getDate("01.01.2013 00:01:00"), getDate("01.01.2013 00:15:00"), 10000);

        // added
        pricesManager.addPrice(1, "test", 1, 1, getDate("01.01.2013 00:05:00"), getDate("01.01.2013 00:10:00"), 10000);

        // test set a bit weird
        PricesManager testPricesManager = new PricesManager();
        testPricesManager.addPrice(2, "test", 1, 1, getDate("01.01.2013 00:01:00"), getDate("01.01.2013 00:15:00"), 10000);

        assertPriceManagersEqual(testPricesManager, pricesManager);
    }

    protected String dumpPrices(PricesManager manager)
    {
        List<String> dumpLines = new ArrayList<String>();
        for (Price price: manager.getAllPricesList())
        {
            dumpLines.add(price.toString());
        }
        Collections.sort(dumpLines);

        StringBuilder builder = new StringBuilder();
        for( String line: dumpLines)
        {
            builder.append(line);
            builder.append('\n');
        }
        return builder.toString();
    }

    protected void assertPriceManagersEqual(PricesManager expectedManager, PricesManager realManager)
    {
        if( expectedManager == realManager) return;

        List<Price> exprectedList = expectedManager.getAllPricesList();
        List<Price> realList = realManager.getAllPricesList();

        if( exprectedList.size() != realList.size())
        {
            fail("Lists has different sizes:\n" +
                    "Expected:\n" +
                    dumpPrices(expectedManager) +
                    "Real:\n" +
                    dumpPrices(realManager)
            );
        };

        for (Price priceA: exprectedList)
        {
            if( !realList.contains(priceA)) fail("Missing expected entry:" + priceA + "\n" +
                    "Expected:\n" +
                            dumpPrices(expectedManager) +
                            "Real:\n" +
                            dumpPrices(realManager)
                );
        }
    }

    protected Date getDate(String date)
    {
        try {
            return df.parse(date);
        }
        catch(Exception e){
            throw new RuntimeException(e);
        }
    }
}
