
public class Animal {// суперкласс

    protected String name;// поля суперкласса
    protected int age;
    protected String type;
    protected int limitRun;
    protected int limitSwim;
    protected boolean canSwim;
    protected int distance;
    protected static int animalCount = 0;// переменная для подсчета всех животных

    // переменные для подсчета животных по виду
    protected static int dogCount = 0;
    protected static int catCount = 0;

    public Animal(String name, int age, String type) { // конструктор
        this.age = age;
        this.type = type;
        this.name = name;
        animalCount++;
    }

    public void typeAnimal() {
        System.out.println("Я-" + type);

    }

    public void run(int distance) { // меттод, который выводит информацию о беге
        if (distance <= limitRun) {
            System.out.println(name + " пробежал " + distance + " м.");
        } else {
            System.out.println(name + " не может пробежать " + distance + " м.");
        }
    }

    public void swim(int distance) { // меттод, который выводит информацию о плавание
        if (distance <= limitRun) {
            if (!canSwim) {
                System.out.println(name + " не умеет плавать");
            } else if (distance <= limitSwim) {
                System.out.println(name + " проплыл " + distance + " м.");
            } else {
                System.out.println(name + " не может проплыть " + distance + " м.");
            }
        }
    }

    public void canSwim() {// меттод, который выводит информацию умеет ли животное плавать
        if (distance <= limitRun) {
            if (canSwim) {
                System.out.println(name + " умеет плавать");
            } else {
                System.out.println(name + " не умеет плавать");
            }
        }
    }

    public static void printAnimalCount() {// метод подсчеат животных
        System.out.println("Всего животных: " + animalCount);
        System.out.println("Собак: " + dogCount);
        System.out.println("Котов: " + catCount);
    }
}
