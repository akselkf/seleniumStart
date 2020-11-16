package no.pasientreiser.pro.tester.setup

import no.pasientreiser.pro.pages.MerkeBilag
import no.pasientreiser.pro.pages.PostMottak
import no.pasientreiser.pro.pages.Tilknytte
import no.pasientreiser.pro.testdata.testpasienter
import no.pasientreiser.pro.tester.TestBase
import org.junit.Test

class ScannTilknyttMarker : TestBase() {

@Test
fun `scann mange saker` (){
    //PostMottak(driver).scann(100)
    tilknyttTilfeldig(10)
}

    @Test
    fun `scannTilknyttMarker`() {
        PostMottak(driver).scann(40)
        tilknyttTilfeldig(10)

        val merkeBilag = MerkeBilag(driver)
        merkeBilag.tilMerkeBilag()
        repeat(5) {
            merkeBilag.markerBilag(1, "0110","opp")
            merkeBilag.markerBilag(2, "0110","bil")
            merkeBilag.markerBilag(3, "0110","park", "140")
            merkeBilag.hentNeste()
        }
    }

    private fun tilknyttTilfeldig(antall: Int) {
        val tilknytte = Tilknytte(driver)
        tilknytte.tilTilknytning()
        repeat(antall) {
            tilknytte.tilknyttOgHentNeste(testpasienter.random())
        }
    }
}