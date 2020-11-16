import org.junit.After
import org.junit.Before
import org.junit.Test
import org.openqa.selenium.Keys
import org.openqa.selenium.WebElement
import org.openqa.selenium.chrome.ChromeDriver
import java.util.concurrent.TimeUnit
val driver = ChromeDriver()
val proLink = "https://49c77e8211f7.ngrok.io/"
class NettleserTest {
    @Before
    fun setup() {
        System.setProperty("webdriver.chrome.driver", "C:\\Program Files\\chromedriver\\chromedriver.exe")
        driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS)
    }
    @Test
    fun tilForsiden() {
        driver.get(proLink)
        vent()
    }
    @Test
    fun `kan tilknytte`() {
        login()
        tilTilknytte()
        sokOppOgTilknytt()
        hentNeste()
    }
    fun login() {
        driver.get(proLink)
        driver.findElementByXPath("//*[@id=\"login-username\"]").sendKeys("manuell_folkereg_adr")
        driver.findElementByXPath("//*[@id=\"login-password\"]").sendKeys("manuell_folkereg_adr")
        driver.findElementByXPath("//*[@id=\"login-submit\"]").click()
    }
    fun tilTilknytte(){
        driver.findElementByXPath("//*[@id=\"oversikt-tilknytning\"]/h5/span[1]").click()
    }
    //Eli 01099199845, Ida 01074000092
    fun sokOppOgTilknytt(){
        driver.findElementByXPath("//*[@id=\"fodselsnummer\"]").click()
        driver.findElementByXPath("//*[@id=\"fodselsnummer\"]").clear()
        driver.findElementByXPath("//*[@id=\"fodselsnummer\"]").sendKeys("01099199845")
        driver.findElementByXPath("//*[@id=\"sok\"]").click()
        vent(200)
        if (driver.findElementByXPath("//*[@id=\"tilknyttSoker\"]").text == "Tilknytt søker") {
            driver.findElementByXPath("//*[@id=\"tilknyttSoker\"]").click()
        }
        vent()
    }
    fun hentNeste(){
        vent()
        driver.findElementByXPath("//*[@id=\"hentNeste\"]").click()
        vent(750)
    }
    fun vent(millis: Long = 75) {
        Thread.sleep(millis)
    }
    @Test
    fun `kan tilknytte mange saker på rad`() {
        login()
        tilTilknytte()
        repeat(10){
            sokOppOgTilknytt()
            hentNeste()
        }
    }
    @Test
    fun `Kan markere flere bilag i mange saker på rad`() {
        login()
        tilMerkeBilag()
        repeat(10){
            markerBilag(1,"0211", "opp")
            markerBilag(2,"0211","bil")
            markerBilag(3,"0211","park", "140")
            vent()
            hentNeste()
        }
    }
    fun tilMerkeBilag() {
        driver.get(proLink)
        login ()
        driver.findElementByXPath("//*[@id=\"oversikt-kategorisering\"]/h5/span[1]").click()
        vent(750)
    }
    fun markerBilag(nr: Int, dato: String, type: String, belop: String = "") {
        val fullXpathStart = "/html/body/div[4]/div/merkebilag/div[1]/div[2]/div[$nr]/div"
        val bilagstype = driver.findElementByXPath("$fullXpathStart/div/div/div/input")
        val datofelt = driver.findElementByXPath("$fullXpathStart/input[1]")
        val belopfelt = driver.findElementByXPath("$fullXpathStart/input[2]")
        clearSendReturnTab(datofelt, dato)
        clearSendReturnTab(bilagstype, type)
        clearSendReturnTab(belopfelt, belop)
    }
    fun clearSendReturnTab(field: WebElement, keysToSend: String) {
        vent()
        field.clear()
        field.sendKeys(keysToSend)
        field.sendKeys(Keys.RETURN)
        field.sendKeys(Keys.TAB)
    }
    @Test
    fun loginPRO() {
        logiN()
        tilknytning()
        merkeBilag()
        registrering()
    }
    @Test
    fun `kan tilknytte 10 saker på rad`() {
        login()
        repeat(15) {
            tilknytning()
        }
    }
    fun logiN() {
        driver.get(proLink) // <-- Endret denne
        driver.findElementById("login-username").sendKeys("admin")
        driver.findElementById("login-password").sendKeys("admin")
        driver.findElementById("login-submit").click()
    }
    fun tilknytning() {
        Thread.sleep(200)
        driver.get(proLink)
        Thread.sleep(200)
        driver.findElementByXPath("//*[@id=\"oversikt-tilknytning\"]/h5/span[1]").click()
        clearSendReturnTab(driver.findElementByXPath("//*[@id=\"fodselsnummer\"]"), "01074000092")
        driver.findElementById("sok").click()
        Thread.sleep(125)
        if (driver.findElementByXPath("//*[@id=\"tilknyttSoker\"]").text == "Tilknytt søker")
            driver.findElementByXPath("//*[@id=\"tilknyttSoker\"]").click()
        driver.findElementById("hentNeste").click() // <-- Lagt til denne
    }
    fun merkeBilag() {
        driver.get(proLink) // <-- Lagt til denne
        driver.findElementByXPath("//*[@id=\"oversikt-kategorisering\"]/h5/span[1]").click() // <-- Endret denne
        Thread.sleep(125)
        cleaSendReturnTab(driver.findElementByXPath("//*[@id=\"dato\"]"), "1011")
        cleaSendReturnTab(
            driver.findElementByXPath("//*[@id=\"merke-bilag\"]/merkebilag/div[1]/div[2]/div[1]/div/div/div/div/input"),
            "1011"
        )
        driver.findElementByXPath("//*[@id=\"belop\"]").sendKeys("150")
        driver.findElementById("hentNeste").click() // <-- Lagt til denne
    }
    fun cleaSendReturnTab (inputFelt: WebElement, tekst: String) {
        inputFelt.clear()
        inputFelt.sendKeys(tekst)
        inputFelt.sendKeys(Keys.RETURN)
        inputFelt.sendKeys(Keys.TAB)
    }
    fun registrering() {
        driver.get(proLink) // <-- Lagt til denne
        driver.findElementByXPath("//*[@id=\"oversikt-registrering\"]/h5/span[1]").click() // <-- Endret denne
        driver.findElementByXPath("//*[@id=\"behandlingssted\"]/div[1]/div/input").click()
        driver.findElementByXPath("//*[@id=\"behandlingssted\"]/div[1]/div/input").sendKeys("rikshosp")
        driver.findElementByXPath("//*[@id=\"behandlingssted\"]/div[1]/div/div/ul/li").click()
        driver.findElementByXPath("//*[@id=\"tillatOppmoteOppslag\"]").click()
        driver.findElementByXPath("//*[@id=\"nyttKravBtn\"]").click()
        driver.findElementByXPath("//*[@id=\"krav-collapse0\"]/div/div/div/div[1]/div[2]/div/div/div/input").click()
        driver.findElementByXPath("//*[@id=\"krav-collapse0\"]/div/div/div/div[1]/div[2]/div/div/div/input")
            .sendKeys("106")
        driver.findElementByXPath("//*[@id=\"krav-collapse0\"]/div/div/div/div[1]/div[2]/div/div/div/input")
            .sendKeys(Keys.RETURN)
        Thread.sleep(125)
        driver.findElementByXPath("//*[@id=\"buttonLagReturReise\"]").click()
        driver.findElementByXPath("//*[@id=\"person-column\"]/div[1]/div[5]/div[2]/div/p[1]/i").click()
        Thread.sleep(125)
        driver.findElementById("kontonummer").sendKeys("12345678903")
        driver.findElementById("buttonFerdigRegistrert").click()
    }
    @After
    fun tearDown() {
        driver.close()
    }
}