import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
/**
 * The test class PositionTest.
 *
 * @author  Kristian Pedersen
 * @version 18-11-24
 */
public class PositionTest
{
    private Game game;
    private Country country1, country2;
    private City cityA, cityB, cityC, cityD, cityE, cityF, cityG;
    private Position pos1, pos2;

    /**
     * Default constructor for test class PositionTest
     */
    @Test
    public void constructor(){
        assertEquals(cityA, pos1.getFrom());
        assertEquals(cityB, pos1.getTo());
        assertEquals(4, pos1.getDistance());
        assertEquals(4, pos1.getTotal());
        assertEquals(cityC, pos2.getFrom());
        assertEquals(cityD, pos2.getTo());
        assertEquals(2, pos2.getDistance());
        assertEquals(2, pos2.getTotal());
    }
    
    @Test
    public void move(){
        //Kalder move indtil afstanden er 0, hvilket betyder spilleren er fremme og ikke kan benytte move()
        assertEquals(true, pos2.move());
        assertEquals(1, pos2.getDistance());
        
        assertEquals(true, pos2.move());
        assertEquals(0, pos2.getDistance());
        
        assertEquals(false, pos2.move());
        assertEquals(0, pos2.getDistance());
    }
    
    @Test
    public void turnAround(){
        //Tester turnAround() ved at 
        pos1.turnAround();
        assertEquals(cityA, pos1.getTo());
        assertEquals(cityB, pos1.getFrom());
        assertEquals(0, pos1.getDistance());
        pos1.move();
        pos1.turnAround();
        assertEquals(4, pos1.getDistance());
    }
    
    @Test
    public void hasArrived(){
        //Ligesom i move() testen sikrer vi os at hasArrived først er sand når vi har nået 0
        while(pos1.getDistance() > 0){
            assertEquals(false, pos1.hasArrived());
            pos1.move();
        }
        assertEquals(true, pos1.hasArrived());
    }
    
    @Test
    public void testToString(){
        assertEquals("City A (80) -> City B (60) : 4/4", pos1.toString());
        assertEquals("City C (40) -> City D (100) : 2/2", pos2.toString());
        pos1.move();
        pos2.move();
        assertEquals("City C (40) -> City D (100) : 1/2", pos2.toString());
        assertEquals("City A (80) -> City B (60) : 3/4", pos1.toString());
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

        // Create cities
        cityA = new City("City A", 80, country1);
        cityB = new City("City B", 60, country1);
        cityC = new City("City C", 40, country1);
        cityD = new City("City D", 100, country1);
        cityE = new City("City E", 50, country2);
        cityF = new City("City F", 90, country2);
        cityG = new City("City G", 70, country2);
        
        pos1 = new Position(cityA, cityB, 4);
        pos2 = new Position(cityC, cityD, 2);

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
