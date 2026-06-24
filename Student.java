import java.util.Map;      // ← нужно добавить
import java.util.HashMap;

public class Student {
    protected String name;
    protected int group;
    protected int course;
    protected Map<String, Integer> marks; // выбран map  так ключ:значение

    public Student (String name, int group, int course){
        this.name = name;
        this.group = group;
        this.course =  course;
        this.marks = new HashMap<>();
    }
    public void addGrades(Map<String, Integer> grades) {// метод добавления оценок
        this.marks.putAll(marks);
    }
    public Map<String, Integer> getGrades() {// метод получения всх оценок
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
    public static void main(String[] args) {
        // Создаем студента
        Student s1 = new Student("Анна", 101, 2);
        
        // Добавляем оценки
        Map<String, Integer> marks = new HashMap<>();
        marks.put("Математика", 5);
        marks.put("Физика", 4);
        marks.put("Программирование", 5);
        s1.addGrades(marks);
        System.out.println(s1);
        System.out.println("Средний балл: " + s1.getAverageMark());
    }
}
