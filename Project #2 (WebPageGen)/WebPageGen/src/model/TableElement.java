package model;

public class TableElement extends TagElement {
    private int width;
    private int height;
    private int size;
    private Element[][] items;

    public TableElement(int width, int height, String attributes) {
        super("table", true, null, attributes);
        this.width = width;
        this.height = height;
        this.items = new Element[width][height];
    }

    public void addItem(int rowIndex, int colIndex, Element item) {
        items[rowIndex][colIndex] = item;
        size++;
    }

    public String genHTML(int indentation) {
        String temp = "";
        String spaces = Utilities.spaces(indentation);
        for (int i = 0; i < height; i++) {
            temp += "\n" + spaces + "   <tr>";
            for (int j = 0; j < width; j++) {
                temp += "<td>" + (items[i][j] == null ? "" : items[i][j].genHTML(0)) + "</td>";
            }
            temp += "</tr>";
        }
        return spaces + getStartTag() + temp + "\n" + spaces + getEndTag();
    }

    public double getTableUtilization() {
        return (double) size / (double) (width * height);
    }
}
