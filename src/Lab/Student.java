package Lab;

public class Student extends Named {
    protected int group;
    protected int course;

    protected Student(String name, Named parent){
        this.parent = parent;
        this.name = name;
    }

    Student(Named parent, String name) {
        this(name, parent);
        group = getInt("Enter the group of a student: ");
        course = getInt("Enter the course of a student: ");
    }

    public int getGroup() {
        return group;
    }

    public void setGroup(int group) {
        this.group = group;
    }

    public int getCourse() {
        return course;
    }

    public void setCourse(int course) {
        this.course = course;
    }
}
