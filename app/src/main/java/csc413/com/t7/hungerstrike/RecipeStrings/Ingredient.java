package csc413.com.t7.hungerstrike.RecipeStrings;
/**
 * Used as intermediary between adding ingredients and database
 *
 * @author Mardan Anwar
 *
 */

public class Ingredient
{
    private String name;
    private int preferred = 1;

    public Ingredient(){

    }

    public Ingredient(String name, int preferred)
    {
        this.name = name;
        this.preferred = preferred;
    }

    public void setName(String name){
        this.name = name.toLowerCase().trim();
    }

    public void setPreferred(int preferred){
        this.preferred = preferred;
    }

    public String getName(){
        return this.name;
    }

    public int getPreferred(){
        return this.preferred;
    }

    public boolean isPreferred(){
        return this.getPreferred() == 1;
    }

    public String toString(){
        return this.name;
    }
}
