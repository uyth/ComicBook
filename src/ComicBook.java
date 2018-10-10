
import java.util.Date;

public class ComicBook {

    private int id;
    private ComicRelease release;
    private Condition condition;
    private Date arrival;
    private double price;
    private double salePrice;
    private Date saleDate;
    private boolean isSold;

    public ComicBook(ComicRelease release, double price, Condition condition, Date arrival) {
        try {
            validateComicBook(release, price, condition, arrival);
            setRelease(release);
            setCondition(condition);
            setArrival(arrival);
            setPrice(price);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void validateComicBook(ComicRelease release, double price, Condition condition, Date arrival) {
        validatePrice(price);
        validateArrival(arrival);
    }

    private void validateArrival(Date arrival) {
        if (arrival.after(new Date())) {
            throw new IllegalArgumentException("Arrival date cannot be after now");
        }
    }

    public void update(Condition condition, double price) {
        validateUpdate(condition, price);
        setCondition(condition);
        setPrice(price);
    }

    void validateUpdate(Condition condition, double price) {
        validatePrice(price);
    }

    public void registerSale(Double salePrice, Date saleDate) {
        validateSale(salePrice, saleDate);
        setSold(true);
        setSalePrice(salePrice);
        setSaleDate(saleDate);
    }

    private void validateSale(Double salePrice, Date saleDate) {
        validatePrice(salePrice);
        validateSaleDate(saleDate);
        validateState();
    }

    private void validatePrice(double price) {
        if (price < 0) {
            throw new IllegalArgumentException("Price cannot be negative");
        }
    }

    private void validateSaleDate(Date saleDate) {
        if (saleDate.before(arrival)) {
            throw new IllegalArgumentException("Sale date cannot be before " +
                    "the arrival date of the comic!");
        }
    }

    private void validateState() {
        if (isSold) {
            throw new IllegalStateException("Cannot sell a book that is already sold");
        }
        if (this.condition == Condition.UNDER_EVALUATION) {
            throw new IllegalStateException("Cannot sell a book that is under evaluation");
        }
    }

    public double getPriceDiff() {
        if (!isSold) {
            throw new IllegalArgumentException("Cannot get sale and original price difference" +
                    "of an item that is not sold yet");
        }
        return this.getPrice() - this.getSalePrice();
    }

    public ComicRelease getRelease() {
        return release;
    }

    public void setRelease(ComicRelease release) {
        this.release = release;
    }

    public Condition getCondition() {
        return condition;
    }

    public void setCondition(Condition condition) {
        this.condition = condition;
    }

    public Date getArrival() {
        return arrival;
    }

    public void setArrival(Date arrival) {
        this.arrival = arrival;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        if (condition == Condition.UNDER_EVALUATION) {
            this.price = 0;
        } else {
            this.price = price;
        }
    }

    public Date getSaleDate() {
        if (!isSold) {
            throw new IllegalArgumentException("Is not sold yet!");
        }
        return saleDate;
    }

    public void setSaleDate(Date saleDate) {
        this.saleDate = saleDate;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public double getSalePrice() {
        return salePrice;
    }

    public void setSalePrice(double salePrice) {
        this.salePrice = salePrice;
    }

    public boolean isSold() {
        return isSold;
    }

    public void setSold(boolean sold) {
        isSold = sold;
    }

    @Override
    public String toString() {
        return "ComicBook{" +
                "id=" + id +
                ", release=" + release +
                ", condition=" + condition +
                ", arrival=" + arrival +
                ", price=" + price +
                ", salePrice=" + salePrice +
                ", saleDate=" + saleDate +
                ", isSold=" + isSold +
                '}';
    }
}
