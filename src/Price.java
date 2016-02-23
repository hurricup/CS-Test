import java.util.Date;
import java.util.List;

/**
 * Created by hurricup on 23.02.2016.
 */
public class Price{
    private long priceId;
    private String productId; // fixme redundant
    private int departmentId; // fixme redundant
    private int priceNumber;
    private Date startDate;
    private Date endDate;
    private long value;

    public Price(long priceId, String productId, int priceNumber, int departmentId, Date startDate, Date endDate, long value) {
        this.priceId = priceId;
        this.productId = productId;
        this.priceNumber = priceNumber;
        this.departmentId = departmentId;
        this.startDate = startDate;
        this.endDate = endDate;
        this.value = value;
    }

    public Price(Price price)
    {
        priceId = price.getPriceId(); // fixme we need new priceId here
        productId = price.getProductId();
        priceNumber = price.getPriceNumber();
        departmentId = price.getDepartmentId();
        startDate = new Date(price.getStartDate().getTime());
        endDate = new Date(price.getEndDate().getTime());
        value = price.getValue();
    }

    public long getPriceId() {
        return priceId;
    }

    public String getProductId() {
        return productId;
    }

    public int getPriceNumber() {
        return priceNumber;
    }

    public int getDepartmentId() {
        return departmentId;
    }

    public Date getStartDate() {
        return startDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public long getValue() {
        return value;
    }

    public void setStartDate(Date startDate) {
        this.startDate = new Date(startDate.getTime());
    }

    public void setEndDate(Date endDate) {
        this.endDate = new Date(endDate.getTime());
    }

    @Override
    public String toString() {
        return String.format("ID: %d; Prod: %s; Num: %d; Dep: %d; Begin: %s; End: %s; Val: %s",
                getPriceId(),
                getProductId(),
                getPriceNumber(),
                getDepartmentId(),
                getStartDate(),
                getEndDate(),
                getValue()
        );
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Price)) return false;

        Price price = (Price) o;

        if (getPriceId() != price.getPriceId()) return false;
        if (getDepartmentId() != price.getDepartmentId()) return false;
        if (getPriceNumber() != price.getPriceNumber()) return false;
        if (getValue() != price.getValue()) return false;
        if (!getProductId().equals(price.getProductId())) return false;
        if (!getStartDate().equals(price.getStartDate())) return false;
        return getEndDate().equals(price.getEndDate());

    }

    @Override
    public int hashCode() {
        int result = (int) (getPriceId() ^ (getPriceId() >>> 32));
        result = 31 * result + getProductId().hashCode();
        result = 31 * result + getDepartmentId();
        result = 31 * result + getPriceNumber();
        result = 31 * result + getStartDate().hashCode();
        result = 31 * result + getEndDate().hashCode();
        result = 31 * result + (int) (getValue() ^ (getValue() >>> 32));
        return result;
    }
}
