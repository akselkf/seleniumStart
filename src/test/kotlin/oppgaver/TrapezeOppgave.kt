package oppgaver

import org.junit.After
import org.junit.Before
import org.junit.Test
import org.openqa.selenium.Keys
import org.openqa.selenium.chrome.ChromeDriver
import java.util.concurrent.TimeUnit

val driver = ChromeDriver()
val trapezeLink = "https://sam-int.test.pasientreiser.nhn.no/"

class TrapezeOppgave {
    @Before
    fun setup() {
        System.setProperty("webdriver.chrome.driver", "C:\\Program Files\\chromedriver\\chromedriver.exe")
        driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS)
    }

    @After
    fun tearDown() {
        driver.close()
    }

    @Test
    fun `kan lage en enkel rekvisisjon`() {
        login()
        nyBestilling()
        settRekvirent("birger")
        settRekvirentTilhorighet("oslo")
        settPasient("adrian moe")
        settDato("1212")
        settOppmoteTidspunkt("1700")
        lagre()
    }

    /*
    Denne oppgaven består av å lage de 8 metodene som er nevnt i testen over, i trapeze sin løsning.
    Link dit er: https://sam-int.test.pasientreiser.nhn.no/

    VIKTIG: bruk full xpath
    På dette nettstedet må man bruke FULL xpath på alle objekter, siden ID'er og classnames forandrer seg automatisk.

    Ellers: Ikke glem å sette inn venting der det skjer endring av sider eller lasting pga søking e.l.
    Tips: Bruk metoden vent() som er vedlagt.

    By default venter den i 100 ms, men gir du den ett tall venter den så lenge, f.eks:
    vent(1000) vil føre til venting i 1000 millisekunder = 1 sekund.
    vent() fører til venting i 100 millisekunder.


    1. Lag en metode: login.
        Denne skal ta deg til trapeze sin nettside, fylle ut brukernavn og passord og deretter klikke login.
        Alt ved hjelp av full xpath kopiert fra nettleseren.

    2. Lag en metode: nyBestilling
        Denne skal klikke på administrer bestillinger, og deretter ny bestilling og ende opp på startsiden av
        utfyllingen av en rekvisisjon

    3. Lag en metode: settRekvirent
        Denne skal søke opp og sette en rekvirent for rekvisisjonen. Bør ha en string som input-parameter, f.eks:

        fun settRekvirent(navn: String){
            driver.findElementByXpath("/123/abc/").sendKeys(navn)
        }

        til info: Ved søk i inputfeltene kan det hende man må vente litt før det kommer en dropwdown man kan velge fra

    4. Lag en metode: settRekvirentTilhorighet
        Denne skal søke opp og sette rekvirentTilhørighet for rekvisisjonen. Bør ha en string som input-parameter.

    5. Lag en metode: settPasient
        Denne skal søke opp og knytte en pasient til rekvisisjonen.

    6. Lag en metode: settDato
        Denne skal fylle ut en angitt dato i datofeltet for rekvisisjonen.

    7. Lag en metode: settOppmoteTidspunkt
        Denne skal fylle ut et angitt klokkeslett

    8. Lag en metode: lagre
        Denne skal lagre rekvisisjonen. Obs: Her kommer det en popup.
     */


    fun vent(millis: Long = 100) {
        Thread.sleep(millis)
    }

    fun login() {
        driver.get(trapezeLink)
        vent()
        val brukernavnFelt = "/html/body/div[2]/div[1]/div/div/div/div/div/div/div/div/div[2]/div/div/div/input"
        val passordFelt = "/html/body/div[2]/div[1]/div/div/div/div/div/div/div/div/div[3]/div/div/div/input"
        val loginKnapp = "/html/body/div[2]/div[1]/div/div/div/div/div/div/div/div/a[2]/span/span"

        driver.findElementByXPath(brukernavnFelt).sendKeys("robisven")
        driver.findElementByXPath(passordFelt).sendKeys("Faraday123")
        driver.findElementByXPath(loginKnapp).click()
    }



    private fun nyBestilling() {
        val administrerBestilling = "/html/body/div[2]/div[3]/div/div/div/div/ul/li[1]/div/div/div[2]"
        val nyBestilling = "/html/body/div[2]/div[3]/div/div/div/div/ul/li[1]/ul/li[1]/div/div/div[2]"
        driver.findElementByXPath(administrerBestilling).click()
        driver.findElementByXPath(nyBestilling).click()
    }

    private fun settRekvirent(rekvirent: String) {
        val rekvirentFelt =
            "/html/body/div[3]/div/div[2]/div[2]/div[1]/div/div/div/div[3]/div/div/div[1]/div/div/fieldset[1]/div/div/div/div[1]/div/div/div[1]/div/div/div[1]/input"
        driver.findElementByXPath(rekvirentFelt).sendKeys(rekvirent)
        vent(1000)
        driver.findElementByXPath(rekvirentFelt).sendKeys(Keys.ENTER)
    }

    private fun settRekvirentTilhorighet(sted: String) {
        val rekvirentTilhørighetFelt =
            "/html/body/div[3]/div/div[2]/div[2]/div[1]/div/div/div/div[3]/div/div/div[1]/div/div/fieldset[1]/div/div/div/div[2]/div/div/div[1]/div/div/div[1]/input"
        driver.findElementByXPath(rekvirentTilhørighetFelt).sendKeys(sted)
        vent(1000)
        driver.findElementByXPath(rekvirentTilhørighetFelt).sendKeys(Keys.ENTER)
    }

    private fun settPasient(pasient: String) {
        val pasientFelt =
            "/html/body/div[3]/div/div[2]/div[2]/div[1]/div/div/div/div[3]/div/div/div[1]/div/div/fieldset[2]/div/div/div/div/div/div/div[1]/div/div/div/div/div/div[1]/input"
        driver.findElementByXPath(pasientFelt).sendKeys(pasient)
        vent(1000)
        driver.findElementByXPath(pasientFelt).sendKeys(Keys.ENTER)
    }

    private fun settDato(dato: String) {
        val datoFelt =
            "/html/body/div[3]/div/div[2]/div[2]/div[1]/div/div/div/div[4]/div/div/fieldset[1]/div/div/div/div/div/div/div[1]/div/div/div/div/div/div[1]/input"
        driver.findElementByXPath(datoFelt).sendKeys(dato)
        driver.findElementByXPath(datoFelt).sendKeys(Keys.TAB)
    }

    private fun settOppmoteTidspunkt(tidspunkt: String) {
        val oppmoteTidspunktFelt =
            "/html/body/div[3]/div/div[2]/div[2]/div[1]/div/div/div/div[4]/div/div/fieldset[1]/div/div/div/div/div/div/div[2]/div/div/div[1]/div/div/div/div/div/div/input"
        driver.findElementByXPath(oppmoteTidspunktFelt).sendKeys(tidspunkt)
    }

    private fun lagre() {
        val forsteLagreKnapp =
            "/html/body/div[3]/div/div[2]/div[2]/div[1]/div/div/div/div[1]/div/div/a[1]/span/span/span[2]"
        val andreLagreKnapp = "/html/body/div[14]/div[2]/div[2]/div/div/a[1]/span/span/span[2]"

        driver.findElementByXPath(forsteLagreKnapp).click()
        vent(750)
        driver.findElementByXPath(andreLagreKnapp).click()
    }


}