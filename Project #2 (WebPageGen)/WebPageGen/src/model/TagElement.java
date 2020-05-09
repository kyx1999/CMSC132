package model;

public class TagElement implements Element {
    private String tagName;
    private boolean endTag;
    private Element content;
    private String attributes;
    private int id;
    private static int ids = 0;
    private static boolean enable = true;

    public TagElement(String tagName, boolean endTag, Element content, String attributes) {
        this.tagName = tagName;
        this.endTag = endTag;
        this.content = content;
        this.attributes = attributes;
        this.id = ++ids;
    }

    public int getId() {
        return id;
    }

    public String getStringId() {
        return tagName + id;
    }

    public String getStartTag() {
        return "<" + tagName + (enable ? " id=\"" + getStringId() + "\"" : "") + (attributes == null || attributes == "" ? "" : " " + attributes) + ">";
    }

    public String getEndTag() {
        if (endTag) {
            return "</" + tagName + ">";
        } else {
            return "";
        }
    }

    public void setAttributes(String attributes) {
        this.attributes = attributes;
    }

    public static void resetIds() {
        ids = 0;
    }

    public static void enableId(boolean enable) {
        TagElement.enable = enable;
    }

    public String genHTML(int indentation) {
        return Utilities.spaces(indentation) + getStartTag() + (content == null ? "" : content.genHTML(0)) + getEndTag();
    }
}
