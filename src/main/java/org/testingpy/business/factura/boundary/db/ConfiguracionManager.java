package org.testingpy.business.factura.boundary.db;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.PrintWriter;

public class ConfiguracionManager {
    
    FacturaManager facturaMgr;
    
    private static ConfiguracionManager instance;
    private ConfiguracionManager(){
     super();
    }
    public static ConfiguracionManager getInstance(){
        if (instance == null)
            instance = new ConfiguracionManager();
        instance.init();
        return instance;
    }
    public void init() {
        // Cargamos coniguracion
        facturaMgr =  FacturaManager.getInstance();
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
         FileReader fr = null;
         BufferedReader br = null;
         
         try {
             archivo = new File("Data/Configuracion.ini");
             fr = new FileReader(archivo);
             br = new BufferedReader(fr);
             
             String linea;
             
             while ( (linea = br.readLine()) != null )   {
                 if (linea.startsWith("FacturaActual")) {
                     facturaMgr.setNumFac(new Integer(linea.substring(14)));
                 }
             }
             
         } catch (Exception e) {
             e.printStackTrace();
             
         } finally   {
             try {
                 if (fr != null)
                     fr.close();
                 
             } catch (Exception e2) {
                 e2.printStackTrace();
             }
         }
     }
     public void grabarConfiguracion() {
        FileWriter fw = null;
        PrintWriter pw = null;
        
        try {
            fw = new FileWriter("Data/Configuracion.ini");
            pw = new PrintWriter(fw);
            
            pw.println("[General]");
            pw.println("FacturaActual=" + facturaMgr.getNumFac());
            
        } catch (Exception ex1) {
            ex1.printStackTrace();
            
        } finally {
            try {
                if (fw != null)
                    fw.close();

            } catch (Exception ex2) {
                ex2.printStackTrace();
            }
        }
        
    }
     
    
}
