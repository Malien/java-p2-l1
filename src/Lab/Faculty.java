package Lab;

import Utility.ArrayList;

import static Utility.DataManagement.*;

public class Faculty extends Named{

    private ArrayList<Cathedra> cathedras = new ArrayList<>();

    private final static String HELP_MSG = "Available commands:\n" +
            "Name    - change name of the faculty\n" +
            "Add     - add cathedras to faculty\n" +
            "Edit    - edit cathedras on faculty\n" +
            "Delete  - delete cathedra on faculty\n" +
            "Display - display all students/teaacters on faculty in alphabetical order\n" +
            "List    - show list of all cathedras on faculty\n" +
            "Stop    - exit current menu\n" +
            "Help    - show this message again";

    /**
     * Constructor for Faculty
     * @param parent parent object of faculty (university)
     * @param name name of the faculty
     */
    public Faculty(Named parent, String name) {
        this.parent = parent;
        this.name = name;
    }

    /**
     * Handle addition of cathedras through console
     * @author Rozhko Andrew
     */
    private void addCathedras() {
        int tempCount = 0;
        String cathedraName;
        System.out.println("To stop entering cathedras names type \"stop\"");
        while (true) {
            cathedraName = getString("Enter the name of the cathedra № " + (cathedras.size() + 1) + " : ");
            if (cathedraName.equals("stop")) break;
            if (contains(cathedras, cathedraName)) {
                System.out.println("Such cathedra already exists.");
                continue;
            }
            if (cathedraName.isEmpty()) {
                System.out.println("Entered name is empty. Enter another one");
                continue;
            }
            cathedras.add(new Cathedra(this, cathedraName));
            tempCount++;
        }
        System.out.println(tempCount + " cathedra(s) were added.");
    }

    /**
     * The console handler for interacting with faculty
     * @author Yaroslav Petryk, Rozhko Andrew
     */
    public void handleConsole() {
        System.out.println(HELP_MSG);
        System.out.println("The list of cathedras:\n" + getNames(cathedras));

        while (true){
            String ans = getString();
            switch (ans){
                case "name":
                    changeName();
                    break;
                case "add":
                    addCathedras();
                    break;
                case "edit":
                    editCathedra();
                    break;
                case "delete":
                    deleteCathedra();
                    break;
                case "display":
                    displayAlphabetic(getStudents(), getTeachers());
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
     * Add cathedra to a faculty
     * @param cathedraName name of cathedra
     * @return newly created cathedra
     */
    public Cathedra addCathedra(String cathedraName){
        Cathedra cathedra = new Cathedra(this, cathedraName);
        cathedras.add(cathedra);
        return cathedra;
    }

    /**
     * Get the list of all students on faculty
     * @return ArrayList of students
     * @author Yaroslav Petryk
     */
    ArrayList<Student> getStudents(){
        ArrayList<Student> students = new ArrayList<>();
        for (Cathedra cathedra : cathedras){
            students.extend(cathedra.getStudents());
        }
        return students;
    }

    /**
     * Get the list of all teachers on faculty
     * @return ArrayList of teachers
     * @author Yaroslav Petryk
     */
    ArrayList<Teacher> getTeachers(){
        ArrayList<Teacher> teachers = new ArrayList<>();
        for (Cathedra cathedra : cathedras){
            teachers.extend(cathedra.getTeachers());
        }
        return teachers;
    }

    /**
     * Handle editing cathedras through console
     * @author Rozhko Andrew
     */
    private void editCathedra() {
        int index = getCathedraIndex();
        if (index == -1) return;
        cathedras.get(index).handleConsole();
    }

    /**
     * Handle deletion of cathedras through console
     * @author Rozhko Andrew
     */
    private void deleteCathedra() {
       int index = getCathedraIndex();
       if (index == -1) return;
        cathedras.remove(index);
        System.out.println("Deletion is completed.");
    }

    /**
     * Ask user to specify cathedra to be operated upon
     * @author Rozhko Andrew
     * @return index of the person in the list
     */
    private int getCathedraIndex(){
        if (cathedras.isEmpty()) {
            System.out.println("There are no cathedras in this faculty! You should have added them first.");
            return -1;
        }
        System.out.println("The list of cathedras:\n" + getNames(cathedras));
        String cathedra = getString("Choose the cathedra: ");
        int index = indexOf(cathedras, cathedra);
        if (index == -1) {
            System.out.println("There is no such a cathedra!");
            return -1;
        }
        return index;
    }

    @Override
    public String toString(){
        return this.name;
    }
}
