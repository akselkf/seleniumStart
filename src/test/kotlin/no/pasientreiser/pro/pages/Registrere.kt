package no.pasientreiser.pro.pages

import org.openqa.selenium.chrome.ChromeDriver

class Registrere(driver: ChromeDriver) : Digitaliserer(driver) {

    var kravnr = 1

    fun settBehandlingsdato(dato: String) {
        markAndSend(driver.findElementByXPath("//*[@id=\"behandlingsdato\"]"), dato)
    }

    fun settBehandlingssted(behandlingssted: String) {
        markAndSend(driver.findElementByXPath("//*[@id=\"behandlingssted\"]/div[1]/div/input"), behandlingssted)
        vent(100)
        driver.findElementByXPath("//*[@id=\"behandlingssted\"]/div[1]/div/input").click()
        driver.findElementByXPath("//*[@id=\"behandlingssted\"]/div[1]/div/div/ul/li").click()
    }

    fun slettReiser() {
        if (driver.findElementsByXPath("//*[@id=\"registrerReiseOversikt\"]/div[1]/a/span[1]").size > 0) {
            driver.findElementByXPath("//*[@id=\"registrerReiseOversikt\"]/div[1]/a/span[1]").click()
            driver.findElementById("bekreftKnapp").click()
            driver.findElementById("buttonLagNyBehandling").click()
        }
    }


    fun nyttKravMedKostnad(krav: String, belop: String, bilagsnummer: String = "1") {
        driver.findElementById("nyttKravBtn").click()
        clearSendReturnTab(
            driver.findElementByXPath("//*[@id=\"krav-collapse0\"]/div/div/div/div[1]/div[2]/div/div/div/input"),
            krav
        )
        clearSendReturnTab(driver.findElementByXPath("//*[@id=\"kostnaderTabell\"]/div/div[2]/div/div/div/input"), krav)
        clearSendReturnTab(driver.findElementById("kostnadBelop"), belop)
        sendReturnTab(driver.findElementByXPath("//*[@id=\"bilagTabell\"]/div/div[2]/select"), bilagsnummer)
    }

    fun nyttKrav(krav: String, bilagsnummer: String = "1", nr: Int = kravnr++) {
        driver.findElementById("nyttKravBtn").click()

        val kravXpath = "/html/body/div[4]/div/div/div[1]/div[2]/div/div/div[3]/div/div/form/div/registrer-krav"
        val kravnavnElement = driver.findElementByXPath("$kravXpath[$nr]/div[2]/div/div/div/div[1]/div[2]/div/div/div/input")
        clearSendReturnTab(kravnavnElement, krav)

        val bilagsnummerPath = if(nr == 1) "$kravXpath/div[2]/div/div/div/div[5]/div/div[2]/select" else "$kravXpath[$nr]/div[2]/div/div/div/div[5]/div/div[2]/select"
        val bilagsnummerElement = driver.findElementByXPath(bilagsnummerPath)
        sendReturnTab(bilagsnummerElement, bilagsnummer)

    }

    fun ferdigRegistrert() {
        driver.findElementById("buttonFerdigRegistrert").click()
        vent()
        if (driver.findElementsById("bekreftKnapp").size > 0) {
            driver.findElementById("bekreftKnapp").click()
        }
    }

    fun nyttRefusjonskrav(kostnad: String, belop: String, bilagsnummer: String = "1", nr: Int = kravnr++) {
        driver.findElementById("nyttKravBtn").click()
        vent()
        val kravXpath = "/html/body/div[4]/div/div/div[1]/div[2]/div/div/div[3]/div/div/form/div/registrer-krav"

        val kravNavnElement = driver.findElementByXPath("$kravXpath[$nr]/div[2]/div/div/div/div[1]/div[2]/div/div/div/input")
        clearSendReturnTab(kravNavnElement, "ige ut")

        val kostnadNavnElement = driver.findElementByXPath("$kravXpath[$nr]/div[2]/div/div/div/div[4]/div/div[2]/div/div/div/input")
        clearSendReturnTab(kostnadNavnElement, kostnad)

        val belopElement = driver.findElementByXPath("$kravXpath[$nr]/div[2]/div/div/div/div[4]/div/div[3]/input")
        clearSendReturnTab(belopElement, belop)


        val bilagsnummerPath = if(nr == 1) "$kravXpath/div[2]/div/div/div/div[5]/div/div[2]/select" else "$kravXpath[$nr]/div[2]/div/div/div/div[5]/div/div[2]/select"
        val bilagsnummerElement = driver.findElementByXPath(bilagsnummerPath)
        sendReturnTab(bilagsnummerElement, bilagsnummer)


    }

    fun settTransportmiddel(transporttype: String) {
        sendReturnTab(driver.findElementByXPath("//*[@id=\"collapseetappe0\"]/div/div[1]/div/div[2]/div/div[1]/label[1]/select"), transporttype)
    }

    fun settSamtykke() {
        driver.findElementById("tillatOppmoteOppslag").click()
    }

    fun retur() {
        driver.findElementById("buttonLagReturReise").click()
    }

}
