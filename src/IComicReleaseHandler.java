import java.util.Collection;
import java.util.Date;

public interface IComicReleaseHandler {
    void registerComicRelease(String genre, double price, Date release);
    Collection<ComicRelease> getReleases();
}
