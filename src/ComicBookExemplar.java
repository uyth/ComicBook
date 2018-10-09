import java.util.ArrayList;
import java.util.Arrays;

public class ComicBookExemplar {

    public int releaseId;
    public int price;
    public ArrayList<Object> comicExemplars;

    public ComicBookExemplar() {
        this.comicExemplars = new ArrayList<>();
    }

    // Registrer tegneserieeksemplar
    public void registerCopy(int id, int price, String grading, String date) {
        if (price < 0) {
            throw new IllegalArgumentException("The price cannot be negative");
        }
        this.price = grading.equals("Til vurdering") ? 0 : price;
        this.releaseId = id;
        this.comicExemplars.addAll(Arrays.asList(this.price, grading, date));
    }

    public int getId() {
        return this.releaseId;
    }

    public ArrayList<Object> getComicExemplars() {
        return this.comicExemplars;
    }
}
