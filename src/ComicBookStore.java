import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;

public class ComicBookStore {

    // Liste med alle tegneseriene
    private HashMap<Integer, ArrayList<Object>> releaseCollection;
    private HashMap<Integer, ArrayList<Object>> exemplarCollection;
    private HashMap<Integer, ArrayList<Object>> soldItems;


    // Instansierer
    private ComicBookStore() {
        this.releaseCollection = new HashMap<>();
        this.exemplarCollection = new HashMap<>();
        this.soldItems = new HashMap<>();
    }

    // FUNKSJONALITET 1


    // Legger til utgivelser, sjekker for tom liste
    private void addComicBookPublish(ComicBookPublish cbp) {
        if (cbp.getReleaseInfo() == null) {
            throw new NullPointerException("You need to add a comic book!");
        }
        this.releaseCollection.put(cbp.getId(), cbp.getReleaseInfo());
    }


    // Legger til eksemplar, sjekker for tom liste
    private void addComicBookExemplar(ComicBookExemplar cbe) {
        if (cbe.getComicExemplars() == null) {
            throw new NullPointerException("You need to add a comic book exemplar!");
        }
        this.exemplarCollection.put(cbe.getId(), cbe.getComicExemplars());
    }

    // Oppdaterer eksemplar med ny pris og gradering
    private void updateCopy(int id, int price, String grading) {
        if (!this.exemplarCollection.keySet().contains(id)) {
            throw new IllegalArgumentException("The comic you are looking for does not exist!");
        }
        this.exemplarCollection.forEach((key, value) -> {
            if (key.equals(id)) {
                int newPrice = grading.equals("TIl vurdering") ? 0 : price;
                value.set(0, newPrice);
                value.set(1, grading);
            }
        });
    }

    // Registrerer alle solgte tegneserier
    private void registerSoldComics(int id, int soldPrice, Date date) {
        if (!(this.releaseCollection.keySet().contains(id) && this.exemplarCollection.get(id).get(1) != "Til vurdering")) {
            throw new IllegalArgumentException("The comic book has not been published or the comic is being considered");
        }
        ArrayList<Object> soldInfo = new ArrayList<>();
        soldInfo.addAll(Arrays.asList(soldPrice, date));
        this.soldItems.put(id, soldInfo);
    }

    // FUNKSJONALITET 2

    // Lister ut alle gradering som er Til vurdering
    private void getGrading() {
        this.exemplarCollection.forEach((key, value) -> {
            if (value.get(1).equals("Til vurdering")) {
                System.out.println("This comic book exemplar with id: " +
                        key + " has a grading of " +
                        value.get(1) + " with price: " +
                        value.get(0) + " and was registered on " + value.get(2));
            }
        });
    }

    // Lister ut alle solgte tegneserier med pris sortert stigende
    private void listSoldItems() {
        this.soldItems.forEach((key, value) -> {
            System.out.println("This comic with id " + key + " got sold for " + value.get(0) + " on " + value.get(1));
            // Rakk ikke å implementer sortering. Kunne ha kanskje lagret tegneserier på en annen måte
            // slik at jeg kunne ha brrukt comparator
        });
    }

    // Lister ut prisforskjellen fra originalpris til solgt pris
    private void comparePrice() {
        this.releaseCollection.forEach((key, value) ->
            this.soldItems.forEach((i, j) -> {
                if (key.equals(i)) {
                    System.out.println("Original price for the comic: " + value.get(0) + ". Comic sold for: " + j.get(0));
                    int profit = (Integer)j.get(0) - (Integer)value.get(0);
                    System.out.println("Profit: " + profit);
                }
            })
        );
    }

    private void updateExemplares() {
        // :((
    }

    public static void main(String[] args) {
        ComicBookStore cbs = new ComicBookStore();
        ComicBookPublish cbp = new ComicBookPublish();
        ComicBookPublish cbp2 = new ComicBookPublish();
        ComicBookPublish cbp3 = new ComicBookPublish();
        ComicBookPublish cbp4 = new ComicBookPublish();
        ComicBookPublish cbp5 = new ComicBookPublish();
        ComicBookExemplar cbe = new ComicBookExemplar();
        ComicBookExemplar cbe2 = new ComicBookExemplar();
        ComicBookExemplar cbe3 = new ComicBookExemplar();
        ComicBookExemplar cbe4 = new ComicBookExemplar();
        ComicBookExemplar cbe5 = new ComicBookExemplar();
        cbp.registerRelease(100, "Action", "05.10.2018");
        cbp2.registerRelease(150, "Comedy", "06.10.2018");
        cbp3.registerRelease(200, "Mystery", "07.10.2018");
        cbp4.registerRelease(250, "Superheroes", "08.10.2018");
        cbp5.registerRelease(300, "Drama", "09.10.2018");
        cbe.registerCopy(cbp.getId(), 100, "Som ny", "05.09.2018");
        cbe2.registerCopy(cbp2.getId(), 200, "Pent brukt", "06.09.2018");
        cbe3.registerCopy(cbp3.getId(), 100, "Til vurdering", "07.09.2018");
        cbe4.registerCopy(cbp4.getId(), 400, "Slitt", "08.09.2018");
        cbe5.registerCopy(cbp5.getId(), 500, "Pent brukt", "09.09.2018");
        cbs.addComicBookPublish(cbp);
        cbs.addComicBookPublish(cbp2);
        cbs.addComicBookPublish(cbp3);
        cbs.addComicBookPublish(cbp4);
        cbs.addComicBookPublish(cbp5);
        cbs.addComicBookExemplar(cbe);
        cbs.addComicBookExemplar(cbe2);
        cbs.addComicBookExemplar(cbe3);
        cbs.addComicBookExemplar(cbe4);
        cbs.addComicBookExemplar(cbe5);
        System.out.println("Lister alle med gradering Til vurdering");
        cbs.getGrading();
        System.out.println("\n");
        System.out.println("Viser endring av eksemplar");
        System.out.println(cbs.exemplarCollection);
        cbs.updateCopy(4, 5000, "Som ny");
        System.out.println(cbs.exemplarCollection);
        System.out.println("\n");
        System.out.println("Lister solgte tegneserier");
        cbs.registerSoldComics(0, 1000, new Date());
        cbs.registerSoldComics(1, 3000, new Date());
        //cbs.registerSoldComics(2, 4000, new Date()); Viser IllegalArgument
        cbs.registerSoldComics(3, 2000, new Date());
        cbs.listSoldItems();
        System.out.println("\n");
        System.out.println("Lister forskjell mellom orignialpris og solgt pris");
        cbs.comparePrice();
    }


}
