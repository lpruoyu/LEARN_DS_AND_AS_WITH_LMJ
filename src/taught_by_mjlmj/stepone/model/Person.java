package taught_by_mjlmj.stepone.model;

public class Person {

    private int age;
    private float height;
    private String name;

    public Person() {
    }

    public Person(int age, float height, String name) {
        this.age = age;
        this.height = height;
        this.name = name;
    }


    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public float getHeight() {
        return height;
    }

    public void setHeight(float height) {
        this.height = height;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public boolean equals(Object o) {
        if (null == o || o.getClass() != getClass()) return false;
        if (this == o) return true;
        Person person = (Person) o;
        return age == person.getAge() &&
                height == person.getHeight() &&
                name == null ? person.getName() == null : name.equals(person.getName());
    }

    @Override
    public int hashCode() {
        int hashCode = 0;
        hashCode = hashCode * 31 + Integer.hashCode(age);
        hashCode = hashCode * 31 + Float.hashCode(height);
        hashCode = hashCode * 31 + (name == null ? 0 : name.hashCode());
        return hashCode;
    }

}
