package Lab;

import Utility.ArrayList;

import static Utility.DataInput.getInt;
import static Utility.DataInput.getString;
import static java.lang.String.valueOf;

public class Sorting {

    University root;

    public Sorting (University kma){
        root = kma;
    }

    private static final String HELP_MSG =
            "FA (Faculty alphabet) - show all students/teachers sorted in alphabetical order.\n" +
            "SC (Students course) - students sorted according to course.\n" +
            "SCA (Student cathedra alphabet) - show all students of a particular cathedra sorted in alphabet order.\n" +
            "SCC (Student cathedra course) - show the students of a particular cathedra and course" +
            "SCCA (Student cathedra course alphabet)" +
            "Help - show this message again" +
            "Stop - to exit";

    public void handleConsole(){
        System.out.println(HELP_MSG);
        while (true){
            String ans = getString();
            switch(ans.toLowerCase()){
                case ("fa"):
                    faSort();
                    break;
                case ("sc"):
                    scSort();
                    break;
                case ("sca"):
                    scaSort();
                    break;
                case ("scc"):
                    sccSort();
                case ("scca"):
                    sccaSort();
                    break;
                case ("help"):
                    System.out.println(HELP_MSG);
                    break;
                case ("stop"):
                    return;
                default:
                    System.out.println("No such command as + \"" + ans + "\"!");
            }
        }
    }

    private void scaSort() {
    }

    private void sccaSort() {
    }

    private void sccSort() {
    }

    private void scSort() {
    }

    /**
     * fcS = from Faculty to Cathedra while processing Students
     * sorts students and teachers of a particular faculty
     */
    private void faSort () {
        System.out.println(root.getFaculties().toPlainString());
        Faculty f;
        while (true) {
            int index = getInt("Choose the number of the faculty: ");
            try {
                 f = root.getFaculties().get(index);
                break;
            } catch (IndexOutOfBoundsException e) {
                System.out.println("Incorrect index!");
            }
        }
        ArrayList studentList = new ArrayList();
            ArrayList<Cathedra> fcS = f.getCathedras();
            for (Cathedra c : fcS) {
                ArrayList<Speciality> cs = c.getSpecialities();
                for (Speciality s : cs) {
                    ArrayList<Student> st = s.getStudents();
                    for (Student stud : st) {
                        studentList.add(stud);
                    }
                }
            }
        System.out.println("Students: " + alphabetSort(studentList));

        ArrayList teacherList = new ArrayList();
            ArrayList<Cathedra> fcT = f.getCathedras();
            for (Cathedra c : fcT) {
                ArrayList<Teacher> cs = c.getTeachers();
                for (Teacher t : cs){
                    teacherList.add(t);
                }
            }
        System.out.println("Teachers: " + alphabetSort(teacherList));
    }


    /**
     * sorts an ArrayList in alphabetical order
     * @param al
     * @return
     * @author Rozhko Andrew
     */
    private static ArrayList alphabetSort(ArrayList al) {
        if (al.size() <= 1) return al;
        int len = al.size();
        ArrayList temp0 =  al.subList(0, len / 2);
        ArrayList temp1 =  al.subList(len / 2, len);

        ArrayList arl_0 = alphabetSort(temp0);
        ArrayList arl_1 = alphabetSort(temp1);
        ArrayList res = new ArrayList();
        System.out.println("Finish1");
        int m = 0;
        int n = 0;
        //int index = 0;

        while (m < arl_0.size() && n < arl_1.size()) {
            if (valueOf(arl_0.get(m)).toLowerCase().charAt(0) < valueOf(arl_1.get(n)).toLowerCase().charAt(0)) {
                res.add(arl_0.get(m));
                m++;
                continue;
            } else if (valueOf(arl_0.get(m)).toLowerCase().charAt(0) > valueOf(arl_1.get(n)).toLowerCase().charAt(0)) {
                res.add(arl_1.get(n));
                n++;
                continue;
            }
            //in case first i letters coincide
            else {

                for (int i = 1; i < Math.max(valueOf(arl_0.get(m)).length(), valueOf(arl_1.get(n)).length()); i++) {
                    if (valueOf(arl_0.get(m)).toLowerCase().charAt(i) < valueOf(arl_1.get(n)).toLowerCase().charAt(i)) {
                        res.add(arl_0.get(m));
                        m++;
                        continue;
                    } else if (valueOf(arl_0.get(m)).toLowerCase().charAt(i) > valueOf(arl_1.get(n)).toLowerCase().charAt(i)) {
                        res.add(arl_1.get(n));
                        n++;
                        continue;
                    }
                }
            }
        }
        if (m == arl_0.size())
            res.addAll(arl_1.subList(n, arl_1.size()));
        else
            res.addAll(arl_0.subList(m, arl_0.size()));
        return res;
    }




}
