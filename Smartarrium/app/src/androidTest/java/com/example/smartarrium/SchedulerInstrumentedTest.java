package com.example.smartarrium;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import androidx.test.ext.junit.rules.ActivityScenarioRule;
import androidx.test.ext.junit.runners.AndroidJUnit4;
import androidx.test.filters.LargeTest;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.Matchers.allOf;

@RunWith(AndroidJUnit4.class)
@LargeTest
public class SchedulerInstrumentedTest {

    private String stringToBetyped;

    @Rule
    public ActivityScenarioRule<Scheduler> activityRule
            = new ActivityScenarioRule<>(Scheduler.class);

    @Before
    public void initValidString() {
        // Specify a valid string.
        stringToBetyped = "Espresso";
    }

    @Test
    public void title_textview_test() {
        onView(withId(R.id.title)).check(matches(isDisplayed()));
    }

    @Test
    public void SuriseHour_textview_test() {
        onView(withId(R.id.SunriseHourEt)).check(matches(isDisplayed()));
    }
    @Test
    public void SunriseMinute_textview_test() {
        onView(withId(R.id.SunriseMinuteEt)).check(matches(isDisplayed()));
    }
    @Test
    public void DayTemperature_textview_test() {
        onView(withId(R.id.DayTemperatureEt)).check(matches(isDisplayed()));
    }
    @Test
    public void SunsetHour_textview_test() {
        onView(withId(R.id.SunsetHourEt)).check(matches(isDisplayed()));
    }
    @Test
    public void nightfalltMinute_textview_test() {
        onView(withId(R.id.SunsetMinuteEt)).check(matches(isDisplayed()));
    }
    @Test
    public void temperatureAtNight_textview_test() {
        onView(withId(R.id.NightTemperatureEt)).check(matches(isDisplayed()));
    }
    @Test
    public void submit_button_presence_test() {
        onView(allOf(withId(R.id.Submit), withText("Ustaw")));
    }
}
