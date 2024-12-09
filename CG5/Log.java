import java.util.*;
import java.io.Serializable;
/**
 * Write a description of class Log here.
 *
 * @author Kristian Pedersen
 * @version 09-12-24
 */
public class Log implements Serializable
{
    // instance variables - replace the example below with your own
    private int seed;
    private Settings settings;
    private Map<Integer, String> choices = new HashMap<>();

    public Log(int seed, Settings settings){
        this.seed = seed;
        this.settings = settings;
    }

    /**
     * Simpel get-metode til at indhente spillets seed
     *
     * @return seed Det seed spillet bruger der skal gemmes i filen
     */
    public int getSeed()
    {
        return seed;
    }
    
    public Settings getSettings(){
        return settings;
    }
    
    /**
     * Bruges til at hente værdien af valgene tilbage. Henter den by der vælges i det angivne trin, step
     * 
     * @param step. Det trin der søges efter
     * @return Byen det vælges i trinnet. Hvis denne ikke kan findes returneres null
     */
    public String getChoice(int step){
        //Skal returnere den by der vælges i skridet, altså den value der svarer til nøglen i mappet
        if(choices.containsKey(step)){
            return choices.get(step);
        }else{
            return null;
        }
    }
    
    /**
     * Tilføjer en by og det tilhørende trin
     * 
     * @param step, City. Den by og det trin der skal danne par
     */
    public void add(int step, City city){
        choices.put(step, city.getName());
    }
}
