package PruebasModelo;

import Modelo.ImpresionCertificados;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author RobertoVillalon
 */
public class TestImpresionCertificado {
    
    public TestImpresionCertificado() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
    }
    
    @After
    public void tearDown() {
    }

    /**
     * Prueba Correcta Efectuada el 02 abril del 2020 a las 23:53
     */
    @Test
    public void TestObtenerFecha(){
        assertTrue(ImpresionCertificados.ObtenerFecha().equals("02 abril 2020 23:53"));
    }
    
}
