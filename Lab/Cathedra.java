package Lab;

import java.util.LinkedList;
import java.util.StringTokenizer;

import static Utility.DataInput.*;

public class Cathedra {

    String name = "";
    private LinkedList<Teacher> listOfTeachers = new LinkedList<Teacher>();

    Cathedra(String s) {
        name = s;
    }

    public void newTeachers() {
        if (listOfTeachers.isEmpty()) System.out.println("The list of teachers is empty.");
        else {
            System.out.println(listOfTeachers.toString());
        }
        int numOfTeachers = listOfTeachers.size() + 1;
        String askForStop = "Yes";
        do {
            String teacherName = getString("The name of a teacher is: ");
            //makes sure there is no duplication among teachers` names
            for (Teacher t : listOfTeachers) {
                if (t.getName().equals(teacherName)) continue;
            }
            listOfTeachers.add(new Teacher(teacherName));
            askForStop = getString("Do you want to continue? ");
        } while (askForStop.equals("Yes"));
    }


    public void changeStuff() {
        if (listOfTeachers.isEmpty()) {
            System.out.println("There are no teachers! No body to edit.)>");
            return;
        }
        System.out.println(listOfTeachers.toString());
        String whatToDo = getString("Edit, Delete or Both?");
        if (whatToDo.equals("Edit")) editStuff();
        else if (whatToDo.equals("Delete")) deleteStuff();
        else if (whatToDo.equals("Both")) {
            deleteStuff();
            editStuff();
        } else System.out.println("No changes are done!");
    }

    private void deleteStuff() {
        String whomToDelete = getString("Whom to delete: ");
        StringTokenizer tokenizer = new StringTokenizer(whomToDelete, ",");
        while(tokenizer.hasMoreTokens()){
            String nextTeacher = tokenizer.nextToken();
            for(int i = 0; i < listOfTeachers.size(); i++){
                if(listOfTeachers.get(i).getName().equals(nextTeacher)) listOfTeachers.remove(i);
                /*
                //this code is supposed to be equal
                for(Teacher t: listOfTeachers){
                if(t.getName().equals(nextTeacher)) t = null;
            }
                 */
            }
        }
        System.out.println(listOfTeachers.toString());
        System.out.println("Deletion completed.");
    }

    private void editStuff() {
        label:
        while (true) {
            String whomToEdit = getString("Whom to change: ");
            if (whomToEdit.equals("-1")) break;
            for (Teacher t : listOfTeachers) {
                if (t.getName().equals(whomToEdit)) {
                    String newName = getString("Enter a new teacher: ");
                    //check for duplication
                    for (Teacher z : listOfTeachers) {
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