
public class Cat extends Animal {

    protected boolean isFull;  // Поле сытости

    public Cat(String name, int age, String type) {
        super(name, age, type);
        this.limitRun = 200;
        this.limitSwim = 0;
        this.canSwim = false;
        this.isFull = false;  // При создании кот голоден
        catCount++;
    }

    // Метод для кормления кота из миски
    public void eat(Bowl bowl, int amount) {
        if (isFull) {
            System.out.println(name + " сытый");
            return;
        }

        // Пытаемся взять еду из миски
        if (bowl.tryTakeFood(amount)) {
            isFull = true;
            System.out.println(name + " съел " + amount + " еды и теперь сыт!");
        } else {
            System.out.println(name + " хочет съесть " + amount + ", но в миске не хватает еды!");
        }
    }

    // Метод для вывода информации о сытости
    public void showSatiety() {
        if (isFull) {
            System.out.println(name + " - сытый");
        } else {
            System.out.println(name + " - голодный");
        }
    }
}
