import org.openqa.selenium.Keys
import org.openqa.selenium.WebElement

/*
Denne filen inneholder oppgaver og noen metoder som jeg har lagd på forhånd.
Dere trenger ikke å forstå metodene jeg har lagd på forhånd, bare hvordan dere kan bruke de.

Hver oppgave avslutter med en test som jeg øsnker dere skal lage og få til å kjøre ved å lage metodene som nevnes.

Jeg foreslår at dere lager løsningen til oppgavene i den filen dere har gående nå hvor testene kjører.
Da må dere copy-paste koden jeg har lagt til i bunn av denne fila over i deres fil. Den koden kan dere fint ha i bunn av deres fil også :)


Til slutt:
En metode lages med ordet fun. Et eksempel på en metode som heter tilForsiden og som tar deg til nettstedet www.pro.no er:

fun tilForsiden(){
    driver.get("www.pro.no")
}
*/



/**
 * Oppgave 1:
 * 1. Lag en metode som logger deg inn i PRO og stanser på forsiden. Kall den login
 * 2. Lag en metode som trykker på køen til tilknytte. Kall den tilTilknytte
 * 3. Lag en metode som søker opp fødselsnummer og tilknytter til saken, ved mindre noen allerede er tilknyttet.
 *    Kall den sokOppOgTilknytt
 * 4. Lag en metode for å hente neste sak. Kall den hentNeste
 *
 *  Når alle overnevnte metoder er lagd bør du kunne lage en test som ser slik ut:
 * */
/*
@Test
fun `Kan tilknytte` (){
    login()
    tilTilknytte()
    sokOppOgTilknytt("01074000092")
    hentNeste()
}
*/



/**
 * Oppgave 2:
 * Lag en test som tilknytter personer til 10 ulike saker
 *
 * Testen bør se noe slik ut:
 */

/*
@Test
fun `kan tilknytte mange saker på rad` (){
    login()
    tilTilknytte()
    repeat(10) {
        sokOppOgTilknytt("01074000092")
        hentNeste()
    }
}
*/


/**
 * Oppgave 3:
 * 1. Lag en metode som trykker på køen til merke bilag. Kall den tilMerkeBilag
 * 2. Bruk metoden som ligger i bunnen av denne fila: markerBilag til å markere dato, type
 * og eventuelt beløp på 3 ulike bilag. Du trenger ikke å skjønne hvordan den metoden fungerer,
 * bare hvordan man bruker den :)
 * 3. Bruk hentNeste fra oppgave 1 til å hente neste sak med nye, umarkerte bilag.
 * 4. Putt steg 2 og 3 inn i en repeat som markerer 3 bilag i 10 forskjellige saker på rad.
 *
 * Testen bør til slutt se noe slik ut:
 */

/*
@Test
fun `kan markere flere bilag i mange saker på rad` (){
    login()
    tilMerkeBilag()
    repeat(10){
        markerBilag(1, "0211", "opp")
        markerBilag(2, "0211", "bil")
        markerBilag(3, "0211", "park", "140")
        hentNeste()
    }
}
*/


/**
 * Oppgave 4:
 * 1. Lag en metode som trykker på køen til registrere. Kall den tilRegistrere
 * 2. Lag en metode som sjekker om saken inneholder reiser fra før og sletter de dersom den gjør det. Kall den slettReiser
 * 3. lag en metode for å sette behandlingsdato i en reise. Kall den settBehandlingsdato.
 * 4. lag en metode for å velge behandlingssted. Kall den settBehandlingssted.
 * 5. Lag en metode for å sjekke om saken inneholder krav, og om den gjør det - slett de. Kall den slettAlleKrav()
 * 5. Bruk metoden som ligger i bunn av denne fila: nyttKravMedBilag til å legge til kravene oppmøtebekreftelse og medisinsk grunnlag.
 * 7. Lag en metode som klikker på ferdig registrert knappen, sjekker om det kommer validering
 *      og om det gjør det: klikk "ignorer og fortsett". Kall den: ferdigRegistrert()
 *
 * etter oppgavene over er fullført bør du kunne lage en test som ser slik ut:
 */

/*
@Test
fun `kan registrere sak med flere krav` (){
    tilRegistrere()
    slettReiser()
    settBehandlingsdato("1505")
    settBehandlingssted("Ahus")
    slettAlleKrav()
    nyttKravMedBilag(1, "oppm")
    nyttKravMedBilag(2, "medi")
    ferdigRegistrert()
}
*/


/**
 * Metode for å fylle ut bilagsdato, type og beløp basert på nummeret du gir som første input.
 * Eksempel:
 *      markerBilag(1, "0105", "oppmote")
 *      markerBilag(2, "0105", "parkering", "140")
 *
 * Vil markere bilag nr. 1 og 2 i saken med dato 01.05 og hhv. typene: oppmøte og parkering, med 140 kr i parkeringsbeløp.
 */
fun markerBilag(nr: Int, dato: String, type: String, belop: String = "") {
    val fullXpathStart = "/html/body/div[4]/div/merkebilag/div[1]/div[2]/div[$nr]/div"
    val bilagstype = driver.findElementByXPath("$fullXpathStart/div/div/div/input")
    val datofelt = driver.findElementByXPath("$fullXpathStart/input[1]")
    val belopfelt = driver.findElementByXPath("$fullXpathStart/input[2]")

    clearSendReturnTab(datofelt, dato)
    clearSendReturnTab(bilagstype, type)
    clearSendReturnTab(belopfelt, belop)
}

fun clearSendReturnTab(field: WebElement, keysToSend: String) {
    vent()
    field.clear()
    field.sendKeys(keysToSend)
    field.sendKeys(Keys.RETURN)
    field.sendKeys(Keys.TAB)
}

fun sendReturnTab(field: WebElement, keysToSend: String) {
    field.sendKeys(keysToSend)
    field.sendKeys(Keys.RETURN)
    field.sendKeys(Keys.TAB)
}

/**
 * Metode for å legge til krav med kun bilag, og ingen kostnad eller begrunnelse. OBS: Må ha med kravnr!
 * Om det allerede eksisterer et krav på nummeret du forsøker å bruke, vil denne feile.
 * Første kall på denne metoden i en sak må ha kravnr. 1. Etterfølgende kall må ha 2, 3 osv.
 *
 * eks:
 *  nyttKrav(1, "oppm")
 *  nyttKrav(2, "medi")
 *  nyttKrav(3, "ledsager")
 *
 */

fun nyttKravMedBilag(nr: Int, krav: String, bilagsnummer: String = "1") {
    driver.findElementById("nyttKravBtn").click()

    val kravXpath = "/html/body/div[4]/div/div/div[1]/div[2]/div/div/div[3]/div/div/form/div/registrer-krav"
    val kravnavnElement = driver.findElementByXPath("$kravXpath[$nr]/div[2]/div/div/div/div[1]/div[2]/div/div/div/input")
    clearSendReturnTab(kravnavnElement, krav)

    val bilagsnummerPath = if (nr == 1) "$kravXpath/div[2]/div/div/div/div[5]/div/div[2]/select" else "$kravXpath[$nr]/div[2]/div/div/div/div[5]/div/div[2]/select"
    val bilagsnummerElement = driver.findElementByXPath(bilagsnummerPath)

    sendReturnTab(bilagsnummerElement, bilagsnummer)
}

/**
 * Metode for å gjøre kall på Thread.sleep enklere å skrive, og justere i plenum.
 */
fun vent(millis: Long = 75) {
    Thread.sleep(millis)
}
