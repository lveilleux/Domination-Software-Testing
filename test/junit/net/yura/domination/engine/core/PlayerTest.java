package net.yura.domination.engine.core;

import java.util.Vector;
import junit.framework.TestCase;
import static junit.framework.TestCase.assertEquals;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

/**
 * SWEN-352 Testing Player.java
 */
public class PlayerTest extends TestCase {
    private Player p;
    
    @Before
    public void setUp() throws Exception {
        super.setUp();
        p = new Player(0, "HUMAN", 4, "localhost:3000");
    }
    
    @After
    public void tearDown() throws Exception {
        super.tearDown();
    }
    
    @Test
    public void testCreatePlayer() {
        assertEquals("HUMAN", p.toString());
        assertEquals("localhost:3000", p.getAddress());
        assertEquals(4, p.getColor());
        assertEquals(0, p.getExtraArmies());
        assertEquals(new Vector(), p.getCards());
        assertEquals(0, p.getType());
    }
    
    @Test
    public void testPlayerType() {
        assertEquals(0, p.getType());
        p.setType(1);
        assertEquals(1, p.getType());
    }
    
    @Test
    public void testAutoEndGo() {
        assertEquals(true, p.getAutoEndGo());
        p.setAutoEndGo(false);
        assertEquals(false, p.getAutoEndGo());
    }
    
    @Test
    public void testColor() {
        assertEquals(4, p.getColor());
        p.setColor(1);
        assertEquals(1, p.getColor());
    }
    
    @Test
    public void testAutoDefend() {
        assertEquals(true, p.getAutoDefend());
        p.setAutoDefend(false);
        assertEquals(false, p.getAutoDefend());
    }
    
    @Test
    public void testIsAlive() {
        assertEquals(false, p.isAlive());
        p.addArmies(4);
        assertEquals(true, p.isAlive());
    }
}