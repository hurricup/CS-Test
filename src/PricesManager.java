import java.util.*;

/**
 * Created by hurricup on 23.02.2016.
 */
public class PricesManager {
    private Map<Integer, DepartmentPrices> departmentPricesMap = new HashMap<Integer, DepartmentPrices>();

    public void addPrice(long priceId, String productId, int priceNumber, int departmentId, Date startDate, Date endDate, long value) {
        addPrice(new Price(priceId, productId, priceNumber, departmentId, startDate, endDate, value));
    }

    public void addPrice(Price newPrice)
    {
        int departmentId = newPrice.getDepartmentId();
        DepartmentPrices departmentPrices = getDepartmentPrices(departmentId);
        if( departmentPrices == null )
        {
            getDepartmentPricesMap().put(departmentId, departmentPrices = new DepartmentPrices(departmentId));
        }
        departmentPrices.addPrice(newPrice);

    }

    public DepartmentPrices getDepartmentPrices(int departmentId)
    {
        return getDepartmentPricesMap().get(departmentId);
    }

    public Map<Integer, DepartmentPrices> getDepartmentPricesMap() {
        return departmentPricesMap;
    }

    public List<Price> getAllPricesList()
    {
        List<Price> priceList = new ArrayList<Price>();
        for (DepartmentPrices departmentPrices: getDepartmentPricesMap().values())
        {
            priceList.addAll(departmentPrices.getAllPricesList());
        }
        return priceList;
    }
}
