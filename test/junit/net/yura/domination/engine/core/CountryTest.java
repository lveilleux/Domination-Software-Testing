/**
 * SWEN-352 Testing Country.java
 */
package net.yura.domination.engine.core;

import java.util.Vector;
import junit.framework.TestCase;
import static junit.framework.TestCase.assertEquals;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

/**
 * Test the Card.java class in the engine.core files
 * @author Luke - SWEN 352
 */
public class CountryTest extends TestCase {
    public CountryTest() {
        super("Testing Country.java");
    }
    
    @Before
    public void setUp() throws Exception {
        super.setUp();
    }
    
    @After
    public void tearDown() throws Exception {
        super.tearDown();
    }
    
    @Test
    public void testCreateCountry() {
        Continent continent = new Continent("CustomContinent", "Northrend", 10, 2);
        Country c = new Country(1, "Custom", "Redshire", continent, 102, 203);
        assertEquals("Custom", c.getIdString());
        assertEquals(new Vector(), c.getNeighbours());
        assertEquals("Redshire", c.getName());
        assertEquals(0, c.getArmies());
        assertEquals(continent, c.getContinent());
        assertEquals(null, c.getOwner());
        assertEquals("Custom", c.getIdString());
        assertEquals(1, c.getColor());
        assertEquals(102, c.getX());
        assertEquals(203, c.getY());
    }
    
    @Test
    public void testSetIdString() {
        Continent continent = new Continent("CustomContinent", "Northrend", 10, 2);
        Country c = new Country(1, "Custom", "Redshire", continent, 102, 203);
        assertEquals("Custom", c.getIdString());
        c.setIdString("Better Custom ID");
        assertEquals("Better Custom ID", c.getIdString());
        assertEquals("Better Custom ID (1)", c.toString());
    }
    
    @Test
    public void testSetName() {
        Continent continent = new Continent("CustomContinent", "Northrend", 10, 2);
        Country c = new Country(1, "Custom", "Redshire", continent, 102, 203);
        assertEquals("Redshire", c.getName());
        c.setName("Blueville");
        assertEquals("Blueville", c.getName());
    }
    
    @Test
    public void testSetContinent() {
        Continent continent = new Continent("CustomContinent", "Northrend", 10, 2);
        Country c = new Country(1, "Custom", "Redshire", continent, 102, 203);
        assertEquals(continent, c.getContinent());
        Continent continent2 = new Continent("Custom2", "Southbound", 5, 4);
        c.setContinent(continent2);
        assertEquals(continent2, c.getContinent());
    }
    
    @Test
    public void testAddArmies() {
        Continent continent = new Continent("CustomContinent", "Northrend", 10, 2);
        Country c = new Country(1, "Custom", "Redshire", continent, 102, 203);
        assertEquals(0, c.getArmies());
        c.addArmy();
        assertEquals(1, c.getArmies());
        c.addArmies(10);
        assertEquals(11, c.getArmies());
        c.looseArmy();
        assertEquals(10, c.getArmies());
        c.removeArmies(20);
        assertEquals(-10, c.getArmies());
    }
    
    @Test
    public void testAddNegativeArmies() {
        Continent continent = new Continent("CustomContinent", "Northrend", 10, 2);
        Country c = new Country(1, "Custom", "Redshire", continent, 102, 203);
        assertEquals(0, c.getArmies());
        c.addArmies(-10);
        assertEquals(-10, c.getArmies());
        c.removeArmies(-20);
        assertEquals(10, c.getArmies());
    }
    
    @Test
    public void testSetColorInt() {
        Continent continent = new Continent("CustomContinent", "Northrend", 10, 2);
        Country c = new Country(1, "Custom", "Redshire", continent, 102, 203);
        assertEquals(1, c.getColor());
        c.setColor(3);
        assertEquals(3, c.getColor());
    }
    
    @Test
    public void testSetX() {
        Continent continent = new Continent("CustomContinent", "Northrend", 10, 2);
        Country c = new Country(1, "Custom", "Redshire", continent, 102, 203);
        assertEquals(102, c.getX());
        c.setX(45);
        assertEquals(45, c.getX());
    }
    
    @Test
    public void testSetY() {
        Continent continent = new Continent("CustomContinent", "Northrend", 10, 2);
        Country c = new Country(1, "Custom", "Redshire", continent, 102, 203);
        assertEquals(203, c.getY());
        c.setY(45);
        assertEquals(45, c.getY());
    }
    
    @Test
    public void testDistanceTo() {
        Continent continent = new Continent("CustomContinent", "Northrend", 10, 2);
        Country c = new Country(1, "Custom", "Redshire", continent, 102, 203);
        assertEquals(0, c.getDistanceTo(102, 203));
        assertEquals(227, c.getDistanceTo(0, 0));
        
    }
    
}
