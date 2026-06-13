
public class Animal {// суперкласс

    protected String name;// поля суперкласса
    protected int age;
    protected String type;
    protected int limitRun;
    protected int limitSwim;
    protected boolean canSwim;

    public Animal(String name, int age, String type) { // конструктор
        this.age = age;
        this.type = type;
        this.name = name;

    }

    public void typeAnimal() {
        System.out.println("Я-" + type);

    }

    public void run(int distance) {
        if (distance <= limitRun) {
            System.out.println(name + " пробежал " + distance + " м.");
        } else {
            System.out.println(name + " не может пробежать " + distance + " м.");
        }
    }

    public void swim(int distance) {
        if (!canSwim) {
            System.out.println(name + " не умеет плавать");
        } else if (distance <= limitSwim) {
            System.out.println(name + " проплыл " + distance + " м.");
        } else {
            System.out.println(name + " не может проплыть " + distance + " м.");
        }
    }

    public void canSwim() {
        if (canSwim) {
            System.out.println(name + " умеет плавать");
        } else {
            System.out.println(name + " не умеет плавать");
        }
    }
}
