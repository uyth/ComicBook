import java.util.Collection;
import java.util.Date;

public interface IComicBookHandler {
    void registerComicBook(int releaseID, double price, Condition condition, Date arrival);
    void registerSale(int id, double salePrice, Date saleDate);
    void updateComicBook(int id, double price, Condition condition);
    void updatePrice(int releaseID, Condition condition, double price);
    Collection<ComicBook> getComicBooks();
    ComicBook getComicBook(int id);
}
