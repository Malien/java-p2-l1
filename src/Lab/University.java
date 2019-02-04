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
}