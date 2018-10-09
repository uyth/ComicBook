import org.junit.Test;

import java.util.Date;

import static org.junit.Assert.*;

public class ComicBookHandlerTest {

    @Test
    public void registerComicBookRelease() {
        ComicBookHandler handler = new ComicBookHandler();
        assertEquals(handler.getReleases().size(), 0);
        handler.registerComicRelease("Test", 100, new Date());
        assertEquals(handler.getReleases().size(), 1);
    }

    @Test
    public void registerComicBook() {
        ComicBookHandler handler = new ComicBookHandler();

        assertEquals(0, handler.getComicBooks().size());

        handler.registerComicRelease("Test", 100, new Date());
        handler.registerComicBook(1, 100, Condition.UNDER_EVALUATION, new Date());


        assertEquals(1, handler.getComicBooks().size());
    }

    @Test
    public void updateComicBook() {
        ComicBookHandler handler = new ComicBookHandler();
        handler.registerComicRelease("Test", 100, new Date());
        handler.registerComicBook(1, 100, Condition.UNDER_EVALUATION, new Date());

        assertEquals(handler.getComicBook(1).getCondition(), Condition.UNDER_EVALUATION);
        assertEquals(0, handler.getComicBook(1).getPrice(), 0.005);

        handler.updateComicBook(1, 200, Condition.AS_NEW);

        assertEquals(handler.getComicBook(1).getCondition(), Condition.AS_NEW);
        assertEquals(handler.getComicBook(1).getPrice(), 200, 0.005);

        handler.registerComicBook(1, 300, Condition.AS_NEW, new Date());

        assertEquals(handler.getComicBook(2).getPrice(), 300, 0.005);
    }

    @Test
    public void registerSale() {
        ComicBookHandler handler = new ComicBookHandler();
        handler.registerComicRelease("Test", 100, new Date());
        handler.registerComicBook(1, 100, Condition.AS_NEW, new Date());

        assertFalse(handler.getComicBook(1).isSold());

        handler.registerSale(1, 80, new Date());

        assertTrue(handler.getComicBook(1).isSold());
    }

    @Test
    public void updatePrice() {
        ComicBookHandler handler = new ComicBookHandler();
        handler.registerComicRelease("Test", 100, new Date());
        handler.registerComicBook(1, 100, Condition.UNDER_EVALUATION, new Date());

        assertEquals(handler.getComicBook(1).getCondition(), Condition.UNDER_EVALUATION);
        assertEquals(0, handler.getComicBook(1).getPrice(), 0.005);

        handler.updateComicBook(1, 200, Condition.AS_NEW);

        assertEquals(handler.getComicBook(1).getCondition(), Condition.AS_NEW);
        assertEquals(handler.getComicBook(1).getPrice(), 200, 0.005);
    }
}