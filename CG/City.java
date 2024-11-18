/**
 * @author Kristian Pedersen
 * @version 11-11-24
 * Formålet med denne klasse er at skabe City objekter der benyttes i Road(), Position(), og Country() klasserne
 */
import java.util.*;

public class City implements Comparable<City>{
    private String name;
    private int value;
    private int initialValue;
    private Country country;

    /**
     * Klassens konstruktør til dens feltvariabler.
     * @param value sætter både this.value og this.initialvalue
     */
    public City(String name, int value, Country country){
        this.name = name;
        this.value = value;
        this.initialValue = value;
        this.country = country;
    }
    
    /**
     * Opdaterer value ved at addere parameteren amount
     * @param amount. Mængden der skal ændres
     */
    public void changeValue(int amount){
        value = value + amount;
    }

    /**
     * Resetter value til dens originale værdi
     */
    public void reset(){
        value = initialValue;
    }

    /**
     * Et @Override af toString metoden der printer byens og dens værdi til konsollen.
     * @return Byens navn efterfulgt af dens pointværdi
     */
    @Override
    public String toString(){
        return name + " (" + value + ")";
    }

    /**
     * Et @Override er hashCode metoden der returnerer et hash for et City objekt. Benytter primtal til at reducerer
     * risikoen for falske positiver.
     * @return Det færdige hash
     */
    @Override
    public int hashCode(){
        int hash = name.hashCode();
        hash = 17 * hash + country.hashCode();
        return hash;
    }
    
    /**
     * Et @Override af equals() metoden til at checke om to City objekter er lige
     * @param Object Det objekt der skal sammenlignes
     * @param otherObject Det objekt Object sammenlignes med
     * @return En boolean der informerer hvorvidt Objekterne er lig hinanden
     */
    @Override
    public boolean equals(Object otherObject){
        if(this == otherObject){return true;}
        if(otherObject == null || getClass() != otherObject.getClass()){return false;}
        City other = (City) otherObject;
        return name.equals(other.name) &&
                   country.equals(other.country);
    }

    /**
     * En simpel compareTo metode der sortere City objekter alfabetisk efter navn
     * @param City Det City objekt der skal sammenlignes med et andet
     * @param other Det andet objekt der skal sammenlignes med
     * @return Sammenligningen af de to objekter
     */
    public int compareTo(City other){
        return name.compareTo(other.name);
    }
    
    /**
     * arrive() metoden benytter bonus() metoden i Country() klassen til at trække et tilfældigt tal
     * i et spænd fra et City objekts value og returnerer tallet.
     * @return Den int bonus der trækkes fra value
     */
    public int arrive(){
        int bonus = country.bonus(value);
        value -= bonus;
        return bonus;
    }

    //=============== Accesor metoder ====================

    /**
     * @return Objektets navn
     */
    public String getName() {
        return name;
    }

    /**
     * @return Objektets værdi
     */
    public int getValue() {
        return value;
    }

    /**
     * @return Objektets originale værdi
     */
    public int getInitialValue() {
        return initialValue;
    }
    
    /**
     * @return Objektets land
     */
    public Country getCountry(){
        return country;
    }
}