package Lab;

import Utility.ArrayList;

import static Utility.DataManagement.*;

public class University extends Named {

    private static final String HELP_MSG = "Available commands:\n" +
            "Name    - change the name of university\n" +
            "Add     - add new faculties\n" +
            "Edit    - edit existing faculties\n" +
            "Delete  - delete any existing faculty\n" +
            "List    - list all faculties\n" +
            "Find    - find teachers or students throughout the university\n" +
            "Display - display all students grouped by course\n" +
            "Help    - show this message again\n" +
            "Stop    - stop execution of program";

    private ArrayList<Faculty> faculties = new ArrayList<>();

    /**
     * constructor for university
     * @param name the name of uni
     * @author Yaroslav Petryk
     */
    public University(String name) {
        this.name = name;
        System.out.println("Initial configuration of university. Please enter the faculties first.");
        addFaculty();
    }

    /**
     * The console handler for interacting with faculty
     * @author Yaroslav Petryk, Rozhko Andrew
     */
    public void handleConsole() {
        System.out.println(HELP_MSG);
        while (true) {
            String ans = getString();
            switch (ans.toLowerCase()) {
                case "name":
                    changeName();
                    break;
                case "add":
                    addFaculty();
                    break;
                case "edit":
                    editFaculty();
                    break;
                case "delete":
                    deleteFaculty();
                case "list":
                    System.out.println(getNames(faculties));
                    break;
                case "find":
                    find();
                    break;
                case "display":
                    displayStudentsByCourse();
                    break;
                case "help":
                    System.out.println(HELP_MSG);
                    break;
                case "stop":
                    return;
                default:
                    System.out.println("No such command as \"" + ans + "\"");
                    break;
            }
        }
    }

    /**
     * @author Rozhko Andrew
     */
    private void addFaculty() {
    int tempCount = 0;
        System.out.println("To stop entering faculty names type \"stop\"");
        while (true) {
            String facultyName = getString("Enter the name of the faculty № " + (faculties.size() + 1) + " : ");
            if (facultyName.equals("stop")) break;
            if (contains(faculties, facultyName)) {
                System.out.println("Such faculty already exists.");
                continue;
            }
            faculties.add(new Faculty(this, facultyName));
            tempCount++;
        }
        System.out.println(tempCount + " faculty(ies) were created.");
    }

    /**
     * @author Rozhko Andrew
     */
    private void deleteFaculty() {
        if (faculties.isEmpty()) {
            System.out.println("There is nothing to delete. No faculties present!");
            return;
        }
        System.out.println(getNames(faculties));
        String toDelete = getString("Which faculty to delete?");
        int index = indexOf(faculties, toDelete);
        if (index == -1) {
            System.out.println("There is no such a faculty.");
            return;
        }
        faculties.remove(index);
    }

    /**
     * @author Rozhko Andrew
     */
    private void editFaculty() {
        if (faculties.isEmpty()) {
            System.out.println("There is nothing to edit. No faculties present!");
            return;
        }
        System.out.println(getNames(faculties));

        String facultyName = getString("Which faculty would you like to edit? ");
        int index = indexOf(faculties, facultyName);
        if (index == -1) {
            System.out.println("There is no such a faculty.");
            return;
        }
        faculties.get(index).handleConsole();
    }

    /**
     * Get the list of all students in university
     * @return ArrayList of students
     * @author Yaroslav Petryk
     */
    private ArrayList<Student> getStudents(){
        ArrayList<Student> students = new ArrayList<>();
        for (Faculty faculty: faculties){
            students.extend(faculty.getStudents());
        }
        return students;
    }

    /**
     * Get the list of all teachers in university
     * @return ArrayList of teachers
     * @author Yaroslav Petryk
     */
    private ArrayList<Teacher> getTeachers(){
        ArrayList<Teacher> teachers = new ArrayList<>();
        for (Faculty faculty: faculties){
            teachers.extend(faculty.getTeachers());
        }
        return teachers;
    }

    /**
     * Search handler for finding students or teachers
     * @author Yaroslav Petryk
     */
    private void find() {
        ArrayList<Student> foundStudents = new ArrayList<>();
        ArrayList<Teacher> foundTeachers = new ArrayList<>();

        String key = getString("Enter part of a name, course or a group");
        boolean isNumber;
        int num = 0;
        try{
            num = Integer.valueOf(key);
            isNumber = true;
        } catch (NumberFormatException e){
            isNumber = false;
        }

        for (Student student : getStudents()){
            if (student.name.contains(key)){
                foundStudents.add(student);
                continue;
            }
            if (isNumber && (student.getCourse() == num || student.getGroup() == num)){
                foundStudents.add(student);
            }
        }
        for (Teacher teacher : getTeachers()){
            if (teacher.name.contains(key)){
                foundTeachers.add(teacher);
                continue;
            }
            if (isNumber && (teacher.getCourse() == num || teacher.getGroup() == num)){
                foundTeachers.add(teacher);
            }
        }

        if (!foundStudents.isEmpty()){
            System.out.println("Students:");
            for (Student student : foundStudents){
                System.out.println("    " + student.getName() + " course:" + student.getCourse() +
                        " group:" + student.getGroup());
            }
        } else {
            System.out.println("No students found");
        }
        if (!foundTeachers.isEmpty()){
            System.out.println("Teachers:");
            for (Teacher teacher : foundTeachers){
                System.out.println("    " + teacher.getName() + " course:" + teacher.getCourse() +
                        " group:" + teacher.getGroup());
            }
        } else {
            System.out.println("No teachers found");
        }
    }

    /**
     * Prints out students sorted out by course
     * @author Yaroslav Petryk
     */
    private void displayStudentsByCourse(){
        ArrayList<Student> students = getStudents();
        if (students.isEmpty()) {
            System.out.println("There are no students");
            return;
        }
        sortByCourse(students);
        int course = students.get(0).getCourse();
        System.out.println(course + ":");
        for (Student student : students){
            if (student.getCourse() != course){
                course = student.getCourse();
                System.out.println(course + ":");
            }
            //NOTE: Should probably change thing or two about this parent.parent thing
            System.out.println("    " + student.getName() + " faculty:" + student.parent.parent.getName());
        }
    }
}