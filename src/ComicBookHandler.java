import java.util.*;

public class ComicBookHandler implements IComicBookHandler, IComicReleaseHandler {
    private List<ComicRelease> comicReleases;
    private List<ComicBook> comicBooks;

    public ComicBookHandler() {
        comicReleases = new ArrayList<>();
        comicBooks = new ArrayList<>();
    }

    @Override
    public void registerComicRelease(String genre, double price, Date releaseDate) {
        try {
            ComicRelease release = new ComicRelease(price, genre, releaseDate);
            comicReleases.add(release);
        } catch (Exception e) {
            System.out.println("Could not register comic book release");
            e.printStackTrace();
        }
    }

    @Override
    public Collection<ComicRelease> getReleases() {
        return comicReleases;
    }

    @Override
    public void registerComicBook(int releaseID, double price, Condition condition, Date arrival) {
        try {
            ComicRelease release = getComicRelease(releaseID);
            ComicBook comicBook = new ComicBook(release, price, condition, arrival);
            comicBooks.add(comicBook);
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Could not register comic book.");
        }
    }

    public void updateComicBook(int id, double price, Condition condition) {
        try {
            ComicBook book = getComicBook(id);
            book.update(condition, price);
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Could not update price and condition");
        }
    }

    @Override
    public void registerSale(int id, double salePrice, Date saleDate) {
        try {
            ComicBook book = getComicBook(id);
            book.registerSale(salePrice, saleDate);
        } catch (Exception e ) {
            e.printStackTrace();
            System.out.println("Could not register sale.");
        }
    }

    private ComicRelease getComicRelease(int id) {
        for (ComicRelease release : comicReleases) {
            if (release.getId() == id) {
                return release;
            }
        }
        throw new IllegalArgumentException("Not a valid release id");
    }

    @Override
    public ComicBook getComicBook(int id) {
        for (ComicBook book : comicBooks) {
            if (book.getId() == id) {
                return book;
            }
        }
        throw new IllegalArgumentException("Not a valid id");
    }

    public List<ComicBook> getComicBooksForSale() {
        List<ComicBook> comicBooks = new ArrayList<>();
        for (ComicBook book : this.comicBooks) {
            if (book.getCondition() != Condition.UNDER_EVALUATION) {
                comicBooks.add(book);
            }
        }
        comicBooks.sort(Comparator.comparing(ComicBook::getPrice));
        return comicBooks;
    }

    public List<ComicBook> getComicsUnderEvaluation() {
        return getComicBooks(Condition.UNDER_EVALUATION);
    }

    @Override
    public List<ComicBook> getComicBooks() {
        return comicBooks;
    }

    public List<ComicBook> getComicBooks(Condition condition) {
        List<ComicBook> comics = new ArrayList<>();
        for (ComicBook book : comicBooks) {
            if (book.getCondition() == condition) {
                comics.add(book);
            }
        }
        return comics;
    }

    public void printAllPriceDiff() {
        for (ComicBook book : comicBooks) {
            if (book.isSold()) {
                System.out.println(
                        "Book " + book.getId() + " was sold for " +
                        book.getPriceDiff() + " less than sale price");
            }
        }
    }

    @Override
    public void updatePrice(int releaseId, Condition condition, double price) {
        for (ComicBook book : comicBooks) {
            if (book.getCondition() == condition && book.getId() == releaseId) {
                book.setPrice(price);
            }
        }
    }

    public void printAllComicBook() {
        for (ComicBook book : comicBooks) {
            System.out.println(book);
        }
    }

    public void printAllReleases() {
        for (ComicRelease release : comicReleases) {
            System.out.println(release);
        }
    }
}
