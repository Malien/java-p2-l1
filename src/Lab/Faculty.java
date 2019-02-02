package Lab;

import java.util.LinkedList;

import static Utility.DataManagement.*;

public class Faculty extends Named{

    private LinkedList<Cathedra> cathedras = new LinkedList<>();

    private final static String HELP_MSG = "Available commands:\n" +
            "Name   - change name of the faculty\n" +
            "Add    - add cathedras to faculty\n" +
            "Edit   - edit cathedras on faculty\n" +
            "Delete - delete cathedra on faculty\n" +
            "List   - show list of all cathedras on faculty\n" +
            "Stop   - exit current menu\n" +
            "Help   - show this message again";

    Faculty(Named parent, String name) {
        this.parent = parent;
        this.name = name;
        addCathedra();
    }

    /**
     * @author Rozhko Andrew
     */
    int count = 0;
    private void addCathedra() {
        int tempCount = 0;
        String cathedraName;
        System.out.println("To stop entering cathedras names type \"stop\"");
        while (true) {
            cathedraName = getString("Enter the name of the cathedra № " + (tempCount + 1) + " : ");
            if (cathedraName.equals("stop")) break;
            if (contains(cathedras, cathedraName)) {
                System.out.println("Such cathedra already exists.");
                continue;
            }
            cathedras.add(new Cathedra(this, cathedraName));
            tempCount++;
            count++;
        }
        System.out.println(tempCount + " cathedra(s) were added.");
    }

    public void settings() {
        System.out.println(HELP_MSG);
        System.out.println("The list of cathedras:\n" + getNames(cathedras));

        while (true){
            String ans = getString();
            switch (ans){
                case "name":
                    changeName();
                    break;
                case "add":
                    addCathedra();
                    break;
                case "edit":
                    editCathedra();
                    break;
                case "delete":
                    deleteCathedra();
                    break;
                case "list":
                    System.out.println(getNames(cathedras));
                    break;
                case "stop":
                    return;
                case "help":
                    System.out.println(HELP_MSG);
                    break;
                default:
                    System.out.println("No such command as \""+ans+"\"");
                    break;
            }
        }
    }
    /**
     * @author Rozhko Andrew
     */
    private int indexOfCathedra() {
        if (cathedras.isEmpty()) {
            System.out.println("There are no cathedras to delete or edit!");
            return -1;
        }
        String cathedra = getString("Which cathedra is to processed?");
        int index = indexOf(cathedras, cathedra);
        if (index == -1) {
            System.out.println("The name is misspelled. No changes are done.");
            return index;
        }
        return index;
    }

    /**
     * @author Rozhko Andrew
     */
    private void editCathedra() {
             int index = indexOfCathedra();
             if (index == -1) return;
        cathedras.get(index).settings();
//        if (getString("Do you want to change the name of the cathedra? ").equals("Yes")) {
//            cathedras.get(index).changeName();
//        }
//        if (getString("Do you want to change the name of staff? ").equals("Yes")) {
//            cathedras.get(index).changeStaff();
//        }
    }

    /**
     * @author Rozhko Andrew
     */
    private void deleteCathedra() {
       int index = indexOfCathedra();
       if (index == -1) return;
        cathedras.remove(index);
        System.out.println("Deletion is completed.");
    }

    /**
     * @author Rozhko Andrew
     */
    public void submitTeachers() {
        int index = submitDuplication();
        if (index == -1) return;
        cathedras.get(index).newTeachers();
    }

    /**
     * @author Rozhko Andrew
     */
    public void submitStudents() {
        int index = submitDuplication();
        if (index == -1) return;
        cathedras.get(index).newStudents();
    }

    /**
     * reduces the duplication of code
     * @author Rozhko Andrew
     * @return index of the person in the list
     */
    private int submitDuplication(){
        if (cathedras.isEmpty()) {
            System.out.println("There are no cathedras in this faculty! You should have added them first.");
            return -1;
        }
        System.out.println("The list of cathedras:\n" + getNames(cathedras));
        String cathedra = getString("Which cathedra you want to enlarge? ");
        int index = indexOf(cathedras, cathedra);
        if (index == -1) {
            System.out.println("There is no such a cathedra!");
            return -1;
        }
        return index;
    }
}
