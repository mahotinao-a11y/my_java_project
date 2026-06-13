
public class Main {

    public static void main(String[] args) {
        Bowl bowl = new Bowl(25);
        bowl.showFood();
        System.out.println();

        Cat[] cats = {
            new Cat("Феликс", 2, "кот"),
            new Cat("Буран", 10, "кот"),
            new Cat("Мурзик", 3, "кот"),
            new Cat("Барсик", 1, "кот")
        };

        Dog dog = new Dog("Бобик", 3, "собака");
        Dog dog1 = new Dog("Верный", 6, "собака");

        dog.typeAnimal();
        dog.run(600);
        dog.swim(150);
        dog.canSwim();

        System.out.println();

        dog1.typeAnimal();
        dog1.run(450);
        dog1.swim(50);
        dog1.canSwim();

        System.out.println("\n----- Кормление котов ---\n");

        for (Cat cat : cats) {
            cat.eat(bowl, 10);
        }

        System.out.println("\n--- Сытость котов ---\n");

        for (Cat cat : cats) {
            cat.showSatiety();
        }

        System.out.println();
        bowl.showFood();

        System.out.println("\n--- Добавить еды ---\n");
        bowl.addFood(20);
        bowl.showFood();

        System.out.println("\n--- едят только голодные коты ---\n");

        for (Cat cat : cats) {
            if (!cat.isFull) {  // прямое обращение к полю
                cat.eat(bowl, 10);
            } else {
                System.out.println(cat.name + " уже сыт, не ест");  // прямое обращение к полю
            }
        }

        System.out.println("\n--- все ли котики сыты ---\n");

        for (Cat cat : cats) {
            cat.showSatiety();
        }

        System.out.println();
        bowl.showFood();

        Animal.printAnimalCount();
    }
}
