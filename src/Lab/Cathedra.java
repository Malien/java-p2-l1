package Lab;

import java.util.LinkedList;
import java.util.StringTokenizer;

import static Utility.DataManagement.getNames;

public class Cathedra extends Named{

    private static final String HELP_MSG = "Available commands:\n" +
            "Name - change the name of faculty\n" +
            "List - show list of all staff\n" +
            "Help - show this message again\n" +
            "Stop - exit cathedra configuration";

    private LinkedList<Teacher> teachers = new LinkedList<>();

    Cathedra(Named parent, String name) {
        this.name = name;
        this.parent = parent;
    }

    public void settings(){
        System.out.println(HELP_MSG);
        while (true){
            String ans = getString();
            switch (ans.toLowerCase()){
                case "name":
                    changeName();
                    break;
                case "list":
                    System.out.println(getNames(teachers));
                    break;
                case "help":
                    System.out.println(HELP_MSG);
                    break;
                case "stop":
                    return;
                default:
                    System.out.println("No such command as \""+ans+"\"");
                    break;
            }
        }
    }

    public void newTeachers() {
        if (teachers.isEmpty()) System.out.println("The list of teachers is empty.");
        else {
            System.out.println(getNames(teachers));
        }
        String askForStop;
        do {
            String teacherName = getString("The name of a teacher is: ");
            //makes sure there is no duplication among teachers` names
            for (Teacher t : teachers) {
                if (t.getName().equals(teacherName)) continue;
            }
            teachers.add(new Teacher(teacherName));
            askForStop = getString("Do you want to continue? ");
        } while (askForStop.equals("Yes"));
    }


    public void changeStaff() {
        if (teachers.isEmpty()) {
            System.out.println("There are no teachers! No body to edit.)>");
            return;
        }
        System.out.println(getNames(teachers));
        String whatToDo = getString("Edit, Delete or Both?");
        if (whatToDo.equals("Edit")) editStaff();
        else if (whatToDo.equals("Delete")) deleteStaff();
        else if (whatToDo.equals("Both")) {
            deleteStaff();
            editStaff();
        } else System.out.println("No changes are done!");
    }

    private void deleteStaff() {
        String whomToDelete = getString("Whom to delete: ");
        StringTokenizer tokenizer = new StringTokenizer(whomToDelete, ",");
        while(tokenizer.hasMoreTokens()){
            String nextTeacher = tokenizer.nextToken();
            for(int i = 0; i < teachers.size(); i++){
                if(teachers.get(i).getName().equals(nextTeacher)) teachers.remove(i);
                /*
                //this code is supposed to be equal
                for(Lab.Teacher t: teachers){
                if(t.getName().equals(nextTeacher)) t = null;
            }
                 */
            }
        }
        System.out.println(teachers.toString());
        System.out.println("Deletion completed.");
    }

    private void editStaff() {
        label:
        while (true) {
            String whomToEdit = getString("Whom to change: ");
            if (whomToEdit.equals("-1")) break;
            for (Teacher t : teachers) {
                if (t.getName().equals(whomToEdit)) {
                    String newName = getString("Enter a new teacher: ");
                    //check for duplication
                    for (Teacher z : teachers) {
                        if (t.getName().equals(newName)) {
                            System.out.println("Duplicate found!");
                            continue;
                        }

                        //if no duplicates found, replace the teacher
                        t = new Teacher(newName);
                        continue label;
                    }
                }

            }
            System.out.println("There is no such a teacher!");
        }
    }
}