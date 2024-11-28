/**
 * Write a description of class CapitalCity here.
 *
 * @author Kristian Pedersen
 * @version 25-11-24
 */
public class CapitalCity extends BorderCity
{
    // instance variables - replace the example below with your own

    /**
     * Nedarver variabler fra superklasserne
     */
    public CapitalCity(String name, int value, Country country){
        super(name, value, country);
    }

    /**
     * Denne metode overskriver arrive metoden i BorderCity og tilføjer funktionalitet hvor spilleren fratrækkes en mængde penge der så lægges til byens værdi
     *
     * @param  Player p Tager et player objekt
     * @return Der returneres værdien fra supermetoden med mængden af penge trukket fra
     */
    @Override
    public int arrive(Player p){
        int bonusAndToll = super.arrive(p); //Værdien efter bonus og told er beregnet
        int moneySpent = getCountry().getGame().getRandom().nextInt(p.getMoney() + bonusAndToll + 1); //Beregner hvor mange penge spilleren bruger som funktion af getMoney() metoden og den told og bonus der er beregnet
        changeValue(moneySpent); //Tilføjer de penge der er brugt til byens værdi
        return bonusAndToll - moneySpent; //Trækker pengene der er brugt fra bonusAndToll for at få det endelige beløb
    }
}
