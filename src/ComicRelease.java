import java.util.Date;

public class ComicRelease {

    private int id;
    private double price;
    private String genre;
    private Date releaseDate;

    public ComicRelease(double price, String genre, Date releaseDate) throws IllegalArgumentException {
        try {
            validateRegister(price, genre, releaseDate);
            setPrice(price);
            setGenre(genre);
            setReleaseDate(releaseDate);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void validateRegister(Double price, String genre, Date releaseDate) {
        validatePrice(price);
        validateGenre(genre);
        validateReleaseDate(releaseDate);
    }

    private void validatePrice(Double price) {
        if (price < 0) {
            throw new IllegalArgumentException("Price cannot be negative");
        }
    }

    private void validateGenre(String genre) {
        // TODO
    }

    private void validateReleaseDate(Date releaseDate) {
        // TODO
    }


    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    public Date getReleaseDate() {
        return releaseDate;
    }

    public void setReleaseDate(Date releaseDate) {
        this.releaseDate = releaseDate;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "ComicRelease{" +
                "id=" + id +
                ", price=" + price +
                ", genre='" + genre + '\'' +
                ", releaseDate=" + releaseDate +
                '}';
    }
}
