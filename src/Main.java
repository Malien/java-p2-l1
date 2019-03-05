import Lab.Faculty;
import Lab.Sorting;
import Lab.University;
import Utility.ArrayList;

public class Main {
    public static void main(String[] args){
        University university = new University("KMA");
        //university.handleConsole();
        ArrayList<Faculty> arl = new ArrayList();
        arl.add(new Faculty(university, "First"));
        arl.add(new Faculty(university, "Second"));
        arl.add(new Faculty(university, "Third"));
        arl.add(new Faculty(university, "Fourth"));
        university.setFaculties(arl);
        Sorting sort = new Sorting(university);
        sort.handleConsole();
    }
}
