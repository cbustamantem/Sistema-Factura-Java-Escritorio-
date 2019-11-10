/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.testingpy.business.usuario.boundary.db;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.PrintWriter;
import org.testingpy.business.producto.boundary.db.ProductoManager;
import org.testingpy.business.usuario.entities.Usuario;

/**
 *
 * @author cbustamante
 */
public class UsuarioManager {

    private int maxUsuario ;
    private int contUsuarios;

    private Usuario misUsuarios[];

    private static UsuarioManager instance;

    private UsuarioManager() {
        super();
    }

    public static UsuarioManager getInstance() {
        if (instance == null) {
            instance = new UsuarioManager();
        }
        instance.init();
        return instance;
    }

    private void init() {
        maxUsuario = 50;
        contUsuarios = 0;
        misUsuarios = new Usuario[maxUsuario];
        cargarUsuarios();
    }

    public void grabarUsuarios() {
        FileWriter fw = null;
        PrintWriter pw = null;

        try {
            fw = new FileWriter("Data/usuario.txt");
            pw = new PrintWriter(fw);

            for (int i = 0; i < contUsuarios; i++) {
                pw.println(misUsuarios[i].toString());
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

    public void cargarUsuarios() {
        File archivo = null;
        FileReader fr = null;
        BufferedReader br = null;

        try {
            archivo = new File("Data/usuario.txt");
            fr = new FileReader(archivo);
            br = new BufferedReader(fr);

            int pos;
            String aux;
            String linea;

            String idUsuario;
            String nombres;
            String apellidos;
            String clave;
            int perfil;

            while ((linea = br.readLine()) != null) {
                //extraemos id usuario
                pos = linea.indexOf('|');
                aux = linea.substring(0, pos);
                idUsuario = aux;
                linea = linea.substring(pos + 1);

                //extraemos nombres
                pos = linea.indexOf('|');
                aux = linea.substring(0, pos);
                nombres = aux;
                linea = linea.substring(pos + 1);

                //extraemos apellidos
                pos = linea.indexOf('|');
                aux = linea.substring(0, pos);
                apellidos = aux;
                linea = linea.substring(pos + 1);

                //extraemos clave y perfil
                pos = linea.indexOf('|');
                aux = linea.substring(0, pos);
                clave = aux;
                linea = linea.substring(pos + 1);
                perfil = new Integer(linea);

                Usuario miUsuario;
                miUsuario = new Usuario(idUsuario, nombres, apellidos, clave, perfil);
                misUsuarios[contUsuarios] = miUsuario;
                contUsuarios++;

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

    //usuarios
    public String agregarUsuario(Usuario miUsuario) {
        if (contUsuarios == maxUsuario) {
            return "Se ha alcanzado el numbero maximo de usuarios";
        }

        misUsuarios[contUsuarios] = miUsuario;
        contUsuarios++;
        return "Usuario agregado";
    }

    public String modificarUsuario(Usuario miUsuario, int pos) {
        misUsuarios[pos].setNombres(miUsuario.getNombres());
        misUsuarios[pos].setApellidos(miUsuario.getApellidos());
        misUsuarios[pos].setClave(miUsuario.getClave());
        misUsuarios[pos].setPerfil(miUsuario.getPerfil());
        return "Usuario modificado correctamente";
    }

    public String borrarUsuario(int pos) {
        for (int i = pos; i < contUsuarios - 1; i++) {
            misUsuarios[i] = misUsuarios[i + 1];
        }
        contUsuarios--;
        return "Usuario borrado correctamente";
    }

    public boolean validarUsuario(String usuario, String clave) {
        boolean aux = false;

        for (int i = 0; i < contUsuarios; i++) {
            if (misUsuarios[i].getIdUsuario().equals(usuario)
                    && misUsuarios[i].getClave().equals(clave)) {
                return true;
            }
        }

        return false;
    }

    public int getPerfil(String usuario) {
        for (int i = 0; i < contUsuarios; i++) {
            if (misUsuarios[i].getIdUsuario().equals(usuario)) {
                return misUsuarios[i].getPerfil();
            }
        }
        return -1;
    }

    public int posicionUsuario(String usuario) {
        int aux = -1;
        for (int i = 0; i < contUsuarios; i++) {
            if (misUsuarios[i].getIdUsuario().equals(usuario)) {
                return 1;
            }
        }

        return -1;
    }

    public int numeroUsuarios() {
        return contUsuarios;
    }

    public Usuario[] getUsuarios() {
        return misUsuarios;
    }

    //cambio clave
    public void cambioClave(String usuario, String clave) {
        for (int i = 0; i < contUsuarios; i++) {
            if (misUsuarios[i].getIdUsuario().equals(usuario)) {
                misUsuarios[i].setClave(clave);
                return;
            }
        }
    }
}
