package Lab;

import java.util.Objects;

import static Utility.DataInput.getInt;

public class Student extends Named {
    private int group;
    private int course;

    Student(String sName) {
        name = sName;
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

    //overrides 'named' method
    @Override
    public String getName(){
        return name;
    }
    public void setName(String newName){
        name = getName();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Student student = (Student) o;
        return group == student.group &&
                course == student.course && name.equals(student.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(group, course);
    }
}
