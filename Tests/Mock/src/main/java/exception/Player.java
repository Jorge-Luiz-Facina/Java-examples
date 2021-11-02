package exception;

import java.util.ArrayList;
import java.util.List;

public class Player {
    private Armor armor;

    public List<String> getElements() {
        List<String> elements = new ArrayList<>();

        try{
            elements = armor.getElements();
        }catch (NullPointerException e){
            throw new NullPointerException("Null Pointer armor...");
        }

        return elements;
    }
}
