package Lab;

import Utility.ArrayList;

import static Utility.DataManagement.contains;
import static Utility.DataManagement.getNames;

public class Speciality extends Named {

    private static final String HELP_MSG = "Available commands:\n" +
            "Name    - change the name of a speciality\n" +
            "List    - show list of all students\n" +
            "Add     - add students to speciality\n" +
            "Student - make changes to the staff\n" +
            "Help    - show this message again\n" +
            "Stop    - exit speciality configuration";

    private ArrayList <Student> students = new ArrayList<>();

    /**
     * Constructor for speciality
     * @param parent parent object of speciality (cathedra)
     * @param name name of the speciality
     */
    Speciality(Named parent, String name){
        this.name = name;
        this.parent = parent;
    }

    /**
     * Console handler for speciality
     * @author Rozhko Andrew, Yaroslav Petryk
     */
    public void handleConsole(){
        System.out.println(HELP_MSG);
        label :while(true) {
            String ans = getString();
            switch (ans.toLowerCase()) {
                case ("name"):
                    changeName();
                    break;
                case ("list"):
                    System.out.println(students);
                    break;
                case ("add"):
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

    /**
     * Add new student to a speciality
     * @param studentName name of the student
     * @param course student's course
     * @param group student's group
     */
    public void addStudent(String studentName, int course, int group){
        students.add(new Student(this, studentName, course, group));
    }

    /**
     * Handle addition of students through console
     * @author Rozhko Andrew
     */
    private void newStudents() {
        if (students.isEmpty()) System.out.println("The list of students is empty.");
        else {
            System.out.println(getNames(students));
        }
        System.out.println("Add students. Enter \"stop\" to finish.");
        while (true) {
            String studentName = getString("The name of a student is: ");
            //makes sure there is no duplication among students` names
            if (studentName.equals("stop")) break;
            if (contains(students, studentName)) {
                System.out.println("Such name for student is already used.");
                continue;
            }
            if (studentName.isEmpty()) {
                System.out.println("Entered name is empty. Enter another one");
                continue;
            }
            students.add(new Student(this, studentName));
        }
    }

    /**
     * Handle change of students through console
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
     * Handle edition of students through console
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

    /**
     * Get students of speciality
     * @return list of students
     */
    public ArrayList<Student> getStudents(){
        return students;
    }

    @Override
    public String toString(){
        return this.name;
    }
}
