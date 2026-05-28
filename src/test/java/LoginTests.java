import com.codeborne.selenide.Configuration;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static com.codeborne.selenide.Selenide.*;
import static com.codeborne.selenide.Condition.*;

public class LoginTests {

    @BeforeEach
    void setup(){
        closeWebDriver();
    }

    @Test
    void successfulAuthorizationTest() {
        open("https://qa-guru.github.io/one-page-form/login.html");
        $("[data-testid=login-input]").setValue("user1");
        $("[data-testid=password-input]").setValue("password1");
        $("[data-testid=submit-button]").click();
        $("[data-testid=welcome-message]").shouldHave(text("Welcome, user1!"));
    }
    @Test
    void successfulAuthorizationByEnterTest() {
        open("https://qa-guru.github.io/one-page-form/login.html");
        $("[data-testid=login-input]").setValue("user1");
        $("[data-testid=password-input]").setValue("password1").pressEnter();
        $("[data-testid=welcome-message]").shouldHave(text("Welcome, user1!"));
    }
    @Test
    void wrongPasswordAuthorizationTest() {
        open("https://qa-guru.github.io/one-page-form/login.html");
        $("[data-testid=login-input]").setValue("user1");
        $("[data-testid=password-input]").setValue("wrong pass");
        $("[data-testid=submit-button]").click();
        $("[data-testid=error-message]").shouldHave(text("Wrong login or password"));
    }
    @Test
    void wrongLoginAuthorizationTest() {
        open("https://qa-guru.github.io/one-page-form/login.html");
        $("[data-testid=login-input]").setValue("user_err");
        $("[data-testid=password-input]").setValue("password1");
        $("[data-testid=submit-button]").click();
        $("[data-testid=error-message]").shouldHave(text("Wrong login or password"));
    }
    @Test
    void emptyLoginAuthorizationTest() {
        open("https://qa-guru.github.io/one-page-form/login.html");
        $("[data-testid=password-input]").setValue("wrong pass");
        $("[data-testid=submit-button]").click();
        $("[data-testid=error-message]").shouldHave(text("Login is required (minimum 3 characters)"));
    }
    @Test
    void emptyLoginEnterAuthorizationTest() {
        open("https://qa-guru.github.io/one-page-form/login.html");
        $("[data-testid=password-input]").setValue("wrong pass").pressEnter();
        $("[data-testid=error-message]").shouldHave(text("Login is required (minimum 3 characters)"));
    }
    @Test
    void emptyPasswordAuthorizationTest() {
        open("https://qa-guru.github.io/one-page-form/login.html");
        $("[data-testid=login-input]").setValue("user1");
        $("[data-testid=submit-button]").click();
        $("[data-testid=error-message]").shouldHave(text("Password is required (minimum 6 characters)"));
    }
    @Test
    void emptyPasswordAuthorizationByEnterTest() {
        open("https://qa-guru.github.io/one-page-form/login.html");
        $("[data-testid=login-input]").setValue("user1").pressEnter();
        $("[data-testid=error-message]").shouldHave(text("Password is required (minimum 6 characters)"));
    }
    @Test
    void emptyAuthorizationTest() {
        open("https://qa-guru.github.io/one-page-form/login.html");
        $("[data-testid=submit-button]").click();
        $("[data-testid=error-message]").shouldHave(text(
                "Login and password are required (minimum 3 and 6 characters)"));
    }
    @Test
    void shortLoginAuthorizationTest() {
        open("https://qa-guru.github.io/one-page-form/login.html");
        $("[data-testid=login-input]").setValue("us");
        $("[data-testid=password-input]").setValue("password1");
        $("[data-testid=submit-button]").click();
        $("[data-testid=error-message]").shouldHave(text("Login must be at least 3 characters"));
    }
    @Test
    void shortPasswordAuthorizationTest() {
        open("https://qa-guru.github.io/one-page-form/login.html");
        $("[data-testid=login-input]").setValue("user1");
        $("[data-testid=password-input]").setValue("p");
        $("[data-testid=submit-button]").click();
        $("[data-testid=error-message]").shouldHave(text("Password must be at least 6 characters"));
    }
}
