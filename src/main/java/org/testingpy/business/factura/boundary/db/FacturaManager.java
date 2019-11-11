/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.testingpy.business.factura.boundary.db;

import java.io.FileWriter;
import java.io.PrintWriter;

/**
 *
 * @author cbustamante
 */
public class FacturaManager {

    private static FacturaManager instance;

    private FacturaManager() {
        super();
    }

    public static FacturaManager getInstance() {
        if (instance == null) {
            instance = new FacturaManager();
        }
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

    public int grabarFactura(String encabezado, String detalle[]) {
        // adicionamos un consecutivo a la factura
        int numFac = getNumFac() + 1;
        //grabamos la factura        
        try (FileWriter fw = new FileWriter("Data/facturas.txt", true);PrintWriter pw = new PrintWriter(fw);) {
            //encabezado de factura
            pw.println(encabezado);

            //detalle de factura
            for (int i = 0; i < detalle.length; i++) {
                pw.println(detalle[i]);
            }

        } catch (Exception ex1) {
            ex1.printStackTrace();

        }
        return numFac;
    }
}
