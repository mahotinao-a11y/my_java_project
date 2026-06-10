
public class Items { // создали класс с полями

    private String name;              // название
    private String productionDate;    // дата производства
    private String manufacturer;      // производитель
    private String countryOrigin;   // страна происхождения
    private double price;             // цена
    private boolean isReserved;       // резервация

    public Items(String name, String productionDate, String manufacturer, String countryOfOrigin, double price, boolean isReserved) {// констурктор
        this.name = name;
        this.productionDate = productionDate;
        this.manufacturer = manufacturer;
        this.countryOrigin = countryOfOrigin;
        this.price = price;
        this.isReserved = isReserved;
    }

    public void info() { // метод для вывода информации по товарам построчно
        System.out.println("Название: " + name);
        System.out.println("Дата производства: " + productionDate);
        System.out.println("Производитель: " + manufacturer);
        System.out.println("Страна: " + countryOrigin);
        System.out.println("Цена: " + price + " руб.");
        System.out.println("Забронирован: " + (isReserved ? "Да" : "Нет"));// можно через if else
    }
}
