package Lab;

import java.util.Comparator;

public class Teacher extends Named{

    public static final Comparator<Teacher> nameComparator = new Comparator<Teacher>() {
        @Override
        public int compare(Teacher o1, Teacher o2) {
            return o1.name.compareToIgnoreCase(o2.name);
        }
    };

    Teacher(Named parent, String name) {
        this.parent = parent;
        this.name = name;
    }

    @Override
    public String toString() {
        return name + ", cathedra:"+parent;
    }
}
