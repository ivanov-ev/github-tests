package github;

import com.codeborne.selenide.Configuration;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.Test;

import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selectors.*;
import static com.codeborne.selenide.Selenide.*;

// ======================================
//TESTS FOR https://github.com/enterprise
// ======================================

public class GithubEnterpriseTests {

    @BeforeAll
    static void setup() {
        //pay attention to the trailing slash, because it concatenates with open() and may result in 2 slashes
        Configuration.baseUrl = "https://github.com";
        Configuration.pageLoadStrategy= "eager";
        //Configuration.browser = "Chrome";
        Configuration.browserSize = "1920x1200";
        Configuration.timeout = 5000; //5 sec; default is 4 sec

    }

    @AfterEach
    void tearDown() {
        closeWebDriver();
    }

    @Test
    void navigateToGithubEnterpriseTest() {
        open("");
        $(".header-menu-wrapper").$(byText("Solutions")).hover();
        $(".header-menu-wrapper").$(byText("Enterprise")).click();

        $(withTagAndText("title", "The AI Powered Developer Platform")).should(exist);
    }
}
