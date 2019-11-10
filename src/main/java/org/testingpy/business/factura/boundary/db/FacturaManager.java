/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.testingpy.business.factura.boundary.db;

/**
 *
 * @author cbustamante
 */
public class FacturaManager {
    
    private static FacturaManager instance;
    
    private FacturaManager(){
        super();
    }
    public static FacturaManager getInstance(){
        if (instance == null)
            instance = new FacturaManager();
        return instance;
    }

    private int numFactura = 0;

    //factura
    public int getNumFac() {
        return numFactura;
    }

    public void setNumFac(int numFac) {
        this.numFactura = numFac;
    }
}
