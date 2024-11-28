/**
 * @author Kristian Pedersen
 * @version 11-11-24
 * Formålet med denne klasse er at skabe Position objekter der benyttes i Country() klassen
 */

public class Position {
    private City from;
    private City to;
    private int distance;
    private int total;
    
     /**
     * Klassens konstruktør til dens feltvariabler
     */
    public Position(City from, City to, int distance){
        this.from = from;
        this.to = to;
        this.distance = distance;
        this.total = distance;
    }

    /**
     * Metoden benyttes til at bevæge sig i spillet. Tæller distance 1 ned hvis distance er større end nul
     * @return true hvis distance er større end nul. false hvis ikke
     */
    public boolean move(){
        if(distance > 0){
            distance--;
            return true;
        }
        return false;
    }

    /**
     * Vender spilleren om. Flipper distance og start- og slutby
     */
    public void turnAround(){
        City tempFrom = from;
        from = to;
        to = tempFrom;
        distance = total - distance;
    }

    /**
     * Tjekker om distance er lig nul, og spilleren dermed er ankommet
     * @return true hvis distance er 0. Ellers false
     */
    public boolean hasArrived(){
        return distance == 0;
    }

    /**
     * Et @Override af toString ligesom i City()
     * @return Streng på formen London (60) -> Liverpool (40) : 3/8
     */
    @Override
    public String toString(){
        return from.getName() + " (" + from.getValue() + ") -> " + to.getName() + " (" + 
                    to.getValue() + ") : " + distance + "/" + total;
    }

    /**
     * Genererer ved hjælp af primtal et hash af et Position objekt ligesom i City()
     * @return Det færdige hash
     */
    @Override
    public int hashCode(){
        int hash = from.hashCode();
        hash = 11 * hash + to.hashCode();
        hash = 13 * hash + distance;
        hash = 29 * hash + total;
        return hash;
    }
    
    /**
     * Tjekker om to Position objekter er ens. Dette er tilfældet hvis de har samme startby, slutby, distance, og total.
     * @param Object Det objekt der skal sammenlignes
     * @param otherObject Det objekt Object sammenlignes med
     * @return En boolean der informerer hvorvidt Objekterne er lig hinanden. Dette er tilfældet hvis startby, slutby, distance, og total er lig hinanden
     */
    @Override
    public boolean equals(Object otherObject){
        if(this == otherObject){return true;}
        if(otherObject == null || getClass() != otherObject.getClass()){return false;}
        Position other = (Position) otherObject;
        return from.equals(other.from) &&
                   to.equals(other.to) &&
                   distance == other.distance &&
                   total == other.total;
    }
    
    //=============== Accesor metoder ====================
    /**
     * @return startby
     */
    public City getFrom() {
        return from;
    }

    /**
     * @return slutby
     */
    public City getTo() {
        return to;
    }

    /**
     * @return distance
     */
    public int getDistance() {
        return distance;
    }

    /**
     * return total
     */
    public int getTotal() {
        return total;
    }
}
