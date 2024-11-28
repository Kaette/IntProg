import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.util.*;
/**
 * The test class MafiaCountryTest.
 *
 * @author  Kristian Pedersen
 * @version 25-11-24
 */
public class MafiaCountryTest
{
    private Game game;
    private Country country1, country2;
    private City cityA, cityB, cityC, cityD, cityE, cityF, cityG;
    
    @Test
    public void bonus() {
        for(int seed = 0; seed < 1000; seed++) {
            game.getRandom().setSeed(seed);
            int robs = 0;
            int loss = 0;
            int sum = 0;
            Set<Integer> values = new HashSet<>();
            for(int i = 0; i<50000; i++) {
                int bonus = country2.bonus(80);
                if(bonus < 0) { // Robbery
                    robs++;
                    assertTrue(-10 >= bonus && bonus >= -50);
                    loss -= bonus;
                    values.add(-bonus);
                }else{ // No Robbery
                    assertTrue(0 <= bonus && bonus <= 80);
                    sum += bonus;
                } 
            }
            assertTrue(9250 < robs && robs < 10750); //Tester at der i gennemsnit finder det forventede antal røverier sted (20%)
            assertTrue((robs * 25) < loss && loss < (robs * 35)); //Tester at der i gennemsnit fratrækkes den forventede mængde ved røveri (30)
            assertEquals(41, values.size()); //Tester at alle mængder fremkommer
            
            //Tester at bonus udregnes korrekt når der ikke foretages et røveri
            double expectedSum = 40000 * 40;
            assertTrue(expectedSum * 0.97 <= sum && sum <= expectedSum * 1.03);
        } 
    }

    @BeforeEach
    public void setUp(){
        // Create countries
        country1 = new Country("Country 1");
        country2 = new MafiaCountry("Country 2");

        // Create cities
        cityA = new City("City A", 80, country1);
        cityB = new City("City B", 60, country1);
        cityC = new City("City C", 40, country1);
        cityD = new City("City D", 100, country1);
        cityE = new City("City E", 50, country2);
        cityF = new City("City F", 90, country2);
        cityG = new City("City G", 70, country2);

        game = new Game();
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
