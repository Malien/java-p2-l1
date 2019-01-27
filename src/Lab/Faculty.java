package Lab;

import java.util.LinkedList;

import static Utility.DataManagement.*;

public class Faculty extends Named{

    private LinkedList<Cathedra> cathedras = new LinkedList<>();

    Faculty(Named parent, String name) {
        this.parent = parent;
        this.name = name;
        System.out.println("You`ve entered '" + name + "' settings.");
        addCathedra();
    }

    private void addCathedra() {
        int count = 0;
        String cathedraName;
        while (true) {
            cathedraName = getString("Enter the name of the cathedra № " + (count + 1) + " : ");
            if (name.equals("-1")) break;
            if (contains(cathedras, cathedraName)) {
                System.out.println("Such cathedra already exists.");
                continue;
            }
            cathedras.add(new Cathedra(this, cathedraName));
            count++;
        }
    }

    public void settings() {
        System.out.println("The list of cathedras:\n" + getNames(cathedras));

        String action = getString("Do you want to Edit, Add or Delete cathedras?");
        switch(action.toLowerCase()){
            case "edit":
                editCathedra();
                break;
            case "delete":
                deleteCathedra();
                break;
            case "add":
                addCathedra();
                break;
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
        if (getString("Do you want to change the name of the cathedra? ").equals("Yes")) {
            cathedras.get(index).changeName();
        }
        if (getString("Do you want to change the name of staff? ").equals("Yes")) {
            cathedras.get(index).changeStaff();
        }
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
        System.out.println("The list of cathedras:\n" + cathedras.toString());
        String cathedra = getString("Which cathedra you want to expand? ");
        int index = indexOf(cathedras, cathedra);
        if (index == -1) {
            System.out.println("There is no such a cathedra!");
            return;
        }
        cathedras.get(index).newTeachers();
    }
}
