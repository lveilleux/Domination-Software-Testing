/**
 * SWEN-352 Testing Card.java
 */
package net.yura.domination.engine.core;

import junit.framework.TestCase;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

/**
 * Test the Card.java class in the engine.core files
 * SWEN 352
 */
public class CardTest extends TestCase {
    public CardTest() {
        super("Testing Card.java");
    }
    
    @Before
    public void setUp() throws Exception {
        super.setUp();
    }
    
    @After
    public void tearDown() throws Exception {
        super.tearDown();
    }
    
	/**
	 * Test initial values.
	 */
    @Test
    public void testCreateCard() {
        Continent continent = new Continent("CustomContinent", "Northrend", 10, 2);
        Country c = new Country(1, "Custom", "Redshire", continent, 102, 203);
        Card card = new Card("Cannon", c);
        assertEquals(c, card.getCountry());
        assertEquals("Cannon", card.getName());
    }
    
	/**
	 * Test creation with null.
	 */
	@Test
    public void testCreateCardNullValue() {
        Card card = new Card("Cannon", null);
        assertEquals(null, card.getCountry());
        assertEquals("Cannon", card.getName());
    }
	
	/**
	 * Test setName.
	 */
    @Test
    public void testSetName() {
        Card card = new Card("Cannon", null);
        card.setName("Infantry");
        assertEquals("Infantry", card.getName());
        card.setName("Cannon");
        assertEquals("Cannon", card.getName());
        card.setName("Cavalry");
        assertEquals("Cavalry", card.getName());
        card.setName("wildcard");
        assertEquals("wildcard", card.getName());
    }
    
	/**
	 * Test setCountry.
	 */
    @Test
    public void testSetCountry() {
        Continent continent = new Continent("CustomContinent", "Northrend", 10, 2);
        Country c = new Country(1, "Custom", "Redshire", continent, 102, 203);
        Card card = new Card("Cannon", null);
        card.setCountry(c);
        assertEquals("Cannon", card.getName());
        assertEquals(c, card.getCountry());
        Continent continent2 = new Continent("CustomContinent", "Southborn", 7, 1);
        Country c2 = new Country(1, "Cust", "Bluevile", continent, 53, 73);
        card.setCountry(c2);
        assertEquals(c2, card.getCountry());
    }
    
	/**
	 * Test toString.
	 */
    @Test
    public void testToString() {
        Continent continent = new Continent("CustomContinent", "Northrend", 10, 2);
        Country c = new Country(1, "Custom", "Redshire", continent, 102, 203);
        Card card = new Card("Cannon", null);
        assertEquals("Cannon", card.toString());
        card.setCountry(c);
        assertEquals("Cannon - Custom (1)", card.toString());
    }
}
