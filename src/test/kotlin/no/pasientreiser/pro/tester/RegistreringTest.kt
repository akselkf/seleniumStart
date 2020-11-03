package no.pasientreiser.pro.tester

import no.pasientreiser.pro.pages.Registrere
import org.junit.Assert
import org.junit.Test

class RegistreringTest : TestBase() {

    @Test
    fun `kan ferdigregistrere en sak` (){
        val digitaliserer = Registrere(driver)
        digitaliserer.tilRegistrering()
        digitaliserer.slettReiser()


        digitaliserer.settBehandlingsdato("1505")
        digitaliserer.settBehandlingssted("ahus")

        digitaliserer.nyttKravMedKostnad("pass", "500")
        digitaliserer.nyttKrav("med")

        val saksnummer = digitaliserer.saksnummer
        digitaliserer.ferdigRegistrert()
        digitaliserer.tilRegistrering()
        Assert.assertNotEquals(saksnummer, digitaliserer.saksnummer)

        Thread.sleep(2000)
    }




    /**
     * - verifiser at man er under registrering av en reise, ikke i oversikten av reiser
     * - sett behandlingsdato og sted
     * - Sett samtykke
     * - Registrer krav
     * -
     */

}