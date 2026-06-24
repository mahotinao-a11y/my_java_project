
import java.util.HashMap;
import java.util.Map;

public class Student {

    protected String name;
    protected int group;
    protected int course;
    protected Map<String, Integer> marks; // выбран map  так ключ:значение

    public Student(String name, int group, int course) {
        this.name = name;
        this.group = group;
        this.course = course;
        this.marks = new HashMap<>();
    }

    public void addMarks(Map<String, Integer> marks) {// метод добавления оценок
        this.marks.putAll(marks);
    }

    public Map<String, Integer> getMarks() {// метод получения всх оценок
        return marks;
    }

    public double getAverageMark() {// метод получения среднего балла
        if (marks.isEmpty()) {
            return 0;
        }
        int sum = 0;
        for (int mark : marks.values()) {
            sum += mark;
        }
        return (double) sum / marks.size();
    }

    public boolean promoteToNextCourse() {// метод для перевода на курс выше
        if (getAverageMark() < 3) {
            System.out.println(name + " НЕ переведен (средний балл: " + getAverageMark() + ")");
            return false;
        }
        course++;
        System.out.println(name + " переведен на " + course + " курс");
        return true;
    }

    @Override // переопределили метод , для того чтобы получить данные по студенту праильно
    public String toString() {
        return "Студент: " + name
                + " Группа: " + group
                + " Курс: " + course
                + " Оценки: " + marks;
    }

    public static void main(String[] args) {
        // Создаем студента
        Student s1 = new Student("Анна", 101, 2);
        Student s2 = new Student("Анна", 102, 4);

        // Добавляем оценки
        Map<String, Integer> marks1 = new HashMap<>();
        marks1.put("Математика", 5);
        marks1.put("Физика", 4);
        marks1.put("Программирование", 5);
        s1.addMarks(marks1);
        System.out.println(s1);
        System.out.println("Средний балл: " + s1.getAverageMark());
        Map<String, Integer> marks2 = new HashMap<>();
        marks2.put("Математика", 3);
        marks2.put("Физика", 3);
        marks2.put("Программирование", 5);
        s2.addMarks(marks2);
        System.out.println(s2);
        System.out.println("Средний балл: " + s2.getAverageMark());
        s1.promoteToNextCourse();
        s2.promoteToNextCourse();
    }
}
