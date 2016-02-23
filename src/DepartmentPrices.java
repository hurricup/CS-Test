import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by hurricup on 23.02.2016.
 */
public class DepartmentPrices {
    private final int departmentId;
    private final Map<String, ProductPrices> productPricesMap = new HashMap<String, ProductPrices>();

    public DepartmentPrices(int departmentId) {
        this.departmentId = departmentId;
    }

    public void addPrice(Price newPrice)
    {
        assert departmentId == newPrice.getDepartmentId();
        String productId = newPrice.getProductId();
        assert productId != null;
        ProductPrices productPrices = getProductPricesByCode(productId);
        if( productPrices == null )
        {
            getProductPricesMap().put(productId, productPrices = new ProductPrices(productId));
        }
        productPrices.addPrice(newPrice);
    }

    public int getDepartmentId() {
        return departmentId;
    }

    public Map<String, ProductPrices> getProductPricesMap() {
        return productPricesMap;
    }

    public ProductPrices getProductPricesByCode(String productCode)
    {
        return getProductPricesMap().get(productCode);
    }

    public List<Price> getAllPricesList()
    {
        List<Price> priceList = new ArrayList<Price>();
        for (ProductPrices productPrices: getProductPricesMap().values())
        {
            priceList.addAll(productPrices.getAllPricesList());
        }
        return priceList;
    }
}

