package no.pasientreiser.pro.pages

import org.openqa.selenium.chrome.ChromeDriver

class PostMottak(driver: ChromeDriver) : Digitaliserer(driver) {
    fun scann(times: Int = 1) {
        vent()
        driver.get("http://localhost:20700/development/upload/")
        repeat(times) { driver.findElementById("luckyButton").click() }
        driver.findElementById("tvingPostmann").click()
    }

}
