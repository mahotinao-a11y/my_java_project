
public class Main {

    public static void main(String[] args) {
        Cat cat = new Cat("Феликс", 2, "кот");
        Dog dog = new Dog("Бобик", 3, "собака");
        dog.typeAnimal();
        dog.run(600);
        dog.swim(150);
        dog.canSwim();
        cat.typeAnimal();
        cat.run(300);
        cat.canSwim();
    }
}
