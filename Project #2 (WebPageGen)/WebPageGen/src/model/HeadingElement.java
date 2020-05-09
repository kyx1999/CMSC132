package model;

public class HeadingElement extends TagElement {
    public HeadingElement(Element content, int level, String attributes) {
        super("h" + level % 6, true, content, attributes);
    }
}
