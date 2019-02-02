package Lab;

public class Student extends Named {
    private int group;
    private int course;

    Student(Named parent, String name) {
        this.parent = parent;
        this.name = name;
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
