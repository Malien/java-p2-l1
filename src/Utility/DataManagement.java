package Utility;

import Lab.Named;

import java.util.LinkedList;

public class DataManagement {

    /**
     * Searches for instances of faculty name inside faculty lists
     * @param list a list of Named entries
     * @param name the name of searched entry
     * @return the first index found of faculty (if none if found returns -1)
     * @author Yaroslav Petryk
     */
    public static int indexOf(LinkedList<? extends Named> list, String name){
        for (int i = 0; i < list.size(); i++){
            if (name.equals(list.get(i).name)){
                return i;
            }
        }
        return -1;
    }

    /**
     * Returns whether or not entry is in the list
     * @param list a list of Named entries
     * @param name the name of searched entry
     * @return true if found, false otherwise
     * @author Yaroslav Petryk
     */
    public static boolean contains(LinkedList<? extends Named> list, String name){
        for (Named entry : list){
            if (name.equals(entry.name)) {
                return true;
            }
        }
        return false;
    }

    /**
     * Gets the printable representation of faculties in the list
     * @param list a list of Named entries
     * @return the String with faculties names
     * @author Yaroslav Petryk
     */
    public static String getNames(LinkedList<? extends Named> list){
        String names = "| ";
        for (Named entry : list){
            names = names.concat(entry.name + " | ");
        }
        return names;
    }
}
