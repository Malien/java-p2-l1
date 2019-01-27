package Lab;

import Utility.DataInput;

public class Named {
    public String name;
    Named parent;

    String getString(String str){
        String path = "";
        Named parent = this.parent;
        while (parent != null) {
            path = path.concat(parent.name);
            parent = parent.parent;
        }
        return DataInput.getString(path, str);
    }
}