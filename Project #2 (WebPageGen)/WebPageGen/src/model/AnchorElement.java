package model;

public class AnchorElement extends TagElement {
    private String url;
    private String linkText;

    public AnchorElement(String url, String linkText, String attributes) {
        super("a", true, new TextElement(linkText), "href=\"" + url + "\"" + (attributes == null ? "" : attributes));
        this.url = url;
        this.linkText = linkText;
    }

    public String genHTML(int indentation) {
        return super.genHTML(indentation);
    }

    public String getLinkText() {
        return linkText;
    }

    public String getUrlText() {
        return url;
    }
}
