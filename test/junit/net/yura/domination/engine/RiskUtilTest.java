package net.yura.domination.engine;

import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Hashtable;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Properties;
import java.util.PropertyResourceBundle;
import java.util.ResourceBundle;
import java.util.Vector;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.prefs.Preferences;
import junit.framework.TestCase;
import net.yura.domination.engine.core.Player;
import net.yura.domination.engine.core.RiskGame;
import net.yura.domination.ui.swinggui.SwingGUIPanel;
import org.junit.Test;
import static org.mockito.Matchers.anyString;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

/**
 *
 * SWEN 352 Testing Risk Utilities
 */
public class RiskUtilTest extends TestCase {
    
    public RiskUtilTest(String testName) {
        super(testName);
    }
    
    @Override
    protected void setUp() throws Exception {
        super.setUp();
    }
    
    @Override
    protected void tearDown() throws Exception {
        super.tearDown();
    }

    /**
     * Test openMapStream.
     */
    @Test
    public void testOpenMapStream() throws Exception {
        
        RiskIO streamOpener = mock(RiskIO.class);
        RiskUtil.streamOpener = streamOpener;
        String a = "a";
        InputStream expResult = new ByteArrayInputStream(new byte[1]);
        when(streamOpener.openMapStream(a)).thenReturn(expResult);

        InputStream result = RiskUtil.openMapStream(a);
        assertEquals(expResult, result);
    }

    /**
     * Test openStream.
     */
    @Test
    public void testOpenStream() throws Exception {
        RiskIO streamOpener = mock(RiskIO.class);
        RiskUtil.streamOpener = streamOpener;
        String a = "a";
        InputStream expResult = new ByteArrayInputStream(new byte[1]);
        when(streamOpener.openStream(a)).thenReturn(expResult);
        
        InputStream result = RiskUtil.openStream(a);
        assertEquals(expResult, result);
    }

    /**
     * Test getResourceBundle.
     */
    @Test
    public void testGetResourceBundle() {
        RiskIO streamOpener = mock(RiskIO.class);
        RiskUtil.streamOpener = streamOpener;
        
        Class c = String.class;
        String n = "n";
        Locale l = Locale.ENGLISH;
        ResourceBundle expResult = null;
        try {
            expResult = new PropertyResourceBundle(new ByteArrayInputStream(new byte[1]));
        } catch (IOException ex) {
            Logger.getLogger(RiskUtilTest.class.getName()).log(Level.SEVERE, null, ex);
            fail();
        }
        when(streamOpener.getResourceBundle(c, n, l)).thenReturn(expResult);
        ResourceBundle result = RiskUtil.getResourceBundle(c, n, l);
        assertEquals(expResult, result);
    }

    /**
     * Test openDocs.
     */
    @Test
    public void testOpenDocs() throws Exception {
        RiskIO streamOpener = mock(RiskIO.class);
        RiskUtil.streamOpener = streamOpener;
        
        String docs = "docs";
        RiskUtil.openDocs(docs);
    }

    /**
     * Test saveFile.
     */
    @Test
    public void testSaveFile() throws Exception {
        RiskIO streamOpener = mock(RiskIO.class);
        RiskUtil.streamOpener = streamOpener;
        
        String file = "file";
        RiskGame aThis = mock(RiskGame.class);
        RiskUtil.saveFile(file, aThis);
    }

    /**
     * Test getLoadFileInputStream.
     */
    @Test
    public void testGetLoadFileInputStream() throws Exception {
        RiskIO streamOpener = mock(RiskIO.class);
        RiskUtil.streamOpener = streamOpener;
        
        String file = "file";
        InputStream expResult = new ByteArrayInputStream(new byte[1]);
        when(streamOpener.loadGameFile(file)).thenReturn(expResult);
        
        InputStream result = RiskUtil.getLoadFileInputStream(file);
        assertEquals(expResult, result);
    }

    /**
     * Test createGameString.
     */
    @Test
    public void testCreateGameString() {
        int easyAI = 0;
        int averageAI = 1;
        int hardAI = 2;
        int gameMode = 0;
        int cardsMode = 0;
        boolean AutoPlaceAll = false;
        boolean recycle = false;
        String mapFile = "mapFile";

        String expResult = "1\n0\n2\nchoosemap mapFile\nstartgame domination increasing";
        String result = RiskUtil.createGameString(easyAI, averageAI, hardAI, gameMode, cardsMode, AutoPlaceAll, recycle, mapFile);
        assertEquals(expResult, result);
        
        AutoPlaceAll = true;
        gameMode = 2;
        cardsMode = 1;
        expResult = "1\n0\n2\nchoosemap mapFile\nstartgame capital fixed autoplaceall";
        result = RiskUtil.createGameString(easyAI, averageAI, hardAI, gameMode, cardsMode, AutoPlaceAll, recycle, mapFile);
        assertEquals(expResult, result);
    
        recycle = true;
        gameMode = 3;
        cardsMode = 2;
        expResult = "1\n0\n2\nchoosemap mapFile\nstartgame mission italianlike autoplaceall recycle";
        result = RiskUtil.createGameString(easyAI, averageAI, hardAI, gameMode, cardsMode, AutoPlaceAll, recycle, mapFile);
        assertEquals(expResult, result);
    
    }

    /**
     * Test getMapNameFromLobbyStartGameOption.
     */
    @Test
    public void testGetMapNameFromLobbyStartGameOption() {
        String options = "1\n2\n3\nchoosemap whatever";
        String expResult = "whatever";
        String result = RiskUtil.getMapNameFromLobbyStartGameOption(options);
        assertEquals(expResult, result);
    }

    /**
     * Test getGameDescriptionFromLobbyStartGameOption.
     */
    @Test
    public void testGetGameDescriptionFromLobbyStartGameOption() {
        String options = "1\n2\n3\nchooseMap whatever\nstartgame now";
        String expResult = "AI:6 now";
        String result = RiskUtil.getGameDescriptionFromLobbyStartGameOption(options);
        System.out.println(result);
        assertEquals(expResult, result);
    }

    /**
     * Test printStackTrace.
     */
    @Test
    public void testPrintStackTrace() {
        Throwable ex = new NullPointerException("This should be logged as a test.");
        RiskUtil.printStackTrace(ex);
    }


    /**
     * Test getPlayerSettings.
     */
    @Test
    public void testGetPlayerSettings() {
        Risk risk = mock(Risk.class);
        String value = "value";
        when(risk.getRiskConfig(anyString())).thenReturn(value);
        Class uiclass = String.class;
        String key = "whatever";
        
        Properties result = RiskUtil.getPlayerSettings(risk, uiclass);
        assertEquals(value, result.getProperty(key));
        
        uiclass = SwingGUIPanel.class;
        Preferences expPreferences = Preferences.userNodeForPackage(uiclass);
        result = RiskUtil.getPlayerSettings(risk, uiclass);
        assertEquals(expPreferences.get(key, value), result.getProperty(key));
    }

    /**
     * Test loadPlayers.
     */
    @Test
    public void testLoadPlayers() {
        Risk risk = mock(Risk.class);
        String name = "name";
        Class uiclass = SwingGUIPanel.class;
        
        RiskUtil.loadPlayers(risk, uiclass);
    }

    /**
     * Test savePlayers GUI.
     */
    @Test
    public void testSavePlayers_Risk_Class() {
        Risk risk = mock(Risk.class);  
        Class uiclass = SwingGUIPanel.class;
        RiskGame riskGame = mock(RiskGame.class);
        when(risk.getGame()).thenReturn(riskGame);
        Vector v = new Vector();
        String name = "name";
        v.add(new Player(0, name, 0, "0"));
        when(riskGame.getPlayers()).thenReturn(v);
        when(risk.getType(0)).thenReturn("type");
        
        RiskUtil.savePlayers(risk, uiclass);
        Preferences expPreferences = Preferences.userNodeForPackage(uiclass);
        String expNameKey = "default.player1.name";
        assertEquals(name, expPreferences.get(expNameKey, null));
    }

    /**
     * Test savePlayers CLI.
     */
    @Test
    public void testSavePlayers_List_Class() {
        List v = new ArrayList();
        String[] player = new String[3];
        player[0] = "name";
        player[1] = "color";
        player[2] = "type";
        v.add(player);
        Class uiclass = SwingGUIPanel.class;
        
        RiskUtil.savePlayers(v, uiclass);
        Preferences expPreferences = Preferences.userNodeForPackage(uiclass);
        String expNameKey = "default.player1.name";
        assertEquals("name", expPreferences.get(expNameKey, null));
    }

    /**
     * Test readMap.
     */
    @Test
    public void testReadMap() throws Exception {
        byte[] b = new byte[3];
        InputStream in = new ByteArrayInputStream(b);
        BufferedReader result = RiskUtil.readMap(in);
        assertNotNull(result);
        
        b[0] = (byte) 0xEF;
        result = RiskUtil.readMap(in);
        assertNotNull(result);
        
        
        b[1] = (byte) 0xBB;
        result = RiskUtil.readMap(in);
        assertNotNull(result);
        
        b[2] = (byte) 0xBF;
        result = RiskUtil.readMap(in);
        assertNotNull(result);
    }

    /**
     * Test saveGameLog.
     */
    @Test
    public void testSaveGameLog() throws Exception {
        File logFile = new File("testSaveGameLog.txt");
        RiskGame game = mock(RiskGame.class);
        Vector v = new Vector();
        v.add("test command");
        when(game.getCommands()).thenReturn(v);
        RiskUtil.saveGameLog(logFile, game);
    }

    /**
     * Test getOutputStream.
     */
    @Test
    public void testGetOutputStream() throws Exception {
        File dir = new File("testGetOutputStreamDir");
        String fileName = "testGetOutputStream.txt";
        OutputStream result = RiskUtil.getOutputStream(dir, fileName);
        assertNotNull(result);
    }

    /**
     * Test rename.
     */
    @Test
    public void testRename() {
        File oldFile = mock(File.class);
        File newFile = mock(File.class);
        when(newFile.exists()).thenReturn(true);
        when(newFile.delete()).thenReturn(false);
        try {
            RiskUtil.rename(oldFile, newFile);
            fail("Should not be able to rename file that doesn't exist/cannot be deleted.");
        } catch (RuntimeException e) {}
        
        when(newFile.delete()).thenReturn(true);
        when(oldFile.renameTo(newFile)).thenReturn(false);
        when(oldFile.delete()).thenReturn(false);
        when(oldFile.toString()).thenReturn("oldFile");
        when(oldFile.isDirectory()).thenReturn(true);
        when(newFile.exists()).thenReturn(true);
        when(oldFile.list()).thenReturn(new String[0]);
        
        RiskUtil.rename(oldFile, newFile);
        
        when(oldFile.list()).thenThrow(new RuntimeException("This runTimeException should occur."));
        try {
        RiskUtil.rename(oldFile, newFile);
        fail("Runtime exception should have occured.");
        } catch (RuntimeException e) {}
    }

    /**
     * Test asVector.
     */
    @Test
    public void testAsVector() {
        List list = new ArrayList();
        assertTrue(RiskUtil.asVector(list) instanceof Vector);
        list = new Vector();
        assertTrue(RiskUtil.asVector(list) instanceof Vector);
    }

    /**
     * Test asHashtable.
     */
    @Test
    public void testAsHashtable() {
        Map map = new Hashtable();
        assertTrue(RiskUtil.asHashtable(map) instanceof Hashtable);
    }

    /**
     * Test replaceAll.
     */
    @Test
    public void testReplaceAll() {
        String string = "this string is a string";
        String notregex = "string";
        String replacement = "thing";
        String expResult = "this thing is a thing";
        String result = RiskUtil.replaceAll(string, notregex, replacement);
        assertEquals(expResult, result);
    }

    /**
     * Test copy.
     */
    @Test
    public void testCopy() throws Exception {
        File src = mock(File.class);
        File dest = mock(File.class);
        
        when(src.isDirectory()).thenReturn(true);
        when(dest.exists()).thenReturn(false);
        String[] files = new String[0];
        when(src.list()).thenReturn(files);
        RiskUtil.copy(src, dest);
    }
}