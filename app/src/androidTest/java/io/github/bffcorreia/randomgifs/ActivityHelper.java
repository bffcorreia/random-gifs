package io.github.bffcorreia.randomgifs;

import android.app.Activity;
import android.support.test.runner.lifecycle.ActivityLifecycleMonitorRegistry;
import android.support.test.runner.lifecycle.Stage;
import java.util.Collection;

import static android.support.test.InstrumentationRegistry.getInstrumentation;
import static org.junit.Assert.assertEquals;

public class ActivityHelper {

  public static void assertCurrentActivityIs(Class<? extends Activity> activityClass) {
    assertEquals(activityClass.getName(), getCurrentActivity().getComponentName().getClassName());
  }

  private static Activity getCurrentActivity() {
    getInstrumentation().waitForIdleSync();
    final Activity[] activity = new Activity[1];
    getInstrumentation().runOnMainSync(() -> {
      Collection<Activity> activities =
          ActivityLifecycleMonitorRegistry.getInstance().getActivitiesInStage(Stage.RESUMED);
      activity[0] = activities.iterator().next();
    });
    return activity[0];
  }
}
