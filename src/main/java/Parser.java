import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.*;
import java.util.Collections;

public class Parser {
    static List<Country> countries = new ArrayList<>();

    public List<Country> sortByName(){
        List<Country> sortedByName = new ArrayList<>(countries);
        // Sort countries alphabetically (least)
        //TODO
        //Collections.sort(sortedByName);
        Collections.sort(sortedByName , Comparator.comparing(Country :: getName));
        return  sortedByName;
    }

    public List<Country> sortByPopulation(){
        List<Country> sortedByPopulation = new ArrayList<>(countries);
        // Sort countries by population (most)
        //TODO
        Collections.sort(sortedByPopulation , Comparator.comparing(Country :: getPopulation));
       return sortedByPopulation;
    }

    public List<Country> sortByArea(){
        List<Country> sortedByArea = new ArrayList<>(countries);
        // Sort countries by area (most)
        //TODO
        //Collections.sort(sortedByArea);
        Collections.sort(sortedByArea , Comparator.comparing(Country :: getArea));
        return sortedByArea;
    }

    public void setUp() throws IOException {
        File file = new File("src\\Resources\\country-list.html");
        Document doc = Jsoup.parse(file, null);
        Element country = doc.select("section#countries").first();
        Elements div = country.select("div.col-md-4.country");
        for (Element countryy : div) {
            String countryName = countryy.select(".country-name").text();
            String capitalCity = countryy.select(".country-capital").text();
            int population = Integer.parseInt(countryy.select(".country-population").text());
            double area = Double.parseDouble(countryy.select(".country-area").text());
            Country country1 = new Country(countryName, capitalCity, population, area);
            Parser.countries.add(country1);
        }

    }


    public static void main(String[] args) throws IOException{
        Parser parser = new Parser();
        parser.setUp();
        while (true) {
            System.out.print("1.Sort By Area     2.Sort By Name    3.Sort By Population");
            Scanner in = new Scanner(System.in);
            String input = in.next();
            if (input.equals("1")) {
                menu1(parser);

            }
            else if (input.equals("2")){
                menu2(parser);

            }
            else if (input.equals("3")){
                menu3(parser);

            }
        }
        //you can test your code here before you run the unit tests ;)
    }


    public static void menu1(Parser parser){
        PrintWriter writer = null;
        try{
            writer = new PrintWriter("SortByArea");
        }
        catch (FileNotFoundException e){
            System.out.print("The File doesnt exist");
        }
        for (int i =countries.size()-1 ; i >=0 ; i--){
            System.out.print(countries.size() - i -1);
            System.out.print("-Country: " + parser.sortByArea().get(i).getName() + ",");
            System.out.print("capital: " + parser.sortByArea().get(i).getCapital() + ",");
            System.out.print("populetion: " + parser.sortByArea().get(i).getPopulation() + ",");
            System.out.print("area: " + parser.sortByArea().get(i).getArea());
            System.out.print("\n");
            writer.print(countries.size() - i -1);
            writer.print("-Country: " + parser.sortByArea().get(i).getName() + ",");
            writer.print("capital: " + parser.sortByArea().get(i).getCapital() + ",");
            writer.print("populetion: " + parser.sortByArea().get(i).getPopulation() + ",");
            writer.print("area: " + parser.sortByArea().get(i).getArea());
            writer.print("\n");
        }
        writer.close();
    }

    public static void menu2(Parser parser) throws FileNotFoundException {
        PrintWriter writer = null;
        try {
            writer = new PrintWriter("SortByName.txt");
        } catch (FileNotFoundException e) {
            System.out.print("The File doesnt exist");
        }
        for (int i = 0 ; i < countries.size() ; i--){
            System.out.print(i);
            System.out.print("-Country: " + parser.sortByName().get(i).getName() + ",");
            System.out.print("capital: " + parser.sortByName().get(i).getCapital() + ",");
            System.out.print("populetion: " + parser.sortByName().get(i).getPopulation() + ",");
            System.out.print("area: " + parser.sortByName().get(i).getArea());
            System.out.print("\n");
            writer.print(i);
            writer.print("-Country: " + parser.sortByArea().get(i).getName() + ",");
            writer.print("capital: " + parser.sortByArea().get(i).getCapital() + ",");
            writer.print("populetion: " + parser.sortByArea().get(i).getPopulation() + ",");
            writer.print("area: " + parser.sortByArea().get(i).getArea());
            writer.print("\n");
        }
        writer.close();
    }

    public static void menu3(Parser parser){
        PrintWriter writer = null;
        try {
            writer = new PrintWriter("SortByPopulation.txt");
        } catch (FileNotFoundException e) {
            System.out.print("The File doesnt exist");
        }
        for (int i =countries.size()-1 ; i >=0 ; i--){
            System.out.print(countries.size() - i -1);
            System.out.print("-Country: " + parser.sortByPopulation().get(i).getName() + ",");
            System.out.print("capital: " + parser.sortByPopulation().get(i).getCapital() + ",");
            System.out.print("populetion: " + parser.sortByPopulation().get(i).getPopulation() + ",");
            System.out.print("area: " + parser.sortByPopulation().get(i).getArea());
            System.out.print("\n");
            writer.print(countries.size() - i -1);
            writer.print("-Country: " + parser.sortByArea().get(i).getName() + ",");
            writer.print("capital: " + parser.sortByArea().get(i).getCapital() + ",");
            writer.print("populetion: " + parser.sortByArea().get(i).getPopulation() + ",");
            writer.print("area: " + parser.sortByArea().get(i).getArea());
            writer.print("\n");
        }
        writer.close();
    }
}
