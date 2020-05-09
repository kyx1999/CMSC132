package model;

public class ImageElement extends TagElement {
    private String imageURL;

    public ImageElement(String imageURL, int width, int height, String alt, String attributes) {
        super("img", false, null, " src=\"" + imageURL + "\" width=\"" + width + "\" height=\"" + height + "\" alt=\"" + alt + "\"" + (attributes == null ? "" : attributes));
        this.imageURL = imageURL;
    }

    public String getImageURL() {
        return imageURL;
    }
}
