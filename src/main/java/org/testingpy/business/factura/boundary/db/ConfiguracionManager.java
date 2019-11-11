package org.testingpy.business.factura.boundary.db;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.PrintWriter;

public class ConfiguracionManager {

    FacturaManager facturaMgr;

    private static ConfiguracionManager instance;

    private ConfiguracionManager() {
        super();
    }

    public static ConfiguracionManager getInstance() {
        if (instance == null) {
            instance = new ConfiguracionManager();
            instance.init();
        }
        return instance;
    }

    public void init() {
        // Cargamos coniguracion
        facturaMgr = FacturaManager.getInstance();
        cargarConfiguracion();
    }

    public void grabarTodo() {
//        grabarUsuarios();
//        grabarProductos();
        grabarConfiguracion();
    }

    //configuracion
    public void cargarConfiguracion() {
        File archivo = null;
        try {
            archivo = new File("Data/Configuracion.ini");
            try (FileReader fr = new FileReader(archivo); BufferedReader br = new BufferedReader(fr);) {

                String linea;

                while ((linea = br.readLine()) != null) {
                    if (linea.startsWith("FacturaActual")) {
                        facturaMgr.setNumFac(new Integer(linea.substring(14)));
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void grabarConfiguracion() {
         
        try(FileWriter fw = new FileWriter("Data/Configuracion.ini");
                PrintWriter pw = new PrintWriter(fw);) {
            pw.println("[General]");
            pw.println("FacturaActual=" + facturaMgr.getNumFac());
        } catch (Exception ex1) {
            ex1.printStackTrace();

        } 

    }

}
