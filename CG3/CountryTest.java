import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.util.*;
/**
 * The test class CountryTest.
 *
 * @author  Kristian Pedersen
 * @version 18-11-24
 */
public class CountryTest
{
    private Game game;
    private Country country1, country2;
    private City cityA, cityB, cityC, cityD, cityE, cityF, cityG, cityH;

    /**
     * Default constructor for test class CountryTest
     */

    @Test
    public void getCities(){
        Set network = country1.getCities();
        assertNotNull(network);
        assertEquals(4, network.size());
    }

    @Test
    public void getCity(){
        //Øverst tjekkes der om et City objekt returneres korrekt hvis det findes i network
        City c = country1.getCity("City A");
        assertNotNull(c);
        assertEquals("City A", c.getName());

        //Nederst, hvorvidt der returneres null hvis objektet ikke findes i network
        City v = country1.getCity("Vrøvl");
        assertNull(v);
    }

    @Test
    public void getRoads(){
        //Tjekker at getRoads() reel returnerer et sæt
        Set<Road> cityRoads = country1.getRoads(cityA);
        assertNotNull(cityRoads);

        //Tjekker
        //Set<Road> fakeRoads = country1.getRoads(cityG);
    }

    @Test
    public void position(){
        //Tjekker at en position returneres
        Position p = country1.position(cityA);
        assertNotNull(p);
    }

    @Test
    public void testToString(){
        assertEquals("Country 1", country1.toString());
        assertEquals("Country 2", country2.toString());
    }

    @Test
    public void reset() {
        //Tjekker at reset() virker ved at ændrer to byer og så kun resette den ene
        cityA.arrive(); cityA.arrive(); cityA.arrive();
        cityE.arrive(); cityE.arrive(); cityE.arrive();
        int valueE = cityE.getValue(); // Værdien huskes
        country1.reset();
        assertEquals(80, cityA.getValue()); // Er reset
        assertEquals(valueE, cityE.getValue()); // Er ikke reset
    }

    @Test
    public void readyToTravel(){
        //Tester samme by
        Position sameCity = country1.readyToTravel(cityA, cityA);
        assertEquals(cityA, sameCity.getFrom());
        assertEquals(cityA, sameCity.getTo());
        assertEquals(0, sameCity.getDistance());

        //Tester to forbundne byer
        Position connectedCity = country1.readyToTravel(cityA, cityB);

        assertEquals(cityA, connectedCity.getFrom());
        assertEquals(cityB, connectedCity.getTo());
        assertEquals(4, connectedCity.getDistance());

        //Tester hvis det ikke er muligt at lave en vej
        Position notCity = country1.readyToTravel(cityA, cityE);
        assertEquals(cityA, notCity.getFrom());
        assertEquals(cityA, notCity.getTo());
        assertEquals(0, sameCity.getDistance());
    }

    @Test
    public void addRoads(){
        //Test af vej mellem to gyldige byer
        //Begge byer ligger i landet
        country1.addRoads(cityA, cityD, 4);
        assertEquals(4, country1.getRoads(cityA).size());
        assertEquals(5, country1.getRoads(cityD).size());
        
        //Hvis kun den ene by ligger i landet
        country1.addRoads(cityA, cityE, 5);
        assertEquals(5, country1.getRoads(cityA).size());
        assertEquals(3, country2.getRoads(cityE).size());
        

        //Test af forskellige ugyldige inputs
        //Vej med længde på 0 eller mindre
        country1.addRoads(cityA, cityD, -4);
        assertEquals(5, country1.getRoads(cityA).size());
        assertEquals(5, country1.getRoads(cityD).size());
        //Den samme by tilføjet 2 gange
        country1.addRoads(cityA, cityA, 4);
        assertEquals(5, country1.getRoads(cityA).size());
        assertEquals(5, country1.getRoads(cityD).size());
        
        //Hvis ingen af byerne ligger i landet
        country1.addRoads(cityE, cityF, 4);
        assertEquals(3, country2.getRoads(cityE).size());
        assertEquals(3, country2.getRoads(cityF).size());
    }

    @Test
    public void bonus() {
        //Tester at bonus virker på samme måde som i DieCup4 opgaven
        for(int seed = 0; seed < 100; seed++) { // 100 forskellige seeds
            game.getRandom().setSeed(seed);
            int sum = 0;
            Set<Integer> values = new HashSet<>();
            for(int i = 0; i < 100000; i++) { // Kaldes 100.000 gange
                int bonus = country1.bonus(80);
                assertTrue(0 <= bonus && bonus <= 80); //Test at værdien ligger i det korrekte interval
                values.add(bonus); //Tilføjer 
                sum += bonus;
            }
            double expectedSum = 100000 * 40;
            assertTrue(expectedSum * 0.99 <= sum && sum <= expectedSum * 1.01); //Udregner om summen af tallene er i det internal man forventer
            assertEquals(81, values.size()); //Tester om alle værdier optræder mindst en gang, hvilket praktisk talt er garanteret
        }
        
        //Tester for hvis parameteren value er 0
        int lowNum = country1.bonus(0);
        int negNum = country1.bonus(-4);
        assertEquals(0, lowNum);
        assertEquals(0, negNum);
    }

    /**
     * Sets up the test fixture.
     *
     * Called before every test case method.
     */
    @BeforeEach
    public void setUp()
    {
        // Create countries
        country1 = new Country("Country 1");
        country2 = new Country("Country 2");

        //Creates a game object
        game = new Game();
        country1.setGame(game);
        country2.setGame(game);

        // Create cities
        cityA = new City("City A", 80, country1);
        cityB = new City("City B", 60, country1);
        cityC = new City("City C", 40, country1);
        cityD = new City("City D", 100, country1);
        cityE = new City("City E", 50, country2);
        cityF = new City("City F", 90, country2);
        cityG = new City("City G", 70, country2);

        cityH = new City("City H", 80, country1);

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
