package csc413.com.t7.hungerstrike.RecipeStrings;

/**
 * Used as intermediary for setting and getting recipe/ingredient info
 *
 * @author Mardan Anwar/Anu Aggarwal
 *
 */

public class RecipeTie
{
    private String title;
    private String image_url;
    private String rId;
    private String apiname;
    private String[] ingredientarr;
    private String sourceurl;

    public String getSourceurl() {
        return sourceurl;
    }

    public void setSourceurl(String sourceurl) {
        this.sourceurl = sourceurl;
    }

    public String getApiname() {
        return apiname;
    }

    public void setApiname(String apiname) {
        this.apiname = apiname;
    }

    public String[] getIngredientarr() {
        return ingredientarr;
    }

    public void setIngredientarr(String[] ingredientarr) {
        this.ingredientarr = ingredientarr;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setImage_url(String image_url) {
        this.image_url = image_url;
    }

    public void setRId(String rId) {
        this.rId = rId;
    }

    public String getTitle() {
        return this.title;
    }

    public String getImage_url() {
        return this.image_url;
    }

    public String getRId() {
        return this.rId;
    }
}


