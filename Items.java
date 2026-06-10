
public class Items {

    private String name;              // название
    private String productionDate;    // дата производства
    private String manufacturer;      // производитель
    private String countryOrigin;   // страна происхождения
    private double price;             // цена
    private boolean isReserved;       // резервация

    public Items(String name, String productionDate, String manufacturer, String countryOfOrigin, double price, boolean isReserved) {
        this.name = name;
        this.productionDate = productionDate;
        this.manufacturer = manufacturer;
        this.countryOrigin = countryOfOrigin;
        this.price = price;
        this.isReserved = isReserved;
    }

    public void info() {
        System.out.println("Название: " + name);
        System.out.println("Дата производства: " + productionDate);
        System.out.println("Производитель: " + manufacturer);
        System.out.println("Страна: " + countryOrigin);
        System.out.println("Цена: " + price + " руб.");
        System.out.println("Забронирован: " + (isReserved ? "Да" : "Нет"));
    }

    public static void main(String[] args) {
        System.out.println("Задание 1");
        Items item1 = new Items("Телевизор", "10.12.2025", "Samsung", "Китай", 85000.00, false);
        Items item2 = new Items("Холодильник", "25.05.2015", "Indesit", "Польша", 217000.00, true);
        item1.info();
        item2.info();
    }
}
