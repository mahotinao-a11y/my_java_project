
public class Dog extends Animal {

    public Dog(String name, int age, String type) {
        super(name, age, type);// super инициализирует поля из главного класса Amimal, так в классе cat нет указания на поля, то нужно super
        this.limitRun = 500;
        this.limitSwim = 10;
        this.canSwim = true;
        dogCount++;// счетчик собак
    }
}
