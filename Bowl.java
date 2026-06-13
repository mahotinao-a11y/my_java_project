
public class Bowl {

    private int food;

    public Bowl(int food) {// констуркторс условием проверки количества еды на отриуательнвй резкльтат
        if (food < 0) {
            this.food = 0;
        } else {
            this.food = food;
        }
    }

    public void addFood(int amount) {
        // Проверка , что нельзя добавить отрицательное количество еды
        if (amount > 0) {
            food += amount;
            System.out.println("Добавлено " + amount + " еды. Теперь в миске: " + food);
        } else {
            System.out.println("Нельзя добавить отрицательное количество еды!");
        }
    }

    // Метод, который пытается взять еду из миски
    public boolean tryTakeFood(int amount) {
        // Если еды достаточно
        if (food >= amount) {
            food -= amount;  // Убрать еду
            return true;      // кот поел достаточно
        } else {
            return false;     // Еды мало
        }
    }

    // Метод показывает сколько еды осталось
    public void showFood() {
        System.out.println("В миске осталось еды: " + food);
    }
}
