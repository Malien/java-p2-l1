package Lab;

import java.util.LinkedList;

import static Utility.DataManagement.contains;
import static Utility.DataManagement.getNames;

public class Cathedra extends Named {

    private static final String HELP_MSG = "Available commands:\n" +
            "Name - change the name of cathedra\n" +
            "List - show list of all staff\n" +
            "Add staff - never guess\n" +
            "Add students - makes students to be added)\n" +
            "Staff - make changes to the staff\n" +
            "Zombies - show the list of students\n" +
            "Dig - make changes to the students\n" +
            "Help - show this message again\n" +
            "Stop - exit cathedra configuration";

    private LinkedList<Teacher> teachers = new LinkedList<>();
    private LinkedList<Student> students = new LinkedList<>();

    Cathedra(Named parent, String name) {
        this.name = name;
        this.parent = parent;
    }

    public void handleConsole() {
        System.out.println(HELP_MSG);
        while (true) {
            String ans = getString();
            switch (ans.toLowerCase()) {
                case "name":
                    changeName();
                    break;
                case "list":
                    System.out.println(getNames(teachers));
                    break;
                case "add staff":
                    newTeachers();
                    break;
                case "add students":
                    newStudents();
                    break;
                case "staff":
                    changeStaff();
                    break;
                case "zombies":
                    System.out.println(getNames(students));
                    break;
                case "dig":
                    changeStudents();
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
    public void newTeachers() {
        if (teachers.isEmpty()) System.out.println("The list of teachers is empty.");
        else {
            System.out.println(getNames(teachers));
        }
        System.out.println("Enter \"stop\" to finish.");
        while (true) {
            String teacherName = getString("The name of a teacher is: ");
            //makes sure there is no duplication among teachers` names
            if (teacherName.equals("stop")) break;
            else if (contains(teachers, teacherName)) {
                System.out.println("Such name for teacher is already used.");
                continue;
            }
            teachers.add(new Teacher(this, teacherName));
        }
    }

    /**
     * @author Rozhko Andrew
     */
    public void changeStaff() {
        if (teachers.isEmpty()) {
            System.out.println("There are no teachers! No body to edit.)>");
            return;
        }
        System.out.println(getNames(teachers));
        String whatToDo = getString("Edit or Delete?");
        switch (whatToDo) {
            case "Edit":
                editStaff();
                break;
            case "Delete":
                deleteFromList(teachers);
                break;
            default:
                System.out.println("No changes are done!");
                return;
        }
    }

    /**
     * @author Rozhko Andrew
     */
    private void editStaff() {
        System.out.println("Enter \"stop\" to exit.");
        label:
        while (true) {
            String whomToEdit = getString("Whom to change: ");
            if (whomToEdit.equals("stop")) break;
            for (int i = 0; i < teachers.size(); i++) {
                if (teachers.get(i).getName().equals(whomToEdit)) {
                    String newName = getString("Enter a new teacher: ");
                    //check for duplication
                    for (Teacher z : teachers) {
                        if (z.getName().equals(newName)) {
                            System.out.println("Duplicate found!");
                            continue;
                        }
                        //if no duplicates found, replace the teacher
                        teachers.set(i, new Teacher(this, newName));
                        continue label;
                    }
                }

            }
            System.out.println("There is no such a teacher!");
        }
    }

    /**
     * @author Rozhko Andrew
     */
    public void newStudents() {
        if (students.isEmpty()) System.out.println("The list of students is empty.");
        else {
            System.out.println(getNames(students));
        }
        System.out.println("Enter \"stop\" to finish.");
        while (true) {
            String studentName = getString("The name of a student is: ");
            //makes sure there is no duplication among students` names
            if (studentName.equals("stop")) break;
            else if (contains(students, studentName)) {
                System.out.println("Such name for student is already used.");
                continue;
            }
            students.add(new Student(this, studentName));
        }
    }

    /**
     * @author Rozhko Andrew
     */
    private void changeStudents() {
        if (students.isEmpty()) {
            System.out.println("There are no students! No body to edit.)>");
            return;
        }
        System.out.println(getNames(students));
        String whatToDo = getString("Edit or Delete?");
        switch (whatToDo) {
            case "Edit":
                editStudents();
                break;
            case "Delete":
                deleteFromList(students);
                break;
            default:
                System.out.println("No changes are done!");
                return;
        }
    }

    /**
     * @author Rozhko Andrew
     */
    private void editStudents() {
        System.out.println("Enter \"stop\" to finish.");
        label:
        while (true) {
            String whomToEdit = getString("Whom to change: ");
            if (whomToEdit.equals("stop")) break;
            for (int i = 0; i < students.size(); i++) {
                if (students.get(i).getName().equals(whomToEdit)) {
                    String newName = getString("Enter a new student: ");
                    //check for duplication
                    for (Student z : students) {
                        if (z.getName().equals(newName)) {
                            System.out.println("Duplicate found!");
                            continue;
                        }
                        //if no duplicates found, replace the student
                        students.set(i, new Student(this, newName));
                        continue label;
                    }
                }
            }
            System.out.println("There is no such a teacher!");
        }

    }
}
