import org.junit.Assert
import org.junit.Test
import org.junit.jupiter.api.AfterEach
import org.junit.jupiter.api.BeforeEach
import java.util.concurrent.TimeUnit

internal class Integrations {

    @BeforeEach
    fun setup() {
        System.setProperty("webdriver.chrome.driver", "C:\\Program Files\\chromedriver\\chromedriver.exe")
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS)
    }

    @AfterEach
    fun teardown() {
        driver.close()
    }

    @Test
    fun login_pro() {
        driver.get("http://192.168.11.153:20000")
        driver.findElementById("login-username").sendKeys("admin")
        driver.findElementById("login-password").sendKeys("admin")
        driver.findElementById("login-submit").click()
    }
}