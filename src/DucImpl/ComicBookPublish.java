package DucImpl;

import java.util.ArrayList;
import java.util.Arrays;

public class ComicBookPublish {

    public int retailPrice;
    public int releaseId;
    public static int count = 0;
    public ArrayList<Object> releaseInfo;

    public ComicBookPublish() {
        this.releaseInfo = new ArrayList<>();
        this.releaseId = count++;
    }

    // Registrerer tegneserieutgivelse
    public void registerRelease(int orgPris, String genre, String date) {
        if (orgPris < 0) {
            throw new IllegalArgumentException("The price cannot be negative");
        }
        this.retailPrice = orgPris;
        this.releaseInfo.addAll(Arrays.asList(this.retailPrice, genre, date));
    }

    public int getId() {
        return this.releaseId;
    }

    public ArrayList<Object> getReleaseInfo() {
        return this.releaseInfo;
    }

}
