import org.junit.Test

internal class Integrations {

    @Test
    fun login_pro() {
        System.setProperty("webdriver.chrome.driver", "C:\\Program Files\\chromedriver\\chromedriver.exe")
        driver.get("http://192.168.11.153:20000")
        driver.findElementById("login-username").sendKeys("admin")
        driver.findElementById("login-password").sendKeys("admin")
        driver.findElementById("login-submit").click()

        Thread.sleep(3000)
        driver.close()
    }
}