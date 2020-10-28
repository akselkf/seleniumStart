import org.junit.Test

internal class Integrations {

    @Test
    fun hello() {
        System.setProperty("webdriver.chrome.driver", "C:\\Program Files\\chromedriver\\chromedriver.exe")
        driver.get("http://vg.no")
        driver.close()
    }
}