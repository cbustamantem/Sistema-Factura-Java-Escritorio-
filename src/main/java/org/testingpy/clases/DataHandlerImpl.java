/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.testingpy.clases;

import java.io.BufferedReader;
import java.io.Closeable;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author cbustamante
 */
public class DataHandlerImpl implements Closeable, DataHandler {

    private String fileName;
    private String content;
    private File file;
    private FileWriter fw;
    private PrintWriter pw;

    public DataHandlerImpl(String fileName) {
        this.fileName = fileName;
    }

    public List<String> readFileContent() {
        List<String> content = new ArrayList();
        File archivo = null;

        try {
            archivo = new File(fileName);
            try (FileReader fr = new FileReader(archivo); BufferedReader br = new BufferedReader(fr);) {
                String linea = "";
                while ((linea = br.readLine()) != null) {
                    content.add(linea);
                }
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return content;
    }

    @Override
    public void close() throws IOException {
//        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Boolean saveContentToFile(String content) {
        try (FileWriter fw = new FileWriter(fileName);
                PrintWriter pw = new PrintWriter(fw);) {
            pw.println(content);
            return true;
        } catch (Exception ex1) {
            ex1.printStackTrace();
        }
        return false;
    }

    @Override
    public Boolean saveContentToFile(String[] content) {
        try (FileWriter fw = new FileWriter(fileName);
                PrintWriter pw = new PrintWriter(fw);) {
            for(String line : content)
                pw.println(line);
            return true;
        } catch (Exception ex1) {
            ex1.printStackTrace();
        }
        return false;
        
    }

}
