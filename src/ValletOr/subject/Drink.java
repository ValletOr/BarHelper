package ValletOr.subject;

import java.util.ArrayList;

public class Drink {
    private final int id;
    private String name;
    private boolean isAlc;
    private boolean isFav;
    private String instruction;
    private ArrayList<String> ingredients;
    private ArrayList<String> ingAmounts;

    public Drink(int id, String name, boolean isAlc, boolean isFav, String instruction, ArrayList<String> ingredients, ArrayList<String> ingAmounts) {
        this.id = id;
        this.name = name;
        this.isAlc = isAlc;
        this.isFav = isFav;
        this.instruction = instruction;
        this.ingredients = ingredients;
        this.ingAmounts = ingAmounts;
    }

    public int getId() {
        return id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setAlc(boolean alc) {
        isAlc = alc;
    }

    public boolean isAlc() {
        return isAlc;
    }

    public void setFav(boolean fav) {
        isFav = fav;
    }

    public boolean isFav() {
        return isFav;
    }

    public void setInstruction(String instruction) {
        this.instruction = instruction;
    }

    public String getInstruction() {
        return instruction;
    }

    public void setIngredients(ArrayList<String> ingredients) {
        this.ingredients = ingredients;
    }

    public ArrayList<String> getIngredients() {
        return ingredients;
    }

    public void setIngAmounts(ArrayList<String> ingAmounts) {
        this.ingAmounts = ingAmounts;
    }

    public ArrayList<String> getIngAmounts() {
        return ingAmounts;
    }

    @Override
    public String toString() {
        return String.format("Id: %d, Название напитка: %s, Алкогольный: %b, Инструкция: %s", getId(), getName(), isAlc(), getInstruction());
    }

    public void addIngredient(String ingredient, String amount){
        this.ingredients.add(ingredient);
        this.ingAmounts.add(amount);
    }

    public void removeIngredient(String ingredient) throws Exception{
        if(this.ingredients.contains(ingredient)){
            this.ingAmounts.remove(this.ingredients.indexOf(ingredient));
            this.ingredients.remove(ingredient);
        }
        else {
            throw new Exception("В этом рецепте нет такого ингредиента!");
        }
    }
}
