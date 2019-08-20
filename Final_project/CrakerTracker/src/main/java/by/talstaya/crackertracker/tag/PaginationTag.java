package by.talstaya.crackertracker.tag;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.tagext.TagSupport;
import java.io.IOException;
import java.util.Locale;
import java.util.ResourceBundle;

/**
 * PaginationTag2 is used to implement pagination
 *
 * @author Anna Talstaya
 * @version 1.0
 */
public class PaginationTag extends TagSupport {

    private int objectsPerPage;
    private int indexOfPage;
    private int numberOfObjects;

    private String locale;
    private String commandValue;

    private String nameOrWordInName;
    private int minCalories;
    private int minProteins;
    private int minLipids;
    private int minCarbohydrates;
    private int maxCalories;
    private int maxProteins;
    private int maxLipids;
    private int maxCarbohydrates;

    @Override
    public int doStartTag() throws JspException {

        Locale localeOfPage = new Locale(locale);

        ResourceBundle rb = ResourceBundle.getBundle("text", localeOfPage);

        JspWriter out = pageContext.getOut();

        try {
            out.write("<nav aria-label=\"...\">");
            out.write("<ul class=\"pagination justify-content-center\">");

            if (indexOfPage == 1) {
                out.write("<li class=\"page-item disabled\">");
                out.write("<a class=\"page-link\">" + rb.getString("pagination.previous") + "</a>");
                out.write("</li>");
            }

            if (indexOfPage > 1) {
                out.write("<li class=\"page-item\">");
                out.write("<form id=\"previousPageForm\" method=\"get\" action=\"" + commandValue + "\">");
                out.write("<input type=\"hidden\" name=\"indexOfPage\" value=\"" + (indexOfPage - 1) + "\">");

                if (commandValue.equals("search")) {
                    out.write("<input type=\"hidden\" name=\"nameOrWordInName\" value=\"" + nameOrWordInName + "\">");
                } else if (commandValue.equals("product_list")) {
                    out.write("<input type=\"hidden\" name=\"nameOrWordInName\" value=\"" + nameOrWordInName + "\">");
                    out.write("<input type=\"hidden\" name=\"minCalories\" value=\"" + minCalories + "\">");
                    out.write("<input type=\"hidden\" name=\"minProteins\" value=\"" + minProteins + "\">");
                    out.write("<input type=\"hidden\" name=\"minLipids\" value=\"" + minLipids + "\">");
                    out.write("<input type=\"hidden\" name=\"minCarbohydrates\" value=\"" + minCarbohydrates + "\">");
                    out.write("<input type=\"hidden\" name=\"maxCalories\" value=\"" + maxCalories + "\">");
                    out.write("<input type=\"hidden\" name=\"maxProteins\" value=\"" + maxProteins + "\">");
                    out.write("<input type=\"hidden\" name=\"maxLipids\" value=\"" + maxLipids + "\">");
                    out.write("<input type=\"hidden\" name=\"maxCarbohydrates\" value=\"" + maxCarbohydrates + "\">");
                }

                out.write("<a class=\"page-link\" onclick=\"document.getElementById('previousPageForm').submit();\">" + rb.getString("pagination.previous") + "</a>");
                out.write("</form>");
                out.write("</li>");
            }

            out.write("<li class=\"page-item\"><a class=\"page-link\">" + indexOfPage + "</a></li>");

            if ((indexOfPage - 1) * objectsPerPage + objectsPerPage < numberOfObjects) {
                out.write("<li class=\"page-item\">");
                out.write("<form id=\"nextPageForm\" method=\"get\" action=\"" + commandValue + "\">");
                out.write("<input type=\"hidden\" name=\"indexOfPage\" value=\"" + (indexOfPage + 1) + "\">");

                if (commandValue.equals("search")) {
                    out.write("<input type=\"hidden\" name=\"nameOrWordInName\" value=\"" + nameOrWordInName + "\">");
                } else if (commandValue.equals("product_list")) {
                    out.write("<input type=\"hidden\" name=\"nameOrWordInName\" value=\"" + nameOrWordInName + "\">");
                    out.write("<input type=\"hidden\" name=\"minCalories\" value=\"" + minCalories + "\">");
                    out.write("<input type=\"hidden\" name=\"minProteins\" value=\"" + minProteins + "\">");
                    out.write("<input type=\"hidden\" name=\"minLipids\" value=\"" + minLipids + "\">");
                    out.write("<input type=\"hidden\" name=\"minCarbohydrates\" value=\"" + minCarbohydrates + "\">");
                    out.write("<input type=\"hidden\" name=\"maxCalories\" value=\"" + maxCalories + "\">");
                    out.write("<input type=\"hidden\" name=\"maxProteins\" value=\"" + maxProteins + "\">");
                    out.write("<input type=\"hidden\" name=\"maxLipids\" value=\"" + maxLipids + "\">");
                    out.write("<input type=\"hidden\" name=\"maxCarbohydrates\" value=\"" + maxCarbohydrates + "\">");
                }

                out.write("<a class=\"page-link\" onclick=\"document.getElementById('nextPageForm').submit();\">" + rb.getString("pagination.next") + "</a>");
                out.write("</form>");
                out.write("</li>");
            }

            if ((indexOfPage - 1) * objectsPerPage + objectsPerPage >= numberOfObjects) {
                out.write("<li class=\"page-item disabled\">");
                out.write("<a class=\"page-link\">" + rb.getString("pagination.next") + "</a>");
                out.write("</li>");
            }

            out.write("</ul>");
            out.write("</nav>");

        } catch (IOException e) {
            throw new JspException(e);
        }

        return SKIP_BODY;
    }

    public void setObjectsPerPage(int objectsPerPage) {
        this.objectsPerPage = objectsPerPage;
    }

    public void setIndexOfPage(int indexOfPage) {
        this.indexOfPage = indexOfPage;
    }

    public void setNumberOfObjects(int numberOfObjects) {
        this.numberOfObjects = numberOfObjects;
    }

    public void setLocale(String locale) {
        this.locale = locale;
    }

    public void setCommandValue(String commandValue) {
        this.commandValue = commandValue;
    }

    public void setNameOrWordInName(String nameOrWordInName) {
        this.nameOrWordInName = nameOrWordInName;
    }

    public void setMinCalories(int minCalories) {
        this.minCalories = minCalories;
    }

    public void setMinProteins(int minProteins) {
        this.minProteins = minProteins;
    }

    public void setMinLipids(int minLipids) {
        this.minLipids = minLipids;
    }

    public void setMinCarbohydrates(int minCarbohydrates) {
        this.minCarbohydrates = minCarbohydrates;
    }

    public void setMaxCalories(int maxCalories) {
        this.maxCalories = maxCalories;
    }

    public void setMaxProteins(int maxProteins) {
        this.maxProteins = maxProteins;
    }

    public void setMaxLipids(int maxLipids) {
        this.maxLipids = maxLipids;
    }

    public void setMaxCarbohydrates(int maxCarbohydrates) {
        this.maxCarbohydrates = maxCarbohydrates;
    }
}
