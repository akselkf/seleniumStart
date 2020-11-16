package no.pasientreiser.pro.pages

import org.openqa.selenium.chrome.ChromeDriver


class MerkeBilag(driver: ChromeDriver) : Digitaliserer(driver) {

    fun markerBilag(nr: Int, dato: String, type: String, belop: String = "") {
        val fullXpathStart = "/html/body/div[4]/div/merkebilag/div[1]/div[2]/div[$nr]/div"
        val bilagstype = driver.findElementByXPath("$fullXpathStart/div/div/div/input")
        val datofelt = driver.findElementByXPath("$fullXpathStart/input[1]")
        val belopfelt = driver.findElementByXPath("$fullXpathStart/input[2]")

        clearSendReturnTab(datofelt, dato)
        clearSendReturnTab(bilagstype, type)
        clearSendReturnTab(belopfelt, belop)
    }



    fun tilRegistreringFraMerkebilag() {
        driver.findElementById("buttonSendTilRegistrering").click()
    }

}


