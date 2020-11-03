package no.pasientreiser.pro.pages

import no.pasientreiser.pro.tester.proLink
import org.openqa.selenium.Keys
import org.openqa.selenium.WebElement
import org.openqa.selenium.chrome.ChromeDriver
import org.openqa.selenium.support.ui.Select

open class Digitaliserer(val driver: ChromeDriver) {

    val saksnummer: String get() = driver.findElementById("person-info-saksnummer").text

    fun vent(millis: Long = 75) {
        Thread.sleep(millis)

    }

    fun tilTilknytning() {
        driver.get(proLink)
        vent()
        driver.findElementById("oversikt-tilknytning").click()
    }

    fun tilMerkeBilag() {
        driver.get(proLink)
        driver.findElementById("oversikt-kategorisering").click()
    }

    fun tilRegistrering() {
        driver.get(proLink)
        driver.findElementById("oversikt-registrering").click()
    }

    fun hentNeste() {
        Thread.sleep(50)
        driver.findElementById("hentNeste").click()
        Thread.sleep(100)
    }

    fun clearSendReturnTab(field: WebElement, keysToSend: String) {
        field.clear()
        field.sendKeys(keysToSend)
        field.sendKeys(Keys.RETURN)
        field.sendKeys(Keys.TAB)
    }

    fun clearSendTab(field: WebElement, keysToSend: String) {
        field.clear()
        field.sendKeys(keysToSend)
        field.sendKeys(Keys.TAB)
    }

    fun sendReturnTab(field: WebElement, keysToSend: String) {
        field.sendKeys(keysToSend)
        field.sendKeys(Keys.RETURN)
        field.sendKeys(Keys.TAB)
    }

    fun markAndSend(field: WebElement, keysToSend: String) {
        field.sendKeys(Keys.chord(Keys.CONTROL, "a"))
        field.sendKeys(keysToSend)
    }

    open fun setDropdownValue(dropdownElement: WebElement, newText: String) {
        val dropdown = Select(dropdownElement)
        dropdown.selectByVisibleText(newText)
    }



}



