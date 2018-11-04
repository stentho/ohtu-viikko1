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
    public void konstruktorinTilavuusEiNegatiivinen() {
        varasto = new Varasto(-2);

        // varastossa ei saa olla negatiivinen tilavuus
        assertEquals(0.0, varasto.getTilavuus(), vertailuTarkkuus);
    }
    
    @Test
    public void konstruktorinSaldoJaTilavuusEiNegatiivisia() {
        varasto = new Varasto(-2, -2);

        // varastossa ei saa olla negatiivinen tilavuus tai saldo
        assertEquals(0.0, varasto.getTilavuus(), vertailuTarkkuus);
        assertEquals(0.0, varasto.getSaldo(), vertailuTarkkuus);
    }
    
    @Test
    public void konstruktorinSaldoJaTilavuusOikein() {
        varasto = new Varasto(2, 2);

        // varastossa ei saa olla negatiivinen tilavuus tai saldo
        assertEquals(2, varasto.getTilavuus(), vertailuTarkkuus);
        assertEquals(2, varasto.getSaldo(), vertailuTarkkuus);
    }
    
    @Test
    public void konstruktorinSaldoEiYlitaTilavuutta() {
        varasto = new Varasto(2, 4);

        // varastossa ei saa olla saldo suurempi kuin tilavuus
        assertEquals(varasto.getTilavuus(), varasto.getSaldo(), vertailuTarkkuus);
    }
    
    @Test
    public void eiVoiLisataNegatiivistaMaaraa() {
        double x = varasto.getSaldo();
        varasto.lisaaVarastoon(-2);

        // varastossa ei saa olla saldo suurempi kuin tilavuus
        assertEquals(x, varasto.getSaldo(), vertailuTarkkuus);
    }
    
    @Test
    public void lisattavaSaldoEiVoiYlittaaTilavuuden() {
        varasto.lisaaVarastoon(varasto.paljonkoMahtuu() + 1);

        // varastossa ei saa olla saldo suurempi kuin tilavuus
        assertEquals(varasto.getTilavuus(), varasto.getSaldo(), vertailuTarkkuus);
    }
    
    @Test
    public void eiVoiOttaaVirheellistaMaaraa() {
        double x = varasto.getSaldo();
        varasto.otaVarastosta(-1);

        // varastossa saldo ei muutu
        assertEquals(x, varasto.getSaldo(), vertailuTarkkuus);
    }
    
    @Test
    public void otettavaMaaraEiVoiYlittaaTilavuuden() {
        varasto.lisaaVarastoon(5);
        varasto.otaVarastosta(varasto.getSaldo() + 1);

        // varastossa ei voi olla negatiivista saldoa
        assertEquals(0.0, varasto.getSaldo(), vertailuTarkkuus);
    }
    
    @Test
    public void merkkijonoToimiiOikein() {
        String malli = "saldo = " + varasto.getSaldo() + ", vielä tilaa " + varasto.paljonkoMahtuu();
        assertEquals(null, varasto.toString());
    }
}