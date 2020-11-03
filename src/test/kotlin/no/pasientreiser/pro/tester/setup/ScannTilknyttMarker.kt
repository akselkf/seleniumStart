package no.pasientreiser.pro.tester.setup

import no.pasientreiser.pro.pages.MerkeBilag
import no.pasientreiser.pro.pages.PostMottak
import no.pasientreiser.pro.pages.Tilknytte
import no.pasientreiser.pro.tester.TestBase
import org.junit.Test

class ScannTilknyttMarker : TestBase() {


    @Test
    fun `scannTilknyttMarker`() {
        PostMottak(driver).scann(40)
        Thread.sleep(3000)

        val tilknytte = Tilknytte(driver)
        tilknytte.tilTilknytning()
        repeat(10){
            tilknytte.tilknyttOgHentNeste("01074000092")

        }

        val merkeBilag = MerkeBilag(driver)
        merkeBilag.tilMerkeBilag()
        repeat(5) {
            merkeBilag.markerBilag(1, "0110","opp")
            merkeBilag.markerBilag(2, "0110","bil")
            merkeBilag.markerBilag(3, "0110","park", "140")
            merkeBilag.hentNeste()
        }
    }
}