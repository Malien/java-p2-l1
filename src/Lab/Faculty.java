package Lab;

import java.util.LinkedList;

import static Utility.DataManagement.*;

public class Faculty extends Named{

    private LinkedList<Cathedra> cathedras = new LinkedList<>();

    private final static String HELP_MSG = "Available commands:\n" +
            "Name   - change name of the faculty" +
            "And    - add cathedras to faculty" +
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

    private void addCathedra() {
        int count = 0;
        String cathedraName;
        System.out.println("To stop entering cathedras names type \"stop\"");
        while (true) {
            cathedraName = getString("Enter the name of the cathedra № " + (count + 1) + " : ");
            if (cathedraName.equals("stop")) break;
            if (contains(cathedras, cathedraName)) {
                System.out.println("Such cathedra already exists.");
                continue;
            }
            cathedras.add(new Cathedra(this, cathedraName));
            count++;
        }
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

    private void editCathedra() {
        if (cathedras.isEmpty()) {
            System.out.println("There are no cathedras to delete or edit!");
            return;
        }
        String cathedra = getString("Which cathedra is to edit?");
        int index = indexOf(cathedras, cathedra);
        if (index == -1) {
            System.out.println("The name is misspelled. No changes are done.");
            return;
        }
        cathedras.get(index).settings();
//        if (getString("Do you want to change the name of the cathedra? ").equals("Yes")) {
//            cathedras.get(index).changeName();
//        }
//        if (getString("Do you want to change the name of staff? ").equals("Yes")) {
//            cathedras.get(index).changeStaff();
//        }
    }

    private void deleteCathedra() {
        if (cathedras.isEmpty()) {
            System.out.println("There are no cathedras to delete or edit!");
            return;
        }
        String whichToDelete = getString("Which cathedra is to delete?");
        int index = indexOf(cathedras, whichToDelete);
        if (index == -1) {
            System.out.println("The name is misspelled. No changes are done.");
            return;
        }
        cathedras.remove(index);
        System.out.println("Deletion is completed.");
    }

    public void submitTeachers() {
        if (cathedras.isEmpty()) {
            System.out.println("There are no cathedras in this faculty! You should have added them first.");
            return;
        }
        System.out.println("The list of cathedras:\n" + getNames(cathedras));
        String cathedra = getString("Which cathedra you want to expand? ");
        int index = indexOf(cathedras, cathedra);
        if (index == -1) {
            System.out.println("There is no such a cathedra!");
            return;
        }
        cathedras.get(index).newTeachers();
    }
}
