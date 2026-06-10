
public class Main {// класс управляет всеми товарами и создает массивы

    public static void main(String[] args) {
        System.out.println("Задание 1");// создание нового экземпляра для класс Товар
        Items item1 = new Items("Телевизор", "10.12.2025", "Samsung", "Китай", 85000.00, false);
        Items item2 = new Items("Холодильник", "25.05.2015", "Indesit", "Польша", 217000.00, true);
        item1.info();
        item2.info();

        // создаем массив товаров
        System.out.println("----------\nЗадание 2");
        Items[] productsArray = new Items[5]; // создание массива товаров
        productsArray[0] = new Items("Samsung S25 Ultra", "01.02.2025", "Samsung Corp.", "Korea", 5599, true);
        productsArray[1] = new Items("iPhone 16 Pro", "15.03.2025", "Apple Inc.", "USA", 6299, false);
        productsArray[2] = new Items("Xiaomi 14", "10.04.2025", "Xiaomi", "China", 3999, true);
        productsArray[3] = new Items("Google Pixel 9", "20.05.2025", "Google", "USA", 4999, false);
        productsArray[4] = new Items("OnePlus 12", "05.06.2025", "OnePlus", "China", 4599, true);

        for (Items item : productsArray) {// проходит по всем элементам массива цикл foreach
            item.info();// вызов метода для получения информации о товарах
        }
        // создаем экземпялр класса парк
        System.err.println("-----------\nЗадание 3");
        Park park1 = new Park("Улыбка", "Нижний Новгород", "М.Горького", 23);
        park1.infoPark();
        System.out.println("Аттракционы");
        Park.Attractions attraction1 = park1.new Attractions("Водная горка", "9:00-18:00", 1500);
        Park.Attractions attraction2 = park1.new Attractions("Колесо обозрения", "10:00-18:00", 2000);
        Park.Attractions attraction3 = park1.new Attractions("Водная горка", "9:00-18:00", 1500.50);
        Park.Attractions attraction4 = park1.new Attractions("Стрельба из лука", "8:00-20:00", 354.80);
        Park.Attractions attraction5 = park1.new Attractions("Карусель", "9:00-18:00", 1500);

        attraction1.infoAttractions();
        attraction2.infoAttractions();
        attraction3.infoAttractions();
        attraction4.infoAttractions();
        attraction5.infoAttractions();

    }
}
