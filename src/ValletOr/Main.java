package ValletOr;

import ValletOr.view.MainFrame;

public class Main {

    public static void main(String[] args) {
/*
        DataBase.initDB();
        ArrayList<Drink> arr = new ArrayList<>();
        for(char c='a'; c<='z'; ++c){
            arr.addAll(ApiHandler.jsonParse("https://www.thecocktaildb.com/api/json/v1/1/search.php?f="+c));;
        }
        //arr=DataBase.getDrinks();
        DataBase.closeDB();
        BarController barController = new BarController(arr);
        ArrayList<String> ingrList = new ArrayList<>();
        ArrayList<String> ingrAmList = new ArrayList<>();
        ingrList.add("TESTINGR1");
        ingrList.add("TESTINGR2");
        ingrAmList.add("1 amount");
        ingrAmList.add("2 amount");
        ingrList.add("TESTINGR1");
        ingrAmList.add("spec amount");
        barController.addDrink(new Drink(123, "TEST", true, true, "TESTINSTR",ingrList, ingrAmList));
        barController.saveDB();
*/

        MainFrame mf = new MainFrame();
    }
}
