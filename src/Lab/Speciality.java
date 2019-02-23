package Lab;

import Utility.ArrayList;

import java.util.Arrays;

import static Utility.DataManagement.contains;
import static Utility.DataManagement.getNames;

public class Speciality extends Named {

    private static final String HELP_MSG = "Available commands:\n" +
            "Name - change the name of a speciality\n" +
            "List - show list of all students\n" +
            "Add students \n" +
            "Student - make changes to the staff\n" +
            "Help - show this message again\n" +
            "Stop - exit speciality configuration";

    private ArrayList <Student> students = new ArrayList<>();

    Speciality(Named parent, String name){
        this.name = name;
        this.parent = parent;
    }



    public void handleConsole(){
        String ans = getString(HELP_MSG);
        label :while(true) {
            switch (ans.toLowerCase()) {
                case ("name"):
                    rename();
                    break;
                case ("list"):
                    System.out.println(students.toPlainString());
                    break;
                case ("add student"):
                    newStudents();
                    break;
                case ("student"):
                    changeStudents();
                    break;
                case ("help"):
                    System.out.println(HELP_MSG);
                    break;
                case ("stop"):
                    break label;
                default : System.out.println("No such command as \"" + ans + "\"!");
            }
        }

    }

    private void rename() {
        name = getString("Enter new name for " + name);
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
                break;
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
                    for (Student student : students) {
                        if (student.getName().equals(newName)) {
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

    public ArrayList<Student> getStudents() {
        return students;
    }
}
