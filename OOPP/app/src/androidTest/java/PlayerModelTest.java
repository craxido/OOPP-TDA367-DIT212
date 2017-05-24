import android.support.test.espresso.Espresso;
import android.support.test.filters.SmallTest;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;

import com.example.sauronsarmy.oopp.R;
import com.example.sauronsarmy.oopp.WelcomeActivity;
import com.example.sauronsarmy.oopp.player.PlayerModel;
import com.example.sauronsarmy.oopp.player.PlayerModelInterface;

import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.RootMatchers.isDialog;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withText;
import static org.junit.Assert.assertEquals;

/**
 * Created by erik on 18/04/17.
 * @author erik
 */
@RunWith(AndroidJUnit4.class)
@SmallTest
public class PlayerModelTest {
    private PlayerModelInterface player;
    private static final double DELTA = 1e-15;

    /**
     * Launches the WelcomeActivity before each method annotated with @Before or @Test is run.
     * Destroys the activity after the test has finished and all methods annotated @After.
     */
    @Rule
    public ActivityTestRule<WelcomeActivity> wActivityRule = new ActivityTestRule<>(WelcomeActivity.class);

    /**
     * After the WelcomeActivity has been launched clicks through the button to launch a new game.
     * This will wipe all the data stored in SharedPreference file.
     *
     * After the MainActivity has been launched we know that the playerModel has been created and we
     * can safely get its instance.
     */
    @Before
    public void newGame() {
        Espresso.onView(withId(R.id.newGame)).perform(click());
        Espresso.onView(withText("OK"))
                .inRoot(isDialog())
                .check(matches(isDisplayed()))
                .perform(click());
        player = PlayerModel.getInstance();
    }

    @After
    public void tearDown() throws Exception {
        player = null;
    }

    /**
     * Tests that the default values are set when loading up a new game
     */
    @Test
    public void testDefaultValues() throws Exception {
        assertEquals(0, player.getMoney(), DELTA);
        assertEquals(0, player.getMoneyPerSecond(), DELTA);
        assertEquals(10, player.getDamage(), DELTA);
        assertEquals(0, player.getDamagePerSecond(), DELTA);
    }

    /**
    * Testing the setters
     */
    @Test
    public void setterTest() throws Exception {
        player.setDamage(50);
        assertEquals(50, player.getDamage());

        player.setDamagePerSecond(3);
        assertEquals(3, player.getDamagePerSecond(), DELTA);

        player.addMoney(10);
        assertEquals(10, player.getMoney(), DELTA);

        player.removeMoney(10);
        assertEquals(0, player.getMoney(), DELTA);

        player.setMoneyPerSecond(35);
        assertEquals(35, player.getMoneyPerSecond(),DELTA);
    }

    /**
     * Testing update()
     * Which should increase the money every second
     */
    @Test
    public void updateTest() throws InterruptedException {
        assertEquals(0, player.getMoney(), DELTA);
        player.setMoneyPerSecond(10);
        Thread.sleep(1010);
        assertEquals(10, player.getMoney(), DELTA);
        player.setMoneyPerSecond(0);
        player.removeMoney(player.getMoney());
    }

}