package github;

import com.codeborne.selenide.Configuration;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.Test;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.*;

// =============================================
//TESTS FOR https://github.com/selenide/selenide
// =============================================

public class SelenidePageTests {

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
    void findCodeSnippetInWikiTest() {
        open("/selenide/selenide");
        $("#wiki-tab").click();
        $("#wiki-pages-box").$("#wiki-pages-filter").setValue("Sof");
        $("#wiki-pages-box").$(byText("SoftAssertions")).click();

        $("#wiki-body").shouldHave(text("""
                       @ExtendWith({SoftAssertsExtension.class})
                        class Tests {
                            @Test
                            void test() {
                                Configuration.assertionMode = SOFT;
                                open("page.html");

                                $("#first").should(visible).click();
                                $("#second").should(visible).click();
                            }
                        }
                """));
    }

}
