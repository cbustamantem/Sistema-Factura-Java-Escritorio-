/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.testingpy.business.cliente.boundary.db;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Date;
import org.testingpy.business.cliente.entities.Cliente;
import org.testingpy.clases.Utilidades;

/**
 *
 * @author cbustamante
 */
public class ClienteManager {

    private int maxCliente;
    private int contClientes;
    private Cliente misClientes[];
    private static ClienteManager instance;

    private ClienteManager() {
        super();
    }

    public static ClienteManager getInstance() {
        if (instance == null) {
            instance = new ClienteManager();
            instance.init();
        }

        return instance;
    }

    private void init() {
        maxCliente = 100;
        contClientes = 0;
        misClientes = new Cliente[maxCliente];
        instance.cargarClientes();
    }

    public void cargarClientes() {

        try {
            File archivo = new File("Data/clientes.txt");
            try (FileReader fr = new FileReader(archivo); BufferedReader br = new BufferedReader(fr)) {
                int pos;
                String aux;
                String linea;

                String idCliente;
                int tipoIdentificacion;
                String nombres;
                String apellidos;
                String direccion;
                String telefono;
                int idDistrito = 0;
                Date fechaNacimiento;
                Date fechaIngreso;

                while ((linea = br.readLine()) != null) {
                    //extraemos id cliente
                    pos = linea.indexOf('|');
                    aux = linea.substring(0, pos);
                    idCliente = aux;
                    linea = linea.substring(pos + 1);

                    //extraemos tipo identificacion
                    pos = linea.indexOf('|');
                    aux = linea.substring(0, pos);
                    tipoIdentificacion = new Integer(aux);
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

                    //extraemos direccion
                    pos = linea.indexOf('|');
                    aux = linea.substring(0, pos);
                    direccion = aux;
                    linea = linea.substring(pos + 1);

                    //extraemos telefono
                    pos = linea.indexOf('|');
                    aux = linea.substring(0, pos);
                    telefono = aux;
                    linea = linea.substring(pos + 1);

                    //extraemos id distrito
                    pos = linea.indexOf('|');
                    aux = linea.substring(0, pos);
                    idDistrito = new Integer(aux);
                    linea = linea.substring(pos + 1);

                    //extraemos fecha nac. y fecha registro
                    pos = linea.indexOf('|');
                    aux = linea.substring(0, pos);
                    fechaNacimiento = Utilidades.stringToDate(aux);
                    linea = linea.substring(pos + 1);
                    fechaIngreso = Utilidades.stringToDate(linea);

                    Cliente miCliente;
                    miCliente = new Cliente(idCliente, tipoIdentificacion, nombres, apellidos, direccion, telefono,
                            idDistrito, fechaNacimiento, fechaIngreso);
                    misClientes[contClientes] = miCliente;
                    contClientes++;
                }
            } catch (IOException | NumberFormatException e) {
                e.printStackTrace();
            }
        } catch (NumberFormatException e) {
            e.printStackTrace();

        }
    }

    public void grabarClientes() {
        try (FileWriter fw = new FileWriter("Data/clientes.txt");
                PrintWriter pw = new PrintWriter(fw);) {

            for (int i = 0; i < contClientes; i++) {
                pw.println(misClientes[i].toString());
            }

        } catch (Exception ex1) {
            ex1.printStackTrace();
        }
    }

    //clientes
    public Cliente[] getClientes() {
        return misClientes;

    }

    public int numeroClientes() {
        return contClientes;

    }

    public int posicionCliente(String cliente
    ) {
        int aux
                = -1;

        for (int i
                = 0; i
                < contClientes;
                i++) {
            if (misClientes[i].getIdCliente().equals(cliente
            )) {
                return 1;

            }
        }

        return -1;

    }

    public String
            agregarCliente(Cliente miCliente
            ) {
        if (contClientes
                == maxCliente) {
            return "Se ha alcanzado el numbero maximo de clientes";

        }

        misClientes[contClientes] = miCliente;

        contClientes++;

        return "Cliente agregado correctamente";

    }

    public String
            modificarCliente(Cliente miCliente,
                    int pos
            ) {
        misClientes[pos].setTipoIdentificacion(miCliente
                .getTipoIdentificacion());
        misClientes[pos].setNombres(miCliente
                .getNombres());
        misClientes[pos].setApellidos(miCliente
                .getApellidos());
        misClientes[pos].setDireccion(miCliente
                .getDireccion());
        misClientes[pos].setTelefono(miCliente
                .getTelefono());
        misClientes[pos].setIdDistrito(miCliente
                .getIdDistrito());
        misClientes[pos].setFechaNacimiento(miCliente
                .getFechaIngreso());
        misClientes[pos].setFechaIngreso(miCliente
                .getFechaNacimiento());

        return "Cliente modificado correctamente";

    }

    public String
            borrarCliente(int pos
            ) {
        for (int i
                = pos;
                i
                < contClientes
                - 1; i++) {
            misClientes[i] = misClientes[i
                    + 1];

        }
        contClientes--;

        return "Cliente borrado correctamente";
    }

}
