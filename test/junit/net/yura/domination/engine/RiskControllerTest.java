package net.yura.domination.engine;

import junit.framework.TestCase;
import org.junit.Test;
import java.util.logging.Level;
import java.util.logging.Logger;
import junit.framework.TestListener;
import junit.runner.BaseTestRunner;
import junit.textui.TestRunner;
import net.yura.domination.engine.core.RiskGame;

/**
 *
 * @author richykapadia
 */
public class RiskControllerTest extends TestCase {

    @Override
    protected void setUp() throws Exception {
        super.setUp();
    }
    
    @Override
    protected void tearDown() throws Exception {
        super.tearDown();
    }
    
    @Test
    public void testCountListeners(){
        RiskController rc = new RiskController();
        assertEquals(0, rc.countListeners());
        RiskListener o = new RiskAdapter() {};
        rc.addListener(o);
        assertEquals(1, rc.countListeners());
        rc.deleteListener(o);
        assertEquals(0, rc.countListeners());
        
    }
    //TODO: Implement the rest of these methods using some sort of TestListener
}