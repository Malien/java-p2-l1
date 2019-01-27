package Lab;
import Lab.KMA;

import static Utility.DataInput.*;


public class Main {
    public static void main(String[] args){
        KMA launcher = new KMA();
        while(true) {
            int keepOn = askForChange(launcher);
            if (keepOn == 0) break;
        }

        launcher.addTeachers();
        launcher.addStudents();
    }


    private static int askForChange(KMA l) {
        String change = "";
        try {
            change = getString("Would you like to make some changes to faculties?");
        }catch (Exception e){}
        if (change.equals("Yes")) l.changeFaculty();
        else return 0;

        //ask if the user wants to stay in changing menu
        String carryOn = getString("Do you want to make some more changes?");
        if (carryOn.equals("Yes")) return 1;
        return 0;
    }
}
