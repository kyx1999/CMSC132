package model;

import java.util.ArrayList;
import java.util.List;

public class WebPage implements Comparable<WebPage> {
    private String title;
    private List<Element> items = new ArrayList<>();

    public WebPage(String title) {
        this.title = title;
    }

    public void addElement(Element element) {
        items.add(element);
    }

    public String getWebPageHTML(int indentation) {
        String spaces = Utilities.spaces(indentation);
        String head = "<!doctype html>\n<html>\n" + spaces + "<head>\n" + spaces + "   <meta charset=\"utf-8\"/>\n" + spaces + "   <title>" + title + "</title>\n" + spaces + "</head>\n" + spaces + "<body>\n";
        String rear = spaces + "</body>\n</html>";
        String middle = "";
        for (Element item : items) {
            middle += item.genHTML(indentation) + "\n";
        }
        return head + middle + rear;
    }

    public void writeToFile(String filename, int indentation) {
        Utilities.writeToFile(filename, getWebPageHTML(indentation));
    }

    public Element findElem(int id) {
        for (Element item : items) {
            if (item instanceof TagElement && ((TagElement) item).getId() == id) {
                return item;
            }
        }
        return null;
    }

    public String stats() {
        int list = 0, paragraph = 0, table = 0;
        double capacity = 0D, empty = 0D, result;
        for (Element item : items) {
            if (item instanceof ListElement) {
                list++;
            }
            if (item instanceof ParagraphElement) {
                paragraph++;
            }
            if (item instanceof TableElement) {
                table++;
                String temp = ((TableElement) item).genHTML(0);
                temp = temp.substring(((TableElement) item).getStartTag().length() + 1, temp.length() - ((TableElement) item).getEndTag().length() - 1);
                String str = temp;
                int index, count = 0;
                while ((index = str.indexOf("</td><td>")) != -1) {
                    str = str.substring(index + 9);
                    count++;
                }
                str = temp;
                while ((index = str.indexOf("</tr>\n   <tr>")) != -1) {
                    str = str.substring(index + 13);
                    count++;
                }
                capacity += count + 1;
                str = temp;
                count = 0;
                while ((index = str.indexOf("<td></td>")) != -1) {
                    str = str.substring(index + 9);
                    count++;
                }
                empty += count;
            }
        }
        if (capacity != 0) {
            result = 100 - empty / capacity * 100;
        } else {
            result = 0;
        }
        return "List Count: " + list + "\nParagraph Count: " + paragraph + "\nTable Count: " + table + "TableElement Utilization: " + String.format("%.1f", result);
    }

    public int compareTo(WebPage webPage) {
        return this.title.compareTo(webPage.title);
    }

    public static void enableId(boolean choice) {
        TagElement.enableId(choice);
    }
}
