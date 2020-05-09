package model;

import java.util.ArrayList;
import java.util.List;

public class ListElement extends TagElement {
    private List<Element> items = new ArrayList<>();

    public ListElement(boolean orderedList, String attributes) {
        super(orderedList ? "ol" : "ul", true, null, attributes);
    }

    public void addItem(Element item) {
        items.add(item);
    }

    public String genHTML(int indentation) {
        String temp = "";
        String spaces = Utilities.spaces(indentation);
        for (Element item : items) {
            temp += "\n" + spaces + "   <li>" + "\n" + spaces + "      " + item.genHTML(0) + "\n" + spaces + "   </li>";
        }
        return spaces + getStartTag() + temp + "\n" + spaces + getEndTag();
    }
}
