/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.testingpy.business.usuario.boundary.db;

import java.util.ArrayList;
import java.util.List;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import org.testingpy.business.usuario.entities.Usuario;

/**
 *
 * @author cbustamante
 */
public class UsuarioManagerTest {
    
    public UsuarioManagerTest() {
    }
    UsuarioManager instance;
    DataHandlerMock dataMock;
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
        dataMock = new DataHandlerMock();
        List<String> content= new ArrayList();
        content.add("test1|test1Name|test1LastName|test1Passwd|1");
        content.add("test2|test2Name|test2LastName|test2Passwd|2");
        dataMock.setContent(content);
        instance = new UsuarioManager(dataMock);
    }
    
    @After
    public void tearDown() {
    }


    /**
     * Test of cambioClave method, of class UsuarioManager.
     */
    @Test
    public void testCargarUsuarios() {
        System.out.println("testCargarUsuarios");
        assertTrue(instance.getUsuarios() != null);
        for (Usuario usuario: instance.getUsuarios()){
            System.out.println("User Name:" + usuario.getNombres() + " - " + usuario.getApellidos());
        }
    }
    
}
