package Lab;

import Utility.ArrayList;

import static Utility.DataManagement.*;

public class Cathedra extends Named {

    private static final String HELP_MSG = "Available commands:\n" +
            "Name       - change the name of cathedra\n" +
            "List       - show list of all staff and specialities\n" +
            "Add staff  - add new teachers to faculty\n" +
            "Add spec   - add new specialities to faculty \n" +
            "Staff      - make changes to the staff\n" +
            "Edit       - edit a speciality\n" +
            "Delete     - delete a speciality\n" +
            "Display    - display students and teachers of cathedra in alphabetic order\n" +
            "Display c  - display students grouped by course\n" +
            "Display at - display students of specified course in alphabetic order\n" +
            "Help       - show this message again\n" +
            "Stop       - exit cathedra configuration";

    private ArrayList<Teacher> teachers = new ArrayList<>();
    private ArrayList<Speciality> spec = new ArrayList<>();

    /**
     * Constructor for Cathedra
     * @param parent parent object of the cathedra (faculty)
     * @param name name of the cathedra
     */
    Cathedra(Named parent, String name) {
        this.name = name;
        this.parent = parent;
    }

    /**
     * The console handler for interacting with faculty
     * @author Yaroslav Petryk, Rozhko Andrew
     */
    public void handleConsole() {
        System.out.println(HELP_MSG);
        while (true) {
            String ans = getString();
            switch (ans.toLowerCase()) {
                case "name":
                    changeName();
                    break;
                case "list":
                    System.out.println("Teachers:\n    " + getNames(teachers) + "\nSpecialities:\n    "+getNames(spec));
                    break;
                case "add staff":
                    newTeachers();
                    break;
                case "add spec":
                    newSpeciality();
                    break;
                case "edit":
                    editSpeciality();
                    break;
                case "delete":
                    deleteSpeciality();
                    break;
                case "staff":
                    changeStaff();
                    break;
                case "display":
                    displayAlphabetic(getStudents(), getTeachers());
                    break;
                case "display c":
                    displayByCourse(getStudents());
                    break;
                case "display at":
                    displayCourse();
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
     * Add teacher to a cathedra
     * @param teacherName name of the teacher
     * @author Yaroslav Petryk
     */
    public void addTeacher(String teacherName){
        teachers.add(new Teacher(this, teacherName));
    }

    /**
     * Add speciality to a cathedra
     * @param specName name of the speciality
     * @return newly created speciality
     * @author Yaroslav Petryk
     */
    public Speciality addSpec(String specName){
        Speciality speciality = new Speciality(this, specName);
        spec.add(speciality);
        return speciality;
    }

    /**
     * Get students of cathedra
     * @return students
     * @author Rozhko Andrew
     */
    public ArrayList<Student> getStudents(){
        ArrayList <Student> stud = new ArrayList<>();
        for (Speciality t : spec){
            stud.extend(t.getStudents());
        }
        return stud;
    }

    /**
     * Handle addition of speciality through console
     * @author Rozhko Andrew
     */
    private void newSpeciality(){
        if (spec.isEmpty()) System.out.println("The list of specialities is empty.");
        else {
            System.out.println(getNames(spec));
        }

        System.out.println("Add specialities. Enter \"stop\" to finish.");
        while (true) {
            String specName = getString("The name of a speciality is: ");
            //makes sure there is no duplication among teachers` names
            if (specName.equals("stop")) break;
            if (contains(spec, specName)) {
                System.out.println("Such name for speciality is already used.");
                continue;
            }
            if (specName.isEmpty()) {
                System.out.println("Entered name is empty. Enter another one");
                continue;
            }
            spec.add(new Speciality(this, specName));
        }
    }

    /**
     * Handle editing specialities through console
     * @author Rozhko Andrew
     */
    private void editSpeciality() {
        int index = getSpecialityIndex();
        if (index == -1) {
            System.out.println("No changes are done!");
            return;
        }
        spec.get(index).handleConsole();
    }

    /**
     * Handle deletion of specialities through console
     * @author Rozhko Andrew
     */
    private void deleteSpeciality(){
        int index = getSpecialityIndex();
        if (index == -1){
            System.out.println("No changes are done!");
            return;
        }
        spec.remove(index);
    }

    /**
     * Handle addition of teachers through console
     * @author Rozhko Andrew
     */
    private void newTeachers() {
        if (teachers.isEmpty()) System.out.println("The list of teachers is empty.");
        else {
            System.out.println(getNames(teachers));
        }
        System.out.println("Add new teachers. Enter \"stop\" to finish.");
        while (true) {
            String teacherName = getString("The name of a teacher is: ");
            //makes sure there is no duplication among teachers` names
            if (teacherName.equals("stop")) break;
            else if (contains(teachers, teacherName)) {
                System.out.println("Such name for teacher is already used.");
                continue;
            }
            teachers.add(new Teacher(this, teacherName));
        }
    }

    /**
     * Handle edition of teachers through console
     * @author Rozhko Andrew
     */
    private void changeStaff() {
        if (teachers.isEmpty()) {
            System.out.println("There are no teachers! No body to edit.)>");
            return;
        }
        System.out.println(getNames(teachers));
        String whatToDo = getString("Edit or Delete?");
        switch (whatToDo) {
            case "Edit":
                editStaff();
                break;
            case "Delete":
                deleteFromList(teachers);
                break;
            default:
                System.out.println("No changes are done!");
                break;
        }
    }


    /**
     * Get the list of all teachers on cathedra
     * @return ArrayList of teachers
     * @author Yaroslav Petryk
     */
    ArrayList<Teacher> getTeachers(){
        return teachers;
    }

    /**
     * Handle edition of teachers through console
     * @author Rozhko Andrew
     */
    private void editStaff() {
        System.out.println("Enter \"stop\" to exit.");
        label:
        while (true) {
            String whomToEdit = getString("Whom to change: ");
            if (whomToEdit.equals("stop")) break;
            for (int i = 0; i < teachers.size(); i++) {
                if (teachers.get(i).getName().equals(whomToEdit)) {
                    String newName = getString("Enter a new teacher: ");
                    //check for duplication
                    for (Teacher teacher : teachers) {
                        if (teacher.getName().equals(newName)) {
                            System.out.println("Duplicate found!");
                            continue;
                        }
                        //if no duplicates found, replace the teacher
                        teachers.set(i, new Teacher(this, newName));
                        continue label;
                    }
                }

            }
            System.out.println("There is no such a teacher!");
        }
    }

    /**
     * Ask user for speciality to be operated upon
     * @return index of speciality
     * @author Rozhko Andrew
     */
    private int getSpecialityIndex(){
        if (spec.isEmpty()) {
            System.out.println("There are no specialities in this faculty! You should have added them first.");
            return -1;
        }
        System.out.println("The list of speciality:\n" + getNames(spec));
        String speciality = getString("Choose the speciality: ");
        int index = indexOf(spec, speciality);
        if (index == -1) {
            System.out.println("There is no such a speciality!");
            return -1;
        }
        return index;
    }

    /**
     * Display students of cathedra grouped by course
     * @author Yaroslav Petryk
     */
    private void displayCourse(){
        int index = getInt("Enter course to be shown");
        ArrayList<Student> found = new ArrayList<>();
        for (Student student : getStudents()){
            if (student.getCourse() == index){
                found.add(student);
            }
        }
        found.sort(Student.nameComparator);
        for (Student student : found){
            System.out.println(student);
        }
    }

    @Override
    public String toString(){
        return this.name;
    }
}
