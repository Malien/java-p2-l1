package Lab;

import Utility.ArrayList;

import java.util.Iterator;

import static Utility.DataManagement.*;

public class University extends Named {

    private static final String HELP_MSG = "Available commands:\n" +
            "Name    - change the name of university\n" +
            "Add     - add new faculties\n" +
            "Edit    - edit existing faculties\n" +
            "Delete  - delete any existing faculty\n" +
            "List    - list all faculties\n" +
            "Find    - find teachers or students\n" +
            "Help    - show this message again\n" +
            "Stop    - stop execution of program";

    private ArrayList<Faculty> faculties = new ArrayList<>();

    /**
     * constructor for university
     *
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

        for(Faculty faculty : faculties){
            Iterator<Cathedra> cathedraIterator = faculty.cathedraIterator();
            while (cathedraIterator.hasNext()){
                Cathedra cathedra = cathedraIterator.next();
                Iterator<Student> studentIterator = cathedra.studentIterator();
                Iterator<Teacher> teacherIterator = cathedra.teacherIterator();
                while (studentIterator.hasNext()){
                    Student student = studentIterator.next();
                    if (student.name.contains(key)){
                        foundStudents.add(student);
                        continue;
                    }
                    if (isNumber && (student.getCourse() == num || student.getGroup() == num)){
                        foundStudents.add(student);
                    }
                }
                while (teacherIterator.hasNext()){
                    Teacher teacher = teacherIterator.next();
                    if (teacher.name.contains(key)){
                        foundTeachers.add(teacher);
                        continue;
                    }
                    if (isNumber && (teacher.getCourse() == num || teacher.getGroup() == num)){
                        foundTeachers.add(teacher);
                    }
                }
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
}