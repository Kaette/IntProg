/**
 * @author Kristian Pedersen
 * @version 11-11-24
 * Formålet med denne klasse er at skabe Road objekter der benyttes i Country() klassen
 */

import java.util.*;

public class Road implements Comparable<Road>{
    private City from;
    private City to;
    private int length;

    /**
     * Klassens konstruktør til dens feltvariabler
     */
    public Road(City from, City to, int length){
        this.from = from;
        this.to = to;
        this.length = length;
    }

    /**
     * Et @Override af toString() metoden ligesom i City()
     * @return Returnerer en streng der forklarer hvilke byer vejen går imellem, på formen: "from -> to : length
     */
    @Override
    public String toString(){
        return from.getName() + " (" + from.getValue() + ") -> " + to.getName() + " (" + to.getValue() + ") : " + length;
    }
    
    /**
     * Sorterer Road objekter, 1. Alfabetisk efter startby 2. Alfabetisk efter slutby 3, Efter længde
     * @param Road Den vej der skal sammenlignes med en anden
     * @param Det objekt der skal sammenlingnes med
     * @return Sortering efter startsbys navn, alfabetisk
     * @return Sortering efter slutbys navn, alfabetisk
     * @return Sortering efter længde, højest til lavest
     */
    public int compareTo(Road other){
        int fromCompare = from.compareTo(other.getFrom());
        if(fromCompare != 0){
            return fromCompare;
        }
        int toCompare = to.compareTo(other.getTo());
        if(toCompare != 0){
            return toCompare;
        }
        return length - other.length;
    }
    
    /**
     * Genererer hashCode med primtal ligesom i City()
     * @return De færdige hash
     */
    @Override
    public int hashCode(){
        int hash = from.hashCode();
        hash = 11 * hash + to.hashCode();
        hash = 13 * hash + length;
        return hash;
    }
    
    /**
     * Returnerer at to Road objekter er lig hinanden hvis deres startby, slutby, og længde er ens
     * @param Object Det objekt der skal sammenlignes
     * @param otherObject Det objekt Object sammenlignes med
     * @return Et bool statement. Er true hvis startby, slutby, og længde er ens. Eller false
     */
    @Override
    public boolean equals(Object otherObject){
        if(this == otherObject){return true;}
        if(otherObject == null || getClass() != otherObject.getClass()){return false;}
        Road other = (Road) otherObject;
        return from.equals(other.from) &&
                   to.equals(other.to) &&
                   length == other.length;
    }
    
    //=============== Accesor metoder ====================
    
    /**
     * @return Objektes startby
     */
    public City getFrom(){
        return from;
    }

    /**
     * @return Objektets slutby
     */
    public City getTo() {
        return to;
    }

    /**
     * @return Objektets længde
     */
    public int getLength() {
        return length;
    }
}