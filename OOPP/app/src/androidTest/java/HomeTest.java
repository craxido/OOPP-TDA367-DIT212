import com.example.sauronsarmy.oopp.R;
import com.example.sauronsarmy.oopp.WelcomeActivity;
import com.example.sauronsarmy.oopp.home.HomePresenter;
import com.example.sauronsarmy.oopp.player.PlayerModel;
import com.example.sauronsarmy.oopp.player.PlayerModelInterface;

import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.RootMatchers.isDialog;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withText;
import static org.junit.Assert.*;
import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import android.support.test.espresso.Espresso;
import android.support.test.filters.SmallTest;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;

/**
 * Created by Sarosh on 2017-05-13.
 * @author Sarosh
 */
@RunWith(AndroidJUnit4.class)
@SmallTest
public class HomeTest {
    private PlayerModelInterface player;
    private HomePresenter home;


    @Rule
    public ActivityTestRule<WelcomeActivity> wActivityRule =
            new ActivityTestRule<>(WelcomeActivity.class);

    /*
     * Initial stats for player:
     * Damage: 10
     * DPS: 0
     * MPS: 0
     * Money: 0
     *
     * Initial stats for home:
     * Oil upgrade counter: 1
     * Oil upgrade stat: 1
     * Oil upgrade cost: 100
     */


    /**
     *  Check out the PlayerModelTest to find out what the
     *  Espresso code does.
     * */
    @Before
    public void setUp(){
        Espresso.onView(withId(R.id.newGame)).perform(click());
        Espresso.onView(withText("OK"))
                .inRoot(isDialog())
                .check(matches(isDisplayed()))
                .perform(click());
        player = PlayerModel.getInstance();
        home = new HomePresenter();
    }

    @After
    public void tearDown(){
        player = null;
        home = null;
    }

    /**
     *  Checks that nothing is null
     * */
    @Test
    public void exists(){
        assertNotNull(home);
        assertNotNull(player);
        assertNotNull(home.getOilPumpUpgrade());
    }

    /**
     *  Tests the set and get on the upgrade counter.
     *  Should always start at 1
     *
     * */
    /* FAILS */
    @Test
    public void homeGetSetCounter(){
        /* Why is this 2 ;_; should be 1 */
        assertEquals(1, home.getOilPumpUpgradeCounter());
        home.setOilCounter(5);
        assertEquals(5, home.getOilPumpUpgradeCounter());
    }

    /**
     *  Tests on player:
     *  |- getMoney();
     *  |- getMoneyPerSecond();
     *  |- setMoney();
     *  |- setMoneyPerSecond();
     *
     *  Tests on home:
     *  |- getOilPumpUpgrade();
     *  |- getOilPumpUpgradeCounter();
     *  |- buyOilPumpUpgrade();
     *
     *  Tests on upgrade:
     *  getCost();
     *
     *  Stats not checked since they are subject to change
     * */
    @Test
    public void playerBuysUpgrade(){
        // check player init stats and set money
        assertEquals(0, player.getMoney());
        assertEquals(0, player.getMoneyPerSecond());
        player.addMoney(100);
        assertEquals(100, player.getMoney());
        // check upgrade init stats
        assertEquals(100, home.getOilPumpUpgrade().getCost());
        assertEquals(1, home.getOilPumpUpgradeCounter());
        //buy upgrade
        assertTrue(home.buyOilPumpUpgrade());
        /* check if bought */
        // check home
        assertEquals(2, home.getOilPumpUpgradeCounter());
        assertNotEquals(100, home.getOilPumpUpgrade().getCost());
        // check player
        assertEquals(0, player.getMoney());
        assertEquals(1, player.getMoneyPerSecond());

    }

    /* FAILS */
    @Test
    public void poorPlayerBuysUpgrade(){
        // check player init stats, make sure 0
        /* Why does the player have 9 gold? ;_; should have 0 */
        assertEquals(0, player.getMoney());

        //try to buy upgrade
        assertFalse(home.buyOilPumpUpgrade());
    }




}
