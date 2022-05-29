package ValletOr.model;

import ValletOr.subject.BarController;
import ValletOr.subject.Drink;

import javax.swing.table.AbstractTableModel;
import java.util.ArrayList;

public class DrinksTableModel extends AbstractTableModel {

    private BarController data;
    private ArrayList<Drink> searchData;
    private boolean searchByFilter;

    public DrinksTableModel(){
        data = new BarController();
        searchData = new ArrayList<>();
        searchByFilter = false;
    }

    @Override
    public int getRowCount() {
        if(searchByFilter){
            return searchData.size();
        }else{
            return data.getSize();
        }
    }

    @Override
    public int getColumnCount() {
        return 3;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        Drink drink;
        if(searchByFilter){
            drink = searchData.get(rowIndex);

        }else{
            drink = data.getDrink(rowIndex);
        }
        switch (columnIndex){
            case 0:
                return rowIndex;
            case 1:
                return drink.getName();
            case 2:
                if(drink.isAlc()){
                    return "Алкогольный";
                }else{
                    return "Безалкогольный";
                }
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
                return "Alcohol";
        }
        return "";
    }
    public boolean isSearchOn(){
        return searchByFilter;
    }

    public int getID(int rowIndex){
        if(searchByFilter){
            return searchData.get(rowIndex).getId();
        }else{
            return data.getDrink(rowIndex).getId();
        }

    }

    public String getName(int rowIndex){
        if(searchByFilter){
            return searchData.get(rowIndex).getName();
        }else{
            return data.getDrink(rowIndex).getName();
        }
    }

    public Boolean isAlc(int rowIndex){
        if(searchByFilter){
            return searchData.get(rowIndex).isAlc();
        }else{
            return data.getDrink(rowIndex).isAlc();
        }
    }

    public Boolean isFav(int rowIndex){
        if(searchByFilter){
            return searchData.get(rowIndex).isFav();
        }else{
            return data.getDrink(rowIndex).isFav();
        }
    }

    public String getInst(int rowIndex){
        if(searchByFilter){
            return searchData.get(rowIndex).getInstruction();
        }else{
            return data.getDrink(rowIndex).getInstruction();
        }
    }

    public ArrayList<String> getIngredientsList(){
        return data.getIngredients();
    }

    public IngredientsTableModel getTableModelForDrink(int rowIndex){
        if(searchByFilter){
            return new IngredientsTableModel(searchData.get(rowIndex));
        }else{
            return new IngredientsTableModel(data.getDrink(rowIndex));
        }

    }

    public void setDrink(int index ,String name, Boolean alc, Boolean fav, String instr, ArrayList<String> ing, ArrayList<String> amo){
        Drink temp;
        if(searchByFilter){
            temp = searchData.get(index);
        }else{
            temp = data.getDrink(index);
        }
        temp.setName(name);
        temp.setAlc(alc);
        temp.setFav(fav);
        temp.setInstruction(instr);
        temp.setIngredients(ing);
        temp.setIngAmounts(amo);
        if(searchByFilter){
            data.setDrink(data.getList().indexOf(searchData.get(index)), temp);
            searchData.set(index, temp);
        }else{
            data.setDrink(index, temp);
        }
        fireTableDataChanged();
    }
    public void delDrink(int index){
        if(searchByFilter){
            data.delDrink(data.getList().indexOf(searchData.get(index)));
            searchData.remove(index);
        }else{
            data.delDrink(index);
        }
        fireTableDataChanged();
    }
    public void addDrink(int id, String name, boolean alc, boolean fav, String instr, ArrayList<String> ing, ArrayList<String> amo){
        data.addDrink(new Drink(id, name, alc, fav, instr, ing, amo));
        fireTableDataChanged();
    }

    public void searchByFilter(String name, boolean alc, boolean fav, String inst, ArrayList<String> ing, ArrayList<String> amo){
        searchData.clear();
        for(Drink drink:data.getList()){
            if(((drink.getName().equals(name))||(name.equals("")))&&(drink.isAlc() == alc)&&(drink.isFav() == fav)&&((drink.getInstruction().equals(inst))||(inst.equals("")))&&(drink.getIngredients().containsAll(ing))){
                searchData.add(drink);
            }
        }
        searchByFilter = true;
        fireTableDataChanged();
    }

    public void searchCancel(){
        searchData.clear();
        searchByFilter = false;
        fireTableDataChanged();
    }

    public int findFreeID(){
        int i = 0;
        boolean inUse;
        do{
            inUse = false;
            i++;
            for(Drink d : data.getList()){
                if(i==d.getId()){
                    inUse = true;
                    break;
                }
            }
        }while(inUse);
        return i;
    }

    public void saveDB(){
        data.saveDB();
    }
}
