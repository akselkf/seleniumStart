package no.pasientreiser.pro.pages

import org.openqa.selenium.chrome.ChromeDriver

class Tilknytte(driver: ChromeDriver): Digitaliserer(driver) {


    fun tilknyttOgHentNeste(soker: String) {
        sokOppFodselsnummer(soker)
        tilknytt()
        hentNeste()
    }

    fun sokOppFodselsnummer(soker: String) {
        driver.findElementById("fodselsnummer").clear()
        driver.findElementById("fodselsnummer").sendKeys(soker)
        driver.findElementByXPath("//*[@id=\"sok\"]").click()
    }

    fun tilknytt() {
        if (driver.findElementByXPath("//*[@id=\"tilknyttSoker\"]").text == "Tilknytt søker") {
            driver.findElementByXPath("//*[@id=\"tilknyttSoker\"]").click()
        }
    }

}


