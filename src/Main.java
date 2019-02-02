import Lab.University;

import static Utility.DataInput.getString;

public class Main {
    public static void main(String[] args){
        University university = new University("KMA");
        /*while(true) {
            int keepOn = askForChange();
            if (keepOn == 0) break;
        }
        university.addTeachers();
        university.addStudents();*/
    }


    private static int askForChange() {
            //ask if the user wants to stay in changing menu
        String carryOn = getString("Do you want to make some more changes? ");
        if (carryOn.equals("Yes")) return 1;
        return 0;
    }
}
