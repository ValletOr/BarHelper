package ValletOr.subject;

import ValletOr.data.ApiHandler;
import ValletOr.data.DataBase;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.ArrayList;

public class BarController {
    private ArrayList<Drink> Drinks = new ArrayList<>();
    private ArrayList<String> Ingredients = new ArrayList<>();
    public BarController(ArrayList<Drink> arrayList){
        Drinks = arrayList;
    }
    public BarController(){
        Drinks = takeDrinks();

        Ingredients = takeIngredients();
    }

    private ArrayList<Drink> takeDrinks(){
        Path path = Paths.get("drinksDB.db");
        ArrayList<Drink> arr = new ArrayList<>();
        if(Files.exists(path)){
            DataBase.initDB();
            arr = DataBase.getDrinks();
            DataBase.closeDB();
        }else{
            for(char c='a'; c<='z'; ++c){
                arr.addAll(ApiHandler.jsonParse("https://www.thecocktaildb.com/api/json/v1/1/search.php?f="+c));
            }
        }
        return arr;
    }

    private ArrayList<String> takeIngredients(){
        ArrayList<String> arr = new ArrayList<>();;
        Path path = Paths.get("drinksDB.db");
        if(Files.exists(path)) {
            DataBase.initDB();
            arr = DataBase.getIngredients();
            DataBase.closeDB();
        }
        return arr;
    }

    public ArrayList<String> getIngredients(){
        return Ingredients;
    }

    public void addDrink(Drink drink){
        Drinks.add(drink);
    }

    public Drink getDrink(int index){
        return Drinks.get(index);
    }

    public void setDrink(int index, Drink drink){
        Drinks.set(index, drink);
    }
    public void delDrink(int index){
        Drinks.remove(index);
    }

    public int getSize(){
        return Drinks.size();
    }

    public ArrayList<Drink> getList(){
        return Drinks;
    }

    public void saveDB(){
        try {
            Path path = Paths.get("drinksDB.db");
            Path pathToBackup = Paths.get("Backup.db");
            if (Files.exists(path)) {
                Files.copy(path, pathToBackup, StandardCopyOption.REPLACE_EXISTING);
                Files.delete(path);
            }
            DataBase.initDB();
            for (Drink drink : Drinks) {
                int counter=0;
                DataBase.addDrink(drink.getId(), drink.getName(), drink.isAlc(), drink.getInstruction());
                for (String ing : drink.getIngredients()) {
                    if(!ing.equals("")) {
                        if (!DataBase.isIngredientExist(ing)) {
                            DataBase.addIngredient(ing);
                        }
                        DataBase.addDrinkIngredient(drink.getId(), DataBase.getIngredientIdByName(ing), drink.getIngAmounts().get(counter), counter);
                    }
                    counter++;
                }
                if (drink.isFav()) {
                    DataBase.addFavorite(drink.getId());
                }
            }
            DataBase.closeDB();
        }catch (IOException e){
            e.printStackTrace();
        }
    }
}
