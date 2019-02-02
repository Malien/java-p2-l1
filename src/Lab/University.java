package Lab;

import java.util.LinkedList;

import static Utility.DataManagement.*;

public class University extends Named {

    private static final String HELP_MSG = "Available commands:\n" +
            "Name    - change the name of university\n" +
            "Add     - add new faculties\n" +
            "Edit    - edit existing faculties\n" +
            "Delete  - delete any existing faculty\n" +
            "Staff   - enter staff configuration mode\n" +
            "Student - enter student configuration mode\n" +
            "List    - list all faculties\n" +
            "Help    - show this message again\n" +
            "Stop    - stop execution of program";

    private LinkedList<Faculty> faculties = new LinkedList<>();

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
        consoleHandler();
    }

    private void consoleHandler() {
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
                case "staff":
                    System.out.println("Enter settings of the appropriate cathedra so as to make changes in the list of teachers.");
                    break;
                case "student":
                    System.out.println("Enter settings of the appropriate cathedra so as to make changes in the list of students.");
                    break;
                case "list":
                    System.out.println(getNames(faculties));
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
    int countFac = 0;
    private void addFaculty() {
    int tempCount = 0;
        System.out.println("To stop entering faculty names type \"stop\"");
        while (true) {
            String facultyName = getString("Enter the name of the faculty № " + (countFac + 1) + " : ");
            if (facultyName.equals("stop")) break;
            if (contains(faculties, facultyName)) {
                System.out.println("Such faculty already exists.");
                continue;
            }
            faculties.add(new Faculty(this, facultyName));
            tempCount++;
            countFac++;
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
        countFac--;
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
        faculties.get(index).settings();
    }

    /**
     * @author Rozhko Andrew
     */
    //there is some duplication but its handling introduces little use
    public void addTeachers() {
        if (faculties.isEmpty()) {
            System.out.println("No faculties present!");
            return;
        }
        System.out.println(getNames(faculties));

        String whichFaculty = getString("What is the faculty you want to add teaches to?");
        int index = indexOf(faculties, whichFaculty);
        if (index == -1) {
            System.out.println("There is no such a faculty. No changes are done.");
            return;
        }
        faculties.get(index).submitTeachers();
    }

    /**
     * @author Rozhko Andrew
     */
    public void addStudents() {
        if (faculties.isEmpty()) {
            System.out.println("No faculties present!");
            return;
        }
        System.out.println(getNames(faculties));

        String whichFaculty = getString("What is the faculty you want to add students to?");
        int index = indexOf(faculties, whichFaculty);
        if (index == -1) {
            System.out.println("There is no such a faculty. No changes are done.");
            return;
        }
        faculties.get(index).submitStudents();
    }
}