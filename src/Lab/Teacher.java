package Lab;

public class Teacher extends Student{

    Teacher(Named parent, String name) {
        super(name, parent);
        group = getInt("Enter the group of a teacher: ");
        course = getInt("Enter the course of a teacher: ");
    }
}
