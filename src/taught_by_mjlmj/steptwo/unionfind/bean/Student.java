package taught_by_mjlmj.steptwo.unionfind.bean;

public class Student {
    private int age;
    private String name;
    public Student(int age, String name) {
        this.age = age;
        this.name = name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Student)) return false;

        Student student = (Student) o;

        if (age != student.age) return false;
        return name.equals(student.name);
    }

    @Override
    public int hashCode() {
        int result = age;
        result = 31 * result + name.hashCode();
        return result;
    }
}