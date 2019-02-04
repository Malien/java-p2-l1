package Lab;

import Utility.ArrayList;
import Utility.DataInput;

import static Utility.DataManagement.delete;
import static Utility.DataManagement.getNames;

public abstract class Named {
    protected String name;
    Named parent;

    /**
     * gets string form console with path and message displayed before the input
     * @param str the message to display
     * @return the string entered from console
     * @author Yaroslav Petryk
     */
    String getString(String str){
        System.out.println(str);
        return getString();
    }

    /**
     * gets string from console with path displayed before the input
     * @return the string entered from console
     * @author Yaroslav Petryk
     */
    String getString(){
        return DataInput.getString(getPath());
    }

    /**
     * Returns printable path in university hierarchy
     * @return string of a path
     * @author Yaroslav Petryk
     */
    String getPath(){
        String path = "";
        Named parent = this.parent;
        while (parent != null) {
            path = path.concat(parent.name + "/");
            parent = parent.parent;
        }
        path += name + "> ";
        return path;
    }

    /**
     * Get integer number typed to a console
     * @param str message to be displayed
     * @return integer form console
     * @author Yaroslav Petryk
     */
    int getInt(String str){
        System.out.println(str);
        return getInt();
    }

    /**
     * Get integer number typed to a console
     * @return integer form console
     * @author Yaroslav Petryk
     */
    int getInt(){
        return DataInput.getInt(getPath());
    }

    /**
     * Changes name of the object
     * @author Yaroslav Petryk
     */
    void changeName(){
        name = getString("Enter new name for "+name);
    }

    /**
     * getter for a name property
     * @return the name property
     * @author Rozhko Andrew
     */
    public String getName(){
        return this.name;
    }

    /**
     * Handler for asking and mass deleting entries from the list
     * @param list list from which entries should be deleted
     * @author Yaroslav Petryk
     */
    void deleteFromList(ArrayList<? extends Named> list){
        String[] names = getString("What entry should be deleted?").split("[\\W]");
        int count = delete(list, names);
        System.out.println("Deleted "+count+" entries");
        System.out.println(getNames(list));
    }
}