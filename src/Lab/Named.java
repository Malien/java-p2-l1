package Lab;

import Utility.DataInput;

public abstract class Named {
    public String name;
    Named parent;

    /**
     * gets string form console with path and message displayed before the input
     * @param str the message to display
     * @return the string entered from console
     * @author Yaroslav Petryk
     */
    String getString(String str){
        String path = "";
        Named parent = this.parent;
        while (parent != null) {
            path = path.concat(parent.name + "/");
            parent = parent.parent;
        }
        path += name;
        return DataInput.getString(path, str);
    }

    /**
     * gets string from console with path displayed before the input
     * @return the string entered from console
     * @author Yaroslav Petryk
     */
    String getString(){
        String path = "";
        Named parent = this.parent;
        while (parent != null) {
            path = path.concat(parent.name + "/");
            parent = parent.parent;
        }
        path += name;
        return DataInput.getString(path+">");
    }

    void changeName(){
        name = getString("Enter new name for "+name);
    }

    //made for catherda 'delete' method
    String getName(){
        return this.name;
    }
}