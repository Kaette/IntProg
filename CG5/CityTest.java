import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/**
 * The test class CityTest.
 *
 * @author  Kristian Pedersen
 * @version 18-11-24
 */
public class CityTest
{
    private Game game;
    private Country country1, country2;
    private City cityA, cityB, cityC, cityD, cityE, cityF, cityG;

    @Test
    public void constructor(){
        assertEquals("City A", cityA.getName());
        assertEquals("City B", cityB.getName());
        assertEquals("City C", cityC.getName());
        assertEquals("City D", cityD.getName());
        assertEquals("City E", cityE.getName());
        assertEquals("City F", cityF.getName());
        assertEquals("City G", cityG.getName());

        assertTrue((80 == cityA.getValue()) && (80 ==cityA.getInitialValue()));
        assertTrue((60 == cityB.getValue()) && (60 ==cityB.getInitialValue()));
        assertTrue((40 == cityC.getValue()) && (40 ==cityC.getInitialValue()));
        assertTrue((100 == cityD.getValue()) && (100 ==cityD.getInitialValue()));
        assertTrue((50 == cityE.getValue()) && (50 ==cityE.getInitialValue()));
        assertTrue((90 == cityF.getValue()) && (90 ==cityF.getInitialValue()));
        assertTrue((70 == cityG.getValue()) && (70 ==cityG.getInitialValue()));
        
        assertEquals("Country 1", cityA.getCountry().getName());
        assertEquals("Country 1", cityB.getCountry().getName());
        assertEquals("Country 1", cityC.getCountry().getName());
        assertEquals("Country 1", cityD.getCountry().getName());
        assertEquals("Country 2", cityE.getCountry().getName());
        assertEquals("Country 2", cityF.getCountry().getName());
        assertEquals("Country 2", cityG.getCountry().getName());
    }

    @Test
    public void arrive(){
        //Tester arrive metoden ved at køre den 1000 gange 
        for(int seed = 0; seed < 1000; seed++) { // Forskellige seeds
            game.getRandom().setSeed(seed);
            int bonus = country1.bonus(80);
            game.getRandom().setSeed(seed); // Seed resettes
            assertEquals(bonus, cityA.arrive());
            assertEquals(80-bonus, cityA.getValue());
            cityA.reset(); //Byen resettes for at testen kan køres igen
        }
    }

    @Test
    public void changeValue(){
        //Tester changeValue ved at kalde den på en by og vise den er den givne mændge lavere end før
        int amount = 42;
        cityA.changeValue(amount);
        assertEquals(cityA.getInitialValue(), cityA.getValue() - amount);
        cityA.reset();
        cityA.changeValue(-amount);
        assertEquals(cityA.getInitialValue(), cityA.getValue() + amount);
    }

    @Test
    public void reset(){
        //Tester reset ved at give byen en forkert værdi, vise den har ændret sig, og så vise at den er tilbage efter er reset()
        cityD.changeValue(1337);
        assertFalse(cityD.getValue() == cityD.getInitialValue());
        cityD.reset();
        assertTrue(cityD.getValue() == cityD.getInitialValue());
    }

    @Test
    public void testToString(){
        assertEquals("City A (80)", cityA.toString());
        assertEquals("City B (60)", cityB.toString());
        assertEquals("City C (40)", cityC.toString());
        assertEquals("City D (100)", cityD.toString());
        assertEquals("City E (50)", cityE.toString());
        assertEquals("City F (90)", cityF.toString());
        assertEquals("City G (70)", cityG.toString());
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
        
        //Creates a game objects
        game = new Game();
        country1.setGame(game);

        // Create cities
        cityA = new City("City A", 80, country1);
        cityB = new City("City B", 60, country1);
        cityC = new City("City C", 40, country1);
        cityD = new City("City D", 100, country1);
        cityE = new City("City E", 50, country2);
        cityF = new City("City F", 90, country2);
        cityG = new City("City G", 70, country2);
        
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
