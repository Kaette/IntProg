import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
/**
 * The test class BorderCityTest.
 *
 * @author  Kristian Pedersen
 * @version 25-11-24
 */
public class BorderCityTest
{
    private Game game;
    private Country country1, country2;
    private City cityA, cityB, cityC, cityD, cityE, cityF, cityG;
    private Position pos1;

    @Test
    public void arriveFromOtherCountry() { //Der skal fratrækkes told når spilleren kommer fra et andet land
        for(int seed = 0; seed < 1000; seed++) {
            Player player = new GUIPlayer(new Position(cityE, cityC, 0), 250); //Skaber et GUIPlayer objekt der rejser imod den ønskede by
            game.getRandom().setSeed(seed); // Sætter et seed
            int bonus = country1.bonus(40); // Beregner bonus
            int toll = 250 / 5; // 20% af 250
            game.getRandom().setSeed(seed); // Beregner nyt seed
            assertEquals(bonus - toll, cityC.arrive(player)); // Tjekker at tolden fratrækkes korrekt
            assertEquals(40 + toll - bonus, cityC.getValue()); //Tjekker at tolden tillægges byens værdi
            cityC.reset(); //Nulstiller byen
        } 
    }
    
    @Test
    public void arriveFromSameCountry() { //Der skal ikke fratrækkes told hvis spilleren kommer fra samme land
        for(int seed = 0; seed < 1000; seed++) {
            Player player = new GUIPlayer(new Position(cityB, cityC, 0), 250);
            game.getRandom().setSeed(seed);
            int bonus = country1.bonus(40);
            game.getRandom().setSeed(seed);
            assertEquals(bonus, cityC.arrive(player)); //Tjekker at der IKKE fratrækkes told
            assertEquals(40 - bonus, cityC.getValue()); //Tjekker at byen ikke for lagt noget til
            cityC.reset();
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
        cityC = new BorderCity("City C", 40, country1);
        cityD = new BorderCity("City D", 100, country1);
        cityE = new BorderCity("City E", 50, country2);
        cityF = new BorderCity("City F", 90, country2);
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
