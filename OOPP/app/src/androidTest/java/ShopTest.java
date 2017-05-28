import android.support.test.espresso.Espresso;
import android.support.test.filters.SmallTest;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;

import com.example.sauronsarmy.oopp.R;
import com.example.sauronsarmy.oopp.WelcomeActivity;
import com.example.sauronsarmy.oopp.player.PlayerModel;
import com.example.sauronsarmy.oopp.player.PlayerModelInterface;
import com.example.sauronsarmy.oopp.shop.ShopMVPInterface;
import com.example.sauronsarmy.oopp.shop.ShopPresenter;
import com.example.sauronsarmy.oopp.upgrade.Upgrade;

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

/**
 * Created by Sarosh on 2017-05-23.
 * @Author Sarosh
 */

@RunWith(AndroidJUnit4.class)
@SmallTest
public class ShopTest {
    private PlayerModelInterface player;
    private ShopPresenter shop;

    @Rule
    public ActivityTestRule<WelcomeActivity> wActivityRule =
            new ActivityTestRule<>(WelcomeActivity.class);
    /**
     * Initial stats for shop:
     *
     * Damage upgrade counter: 1
     * Damage upgrade stat: 2
     * Damage upgrade cost: 50
     *
     * Dps upgrade counter: 1
     * Dps upgrade stat: 1
     * Dps upgrade cost: 100
     */

    @Before
    public void setUp(){
        Espresso.onView(withId(R.id.newGame)).perform(click());
        Espresso.onView(withText("OK"))
                .inRoot(isDialog())
                .check(matches(isDisplayed()))
                .perform(click());
        player = PlayerModel.getInstance();
        shop = new ShopPresenter();
    }

    @After
    public void tearDown(){
        player = null;
        shop = null;
    }

    /**
     *  Checks that nothing is null
     * */
    @Test
    public void exists(){
        assertNotNull(shop);
        assertNotNull(shop.getDamageUpgrade());
        assertNotNull(shop.getDPSUpgrade());
        assertNotNull(player);
    }

    /**
     *  The default values.
     *  Not all of them are tested as they are not needed
     *  as well as some have been tested elsewhere.
     * */
    @Test
    public void testDefaultValues(){
        assertEquals(1, shop.getDamageUpgradeCounter());
        assertEquals(1, shop.getDPSUpgradeCounter());
        Upgrade damageUpgrade = shop.getDamageUpgrade();
        Upgrade dpsUpgrade = shop.getDPSUpgrade();

        assertEquals(2, damageUpgrade.getStat());
        assertEquals(50, damageUpgrade.getCost());

        assertEquals(1, dpsUpgrade.getStat());
        assertEquals(100, dpsUpgrade.getCost());

        assertEquals(0, player.getMoney());
        assertEquals(10, player.getDamage());
        assertEquals(0, player.getDamagePerSecond());
    }

    /**
     *  Player buys an upgrade given that he has the money.
     *  His damage should increase and the ugprades should have updated.
     *  Money should be removed the player.
     * */
    @Test
    public void buyDamageUpgrade(){
        player.addMoney(50);
        assertEquals(50, player.getMoney());
        assertEquals(10, player.getDamage());

        assertTrue(shop.buyDamageUpgrade());
        assertEquals(2, shop.getDamageUpgradeCounter());
        assertEquals(12, player.getDamage());
        assertEquals(0, player.getMoney());
    }

    @Test
    public void buyDPSUpgrade(){
        player.addMoney(100);
        assertEquals(100, player.getMoney());
        assertEquals(0, player.getDamagePerSecond());

        assertTrue(shop.buyDPSUpgrade());
        assertEquals(2, shop.getDPSUpgradeCounter());
        assertEquals(1, player.getDamagePerSecond());
        assertEquals(0, player.getMoney());
    }
}
