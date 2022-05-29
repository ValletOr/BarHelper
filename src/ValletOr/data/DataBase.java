package ValletOr.data;

import ValletOr.subject.Drink;

import java.sql.*;
import java.util.ArrayList;

public class DataBase {

    public static String pathToFile = "drinksDB.db";
    public static String dbURL = "jdbc:sqlite:" + pathToFile;
    public static Connection connection;

    public static void initDB(){
        try {
            connection = DriverManager.getConnection(dbURL);
            if(connection!=null) {
                createDB();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void createDB(){
        try {
            Statement statement = connection.createStatement();
            statement.execute("CREATE TABLE if not exists 'drinks'('id' INTEGER PRIMARY KEY, 'name' TEXT, 'alc' BOOLEAN, 'instruction' TEXT);");
            statement.execute("CREATE TABLE if not exists 'ingredients'('id' INTEGER PRIMARY KEY AUTOINCREMENT, 'name' TEXT);");
            statement.execute("CREATE TABLE if not exists 'drinks_ingredients'('id' TEXT PRIMARY KEY, 'drink_id' INTEGER NOT NULL, 'ingredient_id' INTEGER NOT NULL, 'amount' TEXT, FOREIGN KEY (drink_id) REFERENCES drinks(id), FOREIGN KEY (ingredient_id) REFERENCES ingredients(id));");
            statement.execute("CREATE TABLE if not exists 'favorite'('id' INTEGER PRIMARY KEY AUTOINCREMENT, 'drink_id' INTEGER UNIQUE NOT NULL, FOREIGN KEY (drink_id) REFERENCES drinks(id));");
            statement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    //Добавляторы
    public static void addDrink(int id, String name, boolean alc, String instruction){
        try {
            PreparedStatement statement = connection.prepareStatement("INSERT INTO drinks(id, name, alc, instruction) VALUES(?,?,?,?);");
            statement.setInt(1, id);
            statement.setString(2, name);
            statement.setBoolean(3, alc);
            statement.setString(4, instruction);
            statement.executeUpdate();
            statement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void addIngredient(String name){
        try {
            PreparedStatement statement = connection.prepareStatement("INSERT INTO ingredients(name) VALUES(?);");
            statement.setString(1, name);
            statement.executeUpdate();
            statement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static boolean isIngredientExist(String ingName){
        boolean result = false;
        try{
            PreparedStatement statement = connection.prepareStatement("SELECT EXISTS(SELECT 1 FROM ingredients WHERE name = ?)");
            statement.setString(1, ingName);
            ResultSet resultSet = statement.executeQuery();
            if(resultSet.getInt(1)==1){result=true;}
            statement.close();
        }catch (SQLException e){
            e.printStackTrace();
        }
        return result;
    }

    public static void addDrinkIngredient(int drinkId, int ingredientId, String amount, int num){
        try {
            PreparedStatement statement = connection.prepareStatement("INSERT INTO drinks_ingredients(id, drink_id, ingredient_id, amount) VALUES(?,?,?,?);");
            statement.setString(1, String.format("%d-%d-%d", drinkId, ingredientId, num));
            statement.setInt(2, drinkId);
            statement.setInt(3, ingredientId);
            statement.setString(4, amount);
            statement.executeUpdate();
            statement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void addFavorite(int drinkId){
        try {
            PreparedStatement statement = connection.prepareStatement("INSERT INTO favorite(drink_id) VALUES(?);");
            statement.setInt(1, drinkId);
            statement.executeUpdate();
            statement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    //Выводы
    public static ArrayList<Drink> getDrinks(){
        ArrayList<Drink> arrayList = new ArrayList<>();
        ArrayList<Integer> intArray = new ArrayList<>();
        try {
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("SELECT drinks.id, drinks.name, drinks.alc, favorite.id, drinks.instruction, ingredients.name as ingredient,drinks_ingredients.amount FROM drinks LEFT JOIN drinks_ingredients ON drinks_ingredients.drink_id = drinks.id LEFT JOIN ingredients ON ingredients.id = drinks_ingredients.ingredient_id LEFT JOIN favorite ON favorite.drink_id = drinks.id");
            while(resultSet.next()){
                if(!intArray.contains(resultSet.getInt(1))){
                    intArray.add(resultSet.getInt(1));
                    arrayList.add(new Drink(resultSet.getInt(1), resultSet.getString(2), resultSet.getBoolean(3), resultSet.getInt(4)!=0, resultSet.getString(5), new ArrayList<String>(), new ArrayList<String>()));
                    arrayList.get(arrayList.size()-1).addIngredient(resultSet.getString(6), resultSet.getString(7));
                }
                else{
                    arrayList.get(intArray.indexOf(resultSet.getInt(1))).addIngredient(resultSet.getString(6), resultSet.getString(7));
                }
            }
            statement.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return arrayList;
    }

    public static ArrayList<String> getIngredients(){
        ArrayList<String> arr = new ArrayList<>();
        try {
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("SELECT ingredients.name as ingredient FROM ingredients");
            while(resultSet.next()){
                arr.add(resultSet.getString(1));
            }
            statement.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return arr;
    }

    public static int getIngredientIdByName(String ingName){
        int result = 0;
        try {
            PreparedStatement statement = connection.prepareStatement("SELECT id FROM ingredients WHERE name = ?");
            statement.setString(1, ingName);
            ResultSet resultSet = statement.executeQuery();
            result = resultSet.getInt(1);
            statement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result;
    }

    public static void closeDB(){
        try {
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
