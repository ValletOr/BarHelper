package ValletOr.data;

import ValletOr.subject.Drink;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.*;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;

public class ApiHandler {

    private static String readAllFromUrl(Reader rd) throws IOException {
        StringBuilder sb = new StringBuilder();
        int cp;
        while ((cp = rd.read()) != -1) {
            sb.append((char) cp);
        }
        return sb.toString();
    }

    private static String urlConnect(String sURL) throws IOException {
        InputStream is = new URL(sURL).openStream();
        BufferedReader bf = new BufferedReader(new InputStreamReader(is, StandardCharsets.UTF_8));
        return(readAllFromUrl(bf));
        //System.out.println(jsonText);
    }

    public static ArrayList<Drink> jsonParse(String sUrl){
        ArrayList<Drink> arrayList = new ArrayList<>();
        try {
            Object obj = new JSONParser().parse(urlConnect(sUrl));
            JSONObject jo = (JSONObject) obj;
            obj = jo.get("drinks");
            JSONArray drinks = (JSONArray) obj;
            if(drinks!=null) {
                for (Object i : drinks) {
                    ArrayList<String> ing = new ArrayList<>();
                    ArrayList<String> amo = new ArrayList<>();
                    String buff;
                    for (int j = 1; j <= 15; j++) {
                        buff = (String) ((JSONObject) i).get("strIngredient" + j);
                        if ((buff != null) && (!buff.equals(""))) {
                            ing.add(buff);
                            buff = (String) ((JSONObject) i).get("strMeasure" + j);
                            if (buff == null) {
                                buff = "0";
                            }
                            amo.add(buff);
                        }
                    }
                    arrayList.add(new Drink(Integer.parseInt((String) ((JSONObject) i).get("idDrink")), (String) ((JSONObject) i).get("strDrink"), ((String) ((JSONObject) i).get("strAlcoholic")).equals("Alcoholic"), false, (String) ((JSONObject) i).get("strInstructions"), ing, amo));


                }
            }
        } catch (ParseException | IOException e) {
            e.printStackTrace();
        }

        return arrayList;
    }
}
