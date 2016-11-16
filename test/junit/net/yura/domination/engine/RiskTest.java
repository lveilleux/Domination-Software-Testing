package net.yura.domination.engine;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Enumeration;
import java.util.HashSet;
import java.util.List;
import java.util.Properties;
import java.util.ResourceBundle;
import java.util.Set;
import java.util.Vector;
import java.util.logging.Level;
import java.util.logging.Logger;
import junit.framework.TestCase;
import net.yura.domination.engine.ai.AIManager;
import net.yura.domination.engine.core.Card;
import net.yura.domination.engine.core.Country;
import net.yura.domination.engine.core.Mission;
import net.yura.domination.engine.core.Player;
import net.yura.domination.engine.core.RiskGame;
import static org.mockito.Matchers.anyString;
import static org.mockito.Mockito.when;
import static org.mockito.Mockito.mock;

/**
 * SWEN 352 Testing of larger Domination game functions
 */
public class RiskTest extends TestCase {
    ResourceBundle resourceBundle;
    
    public RiskTest(String testName) {
        super(testName);
    }
    
    @Override
    protected void setUp() throws Exception {
        RiskUtil.streamOpener = mock(RiskIO.class);
        resourceBundle = new ResourceBundle() {
            @Override
            protected Object handleGetObject(String key) {
                return "fake_translated_value";
            }

            @Override
            public Enumeration<String> getKeys() {
                return Collections.emptyEnumeration();
            }
        };
        super.setUp();
    }
    
    @Override
    protected void tearDown() throws Exception {
        super.tearDown();
    }

    /**
     * Test getRiskConfig.
     */
    public void testGetRiskConfig() {
        System.out.println("getRiskConfig");
        
        Properties riskConfig = mock(Properties.class);
        Risk instance = new Risk();
        instance.riskconfig = riskConfig;
        String a = "a";
        String b = "b";
        when(riskConfig.getProperty(a)).thenReturn(b);
        String result = instance.getRiskConfig(a);
        assertEquals(b, result);
    }

    /**
     * Test inGameParser.
     */
    public void testInGameParser() {
        System.out.println("inGameParser");
        
        RiskController rc = mock(RiskController.class);
        RiskGame game = mock(RiskGame.class);
        
        Risk instance = new Risk();
        instance.controller = rc;
        instance.game = game;
        when(game.getState()).thenReturn(RiskGame.STATE_BATTLE_WON);
        instance.resb = resourceBundle;
        
        String message = "ERROR stuff stuff";
        instance.inbox.add("stuff");
        instance.inGameParser(message);
        
        message = "LEAVE 0";
        Player p = new Player(0, "n", 0, "0");
        when(game.getCurrentPlayer()).thenReturn(p);
        Vector playerList = new Vector();
        playerList.add(p);
        when(game.getPlayers()).thenReturn(playerList);
        when(game.getState()).thenReturn(RiskGame.STATE_NEW_GAME);
        when(game.delPlayer(anyString())).thenReturn(false);
        instance.inGameParser(message);
        
        message = "PLACE 0";
        Country c = mock(Country.class);
        when(game.getCountryInt(0)).thenReturn(c);
        when(c.getName()).thenReturn("name");
        instance.inGameParser(message);
        
        message = "PLACEALL 0";
        when(game.getCountryInt(0)).thenReturn(c);
        instance.inGameParser(message);
        
        message = "MISSION 0";
        Vector missions = new Vector();
        Mission mission = mock(Mission.class);
        missions.add(mission);
        when(game.getMissions()).thenReturn(missions);
        instance.inGameParser(message);
        
        message = "SOMETHING ELSE";
        instance.inGameParser(message);
        
        message = "o choosemap";
        instance.inGameParser(message);
        
        message = "o choosemap test";
        instance.inGameParser(message);
        
        message = "o choosecards";
        instance.inGameParser(message);
        
        message = "o choosecards test";
        try {
            when(game.setCardsfile(anyString())).thenReturn(true);
        } catch (Exception ex) {
            Logger.getLogger(RiskTest.class.getName()).log(Level.SEVERE, null, ex);
            fail();
        }
        instance.inGameParser(message);
        
        message = "o delplayer";
        instance.inGameParser(message);
        
        message = "o delplayer test";
        when(game.delPlayer(anyString())).thenReturn(true);
        instance.inGameParser(message);
        
        message = "o info error";
        instance.inGameParser(message);
        
        message = "o info";
        when(game.getMapFile()).thenReturn("mapFile");
        when(game.getCardsFile()).thenReturn("cardsFile");
        instance.inGameParser(message);
        
        message = "o autosetup error";
        instance.inGameParser(message);
        
        message = "o autosetup";
        instance.inGameParser(message);
        
        when(game.getPlayers()).thenReturn(new Vector());
        instance.setReplay(true);
        instance.inGameParser(message);
        
        instance.setReplay(false);
        instance.inGameParser(message);
        
        message = "o startgame error";
        instance.inGameParser(message);
        
        message = "o startgame capital italianlike autoplaceall";
        when(game.getPlayers()).thenReturn(playerList);
        when(game.getState()).thenReturn(RiskGame.STATE_ATTACKING);
        instance.onlinePlayClient = null;
        when(game.getRandomPlayer()).thenReturn(0);
        when(game.getNoMissions()).thenReturn(1);
        when(game.getNoPlayers()).thenReturn(1);
        when(game.getGameMode()).thenReturn(RiskGame.MODE_SECRET_MISSION);
        Vector countries = new Vector();
        countries.add(c);
        when(c.getColor()).thenReturn(0);
        when(game.shuffleCountries()).thenReturn(countries);
        instance.inGameParser(message);
        
        message = "o play";
        instance.inGameParser(message);
        
        message = "o play test";
        instance.inGameParser(message);
        
        message = "o showarmies error";
        instance.inGameParser(message);
        
        message = "o showarmies";
        Country[] countriesArray = new Country[1];
        countriesArray[0] = c;
        when(game.getCountries()).thenReturn(countriesArray);
        instance.inGameParser(message);
        
        when(c.getOwner()).thenReturn(p);
        when(c.getArmies()).thenReturn(1);
        when(game.getGameMode()).thenReturn(2);
        when(game.getSetupDone()).thenReturn(true);
        when(game.getCurrentPlayer()).thenReturn(p);
        p.setCapital(c);
        instance.inGameParser(message);
        
        message = "o showcards error";
        instance.inGameParser(message);
        
        message = "o showcards";
        when(game.getState()).thenReturn(RiskGame.STATE_GAME_OVER);
        Card card = mock(Card.class);
        when(card.getName()).thenReturn(Card.WILDCARD);
        when(game.getCardMode()).thenReturn(RiskGame.CARD_FIXED_SET);
        p.giveCard(card);
        instance.inGameParser(message);
        
        when(card.getName()).thenReturn(Card.CANNON);
        when(game.getCardMode()).thenReturn(RiskGame.CARD_ITALIANLIKE_SET);
        instance.inGameParser(message);
        
        when(game.getCardMode()).thenReturn(RiskGame.CARD_INCREASING_SET);
        instance.inGameParser(message);
        
        message = "o autoendgo";
        instance.inGameParser(message);
        
        message = "o autodefend";
        instance.inGameParser(message);
    }


    /**
     * Test getType.
     */
    public void testGetType_int() {
        System.out.println("getType");
        
        int type = 0;
        Risk instance = new Risk();
        String expResult = "human";
        String result = instance.getType(type);
        assertEquals(expResult, result);
        
        type = 1;
        expResult = "ai easy";
        result = instance.getType(type);
        assertEquals(expResult, result);
    }

    /**
     * Test setReplay.
     */
    public void testSetReplay() {
        System.out.println("setReplay");
        
        boolean a = false;
        Risk instance = new Risk();
        instance.setReplay(a);
    }
    
    /**
     * Test getCurrentCards.
     */
    public void testGetCurrentCards() {
        System.out.println("getCurrentCards");
        
        Risk instance = new Risk();
        RiskGame game = mock(RiskGame.class);
        instance.game = game;
        Player p = mock(Player.class);
        when(game.getCurrentPlayer()).thenReturn(p);
        Vector v = new Vector();
        when(p.getCards()).thenReturn(v);
        List result = instance.getCurrentCards();
        assertEquals(v, result);
    }

    /**
     * Test hasArmiesInt.
     */
    public void testHasArmiesInt() {
        System.out.println("hasArmiesInt");
        
        int name = 0;
        Risk instance = new Risk();
        int expResult = 7;
        RiskGame game = mock(RiskGame.class);
        instance.game = game;
        Country c = mock(Country.class);
        when(c.getArmies()).thenReturn(7);
        when(game.getCountryInt(name)).thenReturn(c);
        int result = instance.hasArmiesInt(name);
        assertEquals(expResult, result);
    }

    /**
     * Test canAttack.
     */
    public void testCanAttack() {
        System.out.println("canAttack");
        
        int nCountryFrom = 0;
        int nCountryTo = 1;
        Risk instance = new Risk();
        RiskGame game = mock(RiskGame.class);
        instance.game = game;
        Country countryFrom = mock(Country.class);
        Country countryTo = mock(Country.class);
        when(countryFrom.isNeighbours(countryTo)).thenReturn(true);
        when(game.getCountryInt(nCountryFrom)).thenReturn(countryFrom);
        when(game.getCountryInt(nCountryTo)).thenReturn(countryTo);
        assertTrue(instance.canAttack(nCountryFrom, nCountryTo));
        
        when(countryFrom.isNeighbours(countryTo)).thenReturn(false);
        assertFalse(instance.canAttack(nCountryFrom, nCountryTo));
    }

    /**
     * Test isOwnedCurrentPlayerInt.
     */
    public void testIsOwnedCurrentPlayerInt() {
        System.out.println("isOwnedCurrentPlayerInt");
        
        int name = 0;
        Risk instance = new Risk();
        RiskGame game = mock(RiskGame.class);
        instance.game = game;
        Player p = mock(Player.class);
        when(game.getCurrentPlayer()).thenReturn(p);
        Country c = mock(Country.class);
        when(game.getCountryInt(name)).thenReturn(c);
        when(c.getOwner()).thenReturn(p);
        
        assertTrue(instance.isOwnedCurrentPlayerInt(name));
        
        Player p2 = mock(Player.class);
        when(c.getOwner()).thenReturn(p2);
        assertFalse(instance.isOwnedCurrentPlayerInt(name));
    }

    /**
     * Test getPlayerColors.
     */
    public void testGetPlayerColors() {
        System.out.println("getPlayerColors");
        
        Risk instance = new Risk();
        instance.game = null;
        assertTrue(instance.getPlayerColors().length == 0);
        
        RiskGame game = mock(RiskGame.class);
        instance.game = game;
        when(game.getState()).thenReturn(RiskGame.STATE_DEFEND_YOURSELF);
        Country c = mock(Country.class);
        Player p = mock(Player.class);
        when(c.getOwner()).thenReturn(p);
        when(game.getDefender()).thenReturn(c);
        int colorInt = 7;
        when(p.getColor()).thenReturn(colorInt);
        assertEquals(colorInt, instance.getPlayerColors()[0]);
        
        when(game.getDefender()).thenReturn(null);
        Vector v = new Vector();
        v.add(p);
        when(game.getPlayers()).thenReturn(v);
        when(game.getSetupDone()).thenReturn(true);
        when(p.getNoTerritoriesOwned()).thenReturn(1);
        when(game.getCurrentPlayer()).thenReturn(p);
        assertEquals(colorInt, instance.getPlayerColors()[0]);
    }

    /**
     * Test getCurrentPlayerColor.
     */
    public void testGetCurrentPlayerColor() {
        System.out.println("getCurrentPlayerColor");
        
        Risk instance = new Risk();
        RiskGame game = mock(RiskGame.class);
        instance.game = game;
        
        when(game.getState()).thenReturn(RiskGame.STATE_NEW_GAME);
        assertEquals(0, instance.getCurrentPlayerColor());
        
        when(game.getState()).thenReturn(RiskGame.STATE_ATTACKING);
        Player p = mock(Player.class);
        int color = 8;
        when(p.getColor()).thenReturn(color);
        when(game.getCurrentPlayer()).thenReturn(p);
        assertEquals(color, instance.getCurrentPlayerColor());
    }

    /**
     * Test getColorOfOwner.
     */
    public void testGetColorOfOwner() {
        System.out.println("getColorOfOwner");
        
        Risk instance = new Risk();
        RiskGame game = mock(RiskGame.class);
        instance.game = game;
        
        int n = 9;
        Country c = mock(Country.class);
        when(game.getCountryInt(n)).thenReturn(c);
        Player p = mock(Player.class);
        when(c.getOwner()).thenReturn(p);
        int color = 39;
        when(p.getColor()).thenReturn(color);
        assertEquals(color, instance.getColorOfOwner(n));
    }

    /**
     * Test canTrade.
     */
    public void testCanTrade() {
        System.out.println("canTrade");
        
        String c1 = "c1";
        String c2 = "c2";
        String c3 = "c3";
        Risk instance = new Risk();
        RiskGame game = mock(RiskGame.class);
        instance.game = game;
        when(game.getState()).thenReturn(RiskGame.STATE_ATTACKING);
        assertFalse(instance.canTrade(c1, c2, c3));
        
        when(game.getState()).thenReturn(RiskGame.STATE_TRADE_CARDS);
        Card[] cards = new Card[3];
        when(game.getCards(c1, c2, c3)).thenReturn(cards);
        assertFalse(instance.canTrade(c1, c2, c3));
        
        Card card = mock(Card.class);
        cards[0] = card;
        cards[1] = card;
        cards[2] = card;
        when(game.checkTrade(card, card, card)).thenReturn(true);
        assertTrue(instance.canTrade(c1, c2, c3));
    }

    /**
     * Test getNewCardState.
     */
    public void testGetNewCardState() {
        System.out.println("getNewCardState");
        
        Risk instance = new Risk();
        RiskGame game = mock(RiskGame.class);
        instance.game = game;
        int cardState = 93;
        when(game.getNewCardState()).thenReturn(cardState);
        assertEquals(cardState, instance.getNewCardState());
    }

    /**
     * Test getGame.
     */
    public void testGetGame() {
        System.out.println("getGame");
        
        Risk instance = new Risk();
        RiskGame game = mock(RiskGame.class);
        instance.game = game;
        assertEquals(game, instance.getGame());
    }

    /**
     * Test getLocalGame.
     */
    public void testGetLocalGame() {
        System.out.println("getLocalGame");
        
        Risk instance = new Risk();
        instance.unlimitedLocalMode = false;
        assertFalse(instance.getLocalGame());
    }

    /**
     * Test getCountryName.
     */
    public void testGetCountryName() {
        System.out.println("getCountryName");
        
        int c = 3;
        Risk instance = new Risk();
        String expResult = "the country's name";
        RiskGame game = mock(RiskGame.class);
        instance.game = game;
        assertEquals("", instance.getCountryName(c));
        
        Country country = mock(Country.class);
        when(game.getCountryInt(c)).thenReturn(country);
        when(country.getName()).thenReturn(expResult);
        assertEquals(expResult, instance.getCountryName(c));
    }

    /**
     * Test getCountryCapital.
     */
    public void testGetCountryCapital() {
        System.out.println("getCountryCapital");
        
        int c = 5;
        Risk instance = new Risk();
        RiskGame game = mock(RiskGame.class);
        instance.game = game;
        Country country = mock(Country.class);
        when(game.getCountryInt(c)).thenReturn(country);
        Vector players = new Vector();
        Player p = mock(Player.class);
        players.add(p);
        when(game.getPlayers()).thenReturn(players);
        assertNull(instance.getCountryCapital(c));
        
        when(p.getCapital()).thenReturn(country);
        assertEquals(p, instance.getCountryCapital(c));
    }

    /**
     * Test getAutoEndGo.
     */
    public void testGetAutoEndGo() {
        System.out.println("getAutoEndGo");
        
        Risk instance = new Risk();
        instance.game = null;
        assertFalse(instance.getAutoEndGo());
        
        RiskGame game = mock(RiskGame.class);
        instance.game = game;
        Player p = mock(Player.class);
        when(game.getCurrentPlayer()).thenReturn(p);
        when(p.getAutoEndGo()).thenReturn(true);
        assertTrue(instance.getAutoEndGo());
    }

    /**
     * Test getAutoDefend.
     */
    public void testGetAutoDefend() {
        System.out.println("getAutoDefend");
        
        Risk instance = new Risk();
        instance.game = null;
        assertFalse(instance.getAutoDefend());
        
        RiskGame game = mock(RiskGame.class);
        instance.game = game;
        Player p = mock(Player.class);
        when(game.getCurrentPlayer()).thenReturn(p);
        when(p.getAutoDefend()).thenReturn(true);
        assertTrue(instance.getAutoDefend());
    }

    /**
     * Test getMyAddress.
     */
    public void testGetMyAddress() {
        System.out.println("getMyAddress");
        
        Risk instance = new Risk();
        String address = "a";
        instance.myAddress = address;
        assertEquals(address, instance.getMyAddress());
    }

    /**
     * Test showMessageDialog.
     */
    public void testShowMessageDialog() {
        System.out.println("showMessageDialog");
        String a = "a";
        Risk instance = new Risk();
        RiskController rc = mock(RiskController.class);
        instance.controller = rc;
        instance.showMessageDialog(a);
    }

    /**
     * Test setOnlinePlay.
     */
    public void testSetOnlinePlay() {
        System.out.println("setOnlinePlay");
        
        OnlineRisk online = mock(OnlineRisk.class);
        Risk instance = new Risk();
        instance.setOnlinePlay(online);
        assertEquals(online, instance.onlinePlayClient);
    }

    /**
     * Test setAddress.
     */
    public void testSetAddress() {
        System.out.println("setAddress");
        
        String address = "a";
        Risk instance = new Risk();
        instance.setAddress(address);
        assertEquals(address, instance.myAddress);
    }

    /**
     * Test findEmptySpot.
     */
    public void testFindEmptySpot() {
        System.out.println("findEmptySpot");
        
        Risk instance = new Risk();
        instance.game = null;
        assertNull(instance.findEmptySpot());
        
        RiskGame game = mock(RiskGame.class);
        instance.game = game;
        Vector ps = new Vector();
        Player p = mock(Player.class);
        ps.add(p);
        when(game.getPlayers()).thenReturn(ps);
        when(p.getType()).thenReturn(Player.PLAYER_AI_CRAP);
        when(p.isAlive()).thenReturn(true);
        
        assertEquals(p, instance.findEmptySpot());
    }
}