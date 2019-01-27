package Lab;

import Lab.Faculty;

import java.io.IOException;
import java.util.*;
import static Utility.DataInput.*;

public class KMA {

    static {
        System.out.println("KMA info office launched.");
    }

    //stores all the names that were entered in order not to allow copies
    ArrayList<String> namesOfFaculties = new ArrayList<>();

    LinkedList<Faculty> facultyLinkedList = new LinkedList<Faculty>();

    KMA() {
        System.out.println("Here you can set and see some info about university");
        addFaculty(facultyLinkedList);
    }

    private void addFaculty(LinkedList f) {
        int count = 0;
        while (true) {
               String name = getString("Enter the name of the faculty № " + (count + 1) + " : ");
            if (name.equals("-1")) break;
            if (namesOfFaculties.contains(name) == true) {
                System.out.println("Such faculty already exists.");
                continue;
            }
            f.add(new Faculty(name));
            namesOfFaculties.add(name);
            count++;
        }
    }


    public void changeFaculty() {
        String ask = getString("Edit or Delete?");
        if (ask.equals("Edit")) editFaculty();
        else if (ask.equals("Delete")) deleteFaculty();
        else System.out.println("No changes are done.");
    }

    private void deleteFaculty() {
        if (namesOfFaculties.isEmpty() == true) {
            System.out.println("There is nothing to delete. No faculties present!");
            return;
        }
        System.out.println(namesOfFaculties.toString());
        String toDelete = getString("Which faculty to delete?");
        //CAUTION the index-element correlation between 'namesOfFaculties' and 'facultyLinkedList' must be kept
        int index = namesOfFaculties.indexOf(toDelete);
        if (index == -1) {
            System.out.println("There is no such a faculty.");
            return;
        }
        namesOfFaculties.remove(index);
        facultyLinkedList.remove(index);
    }


    private void editFaculty() {
        if (namesOfFaculties.isEmpty() == true) {
            System.out.println("There is nothing to edit. No faculties present!");
            //temp!!!!!!!!!
            System.out.println("editFaculty - EXIT");
            return;
        }
        System.out.println(namesOfFaculties.toString());

        String whichOneToEdit = getString("Which faculty would you like to edit?");
        if (namesOfFaculties.contains(whichOneToEdit) == false) {
            System.out.println("There is no such a faculty.");
            //temp!!!!!!
            System.out.println("editFaculty - EXIT");
            return;
        }

        //to ask which parameter to change
        String exactlyWhat = getString("Do you want to change the Name of this faculty, enter cathedras` Settings or Both?");
        //CAUTION the index-element correlation between 'namesOfFaculties' and 'facultyLinkedList' must be kept
        int index = namesOfFaculties.indexOf(whichOneToEdit);
        if (exactlyWhat.equals("Settings")) {
            (facultyLinkedList.get(index)).settings();
            //temp!!!!
            System.out.println("editFaculty - EXIT");
            return;
        }
        //option 'Both' executes 'Settings' and than code for 'Name'
        else if (exactlyWhat.equals("Both")) {
            (facultyLinkedList.get(index)).settings();
        } else if (exactlyWhat.equals("Name") == false) {
            System.out.println("Incorrect choice input. No changes are done.");
            //temp!!!!!!!
            System.out.println("editFaculty - EXIT");
            return;
        }
        String newName = getString("Enter the new name for this faculty: ");
        namesOfFaculties.set(index, newName);
        (facultyLinkedList.get(index)).name = newName;
        //temp!!!!
        System.out.println("editFaculty - EXIT");
    }

    //there is some duplication of code at the beginning but if you put it in a separated method, 'return' doesn`t work
    public void addTeachers() {
        if (namesOfFaculties.isEmpty() == true) {
            System.out.println("No faculties present!");
            //temp!!!!!!!!!
            System.out.println("addTeacher - EXIT");
            return;
        }
        System.out.println(namesOfFaculties.toString());

        String whichFaculty = "";
            whichFaculty = getString("What is the faculty you want to add teaches to?");
        if (namesOfFaculties.contains(whichFaculty) == false) {
            System.out.println("There is no such a faculty. No changes are done.");
            return;
        }
        int indexFaculty = FFindex(whichFaculty);
        facultyLinkedList.get(indexFaculty).submitTeachers();
    }

    /**
     * find faculty index in a facultyLinkedList
     * @param whichFaculty
     * @return
     */
    private int FFindex(String whichFaculty) {
        for (Faculty t : facultyLinkedList) {
            if ((t.name).equals(whichFaculty)) {
                return facultyLinkedList.indexOf(t);
            }
        }
        return -2;
    }

            public void addStudents () {
            }

}