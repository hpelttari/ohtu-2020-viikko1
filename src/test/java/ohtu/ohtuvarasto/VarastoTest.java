package ohtu.ohtuvarasto;

import org.junit.*;
import static org.junit.Assert.*;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

public class VarastoTest {

    Varasto varasto;
    double vertailuTarkkuus = 0.0001;

    @Before
    public void setUp() {
        varasto = new Varasto(10);
    }

    @Test
    public void konstruktoriLuoTyhjanVaraston() {
        assertEquals(0, varasto.getSaldo(), vertailuTarkkuus);
    }

    @Test
    public void uudellaVarastollaOikeaTilavuus() {
        assertEquals(10, varasto.getTilavuus(), vertailuTarkkuus);
    }

    @Test
    public void lisaysLisaaSaldoa() {
        varasto.lisaaVarastoon(8);

        // saldon pitäisi olla sama kun lisätty määrä
        assertEquals(8, varasto.getSaldo(), vertailuTarkkuus);
    }

    @Test
    public void lisaysLisaaPienentaaVapaataTilaa() {
        varasto.lisaaVarastoon(8);

        // vapaata tilaa pitäisi vielä olla tilavuus-lisättävä määrä eli 2
        assertEquals(2, varasto.paljonkoMahtuu(), vertailuTarkkuus);
    }

    @Test
    public void ottaminenPalauttaaOikeanMaaran() {
        varasto.lisaaVarastoon(8);

        double saatuMaara = varasto.otaVarastosta(2);

        assertEquals(2, saatuMaara, vertailuTarkkuus);
    }

    @Test
    public void ottaminenLisääTilaa() {
        varasto.lisaaVarastoon(8);

        varasto.otaVarastosta(2);

        // varastossa pitäisi olla tilaa 10 - 8 + 2 eli 4
        assertEquals(4, varasto.paljonkoMahtuu(), vertailuTarkkuus);
    }

    @Test
    public void negatiivinenLisääminenEiMuutaSaldoa(){
        varasto.lisaaVarastoon(-1);

        assertEquals(10, varasto.paljonkoMahtuu(), vertailuTarkkuus);
    }

    @Test
    public void liikaaOttaminenNollaaSaldon() {
        varasto.lisaaVarastoon(8);

        varasto.otaVarastosta(20);

        assertEquals(0, varasto.getSaldo(), vertailuTarkkuus);
    }

    @Test
    public void liikaaOttaminenPalauttaaKaiken() {
        varasto.lisaaVarastoon(8);

        assertEquals(8, varasto.otaVarastosta(20), vertailuTarkkuus);
    }

    @Test
    public void negatiivinenOttaminenPalauttaaNolla() {
        varasto.lisaaVarastoon(8);

        assertEquals(0, varasto.otaVarastosta(-20), vertailuTarkkuus);
    }

    @Test
    public void toStringToimiiOikein() {
        varasto.lisaaVarastoon(8);

        assertEquals("saldo = 8.0, vielä tilaa 2.0", varasto.toString());
    }

    @Test
    public void liikaaLisääminenTäyttääVaraston(){
        varasto.lisaaVarastoon(100);

        assertEquals(0.0, varasto.paljonkoMahtuu(), vertailuTarkkuus);
    }

    @Test
    public void varastonLuominenNegatiivisellaTilavuudellaLuoKäyttökelvottoman(){
        varasto = new Varasto(-10);

        assertEquals(0.0, varasto.getTilavuus(), vertailuTarkkuus);
    }

    @Test
    public void varstonLuominenAlkusaldollaLuoOikeanTilavuuden(){
        varasto = new Varasto(10, 2);

        assertEquals(10.0, varasto.getTilavuus(), vertailuTarkkuus);
    }

    @Test
    public void varastonLuominenAlkusaldollaJaNegatiivisellaTilavuudellaLuoKäyttökelvottoman(){
        varasto = new Varasto(-10, 2);

        assertEquals(0.0, varasto.getTilavuus(), vertailuTarkkuus);
    }

    @Test
    public void varastonLuominenNegatiivisellaAlkusaldollaLuoTyhjänVaraston(){
        varasto = new Varasto(10, -2);

        assertEquals(0.0, varasto.getSaldo(), vertailuTarkkuus);
    }

    @Test
    public void varastonLuominenTilavuuttaSuuremmallaAlkusaldollaLuoTäydenVaraston(){
        varasto = new Varasto(10, 20);

        assertEquals(0.0, varasto.paljonkoMahtuu(), vertailuTarkkuus);
    }
}