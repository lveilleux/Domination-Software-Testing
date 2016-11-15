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
    
    @Before
    public void setUp() throws Exception {
        super.setUp();
        id = "CONTINENT_ID";
        name = "CONTINENT";
        Continent c = new Continent(id, name, 5, 2);
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
    
    
}
