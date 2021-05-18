package bank;

import java.io.Serializable;

public class ThrowawayClass implements Serializable {
    private String name;
    ThrowawayClass(String nameArg){
        this.name = nameArg;
    }
    
    public ThrowawayClass() {
    }

    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    
}
