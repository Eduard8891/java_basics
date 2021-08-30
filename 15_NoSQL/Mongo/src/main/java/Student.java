import java.util.List;

public class Student {

    private String name;
    private int age;
    private List<String> courses;

    public Student(String name, int age, List courses) {
        this.name = name;
        this.age = age;
        this.courses = courses;
    }

    public String getName() {
        return name;
    }

    public int getAge() {
        return age;
    }

    public List getCourses() {
        return courses;
    }

    @Override
    public String toString() {
        return "name: " + name + ", age: " + age +
                ", courses: " + courses;
    }
}
