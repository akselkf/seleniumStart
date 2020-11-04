package no.pasientreiser.pro.tester

import no.pasientreiser.pro.pages.Digitaliserer
import org.junit.After
import org.junit.Assert
import org.junit.Before
import org.openqa.selenium.chrome.ChromeDriver
import java.util.concurrent.TimeUnit

const val proLink = "localhost:20000"

abstract class TestBase {

    val driver = ChromeDriver()

    @Before
    fun setup() {
        //System.setProperty("webdriver.chrome.driver", "C:\\Program Files\\chromedriver\\chromedriver.exe")
        driver.manage().timeouts().implicitlyWait(1, TimeUnit.SECONDS)
        login(driver)
    }

    @After
    fun teardown() {
        driver.close()
    }

    fun login(driver: ChromeDriver) {
        home(driver)
        driver.findElementById("login-username").sendKeys("admin")
        driver.findElementById("login-password").sendKeys("admin")
        driver.findElementById("login-submit").click()
    }

    /**
     * Henter nester sak fra stegene Tilknytt eller Merke bilag og forsikrer at saksnummeret forandrer seg mellom sakene.
     */
    fun hentNesteOgAssert(digitaliserer: Digitaliserer) {
        val forsteSak = digitaliserer.saksnummer
        digitaliserer.hentNeste()
        val andreSak = digitaliserer.saksnummer

        Assert.assertNotEquals(forsteSak, andreSak)
    }

    fun home(driver: ChromeDriver) {
        driver.get(proLink)
    }
}