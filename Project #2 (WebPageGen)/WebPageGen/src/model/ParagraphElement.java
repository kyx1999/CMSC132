package model;

import java.util.ArrayList;
import java.util.List;

public class ParagraphElement extends TagElement {
    private List<Element> items = new ArrayList<>();

    public ParagraphElement(String attributes) {
        super("p", true, null, attributes);
    }

    public void addItem(Element item) {
        items.add(item);
    }

    public String genHTML(int indentation) {
        String temp = "";
        String spaces = Utilities.spaces(indentation);
        for (Element item : items) {
            temp += "\n" + spaces + "   " + item.genHTML(0);
        }
        return spaces + getStartTag() + temp + "\n" + spaces + getEndTag();
    }
}
