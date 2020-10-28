import org.junit.Before
import org.junit.Test
import org.junit.jupiter.api.AfterEach
import java.util.concurrent.TimeUnit

internal class Integrations {

    @Before
    fun setup() {
        System.setProperty("webdriver.chrome.driver", "C:\\Program Files\\chromedriver\\chromedriver.exe")
        driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS)
    }

    @AfterEach
    fun tearDown() {
        Thread.sleep(3000)
        driver.close()
    }

    @Test
    fun tilknytte_fungerer() {
        login()
        tilknytte()
    }

    private fun tilknytte() {
        driver.findElementByXPath("//*[@id=\"oversikt-tilknytning\"]/h5/span[1]").click()
        driver.findElementById("fodselsnummer").clear()
        driver.findElementById("fodselsnummer").sendKeys("01074000092")

        driver.findElementByXPath("//*[@id=\"sok\"]").click()
        driver.findElementById("tilknyttSoker").click()
        driver.findElementById("hentneste").click()
    }

    private fun login() {
        driver.get("http://192.168.11.153:20000")
        driver.findElementById("login-username").sendKeys("admin")
        driver.findElementById("login-password").sendKeys("admin")
        driver.findElementById("login-submit").click()
    }



}