package no.pasientreiser.pro.tester

import no.pasientreiser.pro.pages.Tilknytte
import no.pasientreiser.pro.testdata.testpasienter
import org.junit.Test

class TilknytteTest : TestBase() {
    val digitaliserer = Tilknytte(driver)

    @Test
    fun `kan tilknytte pasient og hente neste`() {

        digitaliserer.tilTilknytning()

        digitaliserer.sokOppFodselsnummer(testpasienter.random())
        digitaliserer.tilknytt()

        hentNesteOgAssert(digitaliserer)
    }

    @Test
    fun `Kan tilknytte mange på rad`() {
        digitaliserer.tilTilknytning()
        repeat(10) { digitaliserer.tilknyttOgHentNeste(testpasienter.random()) }
    }


    /**
     * Oppgave
     * Lag en test som tilknytter en sak ved hjelp av fornavn, etternavn, fodselsdato, postnr og poststed.
     */


}