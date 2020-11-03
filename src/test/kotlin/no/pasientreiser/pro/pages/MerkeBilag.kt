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


    private fun settTypeOgDato(nr: String, dato: String, type: String) {
        val bilagsfelt: (String) -> String =
            { it -> "/html/body/div[4]/div/merkebilag/div[1]/div[2]/div[$it]/div/div/div/div/input" }
        val datofelt: (String) -> String =
            { it -> "/html/body/div[4]/div/merkebilag/div[1]/div[2]/div[$it]/div/input[1]" }

        clearSendReturnTab(driver.findElementByXPath(bilagsfelt(nr)), type)
        clearSendReturnTab(driver.findElementByXPath(datofelt(nr)), dato)

    }

    fun tilRegistreringFraMerkebilag() {
        driver.findElementById("buttonSendTilRegistrering").click()
    }


    fun markerBilag(bilagsmarkeringer: List<Bilag>) {
        bilagsmarkeringer.forEach { settTypeOgDato(it.bilagsnr, it.dato, it.type) }
    }

    data class Bilag(val bilagsnr: String, val dato: String, val type: String)


}


