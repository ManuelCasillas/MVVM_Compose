package com.formation.mvvm_compose


import androidx.compose.ui.test.*
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.formation.mvvm_compose.screens.login.LoginRoot
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class LoginScreenTest {

    @get:Rule
    val composeTestRule = createComposeRule()

    @Test
    fun loginButton_onlyEnabled_whenUserAndPasswordAreFilled() {
        // Start the login screen in the test environment
        composeTestRule.setContent {
            LoginRoot(onLogin = {})
        }

        // Check that initially, the login button is disabled
        composeTestRule
            .onNodeWithTag("LoginButton") // Find the login button by text
            .assertIsNotEnabled()

        // Enter username in the user text field
        composeTestRule
            .onNodeWithTag("userTextField") // Assuming we tag the text field for testability
            .performTextInput("prueba@")

        // Check that the login button is still disabled
        composeTestRule
            .onNodeWithTag("LoginButton")
            .assertIsNotEnabled()

        // Enter password in the password text field
        composeTestRule
            .onNodeWithTag("passwordTextField") // Assuming we tag the text field for testability
            .performTextInput("1234567")

        // Check that the login button is enabled now
        composeTestRule
            .onNodeWithTag("LoginButton")
            .assertIsEnabled()

    }


    @Test
    fun passwordVisibilityToggle_worksCorrectly() {
        // Start the login screen in the test environment
        composeTestRule.setContent {
            LoginRoot(onLogin = {})
        }

        // Enter password into the password text field
        composeTestRule
            .onNodeWithTag("passwordTextField")
            .performTextInput("password123")

        // Check that password is initially hidden (visual transformation applies '****')
        composeTestRule
            .onNodeWithTag("passwordTextField")
            .assertTextContains("•••••••••••", ignoreCase = false) // Check for bullet masking

        // Click the visibility toggle to show the password
        composeTestRule
            .onNodeWithTag("passwordVisibilityToggle")
            .performClick()

        // Check that password is now visible (no masking)
        composeTestRule
            .onNodeWithTag("passwordTextField")
            .assertTextContains("password123", ignoreCase = true)

        // Click the visibility toggle again to hide the password
        composeTestRule
            .onNodeWithTag("passwordVisibilityToggle")
            .performClick()

        // Check that password is hidden again (masked as '****')
        composeTestRule
            .onNodeWithTag("passwordTextField")
            .assertTextContains("•••••••••••", ignoreCase = false) // Check for bullet masking
    }
}
