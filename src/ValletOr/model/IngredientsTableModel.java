package ValletOr.model;

import ValletOr.subject.Drink;

import javax.swing.table.AbstractTableModel;
import java.util.ArrayList;

public class IngredientsTableModel extends AbstractTableModel {
    private ArrayList<String> ingredients = new ArrayList<>();
    private ArrayList<String> amounts = new ArrayList<>();
    public IngredientsTableModel(){

    }
    public IngredientsTableModel(Drink drink){
        ingredients.addAll(drink.getIngredients());
        amounts.addAll(drink.getIngAmounts());
    }
    @Override
    public int getRowCount() {
        return ingredients.size();
    }

    @Override
    public int getColumnCount() {
        return 3;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        switch (columnIndex){
            case 0:
                return rowIndex;
            case 1:
                return ingredients.get(rowIndex);
            case 2:
                return amounts.get(rowIndex);
        }
        return "";
    }

    @Override
    public String getColumnName(int column){
        switch (column){
            case 0:
                return "№";
            case 1:
                return "Name";
            case 2:
                return "Amount";
        }
        return "";
    }

    public ArrayList<String> getIngredients(){
        return ingredients;
    }

    public ArrayList<String> getAmounts(){
        return amounts;
    }

    public void addIngredient(String name, String amount) throws Exception{
        if(!name.isEmpty()){
            ingredients.add(name);
            if(!amount.isEmpty()){
                amounts.add(amount);
            }else{
                amounts.add("0");
            }
        }else{
            throw new Exception("Пустой ингредиент!");
        }
        fireTableDataChanged();
    }
    public void delIngredient(int index){
        ingredients.remove(index);
        amounts.remove(index);
        fireTableDataChanged();
    }
}
