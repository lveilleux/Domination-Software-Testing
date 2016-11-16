package net.yura.domination.engine.core;

import java.util.Vector;
import junit.framework.TestCase;
import static junit.framework.TestCase.assertEquals;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

/**
 *
 * SWEN-352 Testing Continent.java
 */
public class ContinentTest extends TestCase {
    private Continent c;
    private String id, name;
    private Country country, country2;
    private Player p1, p2;
    
    @Before
    public void setUp() throws Exception {
        id = "CONTINENT_ID";
        name = "CONTINENT";
        Continent c = new Continent(id, name, 5, 2);
        country = new Country(0, "c1", "Country 1", c, 0, 0);
        country2 = new Country(1, "c2", "Country 2", c, 50, 50);
        super.setUp();
    }
    
    @After
    public void tearDown() throws Exception {
        super.tearDown();
    }
    
    @Test
    public void testCreateContinent() {
        assertEquals(id, c.getIdString());
        assertEquals(name, c.getName());
        assertEquals(5, c.getArmyValue());
        assertEquals(2, c.getColor());
    }
    
    @Test
    public void testContinentToString() {
        assertEquals(id + " [" + 5 + "]", c.toString());
        c.setArmyValue(0);
        assertEquals(id, c.toString());
    }
    
    @Test
    public void testArmyValue() {
        assertEquals(5, c.getArmyValue());
        c.setArmyValue(0);
        assertEquals(0, c.getArmyValue());
        c.setArmyValue(-2);
        assertEquals(-2, c.getArmyValue());
    }
    
    @Test
    public void testColor() {
        assertEquals(2, c.getColor());
        c.setColor(1);
        assertEquals(1, c.getColor());
    }
    
    @Test
    public void testTerritoriesContained() {
        assertFalse(c.getTerritoriesContained().contains(country));
        
        c.addTerritoriesContained(country);
        assertTrue(c.getTerritoriesContained().contains(country));
        
        c.addTerritoriesContained(country2);
        assertTrue(c.getTerritoriesContained().contains(country2));
    }
    
    @Test
    public void testGetOwner() {
        assertEquals(null, country.getOwner());
        
        country.setOwner(p1);
        assertEquals("Player 1", country.getOwner().toString());
        
        country.setOwner(p2);
        assertEquals("Player 2", country.getOwner().toString());
    }
    
    @Test
    public void testGetNumberOwned() {
        assertEquals(0, c.getNumberOwned(p1));
        
        c.addTerritoriesContained(country);
        country.setOwner(p1);
        assertEquals(1, c.getNumberOwned(p1));
        
        c.addTerritoriesContained(country2);
        country2.setOwner(p1);
        assertEquals(2, c.getNumberOwned(p1));
    }
    
    @Test
    public void testIsOwned() {
        c.addTerritoriesContained(country);
        country.setOwner(p1);
        assertTrue(c.isOwned(p1));
        
        c.addTerritoriesContained(country2);
        assertFalse(c.isOwned(p1));
        
        country2.setOwner(p2);
        assertTrue(c.isOwned(p2));
    }
}
