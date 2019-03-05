package Lab;

import java.util.Comparator;

public class Student extends Named {
    public static final Comparator<Student> courseComparator = new Comparator<Student>() {
        @Override
        public int compare(Student o1, Student o2) {
            return o1.course - o2.course;
        }
    };
    public static final Comparator<Student> nameComparator = new Comparator<Student>() {
        @Override
        public int compare(Student o1, Student o2) {
            return o1.name.compareToIgnoreCase(o2.name);
        }
    };

    private int group;
    private int course;

    Student(Named parent, String name) {
        this.parent = parent;
        this.name = name;
        group = getInt("Enter the group of a student: ");
        course = getInt("Enter the course of a student: ");
    }

    Student(Named parent, String name, int group, int course){
        this.parent = parent;
        this.name = name;
        this.group = group;
        this.course = course;
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

    @Override
    public String toString() {
        return name +
                ", group:" + group +
                ", course:" + course +
                ", speciality:" + parent;
    }
}
