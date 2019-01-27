package Lab;

import static Utility.DataInput.*;

public class Teacher {

    private String name = "";
    private int group;
    private int course;

    Teacher(String tName){
        name = tName;
        group = getInt("Enter the group of a teacher: ");
        course = getInt("Enter the course of a teacher: ");
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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
