package no.pasientreiser.pro.tester

import no.pasientreiser.pro.pages.Registrere
import no.pasientreiser.pro.pages.vent
import org.junit.Assert
import org.junit.Test

class RegistreringTest : TestBase() {
    private val digitaliserer = Registrere(driver)
    @Test
    fun `kan ferdigregistrere en sak` (){
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

    @Test
    fun `Kan registrere sak med medisinsk behov og bom` (){
        digitaliserer.tilRegistrering()
        digitaliserer.slettReiser()

        digitaliserer.settBehandlingsdato("1505")
        digitaliserer.settBehandlingssted("ahus")

        digitaliserer.nyttRefusjonskrav("bom", "500")
        digitaliserer.nyttKrav("oppm")
        vent()
        digitaliserer.nyttKrav("medi")

        val saksnummer = digitaliserer.saksnummer
        digitaliserer.ferdigRegistrert()
        digitaliserer.tilRegistrering()
        vent()
        Assert.assertNotEquals(saksnummer, digitaliserer.saksnummer)
    }

    @Test
    fun `Kan registrere sak med rutegående båt som krav og transportmiddel` (){
        vent()
        digitaliserer.tilRegistrering()
        digitaliserer.slettReiser()

        digitaliserer.settBehandlingsdato("1505")
        digitaliserer.settBehandlingssted("ahus")
        vent()
        digitaliserer.settSamtykke()
        digitaliserer.settTransportmiddel("pass")

        digitaliserer.nyttKravMedKostnad("pass", "500")
        digitaliserer.retur()
        digitaliserer.ferdigRegistrert()
        Thread.sleep(5000)

    }




    /**
     * - verifiser at man er under registrering av en reise, ikke i oversikten av reiser
     * - sett behandlingsdato og sted
     * - Sett samtykke
     * - Registrer krav
     * -
     */

}