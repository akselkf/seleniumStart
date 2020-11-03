package no.pasientreiser.pro.tester

import org.junit.Test
import no.pasientreiser.pro.pages.MerkeBilag
import org.junit.Assert

class MerkeBilagTest: TestBase() {

    val digitaliserer = MerkeBilag(driver)


    @Test
    fun `Kan markere bilag og hente neste sak`() {
        digitaliserer.tilMerkeBilag()
        digitaliserer.markerBilag(1, "0101", "opp")
        digitaliserer.markerBilag(2, "0101", "bil")
        digitaliserer.markerBilag(3, "0101", "park", "88")

        hentNesteOgAssert(digitaliserer)

    }

    @Test
    fun `fra merke bilag til registrering` (){
        digitaliserer.tilMerkeBilag()
        digitaliserer.markerBilag(1, "0101", "opp")
        digitaliserer.markerBilag(2, "0101", "bil")
        digitaliserer.markerBilag(3, "0101", "park", "88")

        digitaliserer.tilRegistreringFraMerkebilag()
        Assert.assertEquals(1, driver.findElementsById("lesbartBehandlingssted").size)
    }


    /**
     * bonusoppgave: Lag tester med bruk av (+)-ikonet (flere linjer på samme bilag).
     *
     * Tips: fullXpath må nok brukes for å finne riktige linjer
     */

}