import org.junit.After
import org.junit.Before
import org.junit.Test
import org.openqa.selenium.Keys
import org.openqa.selenium.chrome.ChromeDriver
import java.util.concurrent.TimeUnit

val driver = ChromeDriver()
val proLink = "https://7c4d6b406ce1.ngrok.io"

class NettleserTest {
    @Before
    fun setup() {
        //System.setProperty("webdriver.chrome.driver", "C:\\Program Files\\chromedriver\\chromedriver.exe")
        driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS)
    }

    @Test
    fun LoginPRO() {
        Login()
        Tilknytning()
        MerkeBilag()
        Registrering()
    }

    fun Login() {
        driver.get(proLink)
        driver.findElementById("login-username").sendKeys("manuell_folkereg_adr")
        driver.findElementById("login-password").sendKeys("manuell_folkereg_adr")
        driver.findElementById("login-submit").click()
    }

    fun Tilknytning() {
        driver.findElementByXPath("//*[@id=\"oversikt-tilknytning\"]/h5/span[1]").click()
        driver.findElementByXPath("//*[@id=\"fodselsnummer\"]").click()
        driver.findElementByXPath("//*[@id=\"fodselsnummer\"]").clear()
        driver.findElementByXPath("//*[@id=\"fodselsnummer\"]").sendKeys("01086919644")
        driver.findElementById("sok").click()
        Thread.sleep(125)
        if (driver.findElementByXPath("//*[@id=\"tilknyttSoker\"]").text == "Tilknytt søker")
            driver.findElementByXPath("//*[@id=\"tilknyttSoker\"]").click()
        driver.findElementById("tilknyttSoker").click()
        Thread.sleep(125)
    }

    fun MerkeBilag() {
        driver.findElementByXPath("//*[@id=\"tilMerkBilag\"]").click()
        driver.findElementByXPath("//*[@id=\"dato\"]").clear()
        driver.findElementByXPath("//*[@id=\"dato\"]").sendKeys("01.10.2020")
        driver.findElementByXPath("//*[@id=\"merke-bilag\"]/merkebilag/div[1]/div[2]/div[1]/div/div/div/div/input")
            .clear()
        driver.findElementByXPath("//*[@id=\"merke-bilag\"]/merkebilag/div[1]/div[2]/div[1]/div/div/div/div/input")
            .sendKeys("oppmøtebekreftelse")
        driver.findElementByXPath("//*[@id=\"merke-bilag\"]/merkebilag/div[1]/div[2]/div[1]/div/div/div/div/input")
            .sendKeys(Keys.RETURN)
        driver.findElementByXPath("//*[@id=\"merke-bilag\"]/merkebilag/div[1]/div[2]/div[1]/div/div/div/div/input")
            .sendKeys(Keys.TAB)
        driver.findElementByXPath("//*[@id=\"belop\"]").sendKeys("150")
    }

    fun Registrering() {
        driver.findElementByXPath("//*[@id=\"buttonSendTilRegistrering\"]").click()
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
        Thread.sleep(75)
        driver.findElementByXPath("//*[@id=\"buttonLagReturReise\"]").click()
        driver.findElementByXPath("//*[@id=\"person-column\"]/div[1]/div[5]/div[2]/div/p[1]/i").click()
        Thread.sleep(75)
        driver.findElementById("kontonummer").sendKeys("12345678903")
        driver.findElementById("buttonFerdigRegistrert").click()
    }

    @After
    fun tearDown() {
        driver.close()
    }
}