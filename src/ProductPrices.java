import java.util.*;

/**
 * Created by hurricup on 23.02.2016.
 */
public class ProductPrices {
    private final String productId;
    private Map<Integer, List<Price>> allPrices = new HashMap<Integer, List<Price>>();

    public ProductPrices(String productId) {
        assert productId != null;
        this.productId = productId;
    }

    public void addPrice(Price newPrice) {
        assert productId.equals(newPrice.getProductId());

        List<Price> currentPrices = getPricesByNumber(newPrice.getPriceNumber());
        List<Price> newPrices = new ArrayList<Price>();

        boolean consumed = false;

        if (currentPrices != null) {
            for (Price currentPrice : currentPrices) {
                Date currentPriceStart = currentPrice.getStartDate();
                Date currentPriceEnd = currentPrice.getEndDate();
                Date newPriceStart = newPrice.getStartDate();
                Date newPriceEnd = newPrice.getEndDate();
                boolean sameValue = currentPrice.getValue() == newPrice.getValue();

                if (!currentPriceStart.after(newPriceEnd) && !currentPriceEnd.before(newPriceStart)) {
                    int startCompare = currentPriceStart.compareTo(newPriceStart);
                    int endCompare = currentPriceEnd.compareTo(newPriceEnd);

                    if (startCompare >= 0 && endCompare <= 0) // consumed
                    {
                        if (sameValue) {
                            currentPrice.setStartDate(newPriceStart);
                            currentPrice.setEndDate(newPriceEnd);
                            consumed = true;
                        } else {
                            continue;
                        }
                    }
                    else if (startCompare < 0 && endCompare > 0) // splitted
                    {
                        if (sameValue) {
                            consumed = true;
                        } else {
                            Price currentPriceCopy = new Price(currentPrice);
                            currentPriceCopy.setEndDate(new Date(newPriceStart.getTime()));
                            newPrices.add(currentPriceCopy);

                            currentPrice.setStartDate(new Date(newPriceEnd.getTime()));
                        }
                    } else if (startCompare < 0 && endCompare <= 0) // adjust end
                    {
                        if (sameValue) {
                            currentPrice.setEndDate(newPriceEnd);
                            consumed = true;
                        } else {
                            currentPrice.setEndDate(new Date(newPriceStart.getTime()));
                        }
                    } else if (startCompare >= 0 && endCompare > 0) // adjust start
                    {
                        if (sameValue) {
                            currentPrice.setStartDate(newPriceStart);
                            consumed = true;
                        } else {
                            currentPrice.setStartDate(new Date(newPriceEnd.getTime()));
                        }
                    } else {
                        throw new RuntimeException("Can't be: " );
                    }
                }
                newPrices.add(currentPrice);
            }
        }

        if (!consumed)
            newPrices.add(newPrice);

        getAllPrices().put(newPrice.getPriceNumber(), newPrices);
    }

    public Map<Integer, List<Price>> getAllPrices() {
        return allPrices;
    }

    public List<Price> getPricesByNumber(int priceNumber) {
        return getAllPrices().get(priceNumber);
    }

    public String getProductId() {
        return productId;
    }

    public List<Price> getAllPricesList() {
        List<Price> priceList = new ArrayList<Price>();
        for (List<Price> prices : getAllPrices().values()) {
            priceList.addAll(prices);
        }
        return priceList;
    }

}
