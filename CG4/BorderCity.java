
/**
 * Write a description of class BorderCity here.
 *
 * @author Kristian Pedersen
 * @version 25-11-24
 */
public class BorderCity extends City
{
    // instance variables - replace the example below with your own

    /**
     * Constructor klassen nedarver sine variabler fra superklassen
     */
    public BorderCity(String name, int value, Country country){
        super(name, value, country);
    }

    /**
     * En nedarvning af arrive metoden fra superklassen der fratrækker told fra den udbetalte bonus hvis spilleren kommer fra et andet land. Ellers gør den det samme som i superklassen.
     * 
     * @param Player p Tager et Player objekt og bruger det til 
     * @return Hvis spilleren ankommer fra et andet land returneres bonussen med den betale told fratrukket. Ellers returneres bonussen bare
     */
    @Override
    public int arrive(Player p){
        int bonus = super.arrive(); //Beregner bonus ud fra supermetoden
        if(!p.getFromCountry().equals(getCountry()) && bonus > -1){ //Tjekker hvorvidt spilleren kommer fra et andet land
            int toll = getCountry().getGame().getSettings().getTollToBePaid();
            int tollpaid = (p.getMoney() * toll)/100;
            changeValue(tollpaid); //Opdaterer byens værdi da tolden skal lægges til
            return bonus - tollpaid; //Returnerer bonussen med tolden trukket fra
        }
        return bonus; //Hvis spilleren ikke er fra et andet er arrive ligesom i City()
    }
}
