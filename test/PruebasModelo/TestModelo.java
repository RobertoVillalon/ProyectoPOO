package PruebasModelo;

import java.sql.Connection;
import java.sql.SQLException;
import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import static org.mockito.Mockito.when;  
import Modelo.Modelo;
import java.sql.Statement;
import org.junit.Before;
import org.mockito.InjectMocks;
import static org.mockito.Matchers.any;
import org.mockito.MockitoAnnotations;

/**
 *
 * @author RobertoVillalon
 */
@RunWith(MockitoJUnitRunner.class)
public class TestModelo{
    
    @InjectMocks
    Modelo modelo;
    
    @Mock
    Connection mockConexion;
    
    @Mock
    Statement mockS;

    
    @Before
    public void setUp() throws SQLException {
        MockitoAnnotations.initMocks(this);
        when(mockConexion.createStatement()).thenReturn(mockS);
        when(mockS.executeUpdate(any())).thenReturn(1);
    }
    /**
     * Prueba de conexion a la base de datos satisfactoria. Prueba hecha el 23-03-2020;
     * @throws java.sql.SQLException
     * @throws java.lang.ClassNotFoundException
     */
    @Test
    public void TestConexion() throws SQLException, ClassNotFoundException{
        int resultado = modelo.PruebaConexion("");
        assertEquals(resultado, 1);
    }
}