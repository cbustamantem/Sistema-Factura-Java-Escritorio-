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
import java.util.List;
import org.testingpy.business.usuario.entities.Usuario;
import org.testingpy.clases.DataHandler;
import org.testingpy.clases.DataHandlerImpl;

/**
 *
 * @author cbustamante
 */
public class UsuarioManager {

    private int maxUsuario;
    private int contUsuarios;

    private Usuario misUsuarios[];

    private static UsuarioManager instance;

    private DataHandler dataMgr;

    private UsuarioManager() {
        super();
    }

    public UsuarioManager(DataHandler dataMgr) {
        super();
        this.dataMgr = dataMgr;
        init();
    }

    public static UsuarioManager getInstance() {
        if (instance == null) {
            instance = new UsuarioManager();
            instance.init();

        }
        return instance;
    }

    private void init() {
        maxUsuario = 50;
        contUsuarios = 0;
        misUsuarios = new Usuario[maxUsuario];
        if (dataMgr == null) {
            dataMgr = new DataHandlerImpl("Data/usuario.txt");
        }

        cargarUsuarios();
    }

    private String[] convertirCadenaUsuarios() {
        String usuarios[] = new String[contUsuarios];
        for (int i = 0; i < contUsuarios; i++) {
            usuarios[i] = misUsuarios[i].toString();
        }
        return usuarios;
    }

    public void grabarUsuarios() {
        dataMgr.saveContentToFile(convertirCadenaUsuarios());

    }

    public void cargarUsuarios() {

        try {
            List<String> contenidoArchivo = dataMgr.readFileContent();
            
            for (String linea : contenidoArchivo) {
                String campos[] = linea.split("\\|");
                String idUsuario = campos[0];
                String nombres= campos[1];
                String apellidos= campos[2];
                String clave =campos[3];
                int perfil = Integer.parseInt(campos[4]);
                Usuario miUsuario;
                miUsuario = new Usuario(idUsuario, nombres, apellidos, clave, perfil);
                misUsuarios[contUsuarios] = miUsuario;
                contUsuarios++;
            }

        } catch (NumberFormatException e) {
            e.printStackTrace();
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
