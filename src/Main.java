import java.util.Date;

public class Main {

    public static void main(String[] args) {
        ComicBookHandler handler = new ComicBookHandler();
        handler.registerComicRelease("TestGenre", 100, new Date());
        handler.registerComicBook(1, 80, Condition.UNDER_EVALUATION, new Date());
        handler.registerSale(1,70, new Date());
        handler.printAllPriceDiff();
        handler.registerComicBook(1, 80, Condition.USED, new Date());
        handler.registerComicBook(1, 80, Condition.UNDER_EVALUATION, new Date());
        handler.registerSale(1, 80, new Date());
        handler.registerSale(2, 80, new Date());
        handler.printAllPriceDiff();
        for(ComicBook comic : handler.getComicsUnderEvaluation()) {
            System.out.println(comic);
        }
    }
}
