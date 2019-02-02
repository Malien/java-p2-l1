package Lab;

import java.util.LinkedList;
import java.util.StringTokenizer;

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
    private LinkedList<Student> studentsList = new LinkedList<>();

    Cathedra(Named parent, String name) {
        this.name = name;
        this.parent = parent;
    }

    public void settings() {
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
                    System.out.println(getNames(studentsList));
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
            teachers.add(new Teacher(teacherName));
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
        String whatToDo = getString("Edit, Delete or Both?");
        switch (whatToDo) {
            case "Edit":
                editStaff();
                break;
            case "Delete":
                deleteStaff();
                break;
            case "Both":
                deleteStaff();
                editStaff();
                break;
            default:
                System.out.println("No changes are done!");
                return;
        }
    }
    /**
     * @author Rozhko Andrew
     */
    private void deleteStaff() {
        delete(teachers);
        System.out.println(teachers.toString());
        System.out.println("Deletion completed.");
    }

    /**
     * @author Rozhko Andrew
     */
    private void delete(LinkedList<? extends Named> list) {
        String whomToDelete = getString("Whom to delete: ");
        StringTokenizer tokenizer = new StringTokenizer(whomToDelete, ",");
        while (tokenizer.hasMoreTokens()) {
            String nextPerson = tokenizer.nextToken();
            for (int i = 0; i < list.size(); i++) {
                if (list.get(i).getName().equals(nextPerson)) list.remove(i);
            }
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
                        teachers.set(i, new Teacher(newName));
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
        if (studentsList.isEmpty()) System.out.println("The list of students is empty.");
        else {
            System.out.println(getNames(studentsList));
        }
        System.out.println("Enter \"stop\" to finish.");
        while (true) {
            String studentName = getString("The name of a student is: ");
            //makes sure there is no duplication among students` names
            if (studentName.equals("stop")) break;
            else if (contains(studentsList, studentName)) {
                System.out.println("Such name for student is already used.");
                continue;
            }
            studentsList.add(new Student(studentName));
        }
    }

    /**
     * @author Rozhko Andrew
     */
    private void changeStudents() {
        if (studentsList.isEmpty()) {
            System.out.println("There are no students! No body to edit.)>");
            return;
        }
        System.out.println(getNames(studentsList));
        String whatToDo = getString("Edit, Delete or Both?");
        switch (whatToDo) {
            case "Edit":
                editStudents();
                break;
            case "Delete":
                deleteStudents();
                break;
            case "Both":
                deleteStudents();
                editStudents();
                break;
            default:
                System.out.println("No changes are done!");
                return;
        }
    }

    /**
     * @author Rozhko Andrew
     */
    private void deleteStudents() {
        delete(studentsList);
        System.out.println(studentsList.toString());
        System.out.println("Deletion completed.");
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
            for (int i = 0; i < studentsList.size(); i++) {
                if (studentsList.get(i).getName().equals(whomToEdit)) {
                    String newName = getString("Enter a new student: ");
                    //check for duplication
                    for (Student z : studentsList) {
                        if (z.getName().equals(newName)) {
                            System.out.println("Duplicate found!");
                            continue;
                        }
                        //if no duplicates found, replace the student
                        studentsList.set(i, new Student(newName));
                        continue label;
                    }
                }

            }
            System.out.println("There is no such a teacher!");
        }

    }
}
