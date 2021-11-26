package taught_by_mjlmj.stepone.model;

public class Human implements Comparable<Human> {

    private String name;
    private int boneBreak; // 断了多少根骨头，断的骨头越多，优先级越高

    public Human(String name, int boneBreak) {
        this.name = name;
        this.boneBreak = boneBreak;
    }

    // 等价于compare(this, human)
    @Override
    public int compareTo(Human human) {
        return this.boneBreak - human.boneBreak;
    }

    @Override
    public String toString() {
        return "Human [name=" + name + ", boneBreak=" + boneBreak + "]";
    }

}
