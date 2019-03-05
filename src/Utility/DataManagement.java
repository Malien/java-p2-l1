package Utility;

import Lab.Named;
import Lab.Student;
import Lab.Teacher;

public class DataManagement {

    /**
     * Searches for instances of faculty name inside faculty lists
     * @param list a list of Named entries
     * @param name the name of searched entry
     * @return the first index found of faculty (if none is found returns -1)
     * @author Yaroslav Petryk
     */
    public static int indexOf(ArrayList<? extends Named> list, String name){
        for (int i = 0; i < list.size(); i++){
            if (name.equals(list.get(i).getName())){
                return i;
            }
        }
        return -1;
    }

    /**
     * Returns whether or not entry is in the list
     * @param list a list of Named entries
     * @param name the name of searched entry
     * @return true if found, false otherwise
     * @author Yaroslav Petryk
     */
    public static boolean contains(ArrayList<? extends Named> list, String name){
        for (Named entry : list){
            if (name.equals(entry.getName())) {
                return true;
            }
        }
        return false;
    }

    /**
     * Gets the printable representation of faculties in the list
     * @param list a list of Named entries
     * @return the String with faculties names
     * @author Yaroslav Petryk
     */
    public static String getNames(ArrayList<? extends Named> list){
        if (list.isEmpty())return "The list is empty yes.";
        String names = "| ";
        for (Named entry : list){
            names = names.concat(entry.getName() + " | ");
        }
        return names;
    }

    /**
     * Removes entries from the list
     * @param list a list of Named entries
     * @param names an array of names that should be deleted
     * @return the amount of deleted entries
     * @author Rozhko Andrew, Yaroslav Petryk
     */
    public static int delete(ArrayList<? extends Named> list, String[] names) {
        int count = 0;
        for (String name : names) {
            if (delete(list, name)) count++;
        }
        return count;
    }

    /**
     * Removes single entry from the list
     * @param list a list of Named entries
     * @param name the name of searched entry
     * @return whether or not the entry was removed successfully
     * @author Yaroslav Petryk
     */
    public static boolean delete(ArrayList<? extends Named> list, String name) {
        int index = indexOf(list, name);
        if (index != -1){
            list.remove(index);
            return true;
        }
        return false;
    }

    /**
     * Displays lists of students and teachers in alphabetic order
     * @param students list of students to display
     * @param teachers list of teachers to display
     * @author Yaroslav Petryk
     */
    public static void displayAlphabetic(ArrayList<Student> students, ArrayList<Teacher> teachers){
        if (!students.isEmpty()) {
            System.out.println("Students:");
            students.sort(Student.nameComparator);
            for (Student student : students) {
                System.out.println("    " + student);
            }
        } else {
            System.out.println("No students present");
        }
        if (!teachers.isEmpty()) {
            System.out.println("Teachers:");
            teachers.sort(Teacher.nameComparator);
            for (Teacher teacher : teachers) {
                System.out.println("    " + teacher);
            }
        } else {
            System.out.println("No teachers present");
        }
    }

    /**
     * Display students from list grouped by course
     * @param students list of students to display
     */
    public static void displayByCourse(ArrayList<Student> students){
        if (students.isEmpty()) {
            System.out.println("There are no students");
            return;
        }
        students.sort(Student.courseComparator);
        int course = students.get(0).getCourse();
        System.out.println(course + ":");
        for (Student student : students){
            if (student.getCourse() != course){
                course = student.getCourse();
                System.out.println(course + ":");
            }
            System.out.println("    " + student);
        }
    }
}
