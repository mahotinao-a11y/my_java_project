
public class Cat extends Animal {

    public Cat(String name, int age, String type) {
        super(name, age, type);// super инициализирует поля из главного класса Amimal, так в классе cat нет указания на поля, то нужно super
        this.limitRun = 200;
        this.canSwim = false;
    }

}
