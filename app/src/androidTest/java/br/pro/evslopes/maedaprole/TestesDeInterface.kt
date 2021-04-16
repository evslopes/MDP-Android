package br.pro.evslopes.maedaprole

import android.support.test.filters.LargeTest
import android.support.test.runner.AndroidJUnit4
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.action.ViewActions.typeText
import androidx.test.espresso.intent.Intents.intended
import androidx.test.espresso.intent.matcher.IntentMatchers.hasComponent
import androidx.test.espresso.intent.rule.IntentsTestRule
import androidx.test.espresso.matcher.ViewMatchers.withId
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
@LargeTest
class TestesDeInterface {
    @Rule
    @JvmField
    val rule = IntentsTestRule(MainActivity::class.java)

    @Test
    fun useAppContext() {
        onView(withId(R.id.editTextEmailLogin))
            .perform(typeText("testeInterface@teste.com"))

        onView(withId(R.id.editTextSenhaLogin))
            .perform(typeText("123456"))

        onView(withId(R.id.btnLogin))
            .perform(click())

        intended(hasComponent("br.pro.evslopes.maedaprole.ui.meudia.list.ListMeuDiaFragment"))
    }
}

