package io.github.bffcorreia.randomgifs;

import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;
import android.widget.ImageView;
import com.bumptech.glide.load.resource.gif.GifDrawable;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.matcher.ViewMatchers.assertThat;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static io.github.bffcorreia.randomgifs.ActivityHelper.assertCurrentActivityIs;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.not;

@RunWith(AndroidJUnit4.class) public class MainActivityTest {

  @Rule public ActivityTestRule<MainActivity> activityTestRule =
      new ActivityTestRule(MainActivity.class, true, true);

  @Test public void givenRandomGif_whenSurpriseMeButtonClicked_thenNewGifIsShown()
      throws InterruptedException {
    ImageView gifView = activityTestRule.getActivity().findViewById(R.id.gif);

    Thread.sleep(5000);
    GifDrawable originalDrawable = (GifDrawable) gifView.getDrawable();

    onView(withId(R.id.surprise_me)).perform(click());

    Thread.sleep(5000);
    GifDrawable newDrawable = (GifDrawable) gifView.getDrawable();

    assertThat(originalDrawable.getConstantState(), is(not(newDrawable.getConstantState())));
  }

  @Test public void givenRandomGif_whenGifClicked_thenNavigateToShareScreen() {
    onView(withId(R.id.gif)).perform(click());
    assertCurrentActivityIs(ShareActivity.class);
  }
}
