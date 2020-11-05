package oppgaver

import no.pasientreiser.pro.pages.MerkeBilag
import no.pasientreiser.pro.pages.Registrere
import no.pasientreiser.pro.pages.vent
import no.pasientreiser.pro.tester.TestBase
import no.pasientreiser.pro.tester.proLink
import org.junit.Assert
import org.junit.Before
import org.junit.Test
import org.openqa.selenium.Keys
import org.openqa.selenium.WebElement
import org.openqa.selenium.chrome.ChromeDriver
import java.util.concurrent.TimeUnit

class OppgaverLosning : TestBase() {

    /**
     *  Oppgave 1
     *  Lag et program som laster opp saker til PRO. link: http://localhost:20700/development/upload/
     */

    @Test
    fun `kan simulere scanning til PRO løsningsforslag`() {
        vent()
        driver.get("http://localhost:20700/development/upload/")
        repeat(20) { driver.findElementById("luckyButton").click() }
        driver.findElementById("tvingPostmann").click()
    }


    /**
     * Oppgave 2
     * Lag en test som tilknytter en sak i PRO
     */
    @Test
    fun `kan tilknytte og hente neste sak løsningsforslag`() {
        driver.get(proLink)
        driver.findElementById("oversikt-tilknytning").click()
        driver.findElementById("fodselsnummer").clear()
        driver.findElementById("fodselsnummer").sendKeys("01074000092")
        driver.findElementByXPath("//*[@id=\"sok\"]").click()

        if (driver.findElementByXPath("//*[@id=\"tilknyttSoker\"]").text == "Tilknytt søker") {
            driver.findElementByXPath("//*[@id=\"tilknyttSoker\"]").click()
        }

        driver.findElementById("hentNeste").click()
    }


    /**
     * Oppgave 3
     *  Lag en test som markerer bilag på en sak med bruk av page-objectet MerkeBilag
     */
    @Test
    fun `Kan markere bilag med dato, type og beløp løsningsforslag`() {
        driver.get(proLink)
        driver.findElementById("oversikt-kategorisering").click()

        val nr = 1
        val fullXpathStart = "/html/body/div[4]/div/merkebilag/div[1]/div[2]/div[$nr]/div"
        val bilagstype = driver.findElementByXPath("$fullXpathStart/div/div/div/input")
        val datofelt = driver.findElementByXPath("$fullXpathStart/input[1]")
        val belopfelt = driver.findElementByXPath("$fullXpathStart/input[2]")

        clearSendReturnTab(datofelt, "0101")
        clearSendReturnTab(bilagstype, "opp")
        clearSendReturnTab(belopfelt, "140")
    }



    fun clearSendReturnTab(field: WebElement, keysToSend: String) {
        field.clear()
        field.sendKeys(keysToSend)
        field.sendKeys(Keys.RETURN)
        field.sendKeys(Keys.TAB)
    }


    /**
     * Oppgave 3.1
     *  Lag en test som markerer bilag på en sak med bruk av page-objectet MerkeBilag
     */
    @Test
    fun `Kan markere bilag med dato, type og beløp med pageobject løsningsforslag`() {
        val digitaliserer = MerkeBilag(driver)
        digitaliserer.tilMerkeBilag()
        digitaliserer.markerBilag(1, "0101", "opp")
        digitaliserer.markerBilag(2, "0101", "bil")
        digitaliserer.markerBilag(3, "0101", "park", "88")

        hentNesteOgAssert(digitaliserer)
    }


    /**
     * Oppgave 4
     *  Lag en test som registrerer en sak ved bruk av page-objectet Registrere
     */

    @Test
    fun `Kan registrere sak med krav løsningsforslag`() {
        val digitaliserer = Registrere(driver)
        digitaliserer.tilRegistrering()
        digitaliserer.slettReiser()

        digitaliserer.settBehandlingsdato("1505")
        digitaliserer.settBehandlingssted("ahus")

        digitaliserer.nyttKravMedKostnad("pass", "500")
        digitaliserer.nyttKrav("med")

        val saksnummer = digitaliserer.saksnummer
        digitaliserer.ferdigRegistrert()
        digitaliserer.tilRegistrering()
        Assert.assertNotEquals(saksnummer, digitaliserer.saksnummer)
    }
}