package exception;

import java.util.ArrayList;
import java.util.List;

public class Armor {
    private List<String> elements = new ArrayList<>();

    public void add(String element) {
        elements.add(element);
    }

    public List<String> getElements() {
        return elements;
    }
}
