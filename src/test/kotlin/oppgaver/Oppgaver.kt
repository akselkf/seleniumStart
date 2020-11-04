package oppgaver

import no.pasientreiser.pro.tester.TestBase
import no.pasientreiser.pro.tester.proLink
import org.junit.Before
import org.junit.Test
import org.openqa.selenium.chrome.ChromeDriver
import java.util.concurrent.TimeUnit

val driver = ChromeDriver()

internal class Oppgaver {

    @Before
    fun setup() {
        System.setProperty("webdriver.chrome.driver", "C:\\Program Files\\chromedriver\\chromedriver.exe")
        driver.manage().timeouts().implicitlyWait(1, TimeUnit.SECONDS)
    }

    fun login(driver: ChromeDriver) {
        driver.get(proLink)
        driver.findElementById("login-username").sendKeys("admin")
        driver.findElementById("login-password").sendKeys("admin")
        driver.findElementById("login-submit").click()
    }


    /**
     *  Oppgave 1
     *  Lag et program som laster opp saker til PRO. link: http://localhost:20700/development/upload/
     */

    @Test
    fun `kan simulere scanning til PRO`() {

    }



    /**
     * Oppgave 2
     * Lag en test som tilknytter en sak, henter neste og verifiserer at saken er ny.
     */
    @Test
    fun `kan tilknytte og hente neste sak`() {

    }

    /**
     *  Oppgave 2.1
     *  Lag et page-object for tilknytte-siden som inneholder alle driver.getElement-metodene.
     *  Lag deretter en test som kun bruker page-objectet under testen.
     */


    /**
     * Oppgave 3
     *  Lag en test som markerer bilag på en sak
     *
     */
    @Test
    fun `Kan markere bilag med dato, type og beløp`() {
        driver.get(proLink)
        login(driver)
        driver.findElementByXPath("//*[@id=\"oversikt-tilknytning\"]/h5/span[1]").click()
        Thread.sleep(2000)
    }


    /**
     * Oppgave 4
     *  Lag en test som registrerer en sak
     */

    @Test
    fun `Kan registrere sak med krav`() {

    }
}