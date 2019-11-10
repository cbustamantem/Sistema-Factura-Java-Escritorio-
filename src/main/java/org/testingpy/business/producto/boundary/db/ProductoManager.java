/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.testingpy.business.producto.boundary.db;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.PrintWriter;
import org.testingpy.business.producto.entities.Producto;

/**
 *
 * @author cbustamante
 */
public class ProductoManager {

    private int maxProducto;

    private Producto misProductos[];

    private int contProductos;

    private static ProductoManager instance;

    private ProductoManager() {
        super();
    }

    public static ProductoManager getInstance() {
        if (instance == null) {
            instance = new ProductoManager();
            instance.init();

        }
        return instance;
    }

    private void init() {
        maxProducto = 100;
        misProductos = new Producto[maxProducto];
        contProductos = 0;
        cargarProductos();
    }

    public void grabarProductos() {
        FileWriter fw = null;
        PrintWriter pw = null;

        try {
            fw = new FileWriter("Data/productos.txt");
            pw = new PrintWriter(fw);

            for (int i = 0; i < contProductos; i++) {
                pw.println(misProductos[i].toString());
            }

        } catch (Exception ex1) {
            ex1.printStackTrace();

        } finally {
            try {
                if (fw != null) {
                    fw.close();
                }

            } catch (Exception ex2) {
                ex2.printStackTrace();
            }
        }
    }

    public void cargarProductos() {
        File archivo = null;
        FileReader fr = null;
        BufferedReader br = null;

        try {
            archivo = new File("Data/productos.txt");
            fr = new FileReader(archivo);
            br = new BufferedReader(fr);

            int pos;
            String aux;
            String linea;

            String idProducto;
            String descripcion;
            int precio;
            int IGV;
            String nota;

            while ((linea = br.readLine()) != null) {
                //extraemos id producto
                pos = linea.indexOf('|');
                aux = linea.substring(0, pos);
                idProducto = aux;
                linea = linea.substring(pos + 1);

                //extraemos descripcion
                pos = linea.indexOf('|');
                aux = linea.substring(0, pos);
                descripcion = aux;
                linea = linea.substring(pos + 1);

                //extraemos precio
                pos = linea.indexOf('|');
                aux = linea.substring(0, pos);
                precio = new Integer(aux);
                linea = linea.substring(pos + 1);

                //extraemos igv y nota
                pos = linea.indexOf('|');
                aux = linea.substring(0, pos);
                IGV = new Integer(aux);
                linea = linea.substring(pos + 1);
                nota = linea;

                Producto miproProducto;
                miproProducto = new Producto(idProducto, descripcion, precio, IGV, nota);
                misProductos[contProductos] = miproProducto;
                contProductos++;

            }

        } catch (Exception e) {
            e.printStackTrace();

        } finally {
            try {
                if (fr != null) {
                    fr.close();
                }

            } catch (Exception e2) {
                e2.printStackTrace();
            }
        }
    }

    //productos
    public Producto[] getProductos() {
        return misProductos;
    }

    public int numeroProductos() {
        return contProductos;
    }

    public int posicionProducto(String producto) {
        for (int i = 0; i < contProductos; i++) {
            if (misProductos[i].getIdProducto().equals(producto)) {
                return 1;
            }
        }

        return -1;
    }

    public String agregarProducto(Producto miProducto) {
        if (contProductos == maxProducto) {
            return "Se ha alcanzado el numbero maximo de usuarios";
        }

        misProductos[contProductos] = miProducto;
        contProductos++;
        return "Producto agregado correctamente";
    }

    public String modificarProducto(Producto miProducto, int pos) {
        misProductos[pos].setDescripcion(miProducto.getDescripcion());
        misProductos[pos].setPrecio(miProducto.getPrecio());
        misProductos[pos].setIGV(miProducto.getIGV());
        misProductos[pos].setNota(miProducto.getNota());
        return "Producto modificado correctamente";
    }

    public String borrarProducto(int pos) {
        for (int i = pos; i < contProductos - 1; i++) {
            misProductos[i] = misProductos[i + 1];
        }
        contProductos--;
        return "Producto borrado correctamente";
    }

}
