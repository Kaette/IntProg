import java.util.*;
/**
 * Write a description of class MafiaCountry here.
 *
 * @author Kristian Pedersen
 * @version 25-11-24
 */
public class MafiaCountry extends Country
{
    /**
     * Constructor klassen nedarver strengen name der beskriver landets navn fra superklassen
     */
    public MafiaCountry(String name){
        super(name);
    }

    /**
     * En modificering af superklassens bonus metode hvor spilleren har en 20% risiko for at blive berøvet. Ellers kalder den blot supermetoden.
     *
     * @param value Parameteren value benyttes kun hvis røveriet ikke finder sted og super.bonus metoden skal kaldes.
     * @return Hvis røveriet findet sted returneres spillerens tab. Hvis ikke returneres spillerens bonus ved at køre superklassens bonus() metode.
     */
    @Override
    public int bonus(int value){
        int risk = getGame().getSettings().getRisk();
        int odds = getGame().getRandom().nextInt(101);
        if(risk >= odds){ //risk er 20, odds er 100. Der er 20% risiko for at blive berøvet
            int loss = getGame().getLoss();
            return -loss; //Tabet returneres
        }else{
            return super.bonus(value); //Supermetoden kaldes og gør det sædvanelige
        }
    }
}
