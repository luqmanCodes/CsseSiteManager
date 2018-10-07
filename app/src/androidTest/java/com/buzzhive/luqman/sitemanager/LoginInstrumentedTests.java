package com.buzzhive.luqman.sitemanager;

import android.support.test.espresso.assertion.ViewAssertions;
import android.support.test.filters.LargeTest;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.action.ViewActions.typeText;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withId;

@RunWith(AndroidJUnit4.class)
@LargeTest
public class LoginInstrumentedTests {

    @Rule
    public ActivityTestRule<LoginActivity> menuActivityTestRule = new ActivityTestRule<>(LoginActivity.class);
    @Test
    public void tryALogin() {

        onView(withId(R.id.email)).perform(typeText("sm1"));
        onView(withId(R.id.password)).perform(typeText("jhon1234"));
        onView(withId(R.id.email_sign_in_button)).perform(click());
        onView(withId(R.id.btnAddOrder)).check(ViewAssertions.matches(isDisplayed()));
    }
}
