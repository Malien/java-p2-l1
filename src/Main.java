import Lab.Sorting;
import Lab.University;
import Utility.ArrayList;

public class Main {
    public static void main(String[] args){
        University university = new University("KMA");
        university.handleConsole();
        Sorting sort = new Sorting(university);
        sort.handleConsole();
    }
}
