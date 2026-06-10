
public class Park {

    private String name;
    private String city;
    private String street;
    private int buildId;

    public Park(String name, String city, String street, int buildId) {
        this.name = name;
        this.city = city;
        this.street = street;
        this.buildId = buildId;
    }

    public void infoPark() {
        System.err.println("Название " + name);
        System.err.println("Город " + city);
        System.err.println("Улица " + street);
        System.err.println("номер помещения " + buildId);
    }

    public class Attractions {

        private String nameAttraction;
        private String timeOpen;
        private double price;

        public Attractions(String nameAttraction, String timeOpen, double price) {
            this.nameAttraction = nameAttraction;
            this.timeOpen = timeOpen;
            this.price = price;
        }

        public void infoAttractions() {
            System.out.println("Аттракцион: " + nameAttraction);
            System.out.println("Время работы: " + timeOpen);
            System.out.println("Цена: " + price + " руб.");
        }
    }
}
