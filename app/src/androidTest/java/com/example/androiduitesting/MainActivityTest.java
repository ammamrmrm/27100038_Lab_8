package com.example.androiduitesting;

import static androidx.test.espresso.Espresso.onData;
import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.action.ViewActions.typeText;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.CoreMatchers.anything;
import static org.hamcrest.CoreMatchers.instanceOf;
import static org.hamcrest.CoreMatchers.is;

import androidx.test.ext.junit.rules.ActivityScenarioRule;
import androidx.test.ext.junit.runners.AndroidJUnit4;
import androidx.test.filters.LargeTest;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

@RunWith(AndroidJUnit4.class)
@LargeTest
public class MainActivityTest {

    @Rule
    public ActivityScenarioRule<MainActivity> scenario = new
            ActivityScenarioRule<>(MainActivity.class);

    /**
     * Helper method to add a city to the list for testing
     */
    private void addCity(String name) {
        onView(withId(R.id.button_add)).perform(click());
        onView(withId(R.id.editText_name)).perform(typeText(name));
        onView(withId(R.id.button_confirm)).perform(click());
    }

    @Test
    public void testActivitySwitch() {
        addCity("Edmonton");

        // Click the first item in the ListView
        onData(is(instanceOf(String.class)))
                .inAdapterView(withId(R.id.city_list))
                .atPosition(0)
                .perform(click());

        // Verify ShowActivity is displayed by checking for its unique TextView
        onView(withId(R.id.textView_city_display)).check(matches(isDisplayed()));
    }

    @Test
    public void testCityNameConsistency() {
        String cityName = "Vancouver";
        addCity(cityName);

        // Click the city
        onData(is(instanceOf(String.class)))
                .inAdapterView(withId(R.id.city_list))
                .atPosition(0)
                .perform(click());

        // Check if the TextView in ShowActivity matches the city name clicked
        onView(withId(R.id.textView_city_display)).check(matches(withText(cityName)));
    }

    @Test
    public void testBackButton() {
        addCity("Toronto");

        // Navigate to ShowActivity
        onData(is(instanceOf(String.class)))
                .inAdapterView(withId(R.id.city_list))
                .atPosition(0)
                .perform(click());

        // Click the back button in ShowActivity
        onView(withId(R.id.button_back)).perform(click());

        // Verify we are back on MainActivity (check if the Add button is visible)
        onView(withId(R.id.button_add)).check(matches(isDisplayed()));
    }
}