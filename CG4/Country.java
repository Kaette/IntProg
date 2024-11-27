import java.util.*;
/**
 * @author Kristian Pedersen
 * @version 11-11-24
 * Formålet med denne klasse er at benytte og samle City(), Road() og Position() klasserne
 */
public class Country {
    private String name;
    private Map<City, Set<Road>> network;
    private Game game;

    /**
     * Network initialiseres med et TreeMap<>
     */
    public Country(String name){
        this.name = name;
        this.network = new TreeMap<>();
    }

    /**
     * Nulstiller værdierne af byerne i netværket
     */
    public void reset(){
        for (City c : network.keySet()){
            c.reset();
        }
    }

    /**
     * Simpel toString() metode der returnerer navnet på objektet
     */
    @Override
    public String toString(){
        return name;
    }

    /**
     * Sorterer Country objekter, Alfabetisk efter navn
     * @param Country Det land der skal sammenlignes med et andet
     * @param Det land der skal sammenlingnes med
     * @return Sortering efter navn, alfabetisk
     */
    public int compareTo(Country other){
        return name.compareTo(other.name);
    }

    /**
     * En simpel hashCode() metode der tager et hash af navnet
     * @return Et hash af objektets navn
     */
    @Override
    public int hashCode(){
        return name.hashCode();
    }

    /**
     * En metode der sammenlinger to country objekter og returnerer en bool der fortæller om de er ens
     * @param Object Det objekt der skal sammenlignes
     * @param otherObject Det objekt Object sammenlignes med
     * @return En boolean der informerer hvorvidt Objekterne er lig hinanden. Dette er tilfældet hvis navnene er lig hinanden
     */
    @Override
    public boolean equals(Object otherObject){
        if(this == otherObject){return true;}
        if(otherObject == null || getClass() != otherObject.getClass()){return false;}
        Country other = (Country) otherObject;
        return name.equals(other.name);
    }

    /**
     * Bonus metoden der benyttes i arrive() klassen i City(). Benytter Game() klassens getRandom() metode til at
     * genererer et tilfældigt tal fra 1 til tallet. Hvis parameteren er minde end 1 returneres der blot 0
     * @param value Den værdi der benyttes som max i det tilfældige spænd, givet det er over 0
     */
    public int bonus(int value){
        if(value > 0){
            Random random = game.getRandom();
            int min = 1;
            int max = value;
            return random.nextInt(value + 1);
        }else{
            return 0;
        }
    }

    /**
     * Tilføjer en by til netværket. Gør intet hvis den allerede findes
     * @param City c Den by der skal addes
     */
    public void addCity(City c){
        network.putIfAbsent(c, new TreeSet<>());
    }

    /**
     * Tilføjer veje til netværket. Tilføjer veje til og fra to byer, så længe de begge ligger i landet. Hvis kun 1 by ligger i landet bliver
     * der kun skabt en vej fra den til den anden. Hvis ingen af dem gør sker det intet
     * @param City a Den første by
     * @param City b Den anden by
     * @param length Længden af vejen
     */
    public void addRoads(City a, City b, int length){
        if(length > 0 && !a.equals(b)){
            if(network.containsKey(a)){
                Road road = new Road(a, b, length);
                network.get(a).add(road);
            }
            if(network.containsKey(b)){
                Road road = new Road(b, a, length);
                network.get(b).add(road);
            }
        }
    }

    /**
     * Returnerer byens position med et objekt
     * @return Et position objekt med en længde på 0
     */
    public Position position(City city){
        return new Position(city, city, 0);
    }

    /**
     * Returnerer et position objekt der fortæller om spilleren er klar til at rejse
     * @param City from Startbyen der rejses fra
     * @param City to Byen der rejses til
     * @return 1. Hvis start og slutby er ens returneres byens position. 2. Hvis det er muligt at lave en vej mellem dem returneres et objekt med længden af vejen. 3. Hvis det ikke er muligt at lave en vej returneres byens position
     */
    public Position readyToTravel(City from, City to){
        if(from.equals(to)){
            return position (from);//Position(from, to, 0);
        }
        if(network.containsKey(from)){
            Set<Road> roads = getRoads(from);
            for(Road r : roads){
                if(r.getTo().equals(to)){
                    return new Position(from, to, r.getLength());
                }
            }
        }
        return position(from);
    }

    //=============== Accesor og mutator metoder ====================

    /**
     * @return Landets navn
     */
    public String getName() {
        return name;
    }

    /**
     * @return Byer i netværket
     */
    public Set<City> getCities(){
        return network.keySet();
    }

    /**
     * Søger gennem Mappet indtil den finder et City() objekt med det givne navn
     * Hvis der ikke findes et objekt med navnet returneres der null
     * @param navnet på den ønskede by
     * @return Hvis byen findes true. Ellers false
     */
    public City getCity(String name){
        for(City c : getCities()){
            if(c.getName().equals(name)){
                return c;
            }
        }
        return null;
    }

    /**
     * Returnerer vejene i netværket
     * @param City c Den by hvis veje skal returneres
     * @return et sæt af vejene der tilhører byen
     */
    public Set<Road> getRoads(City c){
        return network.getOrDefault(c, new TreeSet<>());
    }

    /**
     * Kobler Country() klassen sammen med Game() klassen
     * @return en game objekt
     */
    public Game getGame(){
        return game;
    }

    /**
     * Skaber et spil via Game() klassen
     * @param Game game Et objekt af Game() klassen
     */
    public void setGame(Game game){
        this.game = game;
    }
}
