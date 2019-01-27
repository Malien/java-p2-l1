package Lab;

import java.util.LinkedList;

import static Utility.DataManagement.*;

public class University extends Named{

    private LinkedList<Faculty> faculties = new LinkedList<>();

    /**
     * constructor for university
     * @param name the name of uni
     * @author Yaroslav Petryk
     */
    public University(String name){
        this.name = name;
        System.out.println("Initial configuration of university. Please enter the faculties first.");
        addFaculty();
    }

    private void addFaculty() {
        int count = 0;
        System.out.println("To stop entering faculty names type \"stop\"");
        while (true) {
            String facultyName = getString("Enter the name of the faculty № " + (count + 1) + " : ");
            if (facultyName.equals("stop")) break;
            if (contains(faculties, facultyName)) {
                System.out.println("Such faculty already exists.");
                continue;
            }
            faculties.add(new Faculty(this, facultyName));
            count++;
        }
    }

    public void changeFaculty() {
        String ask = getString("Edit or Delete?");
        switch (ask.toLowerCase()){
            case "edit":
                editFaculty();
                break;
            case "delete":
                deleteFaculty();
                break;
            default:
                System.out.println("No changes will be done");
                break;
        }
    }

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

    public void addTeachers() {
        if (faculties.isEmpty()) {
            System.out.println("No faculties present!");
            return;
        }
        System.out.println(getNames(faculties));

        String whichFaculty;
        whichFaculty = getString("What is the faculty you want to add teaches to?");
        int index = indexOf(faculties, whichFaculty);
        if (index == -1) {
            System.out.println("There is no such a faculty. No changes are done.");
            return;
        }
        faculties.get(index).submitTeachers();
    }

    public void addStudents () {
    }
}