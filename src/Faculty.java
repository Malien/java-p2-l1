import java.util.ArrayList;
import java.util.LinkedList;

import static Utility.DataInput.getString;


public class Faculty {

    String name = "";

    //stores all the names that were entered in order not to allow copies
    ArrayList<String> namesOfCathedras = new ArrayList<>();
    private LinkedList<Cathedra> cathedraLinkedList = new LinkedList<Cathedra>();

    Faculty(String s){
        name = s;
        System.out.println("You`ve entered '" + name + "' settings.");
        addCathedra(cathedraLinkedList);
    }

    private void addCathedra(LinkedList<Cathedra> c) {
        int count = 0;
        String name = "";
        while (true) {
                name = getString("Enter the name of the cathedra № " + (count + 1) + " : ");
            if (name.equals("-1")) break;
            if(namesOfCathedras.contains(name) == true){
                System.out.println("Such cathedra already exists.");
                continue;
            }
            c.add(new Cathedra (name));
            namesOfCathedras.add(name);
            count++;
        }
    }


    public void settings() {
        if (namesOfCathedras.isEmpty() == true){
            System.out.println("There are no cathedras to delete or edit!");
            return;
        }
        System.out.println("The list of cathedras: " + namesOfCathedras.toString());

        String whatToDo = getString("Do you want to Edit or Delete some cathedras?");
        if (whatToDo.equals("Edit")) cathedraEdit();
        else if (whatToDo.equals("Delete")) cathedraDelete();
        else System.out.println("No changes are done.");
    }

    private void cathedraEdit() {
        String whichToEdit = getString("Which cathedra is to edit?");
        if (namesOfCathedras.contains(whichToEdit) == false){
            System.out.println("The name is misspelled. No changes are done.");
            return;
        }
        int index = namesOfCathedras.indexOf(name);
        if(getString("Do you want to change the name of the cathedra? ").equals("Yes")) cathedraChangeName(whichToEdit);
        if(getString("Do you want to change the name of stuff? ").equals("Yes")) cathedraChangeStuff(whichToEdit);
//TODO add methods for renaming cathedras, changing its stuff
    }

    private void cathedraChangeStuff(String s) {
        for(Cathedra t: cathedraLinkedList){
            if (t.name.equals(s)) {
                t.changeStuff();
                break;
            }
        }
    }

    //27.01.19
    private void cathedraChangeName(String whichToEdit) {
        String newName = getString("Enter a new name: ");
        //deletion from 'namesOfCathedras' the old name
        namesOfCathedras.remove(whichToEdit);
        for (Cathedra t: cathedraLinkedList){
            if (t.name.equals(whichToEdit)) {
                t.name = newName;
                break;
            }
        }

    }

    private void cathedraDelete() {
        String whichToDelete = "";
            whichToDelete = getString("Which cathedra is to delete?");
        if (namesOfCathedras.contains(whichToDelete) == false){
            System.out.println("The name is misspelled. No changes are done.");
            return;
        }
        int index = namesOfCathedras.indexOf(whichToDelete);
        namesOfCathedras.remove(index);
        cathedraLinkedList.remove(index);
        System.out.println("Deletion is completed.");
    }

    public void submitTeachers() {
       if (cathedraLinkedList.isEmpty()){
           System.out.println("There are no cathedras in this faculty! You should have added them first.");
           return;
       }
       else {
           System.out.println("The list of cathedras: " + cathedraLinkedList.toString());
       }
       String whichCathedra = getString("Which cathedra you want to expand? ");
       int index = FCindex(whichCathedra);
       if (index == -1){
           System.out.println("There is no such a cathedra!");
           return;
       }
        cathedraLinkedList.get(index).newTeachers();
    }

    private int FCindex(String whichCathedra) {
        for (Cathedra t : cathedraLinkedList){
            if((t.name).equals(whichCathedra)){
                return cathedraLinkedList.indexOf(t);
            }
        }
        return -1;
    }
}