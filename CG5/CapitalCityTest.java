import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
/**
 * The test class CapitalCityTest.
 *
 * @author  Kristian Pedersen
 * @version 25-11-24
 */
public class CapitalCityTest
{
    private Game game;
    private Country country1, country2;
    private City cityA, cityB, cityC, cityD, cityE, cityF, cityG;
    
    @Test
    public void arriveFromOtherCountry() { //Der skal fratrækkes told når spilleren kommer fra et andet land
        for(int seed = 0; seed < 1000; seed++) {
            Player player = new GUIPlayer(new Position(cityF, cityD, 0), 250);
            game.getRandom().setSeed(seed); //Seed sættes
            int bonus = country1.bonus(100);
            int toll = 250 / 5; // 20% af 250
            int moneySpent = game.getRandom().nextInt(250 - toll + bonus + 1);
            game.getRandom().setSeed(seed); //Nyt seed
            assertEquals(bonus - toll - moneySpent, cityD.arrive(player)); // Tjekker at der fratrækkes told og penge fra spilleren
            assertEquals(moneySpent + 100 + toll - bonus, cityD.getValue()); //Tjekker at byen får tolden og de brugte penge lagt til sin værdi
            cityD.reset(); //Resetter byen
        } 
    }
    
    @Test
    public void arriveFromSameCountry() { //Hvis spilleren kommer fra samme land skal der ikke trækkes told, men stadig betales penge
        for(int seed = 0; seed < 1000; seed++) {
            Player player = new GUIPlayer(new Position(cityB, cityD, 0), 250);
            game.getRandom().setSeed(seed);
            int bonus = country1.bonus(100);
            int moneySpent = game.getRandom().nextInt(250 + bonus + 1);
            game.getRandom().setSeed(seed);
            assertEquals(bonus - moneySpent, cityD.arrive(player)); // Ingen told, men penge betalt
            assertEquals(moneySpent + 100 - bonus, cityD.getValue()); //Pengene lægges til byens værdi
            cityD.reset();
        } 
    }
    
    @BeforeEach
    public void setUp()
    {
        // Create countries
        country1 = new Country("Country 1");
        country2 = new Country("Country 2");

        // Create cities
        cityA = new City("City A", 80, country1);
        cityB = new City("City B", 60, country1);
        cityC = new City("City C", 40, country1);
        cityD = new CapitalCity("City D", 100, country1);
        cityE = new CapitalCity("City E", 50, country2);
        cityF = new City("City F", 90, country2);
        cityG = new City("City G", 70, country2);
        
        //Creates a game objects
        game = new Game();
        country1.setGame(game);
        country2.setGame(game);

        // Connect cities to countries
        country1.addCity(cityA);
        country1.addCity(cityB);
        country1.addCity(cityC);
        country1.addCity(cityD);

        country2.addCity(cityE);
        country2.addCity(cityF);
        country2.addCity(cityG);

        // Create roads
        country1.addRoads(cityA, cityB, 4);
        country1.addRoads(cityA, cityC, 3);
        country1.addRoads(cityA, cityD, 5);
        country1.addRoads(cityB, cityD, 2);
        country1.addRoads(cityC, cityD, 2);
        country1.addRoads(cityC, cityE, 4);
        country1.addRoads(cityD, cityF, 3);
        country2.addRoads(cityE, cityC, 4);
        country2.addRoads(cityE, cityF, 2);
        country2.addRoads(cityE, cityG, 5);
        country2.addRoads(cityF, cityD, 3);
        country2.addRoads(cityF, cityG, 6);
    }

    /**
     * Tears down the test fixture.
     *
     * Called after every test case method.
     */
    @AfterEach
    public void tearDown()
    {
    }
}
