package Lab;

public class Teacher extends Named{

    //"у учителей есть поиск только по кафедрам (это тип и будут для них группы)"

    Teacher(Named parent, String name) {
        this.parent = parent;
        this.name = name;
    }
}
